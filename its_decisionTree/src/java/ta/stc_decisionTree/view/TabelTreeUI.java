package ta.stc_decisionTree.view;

import java.awt.Cursor;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import ta.stc_decisionTree.controller.EksporTask;
import ta.stc_decisionTree.model.TabelTree;
import ta.stc_decisionTree.model.TabelTreeModel;

public class TabelTreeUI extends javax.swing.JFrame {    
    public TabelTreeUI() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTree = new javax.swing.JTable();
        btnEkporCSV = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelTarget = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TABEL TREE");

        tblTree.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTree);

        btnEkporCSV.setText("Ekpor ke CSV");
        btnEkporCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEkporCSVActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Target: ");

        labelTarget.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTarget.setForeground(new java.awt.Color(51, 153, 0));
        labelTarget.setText("nama_target");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEkporCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTarget)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelTarget))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEkporCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEkporCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEkporCSVActionPerformed
        eksporCSV();
    }//GEN-LAST:event_btnEkporCSVActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEkporCSV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTarget;
    private javax.swing.JTable tblTree;
    // End of variables declaration//GEN-END:variables
    
    private TabelTree tabelTree;
    
    public void setObjek(TabelTree tabelTree){
        this.tabelTree = tabelTree;
        this.labelTarget.setText(tabelTree.getTarget().toUpperCase());
        tblTree.setModel(new TabelTreeModel(tabelTree));
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblTree.getModel());
        tblTree.setRowSorter(sorter);
    }
    
    private void eksporCSV(){
        JFileChooser jfc = new JFileChooser();
        int returnVal = jfc.showSaveDialog(this);
        if(returnVal==JFileChooser.APPROVE_OPTION){
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            EksporTask task = new EksporTask();
            File file = jfc.getSelectedFile();
            task.setComponent(this);
            task.setTabelTree(tabelTree);
            task.setPath(file.getPath());
            task.execute();
        }
    }
}
