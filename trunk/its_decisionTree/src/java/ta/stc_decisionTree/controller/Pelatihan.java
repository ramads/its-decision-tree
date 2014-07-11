package ta.stc_decisionTree.controller;

import java.util.LinkedList;
import ta.stc_decisionTree.model.DecisionTree;
import ta.stc_decisionTree.model.MateriInference;
import ta.stc_decisionTree.model.TabelTree;

/**
 * kelas untuk memproses pelatihan dan membentuk decision tree
 * @author ami
 */
public class Pelatihan {
    //daftar atribut target untuk inputan tree
    LinkedList<String> daftarTarget;
    
    //daftar atribut materi (atribut target dan non target)
    LinkedList<String> daftarMateri;
    
    LinkedList<MateriInference> materiInference;
    //objek untuk memproses tabel tree
    ProsesTabelTree pTree;
    
    //objek algoritmaID3 untuk membuat decision tree
    AlgoritmaID3 id3 = new AlgoritmaID3();
    
    public Pelatihan() {}
    
    
    public void setDaftarTarget(LinkedList<String> daftarTarget){
        this.daftarTarget = daftarTarget;
    }
    
    public void setDaftarMateri(LinkedList<String> daftarMateri){
        this.daftarMateri = daftarMateri;
    }
    
    public void setMateriInference(LinkedList<MateriInference> mi){
        materiInference = mi;
    }
    
    /**
     * fungsi untuk mendapatkan decision tree yang dibuat 
     * tanpa menggunakan keterkaitan antar materi (semua materi digunakan sebagai atribut)
     * @param prosesTes hasil tes dari mahasiswa yang didapat dari workshop harian
     * @return daftar decision tree sebanyak target yang ditentukan
     */
    public LinkedList<DecisionTree> buatDecisionTree(ProsesTes prosesTes){
        if(isValid(daftarTarget, daftarMateri)){            
            LinkedList<DecisionTree> daftarDTree = new LinkedList<>();
            pTree = new ProsesTabelTree(prosesTes.ambiHasilTes());
            
            String[] daftarAtribut = new String[daftarMateri.size()];
            
            daftarMateri.toArray(daftarAtribut);
            pTree.setDaftarAtribut(daftarAtribut);
            
            for(String target : daftarTarget){
                pTree.setTargetAtribut(target);
                TabelTree tabelTree = pTree.buatTabelTree();
                DecisionTree tree = id3.runAlgorithm(tabelTree);
                daftarDTree.add(tree);
            }
            return daftarDTree;
        }else{
            return null;
        }
    }
    
    /**
     * fungsi untuk membuat decision tree yang 
     * dibuat berdasarkan materi inference (keterkaitan antar materi)
     * @param prosesTes
     * @return 
     */
    public LinkedList<DecisionTree> buatDecisionTreeInference(ProsesTes prosesTes){
        LinkedList<DecisionTree> daftarDTree = new LinkedList<>();
        pTree = new ProsesTabelTree(prosesTes.ambiHasilTes());
        for(MateriInference mInference : materiInference){
            pTree.setTargetAtribut(mInference.targetMateri);
            pTree.setDaftarAtribut(mInference.materiInference);
            TabelTree tabelTree = pTree.buatTabelTree();
            DecisionTree tree = id3.runAlgorithm(tabelTree);
            daftarDTree.add(tree);
        }
        return daftarDTree;
    }
    
    /**
     * untuk mengecek apakah target terdapat dalah daftar materi
     * jika tidak ada pelatihan tidak bisa diproses
     * @param dTarget daftar target
     * @param dMateri daftar materi 
     * @return boolean true -> target ada, false -> target tidak ada
     */
    private boolean isValid(LinkedList<String> dTarget, LinkedList<String> dMateri){
        boolean valid = true;
        for(String target : dTarget){
            valid = false;
            for(String materi : dMateri){
                if(target.equals(materi)){
                    valid = true;
                    break;
                }
            }
            if(!valid){break;}
        }
        return valid;
    }
}
