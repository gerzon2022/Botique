/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;

import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class frmCreateUser extends javax.swing.JFrame {
Connection conn=null;
ResultSet rs = null;

    /**
     * Creates new form frmCreateUser
     */
    public frmCreateUser() {
        
        initComponents();
        
        conn=BotiqueSystem.conn_db();
        lbluser.setText(BotiqueSystem.user);
        laodusers();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtblockno = new javax.swing.JTextField();
        txtcontactno = new javax.swing.JTextField();
        txtlname = new javax.swing.JTextField();
        txtfname = new javax.swing.JTextField();
        txtlotno = new javax.swing.JTextField();
        txtzone = new javax.swing.JTextField();
        txtcity = new javax.swing.JTextField();
        txtprovince = new javax.swing.JTextField();
        txtstreet = new javax.swing.JTextField();
        cbgender = new javax.swing.JComboBox();
        txtuname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblusers = new javax.swing.JTable();
        txtsearch = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnregister = new javax.swing.JButton();
        btnclr = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        cbprivilege = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Register User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Contact no.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        jLabel2.setText("Lastname");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel3.setText("Firstname");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jLabel4.setText("Username");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jLabel5.setText("Zone");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel6.setText("Street");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel7.setText("Lot no");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, -1));

        jLabel8.setText("Block no");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, -1, -1));

        jLabel9.setText("City");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        txtblockno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtblockno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtblocknoKeyPressed(evt);
            }
        });
        jPanel1.add(txtblockno, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 130, -1));

        txtcontactno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtcontactno, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 160, -1));

        txtlname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtlname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtlnameKeyReleased(evt);
            }
        });
        jPanel1.add(txtlname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 130, -1));

        txtfname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtfname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfnameKeyReleased(evt);
            }
        });
        jPanel1.add(txtfname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 130, -1));

        txtlotno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtlotno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlotnoActionPerformed(evt);
            }
        });
        txtlotno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtlotnoKeyPressed(evt);
            }
        });
        jPanel1.add(txtlotno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 50, -1));

        txtzone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtzone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtzoneKeyPressed(evt);
            }
        });
        jPanel1.add(txtzone, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 130, -1));

        txtcity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtcity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcityActionPerformed(evt);
            }
        });
        txtcity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcityKeyReleased(evt);
            }
        });
        jPanel1.add(txtcity, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 230, -1));

        txtprovince.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtprovince.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprovinceKeyReleased(evt);
            }
        });
        jPanel1.add(txtprovince, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 160, -1));

        txtstreet.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtstreet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtstreetMouseReleased(evt);
            }
        });
        txtstreet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstreetKeyReleased(evt);
            }
        });
        jPanel1.add(txtstreet, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 160, -1));

        cbgender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbgender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gender...", "Female", "Male" }));
        jPanel1.add(cbgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 150, 30));

        txtuname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtuname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 140, -1));

        jLabel10.setText("Province");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        tblusers.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblusers.setModel(new javax.swing.table.DefaultTableModel(
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
        tblusers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblusers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblusersMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblusers);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, 300));

        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });
        jPanel1.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 160, -1));

        jLabel11.setText("Search");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, -1, -1));

        btnregister.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnregister.setText("Register");
        btnregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregisterActionPerformed(evt);
            }
        });
        jPanel1.add(btnregister, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 140, 50));

        btnclr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnclr.setText("Clear/Cancel");
        btnclr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclrActionPerformed(evt);
            }
        });
        jPanel1.add(btnclr, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 140, 50));

        btnedit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel1.add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 140, 50));

        btndelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, 140, 50));

        cbprivilege.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbprivilege.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Privilege...", "Admin", "User" }));
        cbprivilege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbprivilegeActionPerformed(evt);
            }
        });
        jPanel1.add(cbprivilege, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 150, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 1030, 460));

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Register User");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbluser.setText("Welcome");
        jPanel4.add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Welcome");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1100, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcityActionPerformed

    private void txtlotnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlotnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlotnoActionPerformed

    private void btnregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregisterActionPerformed
        sqlcode="select * from tbluseraccount where fld_username='"+ txtuname.getText().trim() +"'";
        String[] textboxes ={ txtcity.getText().trim(), 
            txtcontactno.getText().trim(),
            txtfname.getText().trim(),
            txtlname.getText().trim(), 
            //txtlotno.getText().trim(), 
            //txtblockno.getText().trim(),
            txtuname.getText().trim(),
            txtprovince.getText().trim(),
           // txtstreet.getText().trim(),
            //txtzone.getText().trim(),
            txtuname.getText().trim()
                
        };
        if ((BotiqueSystem.isTextEmpty(textboxes)) || (cbgender.getSelectedIndex()==0) || (cbprivilege.getSelectedIndex()==0)) {
            
            JOptionPane.showMessageDialog(null, "Check Empty Textbox or Gender: Male/Female or privilege : Admin/User"); 
        }
         
        else if (BotiqueSystem.isduplicate(sqlcode, conn)) {
            JOptionPane.showMessageDialog(null, "Username already exist! Please enter another username!");
            txtuname.requestFocus();
        }
        else{
           
            if (btnregister.getText().equals("Register")) {
                 sqlcode="insert into tblusers set "
                    + "fld_username='"+ txtuname.getText() +"', "
                    + "fld_city='"+  txtcity.getText().trim() +"', "
                    + "fld_contactno='"+  txtcontactno.getText().trim() +"', "
                    + "fld_fname='"+ txtfname.getText().trim() +"', "
                    + "fld_lname='"+ txtlname.getText().trim() +"', "
                    + "fld_lotno='"+ txtlotno.getText().trim() +"', "
                    + "fld_blockno='"+  txtblockno.getText().trim() +"', "
                    //+ "fld_username='"+ txtuname.getText().trim() +"', "
                    + "fld_province='"+ txtprovince.getText().trim() +"', "
                    + " fld_street='"+  txtstreet.getText().trim() +"', "
                    + " fld_zone='"+ txtzone.getText().trim() +"', "
                    + "fld_gender='"+ cbgender.getSelectedItem().toString() +"'" ;
                 String fnamehere=txtfname.getText().toLowerCase();
                 String password=fnamehere.charAt(0)+txtlname.getText().trim().toLowerCase().replace(" ", "");
                 //JOptionPane.showMessageDialog(null, password);
                 String sqlcode2="insert into tbluseraccount set fld_username='"+ txtuname.getText().trim() +"', "
                         + " fld_password='"+ password +"', "
                         + "fld_privilege='"+ cbprivilege.getSelectedItem().toString() +"'";
                 BotiqueSystem.addupdatedel(sqlcode2, conn);
                 
            }
            else if (btnregister.getText().equals("Update")) {
                sqlcode="update tblusers set "
                   // + "fld_username='"+ txtuname.getText() +"', "
                    + "fld_city='"+  txtcity.getText().trim() +"', "
                    + "fld_contactno='"+  txtcontactno.getText().trim() +"', "
                    + "fld_fname='"+ txtfname.getText().trim() +"', "
                    + "fld_lname='"+ txtlname.getText().trim() +"', "
                    + "fld_lotno='"+ txtlotno.getText().trim() +"', "
                    + "fld_blockno='"+  txtblockno.getText().trim() +"', "
                    //+ "fld_username='"+ txtuname.getText().trim() +"', "
                    + "fld_province='"+ txtprovince.getText().trim() +"', "
                    + " fld_street='"+  txtstreet.getText().trim() +"', "
                    + " fld_zone='"+ txtzone.getText().trim() +"', "
                    + "fld_gender='"+ cbgender.getSelectedItem().toString() +"' "
                        + "where "
                        + "fld_username='"+ txtuname.getText() +"'" ;
            }
            if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                JOptionPane.showMessageDialog(null, "Success!");
                txtuname.setEditable(true);
                btnregister.setText("Register");
                btnclrActionPerformed(evt);
            }
        }
        
    }//GEN-LAST:event_btnregisterActionPerformed
    public void laodusers(){
          DefaultTableModel dtm = new DefaultTableModel(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false;
                }
            };
           
            dtm.addColumn("Username");
            dtm.addColumn("Lastname");
            dtm.addColumn("Firstname");
            dtm.addColumn("Gender");
              dtm.addColumn("Zone");
               dtm.addColumn("Lot #");
               dtm.addColumn("Blk #");
               dtm.addColumn("Street");
               dtm.addColumn("City");
               dtm.addColumn("Province");
               dtm.addColumn("Contact no");
                
                
                
               sqlcode="select * from tblusers " ;
            try {
                  rs=BotiqueSystem.selectquery(sqlcode, conn);
                  
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        
                        rs.getString("fld_username"),
                        rs.getString("fld_lname"),
                            rs.getString("fld_fname"),
                             rs.getString("fld_gender"),
                             rs.getString("fld_zone"),
                            rs.getString("fld_lotno"),
                            rs.getString("fld_blockno"),
                            rs.getString("fld_street"),
                            rs.getString("fld_city"),
                            rs.getString("fld_province"),
                            rs.getString("fld_contactno"),
                            
                            
                            
                          
                    
                    });
                                  }
                tblusers.setModel(dtm);
                //hidecolumn();
                rs.close();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
              
                
                
            
            
            
            
    }
    
    private void btnclrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclrActionPerformed
       laodusers();
       txtcity.setText(""); 
            txtcontactno.setText("");
            txtfname.setText("");
            txtlname.setText("");
            txtlotno.setText("");
            txtblockno.setText("");
            txtuname.setText("");
            txtprovince.setText("");
            txtstreet.setText("");
            txtzone.setText("");
            txtuname.setText("");
            btnregister.setText("Register");
            txtuname.setEditable(true);
             btnedit.setEnabled(true);
            btndelete.setEnabled(true);
    }//GEN-LAST:event_btnclrActionPerformed
