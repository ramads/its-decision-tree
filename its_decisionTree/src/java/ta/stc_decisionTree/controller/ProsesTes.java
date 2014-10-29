package ta.stc_decisionTree.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import ta.stc_decisionTree.data.NamaMateri;
import ta.stc_decisionTree.model.*;
import ta.stc_decisionTree.util.QueryToDB;
import ta.stc_decisionTree.util.SQL;

/**
 * kelas untuk mengambil hasil tes mahasiswa pada saat workshop dari database
 * @author ami
 */
public class ProsesTes {
    private LinkedList<Tes> hasilTesHarian;
    private LinkedList<Mahasiswa> daftarMahasiswa;
    
    //koneksi ke databases
    private QueryToDB koneksi = new QueryToDB("tugasakhir");

    public ProsesTes(){
        hasilTesHarian = new LinkedList<Tes>();
        daftarMahasiswa = new LinkedList<Mahasiswa>();
    }
    
    /**
     * mengambil hasil tes mahasiswa
     * @return list tes mahasiswa
     */
    public LinkedList<Tes> ambilHasilTes(){
        try {
            prosesDataTes();
        }catch (SQLException ex){
            System.out.println("Error Proses Data Tes karena: "
                    +ex.getMessage());
        }
        return hasilTesHarian;
    }
    
    private void prosesDataTes() throws SQLException{
        ResultSet dataTesHarian = koneksi.querySelect(SQL.AMBIL_SEMUA_TES_HARIAN);
        while (dataTesHarian.next()){
            Tes tes = new Tes();
            tes.setMahasiswa(
                    new Mahasiswa(
                        dataTesHarian.getString("id"), 
                        dataTesHarian.getString("nama")
                    )
            );            
            LinkedList<TesMateri> daftarTesMateri = new LinkedList<TesMateri>();
            for(String namaMateri : NamaMateri.LIST_NAMA_MATERI){
                TesMateri tesMateri = new TesMateri(
                        new Materi(0, namaMateri),
                        dataTesHarian.getInt(namaMateri)
                );
                daftarTesMateri.add(tesMateri);
            }
            tes.setDaftarTesMateri(daftarTesMateri);
            hasilTesHarian.add(tes);
        }
    }
}
