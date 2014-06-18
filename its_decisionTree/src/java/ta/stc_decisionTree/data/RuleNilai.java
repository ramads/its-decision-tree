package ta.stc_decisionTree.data;

import javax.swing.JOptionPane;

/**
 * Kelas RuleNilai berfungsi untuk mengatur cara penentuan kemampuan user 
 * (sangat baik, baik, sedang, kurang, sangat kurang, lulus, tidak) 
 * untuk masing2 materi
 *
 * @author ami
 */
public class RuleNilai {
   // 13 8 7 13	7  8 4 5 5 5 7	9 6 7 7 3

    private static final double HP_BASEJAVA = 17.0;//18.0;
    private static final double HP_CLASS_METHOD = 8.0; //6.0;
    private static final double HP_VARIABLE = 7.0; //7.0;
    private static final double HP_TIPE_DATA_PRIMITIVE = 13.0;//16.0;
    private static final double HP_TIPE_DATA_OBJECT = 8.0; //10.0;
    private static final double HP_ARRAY = 8.0; //9.0;
    private static final double HP_LOGIKA = 4.0;//3.0;
    private static final double HP_OP_ARITMATIKA = 5.0;//7.0;
    private static final double HP_OP_INC_DEC = 5.0; //6.0;
    private static final double HP_OP_RELATIONAL = 5.0; //8.0;
    private static final double HP_OP_LOGIKA = 7.0; //9.0;
    private static final double HP_OP_PREDENCE = 10.0; //7.0;
    private static final double HP_LOOPING = 6.0; //8.0;
    private static final double HP_IF_ELSE = 7.0;//8.0;
    private static final double HP_SWITCH_CASE = 7.0;//8.0;
    private static final double HP_FLOWCHART = 3.0;//4.0;

    //setting jumlah varisasi attribut kemampuan user menjadi 2 kemampuan
    //yaitu Bisa dan Kurang
    public static final int VARIASI_PERTAMA = 1;
    
    //setting jumlah varisasi attribut kemampuan user menjadi 3 kemampuan
    //yaitu Bisa, Sedang dan Kurang
    public static final int VARIASI_KEDUA = 2;
    
    //setting jumlah varisasi attribut kemampuan user menjadi 5 kemampuan
    //yaitu Sangat Bisa, Bisa, Sedang, Kurang dan Sangat Kurang
    public static final int VARIASI_KETIGA = 3;
    
    //defult jumlah variasi adalah variasi dengan kedua
    private static int VARIASI=VARIASI_KEDUA;
    
    public static final int LULUS = 7;
    public static final int TIDAK_LULUS = 6;
    public static final int SANGAT_BAIK = 5;
    public static final int BAIK = 4;
    public static final int SEDANG = 3;
    public static final int KURANG = 2;
    public static final int SANGAT_KURANG = 1;
    public static final boolean FLAG_TARGET = true;
    public static final boolean FLAG_NON_TARGET = false;
    
