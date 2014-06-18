package ta.stc_decisionTree.controller;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import ta.stc_decisionTree.model.DecisionTree;
import ta.stc_decisionTree.view.SimulasiUI;

public class PelatihanTask extends SwingWorker<Void, Void>{
    SimulasiUI simulasiUI;
    LinkedList<String> daftarMateri, daftarTarget;
    LinkedList<DecisionTree> dTreeList;
    
    public void setView(SimulasiUI simulasiUI){
        this.simulasiUI = simulasiUI;
    }
    
    public void setData(LinkedList<String> daftarMateri, LinkedList<String> daftarTarget){
        this.daftarMateri = daftarMateri;
        this.daftarTarget = daftarTarget;
    }
            
    @Override
    protected Void doInBackground() throws Exception {
        Pelatihan pLatih = new Pelatihan();
        pLatih.setDaftarMateri(daftarMateri);
        pLatih.setDaftarTarget(daftarTarget);
        dTreeList = pLatih.buatDecisionTree(new ProsesTes());
        return null;
    }
    
    @Override
    protected void done(){
        simulasiUI.setCursor(null);
        if(dTreeList!=null){
            simulasiUI.setHasilTabel(dTreeList);
            JOptionPane.showMessageDialog(simulasiUI, "Pelatihan Sukses!");
        }else{
            JOptionPane.showMessageDialog(simulasiUI, "Pelatihan gagal!\nTarget tidak ada di materi pilihan!");
        }
    }
    
}
