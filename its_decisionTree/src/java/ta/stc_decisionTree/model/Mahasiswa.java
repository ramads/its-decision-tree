package ta.stc_decisionTree.model;

/**
 *Kelas/Objek mahasiswa untuk menyimpan informasi mahasiswa
 * @author ami
 */
public class Mahasiswa {
    private String nim;
    private String namaMahasiswa;
    
    public Mahasiswa(){
        nim = namaMahasiswa = "";
    }
    
    public Mahasiswa(String nim, String namaMahasiswa){
        this.nim = nim;
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" + "nim=" + nim + ", namaMahasiswa=" + namaMahasiswa + '}';
    }    
}
