/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.KnowledgeDomain;
import model.node.Node;
import model.repository.data.Database;
import model.repository.dbconnection.QueryToDB;
import model.network.StudentModel;

/**
 *
 * @author Arin
 */
public class Lesson {
  QueryToDB q;
  
    public Lesson(){  
        q = new QueryToDB(Database.NAME); 
    }
    
    
    public ResultSet selectWeakFromDB(String idu, String flag) throws SQLException{
        String query= ("select lesson_name from "+Database.Table.TABLE_MATERIAL_OBSERVATION+" where iduser = '"+idu+"' AND observation = "+false+" AND flag = '"+flag+"'");
        ResultSet rs = q.querySelect(query);    
        return rs;
    }
     
    public String getIdTopic (String topic) throws SQLException{  //-----------------------
         String query= ("select idlesson from "+Database.Table.TABLE_LESSON+" where topic = '"+topic+"'");
         ResultSet rs = q.querySelect(query);
         String id="";
         while(rs.next()){
           id=rs.getString("idlesson");
         }
         return id;
    }
    
    public String getIdLessonName (String ln) throws SQLException{  //-----------------------
         String query= ("select idlesson from "+Database.Table.TABLE_LESSON+" where lessonname = '"+ln+"'");
         ResultSet rs = q.querySelect(query);
         String id="";
         while(rs.next()){
           id=rs.getString("idlesson");
        }
         return id;
    }
      
     public String getTopicBaseOnLessonName (String lessonName) throws SQLException{  //--------------------
        String query= ("select topic from "+Database.Table.TABLE_LESSON+" where lessonname= '"+lessonName+"'");
        ResultSet rs = q.querySelect(query);
            String topic="";
        while(rs.next()){
            topic=rs.getString("topic");            
        }
            
         return topic;
    }
    
     public String getLessonName(String idl) throws SQLException{ 
            String query=("select lessonname from "+Database.Table.TABLE_LESSON+" where idlesson = '"+idl+"'");
            ResultSet rs = q.querySelect(query);
            String id="";
            while(rs.next()){
                id=rs.getString("lessonname");
            }
            return id;
    }
    
    public String getTopic(String idl) throws SQLException{
       String query= ("select topic from "+Database.Table.TABLE_LESSON+" where idlesson = '"+idl+"'");
       ResultSet rs = q.querySelect(query);
            String topic="";
        while(rs.next()){
            topic=rs.getString("topic");            
        }
            
         return topic;
    }   
    
     public ResultSet getAllTopic() throws SQLException{
        String query=("select topic from "+Database.Table.TABLE_LESSON);
        ResultSet rs = q.querySelect(query);
       return rs;
    }    
    
     public ResultSet getIDsubLesson(String idl) throws SQLException{ //---------
        String query=("select idsublesson from "+Database.Table.TABLE_LESSON_SUB+" where idlesson in "+
                "(select idlesson from "+Database.Table.TABLE_LESSON+" where idlesson = '"+idl+"' )"); 
//                "order by '"+Database.Table.TABLE_LESSON_SUB+"'.'idsublesson from' ASC");
        ResultSet rs = q.querySelect(query);
        return rs;
    }
     
     public String getsubLesson(String ids) throws SQLException{       //----------------
        String query=("select sublesson from "+Database.Table.TABLE_LESSON_SUB+" where idsublesson = '"+ids+"'");
        ResultSet rs = q.querySelect(query);
        String text="";
         while(rs.next()){
           text=rs.getString("sublesson");
        }
         return text;
    }
     
     public String getLesson(String ids) throws SQLException{ //---------------------------
        String query=("select lesson from "+Database.Table.TABLE_LESSON_SUB+" where idsublesson = '"+ids+"'");
        ResultSet rs = q.querySelect(query);
        String text="";
         while(rs.next()){
           text=rs.getString("lesson");
        }
         return text;
    }
          
     public void addCourseMaterial(String idLearn, String idu, String idl, boolean result){
         String query= "insert into "+Database.Table.TABLE_COURSE_MATERIAL+" (idcoursemat, iduser, idlesson, post_result) "+"Values(?,?,?,?)";
         q.insertToDB(idLearn, "string",idu, "string", idl,"string", result, "boolean", query);
    }
     
     public ResultSet getIdCourseMaterial(String idu) throws SQLException{  //----------------------
//        int i=0, lb=0, lt=0;
//        int totPage=getTotalPage();
//        
//        while(i<totPage){
//            lt=(i+1)*2;
//            lb=(i*2)+i;
//            i++;
//        }
//        
        String query=("select idcoursemat, post_date from "+Database.Table.TABLE_COURSE_MATERIAL+" where idcoursemat like '%"+idu+"' ORDER BY post_date ASC "); 
        ResultSet rs = q.querySelect(query);
        return rs;
    }
     
     public String getCourseMaterial(String idcm) throws SQLException{  //----------------------
        String query=("select topic from "+Database.Table.TABLE_LESSON+" where idlesson in "+
                "(select idlesson from "+Database.Table.TABLE_COURSE_MATERIAL+" where idcoursemat = '"+idcm+"' )"); 
        ResultSet rs = q.querySelect(query);
        String topic="";
         while(rs.next()){
           topic=rs.getString("topic");
        }
         return topic;
    }
     
     public String getCourseMaterialResult(String idcm) throws SQLException{  //----------------------
        String query=("select post_result from "+Database.Table.TABLE_COURSE_MATERIAL+" where idcoursemat = '"+idcm+"'"); 
        ResultSet rs = q.querySelect(query);
        String result="";
         while(rs.next()){
           result=rs.getString("post_result");
        }
         return result;
    }
     
    
    public int getTotalLearn(String idu, String idl) throws SQLException{
        String query=("select idlesson from "+Database.Table.TABLE_COURSE_MATERIAL+" where iduser = '"+idu+"' AND "+"idlesson = '"+idl+"'");
        ResultSet rs = q.querySelect(query);
        String id="";
        int count=1;
         while(rs.next()){
           id=rs.getString("idlesson");
           count++;
        }
         return count;
    }
    
    public void updateMaterialObervation(String id, String lessonName, boolean condition){
        boolean ket;
        String queryUpdate = "update "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" set keterangan=? where iduser = '"+id+"' and lesson_name = '"+lessonName+"'";
        String querySelect = "select keterangan from "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" where iduser = '"+id+"' and lesson_name = '"+lessonName+"'";
        String queryAdd = "insert into "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" (iduser, lesson_name, keterangan) "+"Values(?,?,?)";
        ResultSet rs = q.querySelect(querySelect);
        try {
            if(rs.next()){
                ket = rs.getBoolean("keterangan");
                if (ket!=condition){
                    q.insertToDB(condition, "boolean", queryUpdate);
                }
            }else{
                q.insertToDB(id, "string", lessonName, "string", condition, "boolean", queryAdd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lesson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
     public int getTotalPage()throws SQLException{
        String query= ("select idcoursemat from "+Database.Table.TABLE_COURSE_MATERIAL);
        ResultSet rs = q.querySelect(query);    
        int totalRow=0;
        while(rs.next()){
            totalRow++;
        }  
        
       int i=0;
       int totalPage=0;
        while(i<totalRow){
            totalPage++;
            i+=2;
        }   
        return totalPage;
    }
} 
    