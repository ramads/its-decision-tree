package ta.stc_decisionTree.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String user="root";
    private final String pass="";   
   
     Connection conn;
    
    public Connection getConnection(){
        return conn;
    }
    
    public Connection connect(String db){        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String urlValue="jdbc:mysql://localhost/"+db+"?user="+user+"&password="+pass;
            return DriverManager.getConnection (urlValue);
        }catch(SQLException e){
            System.out.println("Error in Connection: "+e);
            return null;
        }catch(ClassNotFoundException e){
            System.out.println("Driver JDBC Not Found");
            return null;
        }
    }
}
