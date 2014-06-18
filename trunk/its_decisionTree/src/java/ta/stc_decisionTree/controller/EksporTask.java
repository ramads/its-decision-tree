package ta.stc_decisionTree.controller;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import ta.stc_decisionTree.model.TabelTree;
import ta.stc_decisionTree.util.Excel;
import ta.stc_decisionTree.view.TabelTreeUI;

/**
 * Untuk mengeskpor tabel tree ke CSV in background
 * @author ami
 */
public class EksporTask extends SwingWorker<Void, Void>{
    private TabelTree tabelTree;
    private TabelTreeUI tabelTreeUI;
    private String path;

    @Override
    protected Void doInBackground() throws Exception {
        if(!(path==null || path.equals(""))){
            Excel excel = new Excel(tabelTree);
            excel.eksporKeCSV(path);
        }else{
            JOptionPane.showMessageDialog(tabelTreeUI, "Path file kosong!\nEkspor File Gagal!");
        }
        return null;
    }
    
    @Override
    protected void done(){
        tabelTreeUI.setCursor(null);
        JOptionPane.showMessageDialog(tabelTreeUI, "File telah diekspor!");
    }
    
    public void setComponent(TabelTreeUI tabelTreeUI){
        this.tabelTreeUI = tabelTreeUI;
    }
    
    public void setTabelTree(TabelTree tabelTree){
        this.tabelTree = tabelTree;
    }
    
    public void setPath(String path){
        this.path = path;
    }
    
}
