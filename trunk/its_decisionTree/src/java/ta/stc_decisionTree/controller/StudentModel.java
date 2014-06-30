package ta.stc_decisionTree.controller;

import java.util.LinkedList;
import java.util.Vector;
import ta.stc_decisionTree.data.NamaMateri;
import ta.stc_decisionTree.data.RuleNilai;
import ta.stc_decisionTree.data.RuleTypeSoal;
import ta.stc_decisionTree.model.DecisionTree;
import ta.stc_decisionTree.model.MateriPretest;

/**
 *
 * @author ami
 */
public class StudentModel {
    MateriPretest[] materiPretest;
    LinkedList<String> weakMaterial = new LinkedList<>();
    private LinkedList<DecisionTree> dTreeList;
    
    public StudentModel(){
        System.out.println("Konstruktor kepanggil");
        materiPretest = new MateriPretest[NamaMateri.LIST_NAMA_MATERI.length];
        int i = 0;
        for(String namaMateri : NamaMateri.LIST_NAMA_MATERI){
            MateriPretest pretest = new MateriPretest();
            pretest.materi = namaMateri;
            materiPretest[i++] = pretest;
        }
    }
    
    private void createStudentModel(Vector<Boolean> hasilJawaban){
        double range;
        double rangePoint;
        try{
        setHasilPretest(hasilJawaban);
        }catch(Exception e){
            System.out.println("Exception createStudentModel: "+e.getMessage());
        }
        for(MateriPretest mPretest:materiPretest){
            double jmlBenar = mPretest.jumlahBenar;
            double totSoal = mPretest.totalSoal;
            if(jmlBenar>=(totSoal/2.0)){
                weakMaterial.add(mPretest.materi);
            }
            if(RuleNilai.getVariasi()==RuleNilai.VARIASI_PERTAMA){
                range = 2.0;
                rangePoint = totSoal/range;
                if(jmlBenar >= rangePoint) mPretest.hasil = RuleNilai.BAIK;
                else mPretest.hasil = RuleNilai.KURANG;
            }else if(RuleNilai.getVariasi()==RuleNilai.VARIASI_KEDUA){
                range = 3.0;
                rangePoint = totSoal/range;
                if(jmlBenar >= 2.0*rangePoint) mPretest.hasil = RuleNilai.BAIK;
                else if(jmlBenar >= rangePoint) mPretest.hasil = RuleNilai.SEDANG;
                else mPretest.hasil = RuleNilai.KURANG;
            }else if(RuleNilai.getVariasi()==RuleNilai.VARIASI_KETIGA){
                range = 5.0;
                rangePoint = totSoal/range;
                if(jmlBenar >= 4.0*rangePoint) mPretest.hasil = RuleNilai.SANGAT_BAIK;
                else if(jmlBenar >= 3.0*rangePoint) mPretest.hasil = RuleNilai.BAIK;
                else if(jmlBenar >= 2.0*rangePoint) mPretest.hasil = RuleNilai.SEDANG;
                else if(jmlBenar >= rangePoint) mPretest.hasil = RuleNilai.KURANG;
                else mPretest.hasil = RuleNilai.SANGAT_KURANG;
            }
            
        }
    }
    
    private void buatDecisionTree(){
        Pelatihan pLatih = new Pelatihan();
        pLatih.setDaftarMateri((LinkedList<String>) arrayToLinkedList(NamaMateri.LIST_NAMA_MATERI));
        pLatih.setDaftarTarget((LinkedList<String>) arrayToLinkedList(NamaMateri.LIST_NAMA_MATERI));
        dTreeList = pLatih.buatDecisionTree(new ProsesTes());
    }
    
    public LinkedList<String> getWeakMaterial(Vector<Boolean> hasilJawaban){
        createStudentModel(hasilJawaban);
        buatDecisionTree();
        for(DecisionTree dTree : dTreeList){
            System.out.println("Dtree target: "+dTree.tabelTree.getTarget());
            String[] inputPrediksi = getInputPrediksi(dTree);
            String hasilPrediksi = dTree.predictTargetAttributeValue(inputPrediksi);
            System.out.println("Hasil Prediksi: "+hasilPrediksi);
            if(hasilPrediksi!=null){
                if(RuleNilai.ubahStringKeNilai(hasilPrediksi)==RuleNilai.TIDAK_LULUS){
                    weakMaterial.add(dTree.tabelTree.getTarget());
                }
            }
        }
        return weakMaterial;
    }
    
    private String[] getInputPrediksi(DecisionTree dTree){
        String[] inputPrediksi = new String[dTree.allAttributes.length];
        for(int i=0;i<inputPrediksi.length;i++){
            if(dTree.allAttributes[i].equals(dTree.tabelTree.getTarget())){
                inputPrediksi[i] = null;
            }else{
                for(MateriPretest mPretest : materiPretest){
                    if(mPretest.materi.equals(dTree.allAttributes[i])){
                        inputPrediksi[i] = RuleNilai.ubahNilaiKeString(mPretest.hasil);
                    }
                }
            }
        }
        return inputPrediksi;
    }
    
    private void setHasilPretest(Vector<Boolean> hasilJawaban){
        LinkedList<String[]> listTypeSoal = RuleTypeSoal.LIST_TYPE_SOAL();
        System.out.println("hasilJawaban: "+hasilJawaban.size());
        for(int i=0;i<hasilJawaban.size();i++){
            String[] listMateri = listTypeSoal.get(i);
            for(String str:listMateri){
                for(MateriPretest mPretest:materiPretest){
                    if(mPretest.materi.equals(str)){
                        mPretest.totalSoal++;
                        if(hasilJawaban.get(i)){
                            mPretest.jumlahBenar++;
                        }
                    }
                }
            }
        }
    }
    
    private LinkedList arrayToLinkedList(Object[] arr){
        LinkedList list = new LinkedList();
        for(Object o : arr){
            list.add(o);
        }
        return list;
    }
}
