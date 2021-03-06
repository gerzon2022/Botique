/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author KARLA
 */
public class frmReports extends javax.swing.JFrame {

    Connection conn = null;
    String sqlcode = "";
    ResultSet rs = null;
    int startmonth=1, endmonth=3;
     int month=1;

    /**
     * Creates new form frmReports
     */
    public frmReports() {
        initComponents();
        conn = BotiqueSystem.conn_db();
        disableall();
        getyear();
        rbtdaily.setSelected(true);
        Dimension size = datechooser.getCalendarPreferredSize();
            size.width += 90;
            datechooser.setCalendarPreferredSize(size);
        
    }
    
    public void getyear() {
        sqlcode = " SELECT distinct  DATE_FORMAT(`fld_invoice_date`,'%Y') AS invoice_year FROM tblinvoiceno"
                + " inner join tblinvoice "
                + "on tblinvoiceno.fld_invoice_no=tblinvoice.fld_invoice_no";
        
        try {
            cbmonthlyyear.removeAllItems();
             cbyear.removeAllItems();
            rs = BotiqueSystem.selectquery(sqlcode, conn);
            while (rs.next()) {
                cbmonthlyyear.addItem(rs.getString("invoice_year"));
                cbyear.addItem(rs.getString("invoice_year"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void disableall() {
        panel_daily.setEnabled(false);
        panel_monthly.setEnabled(false);
        panel_quarterly.setEnabled(false);
        panel_yearly.setEnabled(false);
//       txtdate.setEnabled(false);
//       txtyear.setEnabled(false);
        cbmonthly.setEnabled(false);
        cbquarterly.setEnabled(false);
        cbyear.setEnabled(false);
        datechooser.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportgroup = new javax.swing.ButtonGroup();
        panel_daily = new javax.swing.JPanel();
        datechooser = new datechooser.beans.DateChooserCombo();
        panel_monthly = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbmonthly = new javax.swing.JComboBox();
        panel_yearly = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbyear = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbltime = new javax.swing.JLabel();
        panel_quarterly = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbquarterly = new javax.swing.JComboBox();
        btnview = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rbtyearly = new javax.swing.JRadioButton();
        rbtmontly = new javax.swing.JRadioButton();
        rbtquarterly = new javax.swing.JRadioButton();
        rbtdaily = new javax.swing.JRadioButton();
        cbmonthlyyear = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_daily.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Daily", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N
        panel_daily.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        datechooser.setFormat(1);
        panel_daily.add(datechooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        getContentPane().add(panel_daily, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 310, 80));

        panel_monthly.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Monthly", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N
        panel_monthly.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Choose a month");
        panel_monthly.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cbmonthly.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cbmonthly.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmonthlyItemStateChanged(evt);
            }
        });
        panel_monthly.add(cbmonthly, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 130, -1));

        getContentPane().add(panel_monthly, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 320, 80));

        panel_yearly.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Yearly", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N
        panel_yearly.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Enter Year");
        panel_yearly.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cbyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel_yearly.add(cbyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        getContentPane().add(panel_yearly, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 220, 90));

        jPanel5.setBackground(new java.awt.Color(255, 255, 153));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Sales Report");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbluser.setText("Welcome");
        jPanel5.add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbldate.setText("Welcome");
        jPanel5.add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Welcome");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        lbltime.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lbltime.setText("Welcome");
        jPanel5.add(lbltime, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 600, 70));

        panel_quarterly.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Quarterly", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N
        panel_quarterly.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Enter Starting Date");
        panel_quarterly.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cbquarterly.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January-March", "April-June", "July-September", "October-December" }));
        cbquarterly.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbquarterlyItemStateChanged(evt);
            }
        });
        panel_quarterly.add(cbquarterly, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 130, -1));

        getContentPane().add(panel_quarterly, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 270, 90));

        btnview.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnview.setText("View");
        btnview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnviewActionPerformed(evt);
            }
        });
        getContentPane().add(btnview, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 220, 80));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Set Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        reportgroup.add(rbtyearly);
        rbtyearly.setText("Yearly");
        rbtyearly.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtyearlyItemStateChanged(evt);
            }
        });
        jPanel1.add(rbtyearly, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        reportgroup.add(rbtmontly);
        rbtmontly.setText("Monthly");
        rbtmontly.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtmontlyItemStateChanged(evt);
            }
        });
        rbtmontly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtmontlyActionPerformed(evt);
            }
        });
        jPanel1.add(rbtmontly, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        reportgroup.add(rbtquarterly);
        rbtquarterly.setText("Quarterly");
        rbtquarterly.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtquarterlyItemStateChanged(evt);
            }
        });
        jPanel1.add(rbtquarterly, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        reportgroup.add(rbtdaily);
        rbtdaily.setText("Daily");
        rbtdaily.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtdailyItemStateChanged(evt);
            }
        });
        jPanel1.add(rbtdaily, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        cbmonthlyyear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbmonthlyyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, -1));

        jLabel2.setText("Year");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 400, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtmontlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtmontlyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtmontlyActionPerformed

    private void rbtdailyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtdailyItemStateChanged
        disableall();
        panel_daily.setEnabled(true);
        datechooser.setEnabled(true);
