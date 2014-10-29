
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import ta.stc_decisionTree.data.Database;
import ta.stc_decisionTree.util.QueryToDB;

/**
 *
 * @author Arin
 */
public class Pretest {
   QueryToDB q;
   private String question="";
   private String answer="";
   private String CorrectAns="";
   public String location="";
   private int idType;
   private int idQuestion;
    Vector<Boolean> result = new Vector();
   public ArrayList<String> pretestResult = new ArrayList();

 
    public Pretest(){  
        q= new QueryToDB(Database.NAME);
        idType=1;  
    }
    /*
    mengeset id soal
    */
    public void setIdQuestion(int idT) {
       idQuestion=rand(idT);
    }
    
    //mengambil id soal yang telah di set
    public int getIdQuestion() {
        return idQuestion;
    }

    //mengeset id type soal
    public void setIdType(int idType) {
        this.idType = idType;
    }
    
    //mengambil id soal yang telah di set
    public int getIdType() {
        return idType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
   
   /*
    berfungsi untuk mengambil soal dari database sesuai dengan type soal
    */
   public String getQuestion(int idType, int idQuest) throws SQLException{           
       String query= ("select question from  "+Database.Table.TABLE_PRETEST_QUESTION+" where idtype = '"+idType+"' AND idquest = '"+idQuest+"'");
       ResultSet rs = q.querySelect(query);
         while(rs.next()){
            question=rs.getString("question");
        }
         return question;
    }
     
   
   /**
    * mengecek hasil jawaban user apakah sesuai dengan jawaban yang benar dari database
    * @param pil -> pilihan jawaban dari user
    * @param idType -> id type soal yang diberikan ke user 
    * @param idQuest -> id soal yang diberikan ke user
    * @return true atau false -> true berarti user menjawab dengan benar dan false user menjawab salah
    * @throws SQLException 
    */
    public String getDBAnswer(char pil, int idType, int idQuest) throws SQLException{      
       String query= ("select ans"+pil+" from "+Database.Table.TABLE_PRETEST_QUESTION+" where idtype = '"+idType+"' AND idquest = '"+idQuest+"'");
       ResultSet rs = q.querySelect(query);
         while(rs.next()){
            answer=rs.getString("ans"+pil);
        }
         return answer;
    }
 
    /**
     * mengambil soal dengan idType tertentu secara random dan akan di tampilkan ke user
     * @param idT -> id type soal
     * @return 
     */
    public int rand(int idT){
       Random r = new Random();  
       int Low = ((idT*2) + (idT-2));
        int High = idT*3;
  
        int R = r.nextInt ((High+1)-Low) + Low;
        System.out.print("Low :"+Low+","+" high :"+High+ "    ");
       return R;
    }
    
    /**
     * membuat log/menyimpan hasil jawaban user ke database
     * @param idpretest -> id pretest user
     * @param id -> 
     * @param flag -> sebagai penanda apakah log untuk pretest atau posttest
     * @param idtype
     * @param idquest
     * @param userAns
     * @param result 
     */
    public void addPretestLogTest(String idpretest, String id, int idtype, int  idquest, String userAns, boolean result){
        String query= "insert into "+Database.Table.TABLE_PRETEST_RESULT+" (idpretest, iduser, flag, idtype, idquest, userAns, result) "+ "Values(?,?,?,?,?,?,?)";
        q.insertToDB(idpretest,"string",id, "string", idtype, "int", idquest, "int", userAns, "string", result, "boolean", query);
    }
    
    /**
     * mengambil data log pretest user yang telah melakukan pretest
     * @return hasil query log pretest user
     * @throws SQLException 
     */
    public ResultSet getDataTestUser() throws SQLException{           
       String query= ("select * from  "+Database.Table.TABLE_PRETEST_RESULT);
       ResultSet rs = q.querySelect(query);
         return rs;
    }
     
   public ResultSet getDBKey(int idType, int idQuest) throws SQLException{           
       String query= ("select corectAns from "+Database.Table.TABLE_PRETEST_QUESTION+" where idtype = '"+idType+"' AND idquest = '"+idQuest+"'");
       ResultSet rs = q.querySelect(query);
         return rs;
    }  
   
     
    public ResultSet getPResult(String idx) throws SQLException{           //----------------------
       String query= ("select result from  "+Database.Table.TABLE_PRETEST_RESULT+" where idpretest = '"+idx+"'");
       ResultSet rs = q.querySelect(query);
         return rs;
    }      
     
      public boolean getPretestLog(String idu) throws SQLException{
            String query= ("select iduser from "+Database.Table.TABLE_PRETEST_LOG+" where iduser = '"+idu+"'");
            ResultSet rs = q.querySelect(query);      
         return rs.next();
    }
     public void addPretestLog(String idu) throws SQLException{
            String query= ("insert into "+Database.Table.TABLE_PRETEST_LOG+" (iduser) "+ "Values(?)");
            q.insertToDB(idu, "string", query);
    } 
}