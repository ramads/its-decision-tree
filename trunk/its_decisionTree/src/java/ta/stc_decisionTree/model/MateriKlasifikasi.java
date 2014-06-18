package ta.stc_decisionTree.model;

/**
 *Kelas MateriKlasifikasi berfungsi untuk menyimpan hasil prediksi/klasifikasi
 * dari masing2 decision tree yang telah dibentuk. Sehingga dapat memudahkan
 * pemilihan materi mana saja yang harus dipilih sebagai materi yang lemah
 * @author ami
 */
public class MateriKlasifikasi {
    Materi materi;
    int hasilKlasifikasi;

    public MateriKlasifikasi() {
    }
    
    public MateriKlasifikasi(Materi materi, int hasilKlasifikasi){
        this.materi = materi;
        this.hasilKlasifikasi = hasilKlasifikasi;
    }
    
    public void setMateri(Materi materi){
        this.materi = materi;
    }
    
    public void setHasilKlasifikasi(int hasilKlasifikasi){
        this.hasilKlasifikasi = hasilKlasifikasi;
    }
}
