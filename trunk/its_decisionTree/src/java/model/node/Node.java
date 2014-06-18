/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.node;

import model.network.query.NodeQuery;
import InferenceGraphs.InferenceGraphNode;
import QuasiBayesianInferences.QBInference;
import controller.editor.RoundingOff;
import java.util.Vector;

/**
 *
 * @author Arin
 */
public abstract class Node extends InferenceGraphNode{

    //untuk membentuk graph dari node
    private BayesGraph bg;
    
    //untuk menentukan query database dari class node
    private NodeQuery nodeQuery;
    
    /**
     * constructor bawaan dari class InferenceGraphNode
     * @param bg
     * @param name 
     */
    public Node(BayesGraph bg, String name){
        super(bg, name);
        this.bg = bg;
        bg.addNode(this);
        this.setNodeQuery();
    }
    
        /*
     * method abstract untuk melakukan set object NodeQuery
     */
    public abstract void setNodeQuery();
    
    /**
     * method untuk menambahkan object NodeQuery
     * @param nodeQuery
     */
    
    public void addNodeQuery(NodeQuery nodeQuery){
        this.nodeQuery = nodeQuery;
    }
    
    /*
     * method untuk melakukan pembuatan jpd dalam sebuah node yg memiliki 2 state saja (true atau false)
     */
    public void setJPD(){
        if(nodeQuery.isProbCreated(this)){
            System.out.println("jpd di set dari data pelatihan");
            setJPDfromTrainingData();
        }else{
            System.out.println("jpd di set dari database");
            this.set_function_values(nodeQuery.getProbFromDB(this));
        }    
    }
    
    /*
     * method untuk mendapatkan nilai probabilitas dari node
     */
    public double getBelief() {
        QBInference qbi = new QBInference(bg.get_bayes_net(), false);
        qbi.inference(this.get_name()); 
        return RoundingOff.roundNumber(qbi.get_result().get_value(0));
    }
    
    private void setJPDfromTrainingData(){
        double[] probability = this.get_function_values();
        if(!hasParent()){
            double prob = nodeQuery.getProbability(this);
            prob = RoundingOff.roundNumber(prob);
            probability[0] = prob;
            probability[1] = RoundingOff.roundNumber(1.0-prob);
        }else{
            Vector<Node> parentList = get_parents();
            int totValue = probability.length/2;
            boolean[] stateCondition = new boolean[parentList.size()];
            int varIndex;
            for(varIndex=0;varIndex<totValue;varIndex++){
                for(int j=0;j<stateCondition.length;j++){
                    if(varIndex==0)
                        stateCondition[j]=true;
                    else if(((varIndex)%(totValue/(int)Math.pow(2,(j+1))))==0)
                        stateCondition[j]=(!stateCondition[j]);
                }
                double prob = nodeQuery.getProbability(this, parentList, stateCondition);
                if(prob==0.0) prob=0.0011;
                if(prob==1.0) prob=0.9900;
                prob = RoundingOff.roundNumber(prob);
                probability[varIndex] = prob;
                probability[varIndex+totValue] = RoundingOff.roundNumber(1.0-prob);
            }
        }
        nodeQuery.setProbToDB(this, probability);
        this.set_function_values(probability);
    }
}
