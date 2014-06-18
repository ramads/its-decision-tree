package model.network;

import model.node.NodeKD;
import java.util.Vector;
import model.node.BayesGraph;
import model.node.Node;


/**
 * pembuatan class ini dengan menggunakan object NodeKD yg querynya sudah di sesuaikan dengan knowledge domain
 * @author arin
 */
public class KnowledgeDomain {
    //konstanta untuk menyimpan nilai batas suatu materi dikatakan weak ato tidak
    private final double THRESHOLD_KNOWLEDGE_DOMAIN = 0.4;
    
    BayesGraph bg = new BayesGraph();
    
    private NodeKD baseJava = new NodeKD(bg, "baseJava");
    private NodeKD classNmethod = new NodeKD(bg, "classNmethod");
    private NodeKD variable = new NodeKD(bg, "variable");
    private NodeKD pdt = new NodeKD(bg, "pdt");
    private NodeKD odt = new NodeKD(bg, "odt");
    private NodeKD array = new NodeKD(bg, "array");
    private NodeKD logic = new NodeKD(bg, "logic");
    private NodeKD opArithmetic = new NodeKD(bg, "opArithmetic");
    private NodeKD opIncNdec = new NodeKD(bg, "opIncNdec");
    private NodeKD opRelation = new NodeKD(bg, "opRelation");
    private NodeKD opLogic = new NodeKD(bg, "opLogic");
    private NodeKD opPredence = new NodeKD(bg, "opPredence");
    private NodeKD looping = new NodeKD(bg, "looping");
    private NodeKD ifElse = new NodeKD(bg, "ifElse");
    private NodeKD switchCase = new NodeKD(bg, "switchCase");
    private NodeKD flowchart = new NodeKD(bg, "flowchart");

    public KnowledgeDomain() {
        //otomatis membuat network saat class knowledge domain dipanggil
        createNetwork();
    }
    
    /*
     * untuk membentuk bayesian network dari knowledge domain
     */
    private void createNetwork(){
        bg.create_arc(baseJava, classNmethod); //
        bg.create_arc(odt, array); //
        bg.create_arc(odt, variable); //
        bg.create_arc(pdt, array); //
        bg.create_arc(pdt, variable); //
        bg.create_arc(pdt, opIncNdec); //
        bg.create_arc(pdt, switchCase); //belum
        bg.create_arc(logic, opRelation); //
        bg.create_arc(logic, opLogic); //
        bg.create_arc(flowchart, variable); //
        bg.create_arc(flowchart, looping); //
        bg.create_arc(flowchart, ifElse); //
        bg.create_arc(flowchart, switchCase); //belum
        bg.create_arc(array, looping); //
        bg.create_arc(variable, opArithmetic); //
        bg.create_arc(variable, opRelation); //
        bg.create_arc(variable, opLogic); //
         bg.create_arc(opIncNdec, opPredence); //
        bg.create_arc(opArithmetic, opPredence); //
        bg.create_arc(opArithmetic, classNmethod); //
        bg.create_arc(opRelation, opPredence); //
        bg.create_arc(opRelation, ifElse); // 
        bg.create_arc(opIncNdec, looping); //
         bg.create_arc(opRelation, looping); //
        bg.create_arc(opLogic, opPredence); //
        bg.create_arc(opLogic, ifElse); //
        bg.create_arc(opLogic, switchCase); //belum
        
        //melakukan set jpd untuk semua node setelah network terbentuk
        bg.setJPDNodes();
    }
    
    public void tampilNodeKD(){
        for(Node i:(Vector<Node>) bg.get_nodes()){
            System.out.println("KD probability of "+i.get_name()+" : "+i.getBelief());
        }
    }
    
    public void setObserv(String namaNode, String value){
        for(Node node:(Vector<Node>)bg.get_nodes()){
            if(node.get_name().equalsIgnoreCase(namaNode)){
                System.out.println("############ nama Node : "+node.get_name() +" value = "+value);
                node.set_observation_value(value);
                break;
            }
        }
    }
    
    public Node getNode(String namaNode){
        for(Node node:(Vector<Node>)bg.get_nodes()){
            if(node.get_name().equalsIgnoreCase(namaNode)){
                return node; 
            }
        }
         return null;     
    }
    
    public Vector<Node> getWeakNode(Vector<String> weakFromDB){
        Vector<Node> nodeWeak = new Vector<Node>();
        for(String lesson_name : weakFromDB){
            for(Node i : (Vector<Node>) bg.get_nodes()){
                if(lesson_name.equals(i.get_name())){
                    nodeWeak.add(i);
                    break;
                }
            }
        }
        return nodeWeak;
    }
    
    /**
     * untuk menentukan node yg lemah berdasarkan threshold yg ditentukan
     * @param node -> node pada knowledge domain
     * @return 
     */
    private boolean isWeak(Node node){
        return (node.getBelief()<THRESHOLD_KNOWLEDGE_DOMAIN);
    }
    
}
