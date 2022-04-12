/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;
import java.sql.Connection;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class frmLogin extends javax.swing.JFrame {
   Connection conn=null;
   String sqlcode;
   Statement stmt = null;
   ResultSet rs = null;
   
    public frmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);//center
        conn=BotiqueSystem.conn_db();
      
        //javax.swing.ImageIcon imageIcon = new javax.swing.ImageIcon(new javax.swing.ImageIcon("D:\\project\\pos\\AUNTIE NI JUNE\\botique-java\\Botique System\\icon\\login.png").getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_DEFAULT)); // transform it back
        //jLabel3.setIcon(imageIcon);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtUname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        txtUpass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JB PHARMA AND TRADE CENTER");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Username");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 74, -1));

        txtUname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 153, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 73, -1));

        txtUpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUpassActionPerformed(evt);
            }
        });
        getContentPane().add(txtUpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 153, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\project\\pos\\AUNTIE NI JUNE\\botique-java\\Botique System\\icon\\login.png")); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 120));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 370, 120));

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 24)); // NOI18N
        jLabel12.setText("JB Pharma and Trade center");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Welcome");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 430, 70));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 420, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
       sqlcode="select * from tbluseraccount where fld_username= binary '"+ txtUname.getText() +"' and fld_password= binary '"+ txtUpass.getText() +"'";
       try {
           
           rs=BotiqueSystem.selectquery(sqlcode, conn);
           if (rs.next()) {
               
          
                        if (rs.getString("fld_privilege").equals("Admin")) {
                            frmMain dmain = new frmMain();
                            dmain.setVisible(true);
                            BotiqueSystem.user_privilege="Admin";
                        }
                        else {
                            frmStockOut out = new frmStockOut();
                            out.setVisible(true);
                        }
                        BotiqueSystem.user=rs.getString("fld_username");
                        BotiqueSystem.loginpass=rs.getString("fld_password");
                         this.dispose();
                         
           }
           else{
               JOptionPane.showMessageDialog(null, "Authentication error!");
                txtUpass.setText(null);
           }
           rs.close();
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, ex.getMessage());
         
//       
    }  
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnameActionPerformed
        txtUpass.requestFocus();
    }//GEN-LAST:event_txtUnameActionPerformed

    private void txtUpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUpassActionPerformed
        btnLoginActionPerformed(evt);
    }//GEN-LAST:event_txtUpassActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed
    
   
    public static void main(String args[]) {
     java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtUname;
    private javax.swing.JPasswordField txtUpass;
    // End of variables declaration//GEN-END:variables
}
