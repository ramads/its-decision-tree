/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.Vector;
import model.network.KnowledgeDomain;
import model.network.StudentModel;
import model.node.Node;

/**
 *
 * @author Arin
 */
public class Tes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
//        Pretest p = new Pretest();
//        p.getPretestResult("henca");
//        p.getWeakMaterial();
//        p.getMaterial();   
//        
//       Pedagogik p = new Pedagogik();
       KnowledgeDomain k = new KnowledgeDomain();
//       StudentModel sm = new StudentModel();
       
//       System.out.println("User ID F1B009037");
//       p.getPretestResultasIsUser("F1B009037");
      
       k.tampilNodeKD();
//       sm.tampilNodeSM();
//      String mat=p.getLearnMaterial("F1B009037");
//      System.out.println("Material = "+mat);
////       l.getWeakMaterial();
////       l.getMaterial();
//        
//       l.getLearnMaterialBasedKD();
//       System.out.println(l.result.size());
       
      //        for (int i=0; i<l.matWarning.size(); i++){
//            System.out.println(l.matWarning.elementAt(i).get_name());
//        }
    }
}
