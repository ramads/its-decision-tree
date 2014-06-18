package ta.stc_decisionTree.model;

import java.util.List;

/**
 * Kelas/objek tabel tree untuk meyimpan informasi/isi tabel tree yang
 * digunakan untuk membuat decision tree
 * @author ami
 */
public class TabelTree {
    private String target;
    private String[] headerTabel;
    private List<String[]> bodyTabel;
    
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
    public String[] getHeaderTabel(){
        return headerTabel;
    }
    
    public void setHeaderTabel(String[] headerTabel){
        this.headerTabel = headerTabel;
    }
    
    public List<String[]> getBodyTabel(){
        return bodyTabel;
    }
    
    public void setBodyTabel(List<String[]> bodyTabel){
        this.bodyTabel = bodyTabel;
    }
    
    public String atributLainnya(){
        String atributLainya = "";
        for(int i=0;i<headerTabel.length;i++){
            if(!headerTabel[i].equals(target)){
                atributLainya+=headerTabel[i]+", ";
            }
        }
        return atributLainya;
    }
}
