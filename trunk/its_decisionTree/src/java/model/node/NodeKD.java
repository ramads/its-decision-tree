/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.node;

import model.network.query.QueryKnowledgeDomain;
import model.node.BayesGraph;
import model.node.Node;

/**
 *
 * @author Arin
 */
public class NodeKD extends Node {
    
 public NodeKD(BayesGraph bg, String name){
        super(bg, name);
    }
    
    /*
     * method abstract dari class Node untuk mengeset query
     */
    @Override
    public void setNodeQuery() {
        addNodeQuery(new QueryKnowledgeDomain());
    }   
}
