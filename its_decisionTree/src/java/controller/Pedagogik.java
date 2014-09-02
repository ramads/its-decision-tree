package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;
import ta.stc_decisionTree.data.Database;
import ta.stc_decisionTree.util.QueryToDB;
import ta.stc_decisionTree.controller.StudentModel;
import ta.stc_decisionTree.data.MateriLevel;

/**
 *
 * @author ami
 */
public class Pedagogik {
  QueryToDB queryDB;
    
    public Pedagogik(){  
        queryDB = new QueryToDB(Database.NAME);
    }
    
    public boolean getBool(String data){
      return !data.equals("0");
    }
    
    /**
     * mendapatkan hasil jawaban pretest user
     * @param iduser
     * @return daftar hasil jawaban user
     */
    public Vector<Boolean> getPretestResultasIsUser(String iduser){  //dipanggil buat test
        Vector<Boolean> result = new Vector<Boolean>();
        String query= ("select result from "+Database.Table.TABLE_PRETEST_RESULT+" where iduser = '"+iduser+"' order by idtype asc");
        ResultSet rs = queryDB.querySelect(query);
        int i=0;
        try {
            while(rs.next()){
                String res=rs.getString("result");          
                result.add(getBool(res));
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("Error in getPretestResultasIsuer because: "+ex.getMessage());
        }
        return result;
     }
    
    /**
     * mendapatkan hasil jawaban postest terakhir user
     * @param idUser
     * @return 
     */
    public Vector<Boolean> getPostTestResult(String idUser){
        PostTest postTest = new PostTest();
        Vector<Boolean> result = new Vector<Boolean>();
        String idCourse = postTest.getLastIdCourse(idUser);
        String query = "select result from "+Database.Table.TABLE_POSTTEST_RESULT
                +" where idCourse='"+idCourse+"' order by idType limit 0,15";
        ResultSet rs = queryDB.querySelect(query);
        try{
            while(rs.next()){
                String res = rs.getString("result");
                result.add(getBool(res));
            }
        }catch(SQLException e){}
        return result;
    }
    
    /**
     * mendapatkan weak material setelah user melakukan pretest
     * @param idUser
     * @return materi yang kurang dikuasai olah user
     */
    public LinkedList<String> getWeakFromSM(String idUser){
        //instansiasi objek StudentModel
        //objek ini merupakan objek yang menganalisis model (materi apa saja yang kurang di kuasai) user 
        StudentModel model = new StudentModel();
        
        //mengambil hasil pretest user
        Vector<Boolean> resultTes = getPretestResultasIsUser(idUser);
        
        //mengembalikan daftar materi2 yang kurang dikuasi oleh user
        return model.getWeakMaterial(resultTes);
    }
    
    /**
     * mendapatkan weak material setelah user melakukan posttest
     * @param idUser
     * @return 
     */
    public LinkedList<String> getWeakAfterPostTest(String idUser){
        StudentModel model = new StudentModel();
        Vector<Boolean> resultPosttest = getPostTestResult(idUser);
        return model.getWeakMaterial(resultPosttest);
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

    /**
     * digunakan untuk menginputkan daftar materi2 yang kurang dikuasai oleh user
     * berdasasrkan hasil evaluasi jawaban pretest user
     * @param idUser -> id user yang sedang aktif
     */
    public void addWeakToDB(String idUser){
        //jika user terdaftar
        if(!isUserExist(idUser)){
            String query = "insert into "+Database.Table.TABLE_MATERIAL_OBSERVATION
                    +" (iduser, lesson_name, observation, flag) values (?,?,?,?)";
            
            //untuk mengetahui apa saja materi yang kurang dikuasi oleh user yang aktif
            //akan digunakan fungsi getWeakFromSM
            LinkedList<String> weaks = getWeakFromSM(idUser);
            for(String lesson_name:weaks){
                System.out.println("insert weak: "+lesson_name);
                queryDB.insertToDB(idUser, "string", lesson_name, "string", false, "boolean", "weak", "string", query);
            }
        }
    }
    
    /**
     * fungsi untuk memperbaharui weak material setelah posttest
     * @param idUser 
     */
    public void updateWeakMaterial(String idUser){
        //query delete materi observasi sebelumnya
        String deleteLast = "delete from "+Database.Table.TABLE_MATERIAL_OBSERVATION+" where iduser='"+idUser+"'";
        queryDB.executeQuery(deleteLast);

        //query insert daftar weak material setelah posttest
        String query = "insert into "+Database.Table.TABLE_MATERIAL_OBSERVATION
                +" (iduser, lesson_name, observation, flag) values (?,?,?,?)";
        LinkedList<String> weaks = getWeakAfterPostTest(idUser);
        for(String lesson_name:weaks){
            queryDB.insertToDB(idUser, "string", lesson_name, "string", false, "boolean", "weak", "string", query);
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
    
    //untuk mengambil materi apa yang akan diberikan ke user setelah melakukan pretest atau posttest
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
}
