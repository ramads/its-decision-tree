package ta.stc_decisionTree.controller;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import ta.stc_decisionTree.data.RuleNilai;
import ta.stc_decisionTree.model.TabelTree;
import ta.stc_decisionTree.model.Tes;
import ta.stc_decisionTree.model.TesMateri;

/**
 * kelas untuk membentuk tabel tree dari hasil tes mahasiswa
 * @author ami
 */
public class ProsesTabelTree{
    //menyimpan jumalh atribut
    private int totalAtribut;
    
    //menyimpan nama target atribut
    private String targetAtribut;
    
    //menyimpan daftar atribut
    private String[] daftarAtribut;
    
    //meyimpan hasil tes semua mahasiswa
    private LinkedList<Tes> hasilTes;

    /**
     * constractor yg memberikan nilai awal pada variable
     */
    public ProsesTabelTree() {
        totalAtribut = 0;
        targetAtribut = "";
    }
    
    public ProsesTabelTree(LinkedList<Tes> hasilTes){
        totalAtribut = 0;
        targetAtribut = "";
        this.hasilTes = hasilTes;
    }
    
    public void setHasilTes(LinkedList<Tes> hasilTes){
        this.hasilTes = hasilTes;
    }
    
    public void setDaftarAtribut(String... daftarAtribut){
        totalAtribut = daftarAtribut.length;
        this.daftarAtribut = daftarAtribut;
    }
    
    public void setTargetAtribut(String targetAtribut){
        this.targetAtribut = targetAtribut;
    }
    
    /**
     * fungsi untuk mengambil tabel tree yang dibuat
     * @return tabel tree
     */
    public TabelTree buatTabelTree(){
        if(!cekTarget()) System.exit(0);
        
        TabelTree tabelTree = new TabelTree();
        tabelTree.setTarget(targetAtribut);
        tabelTree.setHeaderTabel(daftarAtribut);
        
        List<String[]> bodyTabel = new LinkedList<>();
        boolean flag;
        
        for(Tes tes : hasilTes){
            LinkedList<TesMateri> daftarTesMateri = tes.getDaftarTesMateri();
            String[] rowTabel = new String[totalAtribut];
            for(TesMateri tesMateri : daftarTesMateri){
                for(int i=0;i<daftarAtribut.length;i++){
                    if(daftarAtribut[i].equals(tesMateri.getMateri().getNamaMateri())){
                        if(daftarAtribut[i].equals(targetAtribut)){
                            flag = RuleNilai.FLAG_TARGET;
                        }else{
                            flag = RuleNilai.FLAG_NON_TARGET;
                        }
                        rowTabel[i] = RuleNilai.ubahNilaiKeString(
                                RuleNilai.cekHasil(
                                        tesMateri.getMateri().getNamaMateri(), 
                                        tesMateri.getHasil(), 
                                        flag
                                )
                        );
                    }
                }
            }
            bodyTabel.add(rowTabel);
        }
        tabelTree.setBodyTabel(bodyTabel);
        return tabelTree;
    }
    
    private boolean cekTarget(){
        for(String str : daftarAtribut){
            if(str.equalsIgnoreCase(targetAtribut)){
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Target tidak ditemukan!");
        return false;
    }
}
