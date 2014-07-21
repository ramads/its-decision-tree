package ta.stc_decisionTree.util;

import ta.stc_decisionTree.data.Database;

/**
 *
 * @author rama
 */
public class SQL {
    public static final String AMBIL_SEMUA_TES_HARIAN = "select * from "+Database.Table.TABLE_DAILYTEST;
    public static final String AMBIL_TYPE = "";
    public static final String AMBIL_MATERI = "";
    public static final String AMBIL_SOAL = "";
    
    public static String ambil_materi(int idType){
        String query = "";
        return query;
    }
    
    public static String ambil_soal(int idMateri){
        String query = "";
        return query;
    }
}
