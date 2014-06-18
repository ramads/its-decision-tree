package ta.stc_decisionTree.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Kelas/objek tabel tree yang digunakan untuk 
 * penyajian data pada interface desktop
 * @author ami
 */
public class TabelTreeModel extends AbstractTableModel{
    private List<String[]> dataList;
    private String[] columnName;
    
    public TabelTreeModel(TabelTree tabelTree){
        dataList = tabelTree.getBodyTabel();
        columnName = tabelTree.getHeaderTabel();
    }
    @Override
    public int getRowCount() {
        return dataList.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] baris = dataList.get(rowIndex);
        return baris[columnIndex];
    }
    
    @Override
    public String getColumnName(int column){
        return columnName[column];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
    
}
