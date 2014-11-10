package ta.stc_decisionTree.pengujian;

import controller.Pedagogik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import ta.stc_decisionTree.data.Database;
import ta.stc_decisionTree.util.QueryToDB;

/**
 *
 * @author Ami
 */
public class Pengujian {
    public static void main(String[] args){
        Pedagogik pg = new Pedagogik();
        LinkedList<String> dataUser = dataUserPengujian();
        for(String user : dataUser){
            pg.addWeakToDB(user);
        }
    }
    
    public static LinkedList<String> dataUserPengujian(){
        LinkedList<String> dataUser = new LinkedList<>();
        String queryUser = "select iduser from datauser order by iduser";
        try{
            QueryToDB query = new QueryToDB(Database.NAME);
            ResultSet rs = query.querySelect(queryUser);
            while(rs.next()){
                dataUser.add(rs.getString("iduser"));
            }
        }catch(SQLException e){}
        return dataUser;
    }
}
