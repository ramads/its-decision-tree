package ta.stc_decisionTree.data;

import ta.stc_decisionTree.model.MateriInference;

/**
 *
 * @author ami
 */
public class RuleMateriInference {
    public final static MateriInference inferenceDasarJava = new MateriInference(
            NamaMateri.DASAR_JAVA,
                new String[]{
                    NamaMateri.DASAR_JAVA
                }
            );
    public final static MateriInference inferenceKelasMethod = new MateriInference(
            NamaMateri.KELAS_METHOD,
                new String[]{
                    NamaMateri.KELAS_METHOD
                }
            );
    public final static MateriInference inferenceVariable = new MateriInference(
            NamaMateri.VARIABEL,
                new String[]{
                    NamaMateri.VARIABEL
                }
            );
    public final static MateriInference inferenceTDP = new MateriInference(
            NamaMateri.TIPE_DATA_PRIMITIF,
                new String[]{
                    NamaMateri.TIPE_DATA_PRIMITIF
                }
            );
    public final static MateriInference inferenceTDO = new MateriInference(
            NamaMateri.TIPE_DATA_OBJEK,
                new String[]{
                    NamaMateri.TIPE_DATA_OBJEK
                }
            );
    public final static MateriInference inferenceArray = new MateriInference(
            NamaMateri.ARRAY,
                new String[]{
                    NamaMateri.ARRAY
                }
            );
    public final static MateriInference inferenceLogika = new MateriInference(
            NamaMateri.LOGIKA,
                new String[]{
                    NamaMateri.LOGIKA
                }
            );
    public final static MateriInference inferenceOPAritmatika = new MateriInference(
            NamaMateri.OP_ARITMATIKA,
                new String[]{
                    NamaMateri.OP_ARITMATIKA
                }
            );
    public final static MateriInference inferenceOPIncDec = new MateriInference(
            NamaMateri.OP_INCREMENT_DECREMENT,
                new String[]{
                    NamaMateri.OP_INCREMENT_DECREMENT
                }
            );
    public final static MateriInference inferenceOPRelasi = new MateriInference(
            NamaMateri.OP_RELASI,
                new String[]{
                    NamaMateri.OP_RELASI
                }
            );
    public final static MateriInference inferenceOPLogika = new MateriInference(
            NamaMateri.OP_LOGIKA,
                new String[]{
                    NamaMateri.OP_LOGIKA
                }
            );
    public final static MateriInference inferenceOPPredence = new MateriInference(
            NamaMateri.OP_PREDENCE,
                new String[]{
                    NamaMateri.OP_PREDENCE
                }
            );
    public final static MateriInference inferencePerulangan = new MateriInference(
            NamaMateri.PERULANGAN,
                new String[]{
                    NamaMateri.PERULANGAN
                }
            );
    public final static MateriInference inferenceIfElse = new MateriInference(
            NamaMateri.IF_ELSE,
                new String[]{
                    NamaMateri.IF_ELSE
                }
            );
    public final static MateriInference inferenceSwitchCase = new MateriInference(
            NamaMateri.SWITCH_CASE,
                new String[]{
                    NamaMateri.SWITCH_CASE
                }
            );
    public final static MateriInference inferenceFlowchart = new MateriInference(
            NamaMateri.FLOWCHART,
                new String[]{
                    NamaMateri.FLOWCHART
                }
            );
    public final static MateriInference[] getMateriInference = {
        inferenceArray,
        inferenceDasarJava,
        inferenceFlowchart,
        inferenceIfElse,
        inferenceKelasMethod,
        inferenceLogika,
        inferenceOPAritmatika,
        inferenceOPIncDec, 
        inferenceOPPredence, 
        inferenceOPRelasi, 
        inferencePerulangan, 
        inferenceSwitchCase,
        inferenceTDO, 
        inferenceTDP, 
        inferenceVariable
    };
}
