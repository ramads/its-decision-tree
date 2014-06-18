package ta.stc_decisionTree.model;

/**
 *Kelas/Objek untuk meyimpan informasi soal
 * @author ami
 */
public class Soal {
    private int idSoal;
    private String soal;
    
    public Soal(){
        idSoal = 0;
        soal = "";
    }
    
    public Soal(int idSoal, String soal){
        this.idSoal = idSoal;
        this.soal = soal;
    }

    public int getIdSoal() {
        return idSoal;
    }

    public void setIdSoal(int idSoal) {
        this.idSoal = idSoal;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    @Override
    public String toString() {
        return "Soal{" + "idSoal=" + idSoal + ", soal=" + soal + '}';
    }
}
