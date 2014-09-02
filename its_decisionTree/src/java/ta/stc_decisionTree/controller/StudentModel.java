package ta.stc_decisionTree.controller;

import java.util.LinkedList;
import java.util.Vector;
import ta.stc_decisionTree.data.NamaMateri;
import ta.stc_decisionTree.data.RuleNilai;
import ta.stc_decisionTree.data.RuleTypeSoal;
import ta.stc_decisionTree.model.DecisionTree;
import ta.stc_decisionTree.model.MateriPretest;

/**
 * kelas ini berfungsi untuk memodelkan user yang menggunakan aplikasi.
 * pemodelan user ini berdasarkan kemampuan user yang diukur dengan kemampuan
 * menjawab soal pada pretest dan posttes
 * 
 * @author ami
 */
public class StudentModel {
    
    // variable materiPretest digunakan untuk menyimpan data hasil pretest dari user
    // data yang disimpan pada variable ini meliputi nama2 materi yang, hasil pretest dari materi (B, S atau K), 
    // jumlah jawaban benar dan jumlah seluruh soal
    MateriPretest[] materiPretest;
    
    public String materi;
    public int hasil;
    public int jumlahBenar;
    public int totalSoal;
    
    // variable ini digunakan untuk menyimpan daftar materi2 yang belum dikuasai oleh user
    LinkedList<String> weakMaterial = new LinkedList<>();
    
    // variable dTreeList digunakan untuk menyimpan daftar decision tree yang dibentuk
    private LinkedList<DecisionTree> dTreeList = new LinkedList<DecisionTree>();
    
    public StudentModel(){
        System.out.println("Konstruktor kepanggil");
        materiPretest = new MateriPretest[NamaMateri.LIST_NAMA_MATERI.length];
        int i = 0;
        for(String namaMateri : NamaMateri.LIST_NAMA_MATERI){
            MateriPretest pretest = new MateriPretest();
            pretest.materi = namaMateri;
            materiPretest[i++] = pretest;
        }
    }
    
    /**
     * digunakan untuk memetakan hasil jawaban pretes user ke masing2 materi (B, S, K)
     * kemudian hasil pemetaan tersebut akan disimpan ke dalam variable materiPretest
     * @param hasilJawaban jawaban2 pretes user
     */
    private void createStudentModel(Vector<Boolean> hasilJawaban){
        double range;
        double rangePoint;
        try{
            //
            setHasilPretest(hasilJawaban);
        }catch(Exception e){
            System.out.println("Exception createStudentModel: "+e.getMessage());
        }
        for(MateriPretest mPretest:materiPretest){
            double jmlBenar = mPretest.jumlahBenar;
            double totSoal = mPretest.totalSoal;
//            if(jmlBenar>=(totSoal/2.0)){
//                weakMaterial.add(mPretest.materi);
//            }
            if(RuleNilai.getVariasi()==RuleNilai.VARIASI_PERTAMA){
                range = 2.0;
                rangePoint = totSoal/range;
                if(jmlBenar >= rangePoint) mPretest.hasil = RuleNilai.BAIK;
                else mPretest.hasil = RuleNilai.KURANG;
            }else if(RuleNilai.getVariasi()==RuleNilai.VARIASI_KEDUA){
                range = 3.0;
                rangePoint = totSoal/range;
                if(jmlBenar >= 2.0*rangePoint) mPretest.hasil = RuleNilai.BAIK;
                else if(jmlBenar >= rangePoint) mPretest.hasil = RuleNilai.SEDANG;
                else mPretest.hasil = RuleNilai.KURANG;
            }else if(RuleNilai.getVariasi()==RuleNilai.VARIASI_KETIGA){
                range = 5.0;
                rangePoint = totSoal/range;
                if(jmlBenar >= 4.0*rangePoint) mPretest.hasil = RuleNilai.SANGAT_BAIK;
                else if(jmlBenar >= 3.0*rangePoint) mPretest.hasil = RuleNilai.BAIK;
                else if(jmlBenar >= 2.0*rangePoint) mPretest.hasil = RuleNilai.SEDANG;
                else if(jmlBenar >= rangePoint) mPretest.hasil = RuleNilai.KURANG;
                else mPretest.hasil = RuleNilai.SANGAT_KURANG;
            }
            
        }
    }
    
