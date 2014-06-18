package model.network;



import model.node.NodeSM;
import java.util.Vector;
import model.node.BayesGraph;
import model.node.Node;
import controller.editor.Decimal;

/**
 * class student model dengan aturan baru penggunaan question bertipe
 * pada class studentn model digunakan object NodeY yg query nya telah di sesuaikan dengan student model
 * @author arin
 */
public class StudentModel {
    //konstanta untuk menyipan nilai batas suatu materi weak ato tidak
    private final double THRESHOLD_STUDENT_MODEL = 0.3;
    BayesGraph bg = new BayesGraph();
    
    //bentuk array untuk node question sebanyak 15
    NodeSM[] nodeQ= new NodeSM[15];
    private NodeSM baseJava = new NodeSM(bg, "baseJava");
    private NodeSM classNmethod = new NodeSM(bg, "classNmethod");
    private NodeSM variable = new NodeSM(bg, "variable");
    private NodeSM pdt = new NodeSM(bg, "pdt");
    private NodeSM odt = new NodeSM(bg, "odt");
    private NodeSM array = new NodeSM(bg, "array");
    private NodeSM logic = new NodeSM(bg, "logic");
    private NodeSM opArithmetic = new NodeSM(bg, "opArithmetic");
    private NodeSM opIncNdec = new NodeSM(bg, "opIncNdec");
    private NodeSM opRelation = new NodeSM(bg, "opRelation");
    private NodeSM opLogic = new NodeSM(bg, "opLogic");
    private NodeSM opPredence = new NodeSM(bg, "opPredence");
    private NodeSM looping = new NodeSM(bg, "looping");
    private NodeSM ifElse = new NodeSM(bg, "ifElse");
    private NodeSM switchCase = new NodeSM(bg, "switchCase");
    private NodeSM flowchart = new NodeSM(bg, "flowchart");
    
    public StudentModel() {
        //membuat node question
        createNodeQuestion();
        
        //otomatis membuat network saat class new student model di instansiasi
        createNetwork();
    }
    
    /*
     * method untuk membuat node question yg disimpan dalam sebuah array
     */
    private void createNodeQuestion(){
        for(int i=0;i<nodeQ.length;i++){
            String name = "QT"+(i+1);
            nodeQ[i] = new NodeSM(bg, name);
        }
    }
    
    /*
     * method untuk mendapatkan node question yg ada dalam array
     */
    private NodeSM getNodeQ(int x){
        return nodeQ[x-1];
    }
    
    private void createNetwork(){
        bg.create_arc(getNodeQ(1), baseJava);  //
        bg.create_arc(getNodeQ(3), odt);       //
        bg.create_arc(getNodeQ(2), pdt);       //
        bg.create_arc(getNodeQ(6), pdt);       //
        bg.create_arc(getNodeQ(10), pdt);      //
        bg.create_arc(getNodeQ(13), pdt);      //
        bg.create_arc(getNodeQ(14), pdt);      //
        bg.create_arc(getNodeQ(4), logic);      //
        bg.create_arc(getNodeQ(5), flowchart);  //
        bg.create_arc(getNodeQ(6), array);      //
        bg.create_arc(getNodeQ(7), array);      //
        bg.create_arc(getNodeQ(2), variable);   //
        bg.create_arc(getNodeQ(3), variable);   //
        bg.create_arc(getNodeQ(6), variable);   //
        bg.create_arc(getNodeQ(8), opRelation);   //
        bg.create_arc(getNodeQ(11), opRelation);  //
        bg.create_arc(getNodeQ(13), opRelation);  //
        bg.create_arc(getNodeQ(15), opRelation);  //
        bg.create_arc(getNodeQ(10), opIncNdec);  //
        bg.create_arc(getNodeQ(12), opIncNdec);   //
        bg.create_arc(getNodeQ(15), opIncNdec);   //
        bg.create_arc(getNodeQ(4), opLogic);    //
        bg.create_arc(getNodeQ(8), opLogic);    //
        bg.create_arc(getNodeQ(4), opArithmetic);  //
        bg.create_arc(getNodeQ(9), opArithmetic);  //
        bg.create_arc(getNodeQ(11), opArithmetic);  //
        bg.create_arc(getNodeQ(8), opPredence);   //
        bg.create_arc(getNodeQ(9), opPredence);  //
        bg.create_arc(getNodeQ(11), opPredence);  //
        bg.create_arc(getNodeQ(1), classNmethod);  //
        bg.create_arc(getNodeQ(9), classNmethod);  //
        bg.create_arc(getNodeQ(5), looping);  //
        bg.create_arc(getNodeQ(7), looping);  //
        bg.create_arc(getNodeQ(12), looping);  //
        bg.create_arc(getNodeQ(15), looping);  //
        bg.create_arc(getNodeQ(13), ifElse);  //
        bg.create_arc(getNodeQ(15), ifElse);  //
        bg.create_arc(getNodeQ(14), switchCase);  //
        
        //set JPD semua node setelah network dibuat
        bg.setJPDNodes();
    }
    
    /**
     * method untuk mendapatkan materi yang lemah berdasarkan jawaban user
     * @param answer -> jawaban dari user
     * @return 
     */
    public Vector<Node> weakNode;
    public Vector<Node> getWeakNode(Vector<Boolean> answer){
        weakNode = new Vector();
        tampilNodeSM();
        //System.out.println("=================================================");
        //melakukan setting node question berdasarkan jawaban user
        setObservation(answer);
        for(Node i:(Vector<Node>) bg.get_nodes()){
            System.out.println("SM after setObsevation probability of "+i.get_name()+" : "+Decimal.format(i.getBelief()));
            if(!"Q".equals(i.get_name().substring(0, 1))){
                if(isWeak(i)) weakNode.add(i);
            }
        }
        return weakNode;
    }
    
    public void tampilNodeSM(){
        for(Node i:(Vector<Node>) bg.get_nodes()){
            System.out.println("SM before setObservaion probability of "+i.get_name()+" : "+Decimal.format(i.getBelief()));
        }
    }
    
    /**
     * method untuk setting kondisi node question
     * @param answer -> jawaban dari user
     */
    private void setObservation(Vector<Boolean> answer){
        for(int i=0;i<answer.size();i++){
            if(answer.get(i))
                nodeQ[i].set_observation_value("true");
            else
                nodeQ[i].set_observation_value("false");
        }
    }
    
    /*
     * method untuk mengecek apakah kondisi node weak atau tidak
     */
    private boolean isWeak(Node node){
        return (node.getBelief()<THRESHOLD_STUDENT_MODEL);
    }
}
