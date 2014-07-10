
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import model.repository.data.Database;
import model.repository.dbconnection.QueryToDB;

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
    
    public void setIdQuestion(int idT) {
       idQuestion=rand(idT);
    }
    
     public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
    
    public int getIdType() {
        return idType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
       
   public String getQuestion(int idType, int idQuest) throws SQLException{           
       String query= ("select question from  "+Database.Table.TABLE_PRETEST_QUESTION+" where idtype = '"+idType+"' AND idquest = '"+idQuest+"'");
       ResultSet rs = q.querySelect(query);
         while(rs.next()){
            question=rs.getString("question");
        }
         return question;
    }
     
    public String getDBAnswer(char pil, int idType, int idQuest) throws SQLException{      
       String query= ("select ans"+pil+" from "+Database.Table.TABLE_PRETEST_QUESTION+" where idtype = '"+idType+"' AND idquest = '"+idQuest+"'");
       ResultSet rs = q.querySelect(query);
         while(rs.next()){
            answer=rs.getString("ans"+pil);
        }
         return answer;
    }
 
    public int rand(int idT){
       Random r = new Random();  
       int Low = ((idT*2) + (idT-2));
        int High = idT*3;
  
        int R = r.nextInt ((High+1)-Low) + Low;
        System.out.print("Low :"+Low+","+" high :"+High+ "    ");
       return R;
    }
    
    public void addPretestLogTest(String idpretest, String id, String flag, int idtype, int  idquest, String userAns, boolean result){
        String query= "insert into "+Database.Table.TABLE_PRETEST_RESULT+" (idpretest, iduser, flag, idtype, idquest, userAns, result) "+ "Values(?,?,?,?,?,?,?)";
        q.insertToDB(idpretest,"string",id, "string", flag,"string", idtype, "int", idquest, "int", userAns, "string", result, "boolean", query);
    }
    
//    public void addResultTestTemp(String idpretest, String iduser, String flag, int idtype, int  idquest, String userAns, boolean result){
//        String query= "insert into "+Database.Table.TABLE_PRETEST_RESULT_TEMP+" (idpretest,iduser, flag, idtype, idquest, userAns, result) "+ "Values(?,?,?,?,?,?,?)";
//        q.insertToDB(idpretest,"string",iduser, "string", flag,"string", idtype, "int", idquest, "int", userAns, "string", result, "boolean", query);
//    }
    
    
//    public Boolean getResultTestTempSize(String id) throws SQLException{
//       String query= ("select * from  "+Database.Table.TABLE_PRETEST_RESULT_TEMP+" where iduser = '"+id+"'");
//       ResultSet rs = q.querySelect(query);
//       boolean check=false;
//       int i=0;
//       
//       while(rs.next()){     
//           i++;
//           if(i==14) return true;
//        }
//         return false;
//    }
    
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