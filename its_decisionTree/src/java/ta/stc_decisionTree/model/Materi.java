package ta.stc_decisionTree.model;

import java.util.LinkedList;

/**
 *Kelas/Objek materi untuk menyimpan informasi materi
 * @author ami
 */
public class Materi {
    private int idMateri;
    private String namaMateri;
    private LinkedList<Soal> daftarSoal;
    
    public Materi(){
        idMateri = 0;
        namaMateri = "";
        daftarSoal = new LinkedList<Soal>();
    }
    
    public Materi(int idMateri, String namaMateri){
        this.idMateri = idMateri;
        this.namaMateri = namaMateri;
    }
    
    public Materi(int idMateri, String namaMateri, LinkedList<Soal> daftarSoal){
        this.idMateri = idMateri;
        this.namaMateri = namaMateri;
        this.daftarSoal = daftarSoal;
    }

    public int getIdMateri() {
        return idMateri;
    }

    public void setIdMateri(int idMateri) {
        this.idMateri = idMateri;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }

    public LinkedList<Soal> getDaftarSoal() {
        return daftarSoal;
    }

    public void setDaftarSoal(LinkedList<Soal> daftarSoal) {
        this.daftarSoal = daftarSoal;
    }
    
    public void tambahDaftarSoal(Soal soal){
        this.daftarSoal.add(soal);
    }
}
