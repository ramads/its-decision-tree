/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;
import model.network.KnowledgeDomain;
import model.node.Node;
import model.repository.data.Database;
import model.repository.dbconnection.QueryToDB;
import ta.stc_decisionTree.controller.StudentModel;
import ta.stc_decisionTree.data.MateriLevel;

/**
 *
 * @author Arin
 */
public class Pedagogik {
  QueryToDB queryDB;
  public Vector<Node> material; 
  double lowestProb;
  String lowestNode;
  double treshold=0.4;
  boolean checking;
    
    public Pedagogik(){  
        queryDB = new QueryToDB(Database.NAME);
    }
    
    public boolean getBool(String data){
        if(data.equals("0")) return false;
         return true;
    }
    
    public Vector<Boolean> getPretestResultasIsUser(String iduser){  //dipanggil buat test
        Vector<Boolean> result = new Vector<Boolean>();
        String query= ("select result from "+Database.Table.TABLE_PRETEST_RESULT+" where iduser = '"+iduser+"' order by idtype asc");
        ResultSet rs = queryDB.querySelect(query);
        int i=0;
        try {
            while(rs.next()){
                String res=rs.getString("result");         
                System.out.print("No."+(i+1)+" Result = ");           
                result.add(Boolean.valueOf(getBool(res)));               
                System.out.println(result.elementAt(i));
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("Error in getPretestResultasIsuer because: "+ex.getMessage());
        }
        return result;
     }   
     
/*     
    public Vector<Node> getWeakFromSM(String idUser){
        StudentModel model = new StudentModel();
        Vector<Boolean> result = getPretestResultasIsUser(idUser);
        return model.getWeakNode(result);
    }
*/
    
    public LinkedList<String> getWeakFromSM(String idUser){
        StudentModel model = new StudentModel();
        Vector<Boolean> resultTes = getPretestResultasIsUser(idUser);
        return model.getWeakMaterial(resultTes);
    }
    
    public boolean isUserExist(String iduser){
        String query = "select * from "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" where iduser='"+iduser+"'";
        try{
            ResultSet rs = queryDB.querySelect(query);
            return rs.next();
        }catch(SQLException ex){
            System.out.println("Error in method isUserExist because : "+ex.getMessage());
        }
        return false;
    } 

/*    
    public void addWeakToDB(String iduser){
        if(!isUserExist(iduser)){
            String query = "insert into "+Database.Table.TABLE_MATERIAL_OBSERVATION
                    +" (iduser, lesson_name, observation, flag) values (?,?,?,?)";
            Vector<Node> weaks = getWeakFromSM(iduser);
            for(Node i: weaks){
                String lesson_name = i.get_name();
                q.insertToDB(iduser, "string", lesson_name, "string", false, "boolean", "weak", "string", query);
            }
        }
    }
*/
    public void addWeakToDB(String idUser){
        if(!isUserExist(idUser)){
            String query = "insert into "+Database.Table.TABLE_MATERIAL_OBSERVATION
                    +" (iduser, lesson_name, observation, flag) values (?,?,?,?)";
            LinkedList<String> weaks = getWeakFromSM(idUser);
            for(String lesson_name:weaks){
                queryDB.insertToDB(idUser, "string", lesson_name, "string", false, "boolean", "weak", "string", query);
            }
        }
    }
    
    public void setDomainObserv(String iduser, KnowledgeDomain domain){
        String query = "select lesson_name, observation from "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" where iduser='"+iduser+"'";
        try{
            ResultSet rs = queryDB.querySelect(query);
            while(rs.next()){
                String lesson_name = rs.getString("lesson_name");
                boolean observ = rs.getBoolean("observation");
                String observ_value;
                if (observ) observ_value="true";
                else  observ_value="false";
                domain.setObserv(lesson_name, observ_value);
            }
        }catch(SQLException ex){
            System.out.println("Error in setDomainObservation because : "+ex.getMessage());
        }
    }
    
    public void updateMatObserv(String idUser, String lesson_name, boolean observation) throws SQLException{
        boolean oldObserv;
        String queryUpdate = "update "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" set observation=? where iduser = '"+idUser+"' and lesson_name = '"+lesson_name+"'";
        String querySelect = "select observation from "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" where iduser = '"+idUser+"' and lesson_name = '"+lesson_name+"'";
        String queryAdd = "insert into "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" (iduser, lesson_name, observation, flag) "+"Values(?,?,?,?)";
        ResultSet rs = queryDB.querySelect(querySelect);
        try {
            if(rs.next()){
                oldObserv = rs.getBoolean("observation");
                if (oldObserv!=observation){
                    queryDB.insertToDB(observation, "boolean", queryUpdate);
                }
            }else{
                queryDB.insertToDB(idUser, "string", lesson_name, "string", observation, "boolean", "weakparent","string", queryAdd);
            }
        } catch (SQLException ex) {
            System.out.println("Error in updateMatObserv because : "+ex.getMessage());
        }
    }
    
    public Vector<String> getWeak(String iduser){
        String query = "select lesson_name from "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" where iduser='"+iduser+"' and flag='weak'";
        Vector<String> weak = new Vector<String>();
        try{
            ResultSet rs = queryDB.querySelect(query);
            while(rs.next()){
                weak.add(rs.getString("lesson_name"));
            }
        }catch(SQLException ex){
            System.out.println("Error in getWeak because : "+ex.getMessage());
        }
        return weak;
    }

/*    
    public String getLearnMaterial(String iduser){   //-------------------------------- sebelum posttest
        lowestProb = 2;
        lowestNode = "";
        checking = false;
        KnowledgeDomain domain = new KnowledgeDomain();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        tampilKd(domain);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        setDomainObserv(iduser, domain);
        System.out.println("******************************************************************************************************************");
        tampilKd(domain);
        System.out.println("******************************************************************************************************************");
        Vector<Node> weakNode = domain.getWeakNode(getWeak(iduser));
        for(Node i:weakNode){
            getProbMinParent(i);   //
        }
        
        if(lowestNode.equals("")){
            for(Node i:weakNode){
                getProbMinParentFalse(i);
            }
            if(lowestNode.equals("")){
                for(Node i:weakNode){
                    if(i.getBelief()==0.0){
                        lowestNode=i.get_name();
                    }
                }
            }
        }
        System.out.println("\n\nProb terkecil pada node "+lowestNode+" dengan prob = "+lowestProb);
        return lowestNode;  
    }
*/
    
    public String getLearnMaterial(String idUser){
        String learnMaterial = "";
        int lowerLevel = 9999;
        
        String query = "select lesson_name from "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" where iduser='"+idUser+"' and flag='weak'";
        String[] listLevel = MateriLevel.LIST_LEVEL;
        ResultSet rs = queryDB.querySelect(query);
        try {
            while(rs.next()){
                for(int i=0;i<listLevel.length;i++){
                    String nama_materi = rs.getString("lesson_name");
                    if(nama_materi.equals(listLevel[i])){
                        if(i<lowerLevel){
                            lowerLevel = i;
                            learnMaterial = nama_materi;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error di get learn material: "+ex.getMessage());
        }
        return learnMaterial;
    }
    
    public void getProbMinParent(Node node){ //rekursif untuk mencari parent yang prob nya lebih kecil dari th
        if(!node.hasParent()){
            //System.out.println("--prob node "+node.get_name()+" : "+node.getBelief());
            System.out.println("nilai prob parent dari "+node.get_name()+": "+node.getBelief());
            if((node.getBelief()>0.0 && node.getBelief()<treshold) && node.getBelief()<lowestProb){
              //  System.out.println(node.get_name()+" masuk");
                lowestProb = node.getBelief();
                lowestNode = node.get_name();
            }
        }else{
            for(Node parent:((Vector<Node>) node.get_parents())){
                getProbMinParent(parent);
            }
            System.out.println("nilai prob parent dari "+node.get_name()+": "+node.getBelief());
            //System.out.println("--prob node "+node.get_name()+" : "+node.getBelief());
            if((node.getBelief()>0.0 && node.getBelief()<treshold) && node.getBelief()<lowestProb){
             //   System.out.println(node.get_name()+" masuk");
                lowestProb = node.getBelief();
                lowestNode = node.get_name();
            }
        }
    }
    
    public void getProbMinParentFalse(Node node){ //rekursif untuk mencari parent bernilai false yang akan diajarkan
        if(!node.hasParent()){
            //System.out.println("--prob node parent false "+node.get_name()+" : "+node.getBelief());
            if(node.getBelief()==0.0 && checking==false){
             //   System.out.println(node.get_name()+" masuk");
                lowestProb = node.getBelief();
                lowestNode = node.get_name();
                checking = true;
            }
        }else{
            for(Node parent:((Vector<Node>) node.get_parents())){
                getProbMinParentFalse(parent);
            }
           // System.out.println("--prob node parent false "+node.get_name()+" : "+node.getBelief());
            if(node.getBelief()==0.0 && checking==false){
             //   System.out.println(node.get_name()+" masuk");
                lowestProb = node.getBelief();
                lowestNode = node.get_name();
                checking=true;
            }
        }
    }
    
    public void tampilKd(KnowledgeDomain domain){
        domain.tampilNodeKD();
    }
}
