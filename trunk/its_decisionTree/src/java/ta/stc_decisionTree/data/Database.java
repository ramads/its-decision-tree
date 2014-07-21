package ta.stc_decisionTree.data;
/**
 * class yang menyimpan data dari database(nama, tabel2 yg ada)
 * @author arin
 */
public class Database{
    public static final String NAME = "tugasakhir";
    
    /*
     * sub class dari Class Database yang menyimpan nama2 tabel
     */
    public static class Table{
        public static final String TABLE_DAILYTEST = "dailytest";
        public static final String TABLE_QUETION_PROB = "questionprob";
        public static final String TABLE_STUDENT_MODEL = "studentmodel";
        public static final String TABLE_DATA_USER = "datauser";
        public static final String TABLE_PRETEST_QUESTION = "pretestquestion";
        public static final String TABLE_PRETEST_RESULT = "pretestresult";
        public static final String TABLE_POSTTEST_RESULT = "posttest_result";
        public static final String TABLE_PRETEST_LOG = "pretestlog";
        public static final String TABLE_QUESTIONS = "questions";
        public static final String TABLE_LESSON= "lesson";
        public static final String TABLE_LESSON_SUB = "lesson_sub";
        public static final String TABLE_POSTTEST_QUESTIONS = "posttest_question";
        public static final String TABLE_COURSE_MATERIAL = "course_material";
        public static final String TABLE_MATERIAL_OBSERVATION = "material_obervation";
        public static final String TABLE_KD_PROBABILITIES = "kd_probabilities";
        public static final String TABLE_SM_PROBABILITIES = "sm_probabilities";
    }
}
