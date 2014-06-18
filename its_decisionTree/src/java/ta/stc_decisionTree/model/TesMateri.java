package ta.stc_decisionTree.model;

/**
 * kelas/objek untuk menyimpan informasi hasil tes materi
 * @author ami
 */
public class TesMateri {
    private Materi materi;
    private int hasil;

    public TesMateri() {
        materi = null;
    }
    
    public TesMateri(Materi materi, int hasil){
        this.materi = materi;
        this.hasil = hasil;
    }
    
    public Materi getMateri() {
        return materi;
    }

    public void setMateri(Materi materi) {
        this.materi = materi;
    }

    public int getHasil() {
        return hasil;
    }

    public void setHasil(int hasil) {
        this.hasil = hasil;
    }
    
    @Override
    public String toString() {
        return "TesMateri{" + "materi=" + materi + ", hasil=" + hasil + '}';
    }
}
