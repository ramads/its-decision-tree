package ta.stc_decisionTree;

import ta.stc_decisionTree.controller.AlgoritmaID3;
import ta.stc_decisionTree.controller.ProsesTabelTree;
import ta.stc_decisionTree.controller.ProsesTes;
import ta.stc_decisionTree.data.NamaMateri;
import ta.stc_decisionTree.model.DecisionTree;
import ta.stc_decisionTree.model.TabelTree;

public class Main {
    public static void main(String[] args){
        ProsesTes pt = new ProsesTes();
        ProsesTabelTree tTree = new ProsesTabelTree(pt.ambiHasilTes());
        tTree.setDaftarAtribut(
                NamaMateri.ARRAY, 
                NamaMateri.IF_ELSE, 
                NamaMateri.TIPE_DATA_OBJEK, 
                NamaMateri.PERULANGAN
        );
        tTree.setTargetAtribut(NamaMateri.FLOWCHART);
        TabelTree tabelTree = tTree.buatTabelTree();
        
        AlgoritmaID3 id3 = new AlgoritmaID3();
        DecisionTree tree = id3.runAlgorithm(tabelTree);
        id3.printStatistics();
        tree.print();
    }
}