    /*
    fungsi yang digunakan untuk membentuk decision tree
    */
    private void bentukDecisionTree(){
        
        // instansisasi objek Pelatihan
        //objek pelatihan ini digunakan untuk membangun decision tree
        Pelatihan pLatih = new Pelatihan();
        
        //set daftar materi non target
        pLatih.setDaftarMateri((LinkedList<String>) arrayToLinkedList(NamaMateri.LIST_NAMA_MATERI));
        
        //set daftar materi yang akan dijadikan sebagai target
        pLatih.setDaftarTarget((LinkedList<String>) arrayToLinkedList(NamaMateri.LIST_NAMA_MATERI));
        
        //memanggil fungsi bentukDecisionTree yang ada pada objek pelatihan dengan diberikan 
        //parameter objek ProsesTes (objek proses tes digunakan untuk memetakan)
        dTreeList = pLatih.bentukDecisionTree(new ProsesTes());
    }
    
    /**
     * digunakan untuk mencari materi2 yang kurang dikuasai oleh user
     * untuk mencari materi2 ini digunakan metode decision tree
     * @param hasilJawaban -> hasil jawaban pretest user
     * @return daftar materi2 yang kurang dikuasi oleh user
     */
    public LinkedList<String> getWeakMaterial(Vector<Boolean> hasilJawaban){
        
        //memanggil fungsi createStudentModel untuk membentuk student model berdasarkan hasil jawaban pretest dari user
        createStudentModel(hasilJawaban);
        
        //memanggil fungsi bentukDecisionTree untuk membentuk decision tree dari data workshop
        bentukDecisionTree();
        
        for(DecisionTree dTree : dTreeList){
            
            System.out.println("Dtree target: "+dTree.tabelTree.getTarget());
            
            // inisialisasi variable inputPrediksi dengan nilai hasil dari pemanggilan fungsi getInputPrediksi
            // yang diberikan parameter decision tree yang telah dibentuk untuk didapatkan inputannya masing2
            String[] inputPrediksi = getInputPrediksi(dTree);
            String hasilPrediksi = dTree.predictTargetAttributeValue(inputPrediksi);
            System.out.println("Hasil Prediksi: "+hasilPrediksi);
            if(hasilPrediksi!=null && !hasilPrediksi.isEmpty()){
                if(RuleNilai.ubahStringKeNilai(hasilPrediksi)==RuleNilai.TIDAK_LULUS){
                    System.out.println("weak material");
                    System.out.println("========================================");
                    weakMaterial.add(dTree.tabelTree.getTarget());
                }
            }
        }
        return weakMaterial;
    }
    
    /**
     * digunakan untuk mendapatkan 
     * @param dTree
     * @return 
     */
    private String[] getInputPrediksi(DecisionTree dTree){
        String[] inputPrediksi = new String[dTree.allAttributes.length];
        for(int i=0;i<inputPrediksi.length;i++){
            if(dTree.allAttributes[i].equals(dTree.tabelTree.getTarget())){
                inputPrediksi[i] = null;
            }else{
                for(MateriPretest mPretest : materiPretest){
                    if(mPretest.materi.equals(dTree.allAttributes[i])){
                        inputPrediksi[i] = RuleNilai.ubahNilaiKeString(mPretest.hasil);
                    }
                }
            }
        }
        return inputPrediksi;
    }
    
    /**
     * mengeset hasil jawaban pretes user ke objek MateriPretes untuk mempermudah
     * pemetaan hasil pretes ke masing2 materi. pada fungsi ini akan di hitung
     * berapa soal yg muncul untuk masing2 materi dan menghitung berapa jumlah
     * benar dan salahnya
     * @param hasilJawaban hasil jawaban pretes user
     */
    private void setHasilPretest(Vector<Boolean> hasilJawaban){
        //set type soal
        LinkedList<String[]> listTypeSoal = RuleTypeSoal.LIST_TYPE_SOAL();
        
        //untuk masing2 soal (no 1 sampai 15) pretes atau masing2 list type soal
        for(int i=0;i<hasilJawaban.size();i++){
            //ambil materi2 yang menyusun type soal
            String[] listMateri = listTypeSoal.get(i);
            //untuk semua materi yg ada pada type soal
            for(String str:listMateri){
                //untuk semua materi yg ada
                for(MateriPretest mPretest:materiPretest){
                    //jika
                    if(mPretest.materi.equals(str)){
                        mPretest.totalSoal++;       //increment total soal untuk materi
                        //jika jawaban pretes benar
                        if(hasilJawaban.get(i)){
                            mPretest.jumlahBenar++; //increment jumlah benar untuk materi
                        }
                    }
                }
            }
        }
    }
    
    //convert array ke llinkedlist
    private LinkedList arrayToLinkedList(Object[] arr){
        LinkedList list = new LinkedList();
        for(Object o : arr){
            list.add(o);
        }
        return list;
    }
}
