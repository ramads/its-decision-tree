package ta.stc_decisionTree.model;

import java.util.LinkedList;

/**
 *
 * @author rama
 */
public class Type {
    private int idType;
    private String namaType;
    private LinkedList<Materi> daftarMateri;
    
    public Type(){
        idType = 0;
        namaType = "";
        daftarMateri = null;
    }
    
    public Type(int idType, String namaType, LinkedList<Materi> daftarMateri){
        this.idType = idType;
        this.namaType = namaType;
        this.daftarMateri = daftarMateri;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNamaType() {
        return namaType;
    }

    public void setNamaType(String namaType) {
        this.namaType = namaType;
    }

    public LinkedList<Materi> getDaftarMateri() {
        return daftarMateri;
    }

    public void setDaftarMateri(LinkedList<Materi> daftarMateri) {
        this.daftarMateri = daftarMateri;
    }
}
