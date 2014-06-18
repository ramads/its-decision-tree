package model.network.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.network.query.ITSQuery;
import java.util.Vector;
import model.repository.data.Database;
import model.repository.data.HighPoint;
import model.node.Node;


/**
 * class QueryKnowledgeDomain digunakan untuk mengambil probabilitas pada Knowledge Domain
 * class ini mengimplementasikan interface NodeQuery
 * @author arin
 */
public class QueryKnowledgeDomain extends ITSQuery implements NodeQuery{
    public QueryKnowledgeDomain(){
        super(Database.NAME);
    }
    
    /**
     * untuk mendapatkan true probability dari node independent
     * @param node
     * @return 
     */
    @Override
    public double getProbability(Node node) {
        String queryTrueSample = "select id from "+Database.Table.TABLE_DAILYTEST+" where "
                +node.get_name()+" > "+HighPoint.getHighPoint(node.get_name());
        String queryGetSample = "select id from "+Database.Table.TABLE_DAILYTEST;
        
        return super.getProbability(queryTrueSample, queryGetSample);
    }
    
    /**
     * untuk mendapatkan true probability dari non independent node
     * @param node node child
     * @param parentList nodes parent
     * @param stateCondition kondisi dari masing2 variasi state parent
     * @return 
     */
    @Override
    public double getProbability(Node node, Vector<Node> parentList, boolean[] stateCondition) {
        String queryTrueSample = "select id from "
                +Database.Table.TABLE_DAILYTEST+" where "
                +node.get_name()+" > "
                +HighPoint.getHighPoint(node.get_name());
        for(int i=0;i<stateCondition.length;i++){
            queryTrueSample+=" and id in (select id from "
                    +Database.Table.TABLE_DAILYTEST+" where "
                    +parentList.elementAt(i).get_name()+" "
                    +super.getStateCondition(stateCondition[i])+" "
                    +HighPoint.getHighPoint(parentList.elementAt(i).get_name());
        }
        queryTrueSample+=super.generateParenthesis(stateCondition.length);
//        System.out.print("queryTrueSample getProbablity= "+queryTrueSample);
        
        String queryGetSample = "select id from "
                +Database.Table.TABLE_DAILYTEST+" where id in (select id from "
                +Database.Table.TABLE_DAILYTEST+" where "
                +parentList.elementAt(0).get_name()+" "
                +super.getStateCondition(stateCondition[0])+" "
                +HighPoint.getHighPoint(parentList.elementAt(0).get_name());
        for(int i=1;i<stateCondition.length;i++){
            queryGetSample+=" and id in (select id from "
                +Database.Table.TABLE_DAILYTEST+" where "
                +parentList.elementAt(i).get_name()+" "
                +super.getStateCondition(stateCondition[i])+" "
                +HighPoint.getHighPoint(parentList.elementAt(i).get_name());
        }
        queryGetSample+=super.generateParenthesis(stateCondition.length);
//        System.out.print("queryTrueSample getSample= "+queryGetSample);
        return super.getProbability(queryTrueSample, queryGetSample);
    }
    
    @Override
    public double[] getProbFromDB(Node node) {
        ResultSet rs = super.querySelect("select * from "+Database.Table.TABLE_KD_PROBABILITIES+" where node_name='"+
                node.get_name()+"'");
        try{
            if(rs.next()){
                String[] prob = rs.getString("probabilities").split("#");
                double[] probability = new double[prob.length];
                for(int i=0;i<prob.length;i++){
                    probability[i] = Double.parseDouble(prob[i]);
                }
                return probability;
            }
        }catch(SQLException ex){
            System.out.println("Error in setJPDFromDatabase : "+ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean isProbCreated(Node node) {
        boolean condition = false;
        ResultSet rs = super.querySelect("select * from "+Database.Table.TABLE_KD_PROBABILITIES+" where node_name='"+
                node.get_name()+"'");
        try {
            condition = rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in isProbabilitiesEmpty : "+ex.getMessage());
        }
        return !condition;
    }

    @Override
    public void setProbToDB(Node node, double[] prob) {
        String probabilities = "";
        int last = prob.length-1;
        for(int i=0;i<prob.length;i++){
            if(i==last)
                probabilities+=prob[i];
            else
                probabilities+=prob[i]+"#";
        }
        String qInsert = "insert into "+Database.Table.TABLE_KD_PROBABILITIES+" (node_name, probabilities) values(?,?)";
        super.insertToDB(node.get_name(), "string", probabilities, "string", qInsert);
    }
    
}
