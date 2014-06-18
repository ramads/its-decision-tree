package ta.stc_decisionTree.controller;

import java.util.LinkedList;
import ta.stc_decisionTree.data.RuleNilai;
import ta.stc_decisionTree.model.DecisionTree;
import ta.stc_decisionTree.model.KategoriSoal;
import ta.stc_decisionTree.model.Materi;
import ta.stc_decisionTree.model.MateriKlasifikasi;
import ta.stc_decisionTree.model.TesSoal;

/**
 * Kelas Klasifikasi berfungsi untuk melakukan prediksi menggunakan 
 * decision tree yang telah dibentuk
 * @author ami
 */
public class Klasifikasi {
    //untuk menymipan daftar decision tree
    LinkedList<DecisionTree> listTree;
    
    //untuk menyimpan daftar soal dan kategori dari soal2 tersebut
    LinkedList<KategoriSoal> listKategoriSoal;
    
    //konstraktor dengan inputan daftar tree dan daftar soal
    public Klasifikasi(LinkedList<DecisionTree> listTree, LinkedList<KategoriSoal> listKategoriSoal){
        this.listTree = listTree;
        this.listKategoriSoal = listKategoriSoal;
    }
    
    public void setListTree(LinkedList<DecisionTree> listTree){
        this.listTree = listTree;
    }
    
    public void setKategoriSoal(LinkedList<KategoriSoal> listKategoriSoal){
        this.listKategoriSoal = listKategoriSoal;
    }
    
    /**
     * Fungsi ini menentukan hasil klasifikasi materi2 berdasarkan 
     * hasil jawaban dari user yang dipetakan dalam beberapa kemampuan yaitu
     * Sangat Baik, Baik, Sedang, Kurang, Sangat Kurang
     * @return Daftar hasil klasifikasi materi-materi
     */
    public LinkedList<MateriKlasifikasi> ambilHasilKlasifikasi(){
        LinkedList<MateriKlasifikasi> dMateriKlasifikasi = new LinkedList<>();
        
        //untuk masing2 tree hasil pelatihan
        for(DecisionTree tree: listTree){
            // mengambil header dari tabel tree dan dijadikan attribut2 yg akan
            //menjadi inputan prediksi dengan decision tree
            String[] allAtribut = tree.tabelTree.getHeaderTabel();
            
            //inputan prediksi decision tree
            String[] inputKlasifikasi = new String[allAtribut.length];
            
            //target prediksi
            String target = tree.tabelTree.getTarget();
            
            //memetakan hasil jawaban pretest user ke dalam beberapa kemampuan
            //yang telah ditentukan 
            //(Sangat Baik, Baik, Sedang, Kurang atau sangat kurang)
            for(int i=0;i<allAtribut.length;i++){
                //jika atribut adalah target, set null
                if(allAtribut[i].equals(target)){
                    inputKlasifikasi[i]=null;
                }else{
                    inputKlasifikasi[i] = RuleNilai.ubahNilaiKeString(
                            getHasilMateri(
                                    allAtribut[i]
                            )
                    );
                }
            }
            
            //memasukkan hasil prediksi masing2 tree ke dalam list
            dMateriKlasifikasi.add(
                new MateriKlasifikasi(
                    new Materi(0, target),
                    RuleNilai.ubahStringKeNilai(
                            tree.predictTargetAttributeValue(
                                    inputKlasifikasi
                            )
                    )
                )
            );
        }
        return dMateriKlasifikasi;
    }
    
    /**
     * Fungsi ini menghitung jumlah soal yang berkaitan dengan sebuah materi
     * kemudian menentukan hasil tes dari user
     * @param nMateri nama materi
     * @return 5=sangat baik, 4=baik, 3=sedang, 2=kurang, 1=sangat kurang
     */
    private int getHasilMateri(String nMateri){
        int jmlSoal = 0;
        int jmlBenar = 0;
        int hasil = 0;
        for(KategoriSoal kSoal: listKategoriSoal){
            LinkedList<Materi> liMateri = kSoal.getKategori();
            for(Materi materi: liMateri){
                if(nMateri.equals(materi.getNamaMateri())){
                    jmlSoal++;
                    TesSoal tSoal = kSoal.getTesSoal();
                    if(tSoal.isBenar()) jmlBenar++;
                }
            }
        }
        double range;
        double rangePoint;
        
        //menentukan pembagian kemampuan user berdasarkan jumlah variasi yg dipilih
        if(RuleNilai.getVariasi()==RuleNilai.VARIASI_PERTAMA){
            range = 2.0;
            rangePoint = jmlSoal/range;
            if(jmlBenar >= rangePoint) hasil = RuleNilai.BAIK;
            else hasil = RuleNilai.KURANG;
        }else if(RuleNilai.getVariasi()==RuleNilai.VARIASI_KEDUA){
            range = 3.0;
            rangePoint = jmlSoal/range;
            if(jmlBenar >= 2*rangePoint) hasil = RuleNilai.BAIK;
            else if(jmlBenar >= rangePoint) hasil = RuleNilai.SEDANG;
            else hasil = RuleNilai.KURANG;
        }else if(RuleNilai.getVariasi()==RuleNilai.VARIASI_KETIGA){
            range = 5.0;
            rangePoint = jmlSoal/range;
            if(jmlBenar >= 4*rangePoint) hasil = RuleNilai.SANGAT_BAIK;
            else if(jmlBenar >= 3*rangePoint) hasil = RuleNilai.BAIK;
            else if(jmlBenar >= 2*rangePoint) hasil = RuleNilai.SEDANG;
            else if(jmlBenar >= rangePoint) hasil = RuleNilai.KURANG;
            else hasil = RuleNilai.SANGAT_KURANG;
        }
        return hasil;
    }
    
}
