package model.repository.data;

public class HighPoint {
   // 13 8 7 13	7  8 4 5 5 5 7	9 6 7 7 3

//    private static final double HP_BASEJAVA = 18.0;
//    private static final double HP_CLASS_METHOD = 8.0;
//    private static final double HP_VARIABLE = 7.0;
//    private static final double HP_TIPE_DATA_PRIMITIVE = 16.0;
//    private static final double HP_TIPE_DATA_OBJECT = 10.0;
//    private static final double HP_ARRAY = 9.0;
//    private static final double HP_LOGIKA = 3.0;
//    private static final double HP_OP_ARITMATIKA = 7.0;
//    private static final double HP_OP_INC_DEC = 6.0;
//    private static final double HP_OP_RELATIONAL = 8.0;
//    private static final double HP_OP_LOGIKA = 9.0;
//    private static final double HP_OP_PREDENCE = 10.0;
//    private static final double HP_LOOPING = 8.0;
//    private static final double HP_IF_ELSE = 8.0;
//    private static final double HP_SWITCH_CASE = 8.0;
//    private static final double HP_FLOWCHART = 4.0;
    
    private static final double HP_BASEJAVA = 17.0;//18.0;
    private static final double HP_CLASS_METHOD = 8.0; //6.0;
    private static final double HP_VARIABLE = 7.0; //7.0;
    private static final double HP_TIPE_DATA_PRIMITIVE = 13.0;//16.0;
    private static final double HP_TIPE_DATA_OBJECT = 7.0; //10.0;
    private static final double HP_ARRAY = 8.0; //9.0;
    private static final double HP_LOGIKA = 3.0;//3.0;
    private static final double HP_OP_ARITMATIKA = 5.0;//7.0;
    private static final double HP_OP_INC_DEC = 5.0; //6.0;
    private static final double HP_OP_RELATIONAL = 4.0; //8.0;
    private static final double HP_OP_LOGIKA = 7.0; //9.0;
    private static final double HP_OP_PREDENCE = 10.0; //7.0;
    private static final double HP_LOOPING = 6.0; //8.0;
    private static final double HP_IF_ELSE = 8.0;//8.0;
    private static final double HP_SWITCH_CASE = 7.0;//8.0;
    private static final double HP_FLOWCHART = 3.0;//4.0;
    
    public static double getHighPoint(String modulName){
        if(modulName.equals("baseJava")) return HP_BASEJAVA/2.0;
        if(modulName.equals("classNmethod")) return HP_CLASS_METHOD/2.0;
        if(modulName.equals("variable")) return HP_VARIABLE/2.0;
        if(modulName.equals("pdt")) return HP_TIPE_DATA_PRIMITIVE/2.0;
        if(modulName.equals("odt")) return HP_TIPE_DATA_OBJECT/2.0;
        if(modulName.equals("array")) return HP_ARRAY/2.0;
        if(modulName.equals("logic")) return HP_LOGIKA/2.0;
        if(modulName.equals("opArithmetic")) return HP_OP_ARITMATIKA/2.0;
        if(modulName.equals("opIncNdec")) return HP_OP_INC_DEC/2.0;
        if(modulName.equals ("opRelation")) return HP_OP_RELATIONAL/2.0;
        if(modulName.equals ("opLogic")) return HP_OP_LOGIKA/2.0;
        if(modulName.equals ("opPredence")) return HP_OP_PREDENCE/2.0;
        if(modulName.equals ("looping")) return HP_LOOPING/2.0;
        if(modulName.equals ("ifElse")) return HP_IF_ELSE/2.0;
        if(modulName.equals ("switchCase")) return HP_SWITCH_CASE/2.0;
        if(modulName.equals ("flowchart")) return HP_FLOWCHART/2.0;
        
        return 0.0;
    }
}
