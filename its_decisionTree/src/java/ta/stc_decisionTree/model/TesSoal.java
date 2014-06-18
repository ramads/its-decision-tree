package ta.stc_decisionTree.model;

/**
 *Kelas TesSoal digunakan untuk menyimpan hasil jawaban dari user terhadat soal2
 * pretes maupun posttest
 * @author ami
 */
public class TesSoal {
    private Soal soal;
    private boolean benar;

    public Soal getSoal() {
        return soal;
    }

    public void setSoal(Soal soal) {
        this.soal = soal;
    }

    public boolean isBenar() {
        return benar;
    }

    public void setBenar(boolean benar) {
        this.benar = benar;
    }
}
