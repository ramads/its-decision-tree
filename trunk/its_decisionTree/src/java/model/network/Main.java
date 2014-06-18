package model.network;

import model.node.NodeKD;
import java.util.Vector;
import model.node.BayesGraph;
import model.node.Node;
import model.node.NodeSM;
import model.network.StudentModel;


public class Main {
    public static void main(String[] args) {
        BayesGraph bg = new BayesGraph();
//        KnowledgeDomain domain = new KnowledgeDomain();
       StudentModel studentModel = new StudentModel();
        
        //untuk menyimpan weak node yg meruapakan hasil dari method getWeakNode pada class student model
        //ini akan menjadi input untuk method getWeakNode pada class knowledge domain
        Vector<Node> weakNode = new Vector();
        
        //untuk menyimpan jawaban dari user yg akan menjadi inputan method getWeakNode pada class student model
        Vector<Boolean> answer = new Vector();
        
        NodeSM[] nodeQ= new NodeSM[15];
        for(int i=0;i<nodeQ.length;i++){
            String name = "QT"+(i+1);
            nodeQ[i] = new NodeSM(bg, name);
        }
        
        Node baseJava = new NodeKD(bg, "baseJava");
        Node classNmethod = new NodeKD(bg, "classNmethod");
        Node variable = new NodeKD(bg, "variable");
        Node pdt = new NodeKD(bg, "pdt");
        Node odt = new NodeKD(bg, "odt");
        Node array = new NodeKD(bg, "array");
        Node logic = new NodeKD(bg, "logic");
        Node opArithmetic = new NodeKD(bg, "opArithmetic");
        Node opIncNdec = new NodeKD(bg, "opIncNdec");
        Node opRelation = new NodeKD(bg, "opRelation");
        Node opLogic = new NodeKD(bg, "opLogic");
        Node opPredence = new NodeKD(bg, "opPredence");
        Node looping = new NodeKD(bg, "looping");
        Node ifElse = new NodeKD(bg, "ifElse");
        Node switchCase = new NodeKD(bg, "switchCase");
        Node flowchart = new NodeKD(bg, "flowchart");
//        weakNode.addElement(flowchart);
//        domain.getWeakNode(weakNode);
        
        //tes panggil method getWeakNode pada student model utk liat probabilitasnya
        studentModel.getWeakNode(answer);
        
    }
}
