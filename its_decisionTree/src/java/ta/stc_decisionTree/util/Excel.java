package ta.stc_decisionTree.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import ta.stc_decisionTree.model.TabelTree;

public class Excel {
    TabelTree tabelTree;
    public Excel(TabelTree tabelTree){
        this.tabelTree = tabelTree;
    }
    
    public void eksporKeCSV(String path){
        try {
            FileWriter csv = new FileWriter(path+".csv");
            String[] header = tabelTree.getHeaderTabel();
            for(int i=0;i<header.length;i++){
                if(i!=0) csv.append(",");
                csv.append(header[i]);
            }
            csv.append("\n");
            List<String[]> bodyTabel = tabelTree.getBodyTabel();
            for(int i=0;i<bodyTabel.size();i++){
                String[] row = bodyTabel.get(i);
                for(int j=0;j<row.length;j++){
                    if(j!=0) csv.append(",");
                    csv.append(row[j]);
                }
                csv.append("\n");
            }
            csv.flush();
	    csv.close();
        } catch (IOException ex) {
            System.out.println("Error Ekspor to CSV -> "+ex.getMessage());
        }
    }
    
    public void ekspor(String path){
        path = path+"_"+getTanggalSekarang();
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(path+".xls"));
            WritableSheet sheet = workbook.createSheet("Sheet "+tabelTree.getTarget(), 0);
            String[] header = tabelTree.getHeaderTabel();
            for(int i=0;i<header.length;i++){
                sheet.addCell(new Label(i, 0, header[i]));
            }
            List<String[]> bodyTabel = tabelTree.getBodyTabel();
            for(int i=0;i<bodyTabel.size();i++){
                String[] row = bodyTabel.get(i);
                for(int j=0;j<row.length;j++){
                    sheet.addCell(new Label(j, i+1, row[j]));
                }
            }
            workbook.write();
            workbook.close();
        } catch (IOException | WriteException ex) {
            JOptionPane.showMessageDialog(null, "Ekspor gagal!\n"+ex.getMessage());
        }
    }
    
    private String getTanggalSekarang(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }
    
}