String refid="";
    private void tblusersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblusersMousePressed
        if ((tblusers.getSelectedRowCount() >0) && (btnregister.getText().equals("Register")) ) {
            refid = tblusers.getValueAt(tblusers.getSelectedRow(), 0).toString();
            btnedit.setEnabled(true);
            btndelete.setEnabled(true);
        }
        else{
            btndelete.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Cancel or finished last transaction first");
        }
    }//GEN-LAST:event_tblusersMousePressed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        if (tblusers.getSelectedRowCount() >0) {
            
            sqlcode="delete from tblusers where fld_username='"+ refid +"'";
            String sqlcode1="delete from tbluseraccount where fld_username='"+ refid +"'";
            if ((BotiqueSystem.addupdatedel(sqlcode, conn)) &&
                    (JOptionPane.showConfirmDialog(null, "Delete this item?", "Deleting...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                    ==JOptionPane.YES_OPTION) && 
                    (BotiqueSystem.addupdatedel(sqlcode1, conn))
                    )
                    
                     {
                JOptionPane.showMessageDialog(null, "Deleted!");
                         btnclrActionPerformed(evt);
                
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No item selected!");
        }
        
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed


        if (tblusers.getSelectedRowCount() >0) {
           btnregister.setText("Update");
            txtuname.setEditable(false);
            txtuname.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 0).toString());
            txtlname.setText(tblusers.getValueAt(tblusers.getSelectedRow(),1).toString());
            txtfname.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 2).toString());
            cbgender.setSelectedItem(tblusers.getValueAt(tblusers.getSelectedRow(), 3).toString());
            txtzone.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 4).toString());
            txtlotno.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 5).toString());
            txtblockno.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 6).toString());
            txtstreet.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 7).toString());
            txtcity.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 8).toString());
            txtprovince.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 9).toString());
            txtcontactno.setText(tblusers.getValueAt(tblusers.getSelectedRow(), 10).toString());
            btnedit.setEnabled(false);
            btndelete.setEnabled(false);
            
        }
        else{
              JOptionPane.showMessageDialog(null, "No item selected!");
        }
    }//GEN-LAST:event_btneditActionPerformed

    private void txtlnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlnameKeyReleased
         if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{
        txtlname.setText(BotiqueSystem.toTitleCase(txtlname.getText()));}
    }//GEN-LAST:event_txtlnameKeyReleased

    private void txtfnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfnameKeyReleased
        if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{txtfname.setText(BotiqueSystem.toTitleCase(txtfname.getText()));}
    }//GEN-LAST:event_txtfnameKeyReleased

    private void txtstreetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtstreetMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstreetMouseReleased

    private void txtstreetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstreetKeyReleased
         if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{txtstreet.setText(BotiqueSystem.toTitleCase(txtstreet.getText()));}
    }//GEN-LAST:event_txtstreetKeyReleased

    private void txtcityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcityKeyReleased
      if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{  txtcity.setText(BotiqueSystem.toTitleCase(txtcity.getText()));}
    }//GEN-LAST:event_txtcityKeyReleased

    private void txtprovinceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprovinceKeyReleased
       if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{ txtprovince.setText(BotiqueSystem.toTitleCase(txtprovince.getText()));}
    }//GEN-LAST:event_txtprovinceKeyReleased

    private void txtzoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtzoneKeyPressed
        if (BotiqueSystem.isNumber(evt)) {
            txtzone.setText("");
            txtzone.requestFocus();
        }
    }//GEN-LAST:event_txtzoneKeyPressed

    private void txtlotnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlotnoKeyPressed
        if (BotiqueSystem.isNumber(evt)) {
             txtlotno.setText("");
             txtlotno.requestFocus();
        }
    }//GEN-LAST:event_txtlotnoKeyPressed

    private void txtblocknoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtblocknoKeyPressed
        if (BotiqueSystem.isNumber(evt)) {
              txtblockno.setText("");
              txtblockno.requestFocus();
        }
    }//GEN-LAST:event_txtblocknoKeyPressed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
      if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{
        txtsearch.setText(BotiqueSystem.toTitleCase(txtsearch.getText()));
        }
        if (txtsearch.getText().equals("")) {
            laodusers();
        }
        else{
             DefaultTableModel dtm = new DefaultTableModel(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false;
                }
            };
           
            dtm.addColumn("Username");
            dtm.addColumn("Lastname");
            dtm.addColumn("Firstname");
            dtm.addColumn("Gender");
              dtm.addColumn("Zone");
               dtm.addColumn("Lot #");
               dtm.addColumn("Blk #");
               dtm.addColumn("Street");
               dtm.addColumn("City");
               dtm.addColumn("Province");
               dtm.addColumn("Contact no");
                
                
                
               sqlcode="select * from tblusers where fld_lname like '%"+ txtsearch.getText() +"%' or fld_fname like '%"+ txtsearch.getText() +"%' " ;
            try {
                  rs=BotiqueSystem.selectquery(sqlcode, conn);
                  
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        
                        rs.getString("fld_username"),
                        rs.getString("fld_lname"),
                            rs.getString("fld_fname"),
                             rs.getString("fld_gender"),
                             rs.getString("fld_zone"),
                            rs.getString("fld_lotno"),
                            rs.getString("fld_blockno"),
                            rs.getString("fld_street"),
                            rs.getString("fld_city"),
                            rs.getString("fld_province"),
                            rs.getString("fld_contactno"),
                            
                            
                            
                          
                    
                    });
                                  }
                tblusers.setModel(dtm);
                //hidecolumn();
                rs.close();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
              
                
        }
    }//GEN-LAST:event_txtsearchKeyReleased

    private void cbprivilegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbprivilegeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbprivilegeActionPerformed
String sqlcode="";
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
            java.util.logging.Logger.getLogger(frmCreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCreateUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclr;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnregister;
    private javax.swing.JComboBox cbgender;
    private javax.swing.JComboBox cbprivilege;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbluser;
    private javax.swing.JTable tblusers;
    private javax.swing.JTextField txtblockno;
    private javax.swing.JTextField txtcity;
    private javax.swing.JTextField txtcontactno;
    private javax.swing.JTextField txtfname;
    private javax.swing.JTextField txtlname;
    private javax.swing.JTextField txtlotno;
    private javax.swing.JTextField txtprovince;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtstreet;
    private javax.swing.JTextField txtuname;
    private javax.swing.JTextField txtzone;
    // End of variables declaration//GEN-END:variables
}
