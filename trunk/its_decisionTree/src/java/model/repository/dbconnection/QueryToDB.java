package model.repository.dbconnection;

import java.sql.*;
import java.util.LinkedList;

public class QueryToDB extends DBConnection{
    public Connection conn;
    public String database;
    
    public QueryToDB(String database) {
        this.database = database;
        conn = super.connect(database);
    }
    
    public void setDatabase(String database){
        this.database = database;
    }
    
    public ResultSet querySelect(String query) {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("cannot excute query \""+query+"\" because : "+ex.getMessage());
        }
        return rs;
    }
    
    private void insert(LinkedList<Data> data, String query){
        PreparedStatement ps = null;
        int index = 1;
        try{
            ps = conn.prepareStatement(query);
            for(int i=0;i<data.size();i++){
                Data field = data.get(i);
                if(field.info.equals("int")){
                    int dataConv = (Integer)field.data;
                    ps.setInt(index,dataConv);
                }
                if(field.info.equals("string")){
                    String dataConv = (String)field.data;
                    ps.setString(index,dataConv);
                }
                if(field.info.equals("boolean")){
                    boolean dataConv = (Boolean)field.data;
                    ps.setBoolean(index,dataConv);
                }
                if(field.info.equals("double")){
                    double dataConv = (Double)field.data;
                    ps.setDouble(index,dataConv);
                }
                if(field.info.equals("float")){
                    float dataConv = (Float)field.data;
                    ps.setFloat(index,dataConv);
                }
                field.setNull();
                field = null;
                index++;
            }
            data = null;
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println("error insertDB:\nin query \""+query+"\" because :" + e.getMessage());
        }
    }
    
    public void insertToDB(Object data, String type, String query){
        Data data1 = new Data(data, type);
        LinkedList<Data> linkData= new LinkedList();
        linkData.addLast(data1);
        insert(linkData,query);
    }
    
    public void insertToDB(Object data, String type, 
            Object data1, String type1, String query){
        Data d1 = new Data(data, type);
        Data d2 = new Data(data1, type1);
        LinkedList<Data> linkData= new LinkedList();
        linkData.addLast(d1);
        linkData.addLast(d2);
        insert(linkData,query);
    }
    
    public void insertToDB(Object data, String type, 
            Object data1, String type1, 
            Object data2, String type2, String query){
        Data d1 = new Data(data, type);
        Data d2 = new Data(data1, type1);
        Data d3 = new Data(data2, type2);
        LinkedList<Data> linkData= new LinkedList();
        linkData.addLast(d1);
        linkData.addLast(d2);
        linkData.addLast(d3);
        insert(linkData,query);
    }
    
    public void insertToDB(Object data, String type, 
            Object data1, String type1, 
            Object data2, String type2, 
            Object data3, String type3, String query){
        Data d1 = new Data(data, type);
        Data d2 = new Data(data1, type1);
        Data d3 = new Data(data2, type2);
        Data d4 = new Data(data3, type3);
        LinkedList<Data> linkData= new LinkedList();
        linkData.addLast(d1);
        linkData.addLast(d2);
        linkData.addLast(d3);
        linkData.addLast(d4);
        insert(linkData,query);
    }
    
    public void insertToDB(Object data, String type, 
            Object data1, String type1, 
            Object data2, String type2, 
            Object data3, String type3, 
            Object data4, String type4, String query){
        Data d1 = new Data(data, type);
        Data d2 = new Data(data1, type1);
        Data d3 = new Data(data2, type2);
        Data d4 = new Data(data3, type3);
        Data d5 = new Data(data4, type4);
        LinkedList<Data> linkData= new LinkedList();
        linkData.addLast(d1);
        linkData.addLast(d2);
        linkData.addLast(d3);
        linkData.addLast(d4);
        linkData.addLast(d5);
        insert(linkData,query);
    }
    
    public void insertToDB(Object data, String type, 
            Object data1, String type1, 
            Object data2, String type2, 
            Object data3, String type3, 
            Object data4, String type4, 
            Object data5, String type5, String query){
        Data d1 = new Data(data, type);
        Data d2 = new Data(data1, type1);
        Data d3 = new Data(data2, type2);
        Data d4 = new Data(data3, type3);
        Data d5 = new Data(data4, type4);
        Data d6 = new Data(data5, type5);
        LinkedList<Data> linkData= new LinkedList();
        linkData.addLast(d1);
        linkData.addLast(d2);
        linkData.addLast(d3);
        linkData.addLast(d4);
        linkData.addLast(d5);
        linkData.addLast(d6);
        insert(linkData,query);
    }
    
    public void insertToDB(Object data, String type, 
            Object data1, String type1, 
            Object data2, String type2, 
            Object data3, String type3, 
            Object data4, String type4, 
            Object data5, String type5, 
            Object data6, String type6, String query){
        Data d1 = new Data(data, type);
        Data d2 = new Data(data1, type1);
        Data d3 = new Data(data2, type2);
        Data d4 = new Data(data3, type3);
        Data d5 = new Data(data4, type4);
        Data d6 = new Data(data5, type5);
        Data d7 = new Data(data6, type6);
        LinkedList<Data> linkData= new LinkedList();
        linkData.addLast(d1);
        linkData.addLast(d2);
        linkData.addLast(d3);
        linkData.addLast(d4);
        linkData.addLast(d5);
        linkData.addLast(d6);
        linkData.addLast(d7);
        insert(linkData,query);
    }
    
    public void makeTableEmpty(String table){
        String queryMakeEmpty = "truncate table "+table+"";
        try {
            Statement st = conn.createStatement();
            st.execute(queryMakeEmpty);
            st.close();
        } catch (SQLException ex) {
            System.out.println("error makeTableEmpty in table "+table+":" + ex.getMessage());
        } 
    }
    
    /**
     * 
     * @param table
     * @param field
     * @param condition
     * @param ident
     */
    public void deleteRow(String table, String field, String condition, int ident){
        String queryDelete = "";
        if (ident==0)
             queryDelete = "delete from "+table+" where "+field+" = '"+condition+"'";
        if (ident==1){
            queryDelete = "delete from "+table+" where "+field+" = "+condition+"";
        }
        try {
            Statement st = conn.createStatement();
            st.execute(queryDelete);
            st.close();
        } catch (SQLException ex) {
            System.out.println("error delete in table "+table+":" + ex.getMessage());
        }
    }
    
    public void executeQuery(String query){
        try {
            Statement st = conn.createStatement();
            st.execute(query);
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error execute query : "+query+" Because : "+ex.getMessage());
        }
    }
    
    public void delete(String name){
        
    }
}
