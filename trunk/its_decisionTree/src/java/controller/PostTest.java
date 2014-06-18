/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import model.repository.data.Database;
import model.repository.dbconnection.QueryToDB;

/**
 *
 * @author Arin
 */
public class PostTest {
    QueryToDB q;
  
    public PostTest(){  
        q= new QueryToDB(Database.NAME);
       
    }
    
    public ResultSet getidPostQuest(String idl) throws SQLException{           
       String query= ("select idpostquestion from  "+Database.Table.TABLE_POSTTEST_QUESTIONS+" where idlesson = '"+idl+"'");
       ResultSet rs = q.querySelect(query);
      
        return rs;
    }
    
    public String getQuestion(String idp) throws SQLException{           
       String query= ("select postquestion from  "+Database.Table.TABLE_POSTTEST_QUESTIONS+" where idpostquestion = '"+idp+"'");
       ResultSet rs = q.querySelect(query);
       String question="";
         while(rs.next()){
            question=rs.getString("postquestion");
        }
         return question;
    }
      
    public String getDBAnswer(char pil, String idp) throws SQLException{      
       String query= ("select postans"+pil+" from "+Database.Table.TABLE_POSTTEST_QUESTIONS+" where idpostquestion = '"+idp+"'");
       ResultSet rs = q.querySelect(query);
       String answer="";
         while(rs.next()){
            answer=rs.getString("postans"+pil);
        }
         return answer;
    }
    
    public String getCorrectAnswer(String idp) throws SQLException{      
       String query= ("select postcorectAns from "+Database.Table.TABLE_POSTTEST_QUESTIONS+" where idpostquestion = '"+idp+"'");
       ResultSet rs = q.querySelect(query);
       String correctAnswer="";
         while(rs.next()){
            correctAnswer=rs.getString("postcorectAns");
        }
         return correctAnswer;
    }
    
    public boolean hadPostest(String idu) throws SQLException{
        boolean check=false;
            String query= ("select iduser from  "+Database.Table.TABLE_COURSE_MATERIAL+" where iduser = '"+idu+"'");
            ResultSet rs = q.querySelect(query);
            check=rs.next();
            
            return check;
    }
}
