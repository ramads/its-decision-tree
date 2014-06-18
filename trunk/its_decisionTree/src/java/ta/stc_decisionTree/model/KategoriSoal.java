package ta.stc_decisionTree.model;

import java.util.LinkedList;

/**
 * Kelas/Objek KategoriSoal digunakn untuk menyimpan soal dan materi2 apa saja
 * yang berkaitan dengan soal tersebut
 * @author ami
 */
public class KategoriSoal {
    TesSoal tSoal;
    LinkedList<Materi> kategori;

    public KategoriSoal() {
        tSoal = null;
        kategori = null;
    }
    
    public void setTesSoal(TesSoal tSoal){
        this.tSoal = tSoal;
    }
    
    public void setKategori(LinkedList<Materi> kategori){
        this.kategori = kategori;
    }

    public TesSoal getTesSoal() {
        return tSoal;
    }

    public LinkedList<Materi> getKategori() {
        return kategori;
    }
}
