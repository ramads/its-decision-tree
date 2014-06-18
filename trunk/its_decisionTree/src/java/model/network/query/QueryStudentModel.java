/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.network.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.node.Node;
import java.util.Vector;
import model.repository.data.Database;
import model.repository.data.HighPoint;

/**
 *
 * @author Arin
 */
public class QueryStudentModel extends ITSQuery implements NodeQuery {
    
     public QueryStudentModel() {
        super(Database.NAME);
    }

    @Override
    public double getProbability(Node node) {
        String queryTrueSample = "select id from "+Database.Table.TABLE_STUDENT_MODEL+" where "
                +node.get_name()+" = true";
        String querySample = "select id from "+Database.Table.TABLE_STUDENT_MODEL;
        return super.getProbability(queryTrueSample, querySample);
    }

    @Override
    public double getProbability(Node node, Vector<Node> parentList, boolean[] stateCondition) {
          String queryTrueSample = "select id from "
                +Database.Table.TABLE_DAILYTEST+" where "
                +node.get_name()+" > "
                +HighPoint.getHighPoint(node.get_name());
        for(int i=0;i<stateCondition.length;i++){
            queryTrueSample+=" and id in (select id from "
                    +Database.Table.TABLE_STUDENT_MODEL+" where "
                    +parentList.elementAt(i).get_name()+" = "
                    +stateCondition[i];
        }
        queryTrueSample+=super.generateParenthesis(stateCondition.length);
//        System.out.print("queryTrueSample getProbablity= "+queryTrueSample);
        
        String querySample = "select id from "
                +Database.Table.TABLE_STUDENT_MODEL+" where id in (select id from "
                +Database.Table.TABLE_STUDENT_MODEL+" where "
                +parentList.elementAt(0).get_name()+" = "
                +stateCondition[0];
        for(int i=1;i<stateCondition.length;i++){
            querySample+=" and id in (select id from "
                    +Database.Table.TABLE_STUDENT_MODEL+" where "
                    +parentList.elementAt(i).get_name()+" = "
                    +stateCondition[i];
        }
        querySample+=super.generateParenthesis(stateCondition.length);
//        System.out.print("queryTrueSample getSample= "+ querySample);
        return super.getProbability(queryTrueSample, querySample);
    }
    
    @Override
    public double[] getProbFromDB(Node node) {
        ResultSet rs = super.querySelect("select * from "+Database.Table.TABLE_SM_PROBABILITIES+" where node_name='"+
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
        ResultSet rs = super.querySelect("select * from "+Database.Table.TABLE_SM_PROBABILITIES+" where node_name='"+
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
        String qInsert = "insert into "+Database.Table.TABLE_SM_PROBABILITIES+" (node_name, probabilities) values(?,?)";
        super.insertToDB(node.get_name(), "string", probabilities, "string", qInsert);
    }
}