//        txtdate.setEnabled(true);
    }//GEN-LAST:event_rbtdailyItemStateChanged

    private void rbtmontlyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtmontlyItemStateChanged
        disableall();
        panel_monthly.setEnabled(true);
        cbmonthly.setEnabled(true);
    }//GEN-LAST:event_rbtmontlyItemStateChanged

    private void rbtquarterlyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtquarterlyItemStateChanged
        disableall();
        panel_quarterly.setEnabled(true);
        cbquarterly.setEnabled(true);
        
    }//GEN-LAST:event_rbtquarterlyItemStateChanged

    private void rbtyearlyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtyearlyItemStateChanged
        disableall();
        panel_yearly.setEnabled(true);
        cbyear.setEnabled(true);
//       txtyear.setEnabled(true);
    }//GEN-LAST:event_rbtyearlyItemStateChanged
 int year;
    private void btnviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewActionPerformed
        year=Integer.parseInt(cbmonthlyyear.getSelectedItem().toString());
        JasperDesign jd = null;
        HashMap param1 = new HashMap();
        param1.put("this_year", year);
        if (rbtdaily.isSelected()) {
            
            SimpleDateFormat df;
            df = new SimpleDateFormat("M-dd-yyyy");
            // jd= JRXmlLoader.load(BotiqueSystem.rootdir+"rpt_daily_report.jrxml");
            param1.put("this_date", datechooser.getSelectedDate().getTime());
            BotiqueSystem.openreport(BotiqueSystem.rootdir+"rpt_daily_report.jasper", param1, conn);
        } else if (rbtmontly.isSelected()) {
            
           
            for (int i = 0; i < 12; i++) {
                if (cbmonthly.getSelectedIndex() == i) {
                    month=i+1;
                    
                }
                
            }
           
            //jd= JRXmlLoader.load(BotiqueSystem.rootdir+"rpt_monthly_report.jrxml");
            param1.put("this_month", month);
            param1.put("this_year", year);
            
            BotiqueSystem.openreport(BotiqueSystem.rootdir+"rpt_monthly_report.jasper", param1, conn);
//                 SimpleDateFormat df = new SimpleDateFormat("M-yyyy");
//                 Date this_month= df.parse(cbmonthly.getSelectedItem().toString());
//
        } else if (rbtquarterly.isSelected()) {
            SimpleDateFormat df;
//            df = new SimpleDateFormat("yyyy");
//            String strmonth=df.format(datechooser.getSelectedDate().getTime());
//            int year=Integer.parseInt(strmonth);
            
            // jd= JRXmlLoader.load(BotiqueSystem.rootdir+"rpt_quarterly_report.jrxml");
            
            param1.put("this_month", startmonth);
            param1.put("to_this_month", endmonth);
             param1.put("this_year", year);
            
            
            
            BotiqueSystem.openreport(BotiqueSystem.rootdir+"rpt_quarterly_report.jasper", param1, conn);
            
        } else if (rbtyearly.isSelected()) {
//             SimpleDateFormat df;
//            df = new SimpleDateFormat("yyyy");
//            String strmonth=df.format(datechooser.getSelectedDate().getTime());
//            int year=Integer.parseInt(strmonth);
            //jd= JRXmlLoader.load(BotiqueSystem.rootdir+"rpt_yearly_report.jrxml");
            year=Integer.parseInt(cbyear.getSelectedItem().toString());
            param1.put("this_year", year);
            BotiqueSystem.openreport(BotiqueSystem.rootdir+"rpt_yearly_report.jasper", param1, conn);
            //SimpleDateFormat df = new SimpleDateFormat("yyyy");
//                 Date this_year= df.parse(txtyear.getText());
//                jd= JRXmlLoader.load(BotiqueSystem.rptLoc+"rpt_printInvoice-GOV.jrxml");
//                param1.put("invoiceno", this_year);
        }

    }//GEN-LAST:event_btnviewActionPerformed

    private void cbquarterlyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbquarterlyItemStateChanged
        if (cbquarterly.getSelectedIndex()==0) {
           startmonth=1;
            endmonth=3;
           
        }
        else if (cbquarterly.getSelectedIndex()==1) {
             startmonth=4;
            endmonth=6;
        }
         else if (cbquarterly.getSelectedIndex()==2) {
           startmonth=7;
            endmonth=9;
        }
         else if (cbquarterly.getSelectedIndex()==3) {
            startmonth=10;
            endmonth=12;
        }
         else{
             JOptionPane.showMessageDialog(null, "Please contanct the developer");
         }
    }//GEN-LAST:event_cbquarterlyItemStateChanged

    private void cbmonthlyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmonthlyItemStateChanged
       
    }//GEN-LAST:event_cbmonthlyItemStateChanged

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
            java.util.logging.Logger.getLogger(frmReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReports().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnview;
    private javax.swing.JComboBox cbmonthly;
    private javax.swing.JComboBox cbmonthlyyear;
    private javax.swing.JComboBox cbquarterly;
    private javax.swing.JComboBox cbyear;
    private datechooser.beans.DateChooserCombo datechooser;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbltime;
    private javax.swing.JLabel lbluser;
    private javax.swing.JPanel panel_daily;
    private javax.swing.JPanel panel_monthly;
    private javax.swing.JPanel panel_quarterly;
    private javax.swing.JPanel panel_yearly;
    private javax.swing.JRadioButton rbtdaily;
    private javax.swing.JRadioButton rbtmontly;
    private javax.swing.JRadioButton rbtquarterly;
    private javax.swing.JRadioButton rbtyearly;
    private javax.swing.ButtonGroup reportgroup;
    // End of variables declaration//GEN-END:variables
}
