package model.network.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.repository.dbconnection.QueryToDB;
import model.repository.dbconnection.QueryToDB;

/*
 * class ITSQuery digunakan untuk membantu keperluan query
 */
public class ITSQuery extends QueryToDB{
    
    public ITSQuery(String database) {
        super(database);
    }
    
    /**
     * 
     * @param database nama database
     */
    public void changeDatabase(String database){
        super.setDatabase(database);
    }
    
    /**
     * untuk mengambil data true atau false dari data nilai materi yg dibagi 2
     * @param cond
     * @return 
     */
    protected String getStateCondition(boolean cond){
        if(cond)
            return ">";
        else
            return "<=";
    }
    
    /**
     * untuk mendapatkan penutup kurung dari query
     * @param a jumlah tutup kurung
     * @return 
     */
    protected String generateParenthesis(int a){
        String parenthesis = "";
        for(int i=0;i<a;i++){
            parenthesis+=")";
        }
        return parenthesis;    
    }
    
    /**
     * untuk mendapatkan jumlah semesta dari node (independent/non independent)
     * @param queryGetSample
     * @return 
     */
    private double getSample(String queryGetSample){
        double count = 0.0;
        try{
            ResultSet rs = super.querySelect(queryGetSample);
            while(rs.next())
                count++;
        }catch(SQLException ex){
            System.out.println("Error in getSample : "+ex.getMessage());
        }
        System.out.println("semesta ====> "+count);
        return count;
    }
    
    /**
     * untuk mendapatkan probability true dari node (independent/non independent)
     * @param queryTrueSample query untuk mendapatkan sample true
     * @param queryGetSample
     * @return 
     */
    public double getProbability(String queryTrueSample, String queryGetSample){
        double countTrue = 0;
        try{
            ResultSet rs = super.querySelect(queryTrueSample);
            while(rs.next())
                countTrue++;
        }catch(SQLException ex){
            System.out.println("Error in getTotalSample : "+ex.getMessage());
        }
        System.out.println("jmlh true ====> "+countTrue);
        double totSample = getSample(queryGetSample);
        if(totSample!=0.0)
            return (countTrue/totSample);
        else
            return 0.0;
    }
}
