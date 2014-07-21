package ta.stc_decisionTree.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import ta.stc_decisionTree.data.Database;

/**
 *
 * @author rama
 */
public class KoneksiData {
    private boolean nothing_error=true;
    private String comment;
    private String url = "jdbc:mysql://localhost/"+Database.NAME;
    
    //constructor
    public KoneksiData (){}
    
    private Connection setKoneksi (){
        Statement stat;
        //untuk mengeksekusi query
        Connection koneksi=null;
         try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi=DriverManager.getConnection(url,"root","");
            nothing_error&=true;
        }catch(ClassNotFoundException e){
            nothing_error&=false;
            System.out.println(e.getMessage());
        }catch(SQLException ex){
            System.out.println("error found : "+ex.getMessage());
        }
        return koneksi;
    }
    
    private void tutupKoneksi(Connection c, Statement s){
        try {
            s.close();
            c.close();
            
            //conect database
            //System.out.println("connection closed");
        } catch (SQLException ex) {
            System.out.println("error found : "+ex.getMessage());
        }
    }
    
    public ResultSet querySelect(String sQuery){
        ResultSet result = null;
        try{
            Connection c = this.setKoneksi();
            Statement s = c.createStatement();
            result = s.executeQuery(sQuery);          
        }catch(SQLException e){
            System.out.println("error di query \""+sQuery+"\"");
        }
        return result;
    }
  
    public LinkedList querySelect(String sQuery, String[] s){ 
        ResultSet result;
        LinkedList list = new LinkedList();
        try{
            Connection c = this.setKoneksi();
            Statement stat=c.createStatement();
            result = stat.executeQuery(sQuery);
            while (result.next()){
                LinkedList list2 = new LinkedList();
                for (String item : s) {
                    list2.add(result.getString(item));
                }
                list.add(list2);
            }
            this.tutupKoneksi(c,stat);
        }catch(SQLException ex){
            javax.swing.JOptionPane.showMessageDialog(null, "sorry, "+ex.getMessage(), "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return list;
    }
    
    public boolean eksekusiQuery(String query){
        try{
            Connection c = this.setKoneksi();
            PreparedStatement ps=(PreparedStatement) c.prepareStatement(query);         
            int i = ps.executeUpdate(query);
            this.tutupKoneksi(c, ps);
            return true;
        }catch(SQLException ex){
            System.out.println(""+ex.getMessage());
            return false;
        }
    }
}
