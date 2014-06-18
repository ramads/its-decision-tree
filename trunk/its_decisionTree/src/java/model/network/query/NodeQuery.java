/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.network.query;

import java.util.Vector;
import model.node.Node;

/**
 * interface NodeQuery harus di implementasikan untuk melakuakan set query pada class Node
 * karena method getProbability yang ada pada NodeQuery akan dipanggil dalam method setJPD pada class Node
 * 
 * @author arin
 */

public interface NodeQuery {
  
    /**
     * method ini digunakan untuk mengambil probability true dari node independent
     * query yg diberikan pada node ini hanya untuk mendapatkan nilai true saja,
     * karena nilai probability false akan di set secara otomatis oleh clas Node
     * 
     * @param node
     * @return 
     */
    
    public double getProbability(Node node);
    
    /**
     * method ini digunakan untuk mengambil probabilty true dari node non independent
     * query yg diberikan pada node ini hanya untuk mendapatkan nilai true 
     * dari masing-masing variasi kondisi state list parent yg diberikan,
     * 
     * @param node -> node child
     * @param parentList -> list dari parent
     * @param stateCondition -> variasi kondisi state dari list parent
     * @return 
     */
    
    public double getProbability(Node node, Vector<Node> parentList, boolean[] stateCondition);  
    
    public double[] getProbFromDB(Node node);
    
    public boolean isProbCreated(Node node);
    
    public void setProbToDB(Node node, double[] prob);
}
