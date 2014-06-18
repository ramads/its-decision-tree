/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.node;

import model.network.query.QueryStudentModel;
import model.node.BayesGraph;
import model.node.Node;

/**
 *
 * @author Arin
 */
public class NodeSM extends Node {
    
    public NodeSM(BayesGraph bg, String name){
        super(bg, name);
    }
    
     /*
     * method abstract dari class Node untuk mengeset query
     */
    @Override
    public void setNodeQuery() {
        addNodeQuery(new QueryStudentModel());
    }   
}
