/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author KARLA
 */
public class frmReportSetting extends javax.swing.JFrame {

    /**
     * Creates new form frmReportSetting
     */
    public frmReportSetting() {
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel3 = new javax.swing.JPanel();
        txtreportloc = new javax.swing.JTextField();
        btnsave2 = new javax.swing.JButton();
        Path = new javax.swing.JLabel();
        btntest = new javax.swing.JButton();
        btnopen = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Report Path", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtreportloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 450, 30));

        btnsave2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsave2.setText("Save Setting");
        btnsave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsave2ActionPerformed(evt);
            }
        });
        jPanel3.add(btnsave2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 130, 40));

        Path.setText("Selected path");
        jPanel3.add(Path, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        btntest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btntest.setText("Test");
        btntest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntestActionPerformed(evt);
            }
        });
        jPanel3.add(btntest, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 130, 40));

        btnopen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnopen.setText("Browse");
        btnopen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnopenActionPerformed(evt);
            }
        });
        jPanel3.add(btnopen, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 120, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 490, 190));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 590, 30));

        jPanel5.setBackground(new java.awt.Color(255, 255, 153));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Report Setup");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsave2ActionPerformed

    }//GEN-LAST:event_btnsave2ActionPerformed

    private void btntestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntestActionPerformed
       

        //return conn;
    }//GEN-LAST:event_btntestActionPerformed

    private void btnopenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnopenActionPerformed
       JFileChooser chooser = new JFileChooser();
       chooser.setApproveButtonText("Select");
       chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       chooser.setDialogTitle("Locate path");
     

int returnVal = chooser.showOpenDialog(this);
            if (returnVal== JFileChooser.APPROVE_OPTION) {
                txtreportloc.setText(chooser.getSelectedFile().getPath().toString());
            }
           
    }//GEN-LAST:event_btnopenActionPerformed

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
            java.util.logging.Logger.getLogger(frmReportSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReportSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReportSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReportSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReportSetting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Path;
    private javax.swing.JButton btnopen;
    private javax.swing.JButton btnsave2;
    private javax.swing.JButton btntest;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField txtreportloc;
    // End of variables declaration//GEN-END:variables
}