/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.node;

import InferenceGraphs.InferenceGraph;
import java.awt.Point;
import java.util.Vector;

/**
 *
 * @author Arin
 */
public class BayesGraph extends InferenceGraph {
    
    /**
     * addNode digunakan untuk menambahkan object node ke dalam graph
     * @param node 
     */
    
    public void addNode(Node node){
        Point p = new Point(0, 0);
        super.get_nodes().addElement(node);
        
        // Synchronize object QuasiBayesNet dengan graph.
        convert_graph();
    }
    
     /*
     * setJPDNode untuk melakukan pembentukan JPD pada semua node 
     * yg ada dalam graph
     * method ini harus dipanggil setelah network terbentuk
     */
    public void setJPDNodes(){
        Vector<Node> nodes = super.get_nodes();
        for(Node node : nodes){
            node.setJPD();
        }
    }
    
}
