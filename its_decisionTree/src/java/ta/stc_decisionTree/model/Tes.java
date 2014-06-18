package ta.stc_decisionTree.model;

import java.util.LinkedList;

/**
 * kelas/objek untuk menyimpan informasi tes
 * @author ami
 */
public class Tes {
    private Mahasiswa mahasiswa;
    private LinkedList<TesMateri> daftarTesMateri;
    
    public Tes(){
        mahasiswa = null;
    }
    
    public Mahasiswa getMahasiswa(){
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa){
        this.mahasiswa = mahasiswa;
    }

    public LinkedList<TesMateri> getDaftarTesMateri() {
        return daftarTesMateri;
    }

    public void setDaftarTesMateri(LinkedList<TesMateri> daftarTesMateri) {
        this.daftarTesMateri = daftarTesMateri;
    }
    
    @Override
    public String toString() {
        return "Tes{" + "mahasiswa=" + mahasiswa + ", daftarTesMateri=" + daftarTesMateri.toString() + '}';
    }
}
