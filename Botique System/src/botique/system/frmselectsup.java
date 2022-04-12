/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Admin
 */
public class frmselectsup extends javax.swing.JFrame {
String sqlcode;


    int refid;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    
  
    public frmselectsup() {
        initComponents();
        conn=BotiqueSystem.conn_db();
        loadsupplier();
    }
 public void loadsupplier(){
        DefaultTableModel tblmodel =new DefaultTableModel();
        tblmodel.addColumn("ID");
        tblmodel.addColumn("Code");
        tblmodel.addColumn("Supplier");
        tblmodel.addColumn("Zone");
        tblmodel.addColumn("Street");
        tblmodel.addColumn("City");
        tblmodel.addColumn("Province");
        tblmodel.addColumn("Mobile no.");
        tblmodel.addColumn("Tel no.");
        
        try {
            sqlcode="select * from tblsupplier";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sqlcode);
            
            while (rs.next()) {
                tblmodel.addRow(new Object[]{
                rs.getString("fld_sup_id"),
               rs.getString("fld_sup_code"),
               rs.getString("fld_sup_name"),
               
               rs.getString("fld_sup_zone"),
               rs.getString("fld_sup_street"),
               rs.getString("fld_sup_city"),
               rs.getString("fld_sup_province"),
               rs.getString("fld_sup_mobile"),
               rs.getString("fld_sup_telno")
                });
            }
            tblsupplier.setModel(tblmodel);
             rs.close();
             stmt.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(rootPane, err.getMessage());
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsupplier = new javax.swing.JTable(){public boolean isCellEditable(int rowIndex, int colIndex){return false;}};
        btnopensupselect = new javax.swing.JButton();
        btnopensupcan = new javax.swing.JButton();
        txtsearchsup = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Select Supplier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblsupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tblsupplier.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblsupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsupplier);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 430, 269));

        btnopensupselect.setText("Select");
        btnopensupselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnopensupselectActionPerformed(evt);
            }
        });
        jPanel1.add(btnopensupselect, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 91, 40));

        btnopensupcan.setText("Cancel");
        btnopensupcan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnopensupcanActionPerformed(evt);
            }
        });
        jPanel1.add(btnopensupcan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 92, 40));

        txtsearchsup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchsupKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsearchsupKeyTyped(evt);
            }
        });
        jPanel1.add(txtsearchsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 130, 30));

        jLabel9.setText("Search");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 518, 428));

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Select Supplier");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbluser.setText("Welcome");
        jPanel4.add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Welcome");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 550, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtsearchsupKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchsupKeyTyped

    }//GEN-LAST:event_txtsearchsupKeyTyped

    private void txtsearchsupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchsupKeyReleased
       

        if (txtsearchsup.getText().equals("")) {
            loadsupplier();
        }
        else{
           DefaultTableModel tblmodel =new DefaultTableModel();
        tblmodel.addColumn("ID");
        tblmodel.addColumn("Code");
        tblmodel.addColumn("Supplier");
        tblmodel.addColumn("Zone");
        tblmodel.addColumn("Street");
        tblmodel.addColumn("City");
        tblmodel.addColumn("Province");
        tblmodel.addColumn("Mobile no.");
        tblmodel.addColumn("Tel no.");
        
        try {
            sqlcode="select * from tblsupplier where fld_sup_name like '%"+ txtsearchsup.getText() +"%'";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sqlcode);
            
            while (rs.next()) {
                tblmodel.addRow(new Object[]{
                rs.getString("fld_sup_id"),
               rs.getString("fld_sup_code"),
               rs.getString("fld_sup_name"),
               
               rs.getString("fld_sup_zone"),
               rs.getString("fld_sup_street"),
               rs.getString("fld_sup_city"),
               rs.getString("fld_sup_province"),
               rs.getString("fld_sup_mobile"),
               rs.getString("fld_sup_telno")
                });
            }
            tblsupplier.setModel(tblmodel);
             rs.close();
             stmt.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(rootPane, err.getMessage());
        }

        }
    }//GEN-LAST:event_txtsearchsupKeyReleased

    private void btnopensupcanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnopensupcanActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnopensupcanActionPerformed

    private void btnopensupselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnopensupselectActionPerformed
        if (tblsupplier.getSelectedRowCount()>0) {
            frmcreatepo.txtposuppliercode.setText(tblsupplier.getValueAt(tblsupplier.getSelectedRow(), 1).toString());
            frmcreatepo.txtsupdetails.setText(tblsupplier.getValueAt(tblsupplier.getSelectedRow(),2).toString()+"\n"
                +tblsupplier.getValueAt(tblsupplier.getSelectedRow(),3).toString()+" "
                +tblsupplier.getValueAt(tblsupplier.getSelectedRow(),4).toString()+"\n"
                +tblsupplier.getValueAt(tblsupplier.getSelectedRow(),5).toString()+"\n"
                +tblsupplier.getValueAt(tblsupplier.getSelectedRow(),6).toString()
            );

            //frmcreatepo.supname=tblsupplier.getValueAt(tblsupplier.getSelectedRow(),2).toString();
            //frmcreatepo.supzone=tblsupplier.getValueAt(tblsupplier.getSelectedRow(),3).toString();
            //frmcreatepo.supstreet=tblsupplier.getValueAt(tblsupplier.getSelectedRow(),4).toString();
            //frmcreatepo.supcity=tblsupplier.getValueAt(tblsupplier.getSelectedRow(),5).toString();
            //frmcreatepo.supprov=tblsupplier.getValueAt(tblsupplier.getSelectedRow(),6).toString();
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "No Selection");
        }
    }//GEN-LAST:event_btnopensupselectActionPerformed

    private void tblsupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsupplierMouseClicked
        if (evt.getClickCount()==2) {
            btnopensupselectActionPerformed(null);
        }
        evt.getClickCount();
    }//GEN-LAST:event_tblsupplierMouseClicked
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmselectsup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmselectsup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmselectsup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmselectsup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmselectsup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnopensupcan;
    public static javax.swing.JButton btnopensupselect;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbluser;
    private javax.swing.JTable tblsupplier;
    private javax.swing.JTextField txtsearchsup;
    // End of variables declaration//GEN-END:variables
}