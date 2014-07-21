/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import ta.stc_decisionTree.data.Database;
import model.repository.dbconnection.QueryToDB;

/**
 *
 * @author Arin
 */
public class PostTest {
    QueryToDB queryDB;
  
    public PostTest(){  
        queryDB = new QueryToDB(Database.NAME);
       
    }
    
    public ResultSet getidPostQuest(String idl) throws SQLException{           
       String query= ("select idpostquestion from  "+Database.Table.TABLE_POSTTEST_QUESTIONS+" where idlesson = '"+idl+"'");
       ResultSet rs = queryDB.querySelect(query);
      
        return rs;
    }
    
    public String getQuestion(String idp) throws SQLException{           
       String query= ("select postquestion from  "+Database.Table.TABLE_POSTTEST_QUESTIONS+" where idpostquestion = '"+idp+"'");
       ResultSet rs = queryDB.querySelect(query);
       String question="";
         while(rs.next()){
            question=rs.getString("postquestion");
        }
         return question;
    }
      
    public String getDBAnswer(char pil, String idp) throws SQLException{      
       String query= ("select postans"+pil+" from "+Database.Table.TABLE_POSTTEST_QUESTIONS+" where idpostquestion = '"+idp+"'");
       ResultSet rs = queryDB.querySelect(query);
       String answer="";
         while(rs.next()){
            answer=rs.getString("postans"+pil);
        }
         return answer;
    }
    
    public String getCorrectAnswer(String idp) throws SQLException{      
       String query= ("select postcorectAns from "+Database.Table.TABLE_POSTTEST_QUESTIONS+" where idpostquestion = '"+idp+"'");
       ResultSet rs = queryDB.querySelect(query);
       String correctAnswer="";
         while(rs.next()){
            correctAnswer=rs.getString("postcorectAns");
        }
         return correctAnswer;
    }
    
    public boolean hadPostest(String idu) throws SQLException{
        boolean check=false;
            String query= ("select iduser from  "+Database.Table.TABLE_COURSE_MATERIAL+" where iduser = '"+idu+"'");
            ResultSet rs = queryDB.querySelect(query);
            check=rs.next();
            
            return check;
    }
    
    public void cekLastPostTest(String idUser){
        String idCourse = getLastIdCourse(idUser);;
        int jml = 0;
        String sqlCek = "select count(id_posttest) as jml "
                + "from posttest_result where idCourse = '"+idCourse+"'";
        ResultSet rs2 = queryDB.querySelect(sqlCek);
        try{
            if(rs2.next()){
                jml = rs2.getInt("jml");
            }else{
                jml = 0;
            }
        } catch(SQLException e){}
        if (jml!=15){
            String sqlDel = "delete from posttest_result where idCourse = '"+idCourse+"'";
            queryDB.executeQuery(sqlDel);
        }
    }
    
    public void addPosttestResult(String idUser, String idCourse, int idType, int idQuestion, String userAnswer, boolean result){
        String sql = "insert into "+Database.Table.TABLE_POSTTEST_RESULT
                +" (id_user, idCourse, idType, idQuestion, userAnswer, result) "
                +" Values (?,?,?,?,?,?)";
        queryDB.insertToDB( 
                idUser, "string", 
                idCourse, "string", 
                idType, "int", 
                idQuestion, "int", 
                userAnswer, "string", 
                result, "boolean", 
                sql);
    }
    
    public String getLastIdCourse(String idUser){
        String idCourse = "";
        String sqlIDcourse = "select idcoursemat from "
                +Database.Table.TABLE_COURSE_MATERIAL
                +" where iduser = '"+idUser+"' "
                + "and post_date in ("
                + "select max(post_date) from "
                +Database.Table.TABLE_COURSE_MATERIAL
                +" where iduser='"+idUser+"')";
        ResultSet rs1 = queryDB.querySelect(sqlIDcourse);
        try {
            if(rs1.next()){
                idCourse = rs1.getString("idcoursemat");
            }
        } catch (SQLException ex) {}
        return idCourse;
    }
    
    public ResultSet getLastPostResult(String idUser){
        String idCourse = getLastIdCourse(idUser);
        String query = "select result from "
                +Database.Table.TABLE_POSTTEST_RESULT
                +" where idCourse='"
                +idCourse+"' order by id_posttest limit 0,15";
        return queryDB.querySelect(query);
    }
}
