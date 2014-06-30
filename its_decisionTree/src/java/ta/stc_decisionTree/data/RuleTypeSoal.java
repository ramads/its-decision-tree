package ta.stc_decisionTree.data;

import java.util.LinkedList;

/**
 *
 * @author ami
 */
public class RuleTypeSoal {
    public final static String[] SOAL_TYPE_1 = {
        NamaMateri.DASAR_JAVA, 
        NamaMateri.KELAS_METHOD
    };
    public final static String[] SOAL_TYPE_2 = {
        NamaMateri.VARIABEL, 
        NamaMateri.TIPE_DATA_PRIMITIF
    };
    public final static String[] SOAL_TYPE_3 = {
        NamaMateri.VARIABEL, 
        NamaMateri.TIPE_DATA_OBJEK
    };
    public final static String[] SOAL_TYPE_4 = {
        NamaMateri.LOGIKA, 
        NamaMateri.OP_ARITMATIKA, 
        NamaMateri.OP_LOGIKA
    };
    public final static String[] SOAL_TYPE_5 = {
        NamaMateri.PERULANGAN, 
        NamaMateri.FLOWCHART
    };
    public final static String[] SOAL_TYPE_6 = {
        NamaMateri.TIPE_DATA_PRIMITIF, 
        NamaMateri.VARIABEL, 
        NamaMateri.ARRAY
    };
    public final static String[] SOAL_TYPE_7 = {
        NamaMateri.ARRAY, 
        NamaMateri.PERULANGAN
    };
    public final static String[] SOAL_TYPE_8 = {
        NamaMateri.OP_RELASI, 
        NamaMateri.OP_LOGIKA,
        NamaMateri.OP_PREDENCE
    };
    public final static String[] SOAL_TYPE_9 = {
        NamaMateri.KELAS_METHOD,
        NamaMateri.OP_ARITMATIKA,
        NamaMateri.OP_PREDENCE
        
    };
    public final static String[] SOAL_TYPE_10 = {
        NamaMateri.TIPE_DATA_PRIMITIF, 
        NamaMateri.OP_INCREMENT_DECREMENT
    };
    public final static String[] SOAL_TYPE_11 = {
        NamaMateri.OP_ARITMATIKA, 
        NamaMateri.OP_RELASI,
        NamaMateri.OP_PREDENCE
    };
    public final static String[] SOAL_TYPE_12 = {
        NamaMateri.OP_INCREMENT_DECREMENT, 
        NamaMateri.PERULANGAN
    };
    public final static String[] SOAL_TYPE_13 = {
        NamaMateri.TIPE_DATA_PRIMITIF, 
        NamaMateri.OP_RELASI,
        NamaMateri.IF_ELSE
    };
    public final static String[] SOAL_TYPE_14 = {
        NamaMateri.TIPE_DATA_PRIMITIF, 
        NamaMateri.SWITCH_CASE
    };
    public final static String[] SOAL_TYPE_15 = {
        NamaMateri.OP_INCREMENT_DECREMENT, 
        NamaMateri.OP_RELASI,
        NamaMateri.PERULANGAN,
        NamaMateri.IF_ELSE
    };
    
    public final static LinkedList<String[]> LIST_TYPE_SOAL(){
        LinkedList<String[]> listTypeSoal = new LinkedList<>();
        listTypeSoal.add(SOAL_TYPE_1);
        listTypeSoal.add(SOAL_TYPE_2);
        listTypeSoal.add(SOAL_TYPE_3);
        listTypeSoal.add(SOAL_TYPE_4);
        listTypeSoal.add(SOAL_TYPE_5);
        listTypeSoal.add(SOAL_TYPE_6);
        listTypeSoal.add(SOAL_TYPE_7);
        listTypeSoal.add(SOAL_TYPE_8);
        listTypeSoal.add(SOAL_TYPE_9);
        listTypeSoal.add(SOAL_TYPE_10);
        listTypeSoal.add(SOAL_TYPE_11);
        listTypeSoal.add(SOAL_TYPE_12);
        listTypeSoal.add(SOAL_TYPE_13);
        listTypeSoal.add(SOAL_TYPE_14);
        listTypeSoal.add(SOAL_TYPE_15);
        return listTypeSoal;
    } 
}

/*
 
Dasar java      ->  type 1                  = 1
Kelas & Method  ->  type 1, 9               = 2
PDT             ->  type 2, 6, 10, 13, 14   = 5
Variabel        ->  type 2, 3, 6            = 3
ODT             ->  type 3                  = 1
Array           ->  type 6, 7               = 2
Logika          ->  type 4                  = 1
Op Inc Dec      ->  type 10, 12, 15         = 3

 
 */