    /**
     * fungsi untuk mengecek hasil tes (tergolong baik, sedang, kurang, lulus atau tidak)
     * @param namaMateri nama materi yang di tes
     * @param hasil hasil tes mahasiswa permateri
     * @param flag untuk mengetahui yg akan di cek atribut target atau bukan)
     * karena untuk target dan non target berbeda cara pengecekan nya
     * target => lulus atau tidak lulus
     * nontarget => baik, sedang, kurang
     * 
     * @return nilai 1=kurang, 2=sedang, 3=baik, 4=tidak lulu, 5=lulus
     */
    public static int cekHasil(String namaMateri, int hasil, boolean flag){
        if(VARIASI!=0){
            if(flag==FLAG_TARGET){
                return getTargetHasil(namaMateri, hasil);
            }else{
                return getNonTargetHasil(namaMateri, hasil);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Rule belum diset!");
        }
        return 0;
    }
    
    /**
     * setting rule untuk 2 atau 3 item atribut
     * @param variasi 
     */
    public static void setVariasi(int variasi){
        VARIASI = variasi;
    }
    
    public static int getVariasi(){
        return VARIASI;
    }
    
    private static double getRange(String namaMateri, double range){
        switch(namaMateri){
            case NamaMateri.DASAR_JAVA: return HP_BASEJAVA/range;
            case NamaMateri.KELAS_METHOD: return HP_CLASS_METHOD/range;
            case NamaMateri.VARIABEL: return HP_VARIABLE/range;
            case NamaMateri.TIPE_DATA_PRIMITIF: return HP_TIPE_DATA_PRIMITIVE/range;
            case NamaMateri.TIPE_DATA_OBJEK: return HP_TIPE_DATA_OBJECT/range;
            case NamaMateri.ARRAY: return HP_ARRAY/range;
            case NamaMateri.LOGIKA: return HP_LOGIKA/range;
            case NamaMateri.OP_ARITMATIKA: return HP_OP_ARITMATIKA/range;
            case NamaMateri.OP_INCREMENT_DECREMENT: return HP_OP_INC_DEC/range;
            case NamaMateri.OP_RELASI: return HP_OP_RELATIONAL/range;
            case NamaMateri.OP_LOGIKA: return HP_OP_LOGIKA/range;
            case NamaMateri.OP_PREDENCE: return HP_OP_PREDENCE/range;
            case NamaMateri.PERULANGAN: return HP_LOOPING/range;
            case NamaMateri.IF_ELSE: return HP_IF_ELSE/range;
            case NamaMateri.SWITCH_CASE: return HP_SWITCH_CASE/range;
            case NamaMateri.FLOWCHART: return HP_FLOWCHART/range;
            default: return 0.0;
        }
    }
    
    /**
     * untuk mendapatkan hasil dari atribut target
     * @param namaMateri
     * @param hasil
     * @return 
     */
    private static int getTargetHasil(String namaMateri, int hasil){
        double targetRange = 2.0;
        double rangePoint = getRange(namaMateri, targetRange);
        if((double)hasil >= rangePoint) return LULUS;
        else return TIDAK_LULUS;
    }
    
    /**
     * untuk mendapatkan hasil dari atribut non target
     * @param namaMateri
     * @param hasil
     * @return 
     */
    private static int getNonTargetHasil(String namaMateri, int hasil){
        double range;
        double rangePoint;
        if(VARIASI==VARIASI_PERTAMA){
            range = 2.0;
            rangePoint = getRange(namaMateri, range);
            if((double)hasil >= rangePoint) return BAIK;
            else return KURANG;
        }else if(VARIASI==VARIASI_KEDUA){
           range = 3.0; 
           rangePoint = getRange(namaMateri, range);
           if((double)hasil >= (2.0*rangePoint)) return BAIK;
           else if((double)hasil >= rangePoint) return SEDANG;
           else return KURANG;
        }else if(VARIASI==VARIASI_KETIGA){
            range = 5.0;
            rangePoint = getRange(namaMateri, range);
            double hsl = (double)hasil;
            if(hsl >= (4.0*rangePoint)) return SANGAT_BAIK;
            else if(hsl >= (3.0*rangePoint)) return BAIK;
            else if(hsl >= (2.0*rangePoint)) return SEDANG;
            else if(hsl >= rangePoint) return KURANG;
            else return SANGAT_KURANG;
        }else{
            return 0;
        }
    }
    
    public static String ubahNilaiKeString(int hasil){
        switch(hasil){
            case RuleNilai.LULUS: return "LULUS";
            case RuleNilai.TIDAK_LULUS: return "TIDAK";
            case RuleNilai.SANGAT_BAIK: return "SB";
            case RuleNilai.BAIK: return "B";
            case RuleNilai.SEDANG: return "S";
            case RuleNilai.KURANG: return "K";
            case RuleNilai.SANGAT_KURANG: return "SK";
            default: return "";
        }
    }
    
    public static int ubahStringKeNilai(String str){
        switch(str){
            case "LULUS": return LULUS;
            case "TIDAK": return TIDAK_LULUS;
            case "SB": return SANGAT_BAIK;
            case "B": return BAIK;
            case "S": return SEDANG;
            case "K": return KURANG;
            case "SK": return SANGAT_KURANG;
            default: return 0;
        }
    }
}
