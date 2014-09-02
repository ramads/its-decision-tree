/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import ta.stc_decisionTree.data.Database;
import ta.stc_decisionTree.util.QueryToDB;

/**
 *
 * @author Arin
 */
public class DataUser {
   QueryToDB q;    
     
   public DataUser(){
        q = new QueryToDB(Database.NAME);
    }

   public ResultSet getDataUser() throws SQLException{           
       String query= ("select * from "+Database.Table.TABLE_DATA_USER);
       ResultSet rs = q.querySelect(query);
         return rs;
    }
   
   public String getName(String userID) throws SQLException{    
       String query= ("select name from "+Database.Table.TABLE_DATA_USER+" where iduser = '"+userID+"'");
       String userName = "";
       ResultSet rs = q.querySelect(query);
         while(rs.next()){
            userName=rs.getString("name");
        }
         return userName;
    }
    
   public String getPassword(String userID) throws SQLException{   
       String query= ("select password from "+Database.Table.TABLE_DATA_USER+" where iduser = '"+userID+"'");
       String userPassword = "";
       ResultSet rs = q.querySelect(query);
         while(rs.next()){
            userPassword=rs.getString("password");
        }
         return userPassword;
    }
   
     public boolean checkIDuser(String id) throws SQLException {
        String query= "select * from "+Database.Table.TABLE_DATA_USER+" where iduser = '"+id+"'";
        ResultSet r = q.querySelect(query);
        return r.next();
    }
    
     public boolean checkUser(String id, String pass) throws SQLException {
        String query= "select * from "+Database.Table.TABLE_DATA_USER+" where iduser = '"+id+"' and password = '"+pass+"'";
        ResultSet r = q.querySelect(query);
        return r.next();
    }
   
    public boolean checkUserName(String name) throws SQLException {
        String query= "select * from "+Database.Table.TABLE_DATA_USER+" where name = '"+name+"'";
        ResultSet r = q.querySelect(query);
        return r.next();
    }
    
    public boolean checkUserPass(String pass) throws SQLException {
        String query= "select * from "+Database.Table.TABLE_DATA_USER+" where password = '"+pass+"'";
        ResultSet r = q.querySelect(query);
        return r.next();
    }
     
    public void addDataUser(String id, String name, String pass){
        String query= "insert into "+Database.Table.TABLE_DATA_USER+" (iduser,name,password) "+ "Values(?,?,?)";
        q.insertToDB(id, "string", name, "string", pass, "string", query);
    }
     
   public boolean checkValid(String id, String nama, String password){
       boolean check=false;
       
       if(id.length()>=5&&nama.length()>=5&&password.length()>=5){
           check=true;
       }
       
       return check;
   } 
   
}
