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
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author Admin
 */
public class frmSupplier extends javax.swing.JFrame {
String sqlcode;
Connection conn=null;
Statement stmt=null;
ResultSet rs=null;
int sup_id;

public void btnaddstatus(){
        btnaddsup.setEnabled(true);
         btneditsup.setEnabled(false);
       // btndeletesup.setEnabled(false);
        
//         if (btneditsup.isEnabled()|| txtsupplier.getText().equals("") || txtsuppliercode.getText().equals("")) {
////             btnaddcat.setEnabled(false);
////             btndeletecat.setEnabled(false);
////             btneditcat.setEnabled(false);
//             
//             btnaddsup.setEnabled(false);
//            
//            btndeletesup.setEnabled(false);
//          
//            
//           
//             
//         } else {
//               btnaddsup.setEnabled(true);
//                 btneditsup.setEnabled(false);
//         }
//        

      
    }
    public void loadsupplier(){
        DefaultTableModel tblmodel =new DefaultTableModel();
        tblmodel.addColumn("ID");
        tblmodel.addColumn("Code");
        tblmodel.addColumn("Supplier");
        //tblmodel.addColumn("Zone");
        tblmodel.addColumn("Street");
        tblmodel.addColumn("City");
        tblmodel.addColumn("Province");
        tblmodel.addColumn("Mobile no.");
        tblmodel.addColumn("Tel no.");
        
        try {
            sqlcode="select * from tblsupplier order by fld_sup_name asc";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sqlcode);
            
            while (rs.next()) {
                tblmodel.addRow(new Object[]{
                rs.getString("fld_sup_id"),
               rs.getString("fld_sup_code"),
               rs.getString("fld_sup_name"),
               
               //rs.getString("fld_sup_zone"),
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
              hidecolumn() ;
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(rootPane, err.getMessage());
        }
       
        
    }
    
    
    /**
     * Creates new form frmSupplier
     */
    public frmSupplier() {
        initComponents();
        setExtendedState(frmSupplier.MAXIMIZED_BOTH);
        btnaddstatus();
        conn=BotiqueSystem.conn_db();
        loadsupplier();
        txtsuppliercode.setText("SUP"+BotiqueSystem.nextSupplierCode());
        txtsupplier.requestFocus();
       
        
    }
    public void cleartext(){
        txtsupcity.setText("");
        txtsupmobile.setText("");
        txtsuppliercode.setText("");
        txtsupplier.setText("");
        txtsupprovince.setText("");
        txtsupstreet.setText("");
        txtsuptelno.setText("");
        //txtsupzone.setText("");
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbltime = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtsupcity = new javax.swing.JTextField();
        txtsupplier = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtsuppliercode = new javax.swing.JTextField();
        txtsupstreet = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsupplier = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnaddsup = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtsuptelno = new javax.swing.JTextField();
        txtsupmobile = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btneditsup = new javax.swing.JButton();
        txtsupprovince = new javax.swing.JTextField();
        txtsearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btncancelsup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Register Supplier");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbluser.setText("Welcome");
        jPanel4.add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbldate.setText("Welcome");
        jPanel4.add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Welcome");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        lbltime.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbltime.setText("Welcome");
        jPanel4.add(lbltime, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 70));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Register Supplier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtsupcity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsupcity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsupcityActionPerformed(evt);
            }
        });
        txtsupcity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsupcityKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsupcityKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsupcityKeyTyped(evt);
            }
        });
        jPanel1.add(txtsupcity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 270, 30));

        txtsupplier.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsupplierActionPerformed(evt);
            }
        });
        txtsupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsupplierKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsupplierKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsupplierKeyTyped(evt);
            }
        });
        jPanel1.add(txtsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 270, 30));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Province");
        jLabel8.setAlignmentX(0.5F);
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 130, 20));

        txtsuppliercode.setEditable(false);
        txtsuppliercode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsuppliercode.setFocusable(false);
        txtsuppliercode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsuppliercodeActionPerformed(evt);
            }
        });
        txtsuppliercode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsuppliercodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsuppliercodeKeyTyped(evt);
            }
        });
        jPanel1.add(txtsuppliercode, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 110, 30));

        txtsupstreet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsupstreet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsupstreetActionPerformed(evt);
            }
        });
        txtsupstreet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsupstreetKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsupstreetKeyTyped(evt);
            }
        });
        jPanel1.add(txtsupstreet, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 130, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Name");
        jLabel3.setAlignmentX(0.5F);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 60, -1));

        tblsupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category ID", "Category Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblsupplierFocusGained(evt);
            }
        });
        tblsupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsupplierMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblsupplierMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblsupplier);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 620, 490));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Tel no.");
        jLabel10.setAlignmentX(0.5F);
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 130, 20));

        btnaddsup.setText("Add");
        btnaddsup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddsupActionPerformed(evt);
            }
        });
        jPanel1.add(btnaddsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 270, 40));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("City");
        jLabel7.setAlignmentX(0.5F);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, 20));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Street");
        jLabel5.setAlignmentX(0.5F);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 130, 20));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Mobile no.");
        jLabel9.setAlignmentX(0.5F);
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 130, 20));

        txtsuptelno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsuptelno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsuptelnoActionPerformed(evt);
            }
        });
        txtsuptelno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsuptelnoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsuptelnoKeyTyped(evt);
            }
        });
        jPanel1.add(txtsuptelno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 130, 30));

        txtsupmobile.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsupmobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsupmobileActionPerformed(evt);
            }
        });
        txtsupmobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsupmobileKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsupmobileKeyTyped(evt);
            }
        });
        jPanel1.add(txtsupmobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 130, 30));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Code");
        jLabel1.setAlignmentX(0.5F);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 20));

        btneditsup.setText("Edit");
        btneditsup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditsupActionPerformed(evt);
            }
        });
        jPanel1.add(btneditsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 270, 40));

        txtsupprovince.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtsupprovince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsupprovinceActionPerformed(evt);
            }
        });
        txtsupprovince.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsupprovinceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsupprovinceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsupprovinceKeyTyped(evt);
            }
        });
        jPanel1.add(txtsupprovince, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 270, 30));

        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });
        jPanel1.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 160, 30));

        jLabel2.setText("Search");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        btncancelsup.setText("Cancel");
        btncancelsup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelsupActionPerformed(evt);
            }
        });
        jPanel1.add(btncancelsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 270, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 980, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents
 public void hidecolumn() {
       
        TableColumn col = tblsupplier.getColumnModel().getColumn(0);
        col.setPreferredWidth(0);
        col.setMaxWidth(0);
        col.setMinWidth(0);
        
        col.setPreferredWidth(0);
        col.setMaxWidth(0);
        col.setMinWidth(0);
}
    private void txtsuppliercodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsuppliercodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsuppliercodeActionPerformed

    private void txtsuppliercodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsuppliercodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsuppliercodeKeyPressed

    private void txtsuppliercodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsuppliercodeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsuppliercodeKeyTyped

    private void btneditsupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditsupActionPerformed
        if (tblsupplier.getSelectedRowCount() >0 && btneditsup.getText().equals("Edit")) {
            btneditsup.setText("Update");
            //btndeletesup.setEnabled(false);
            
            txtsupplier.setText(tblsupplier.getValueAt( tblsupplier.getSelectedRow(),2 ).toString());
            txtsuppliercode.setText(tblsupplier.getValueAt( tblsupplier.getSelectedRow(),1 ).toString());
            txtsupstreet.setText(tblsupplier.getValueAt( tblsupplier.getSelectedRow(),4 ).toString());
          
            txtsupcity.setText(tblsupplier.getValueAt( tblsupplier.getSelectedRow(),4 ).toString());
            txtsupprovince.setText(tblsupplier.getValueAt( tblsupplier.getSelectedRow(),5 ).toString());
            txtsupmobile.setText(tblsupplier.getValueAt( tblsupplier.getSelectedRow(),6 ).toString());
            txtsuptelno.setText(tblsupplier.getValueAt( tblsupplier.getSelectedRow(),7 ).toString());
        }
        else if (btneditsup.getText().equals("Update")) {
            if (txtsupplier.getText().equals("") || txtsuppliercode.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Null input not allowed");
            }
            else
            {
                sqlcode="update tblsupplier set fld_sup_code='"+ txtsupplier.getText() +"', fld_sup_name='"+ txtsuppliercode.getText() +"',"
                        + " fld_sup_street='"+ txtsupstreet.getText() +"', fld_sup_city='"+ txtsupcity.getText() +"', fld_sup_province='"+ txtsupprovince.getText() +"', "
                        + "fld_sup_mobile='"+ txtsupmobile.getText() +"', fld_sup_telno='"+ txtsuptelno.getText() +"' where fld_sup_id=" + sup_id ;

                if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                    JOptionPane.showMessageDialog(null, "Updated");
                    btncancelsupActionPerformed(evt);
                    loadsupplier();
                 

                }
//                try {
//                    sqlcode="update tblsupplier set fld_sup_code='"+ txtsuppliercode.getText() +"', fld_sup_name='"+ txtsupplier.getText() +"' where fld_sup_id=" + sup_id ;
//                    stmt=conn.prepareStatement(sqlcode);
//                    stmt.executeUpdate(sqlcode);
//                    JOptionPane.showMessageDialog(null, "Updated");
//                    btneditsup.setText("Edit");
//                    btneditsup.setEnabled(false);
//                    txtsuppliercode.setText("");
//                    txtsupplier.setText("");
//
//                    loadsupplier();
//                    btnaddstatus();
//
//                } catch (SQLException e) {
//                    JOptionPane.showMessageDialog(null, e.getMessage());
//
//                }
            }

        }
        else
        {
            JOptionPane.showMessageDialog(null, "No Item selected");

        }
    }//GEN-LAST:event_btneditsupActionPerformed

    private void btncancelsupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelsupActionPerformed
       
        btneditsup.setText("Edit");
        btneditsup.setEnabled(false);
       cleartext();
        txtsuppliercode.requestFocus();
         btnaddstatus();
         txtsuppliercode.setText("SUP"+BotiqueSystem.nextSupplierCode());
         
    }//GEN-LAST:event_btncancelsupActionPerformed

    private void btnaddsupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddsupActionPerformed
        if ((txtsupplier.getText().trim().equals("")) 
                || (txtsuppliercode.getText().trim().equals("")) 
                
                //|| (txtsupstreet.getText().trim().equals(""))
                || (txtsupcity.getText().trim().equals(""))
                || (txtsupprovince.getText().trim().equals(""))
              //  || (txtsupmobile.getText().trim().equals(""))
               // || (txtsuptelno.getText().trim().equals(""))
                ) {
            JOptionPane.showMessageDialog(null, "Null input not allowed");

        }
        else if (btneditsup.getText().equals("Update")) {
            JOptionPane.showMessageDialog(null, "Finish or Cancel Update first!");
        }
        else{
           sqlcode="insert into tblsupplier (fld_sup_code, fld_sup_name, fld_sup_street,"
                   + " fld_sup_city, fld_sup_province, fld_sup_mobile, fld_sup_telno"
                   + ")values('"+ txtsuppliercode.getText() +"',"
                   + " '"+ txtsupplier.getText() +"', '"+ txtsupstreet.getText() +"',"
                   + "'"+ txtsupcity.getText() +"',"
                   + "'"+ txtsupprovince.getText() +"',"
                   + "'"+ txtsupmobile.getText() +"', "
                   + "'"+ txtsuptelno.getText() +"')";

            if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                JOptionPane.showMessageDialog(null, "Successfully added");
                
//                txtsuppliercode.setText("");
//                txtsupplier.setText("");
                loadsupplier();
              btncancelsupActionPerformed(evt);
            }
            
//            try {
//                sqlcode="insert into tblsupplier (fld_sup_code, fld_sup_name)values('"+ txtsuppliercode.getText() +"', '"+ txtsupplier.getText() +"')";
//                stmt=conn.createStatement();
//                stmt.executeUpdate(sqlcode);
//                JOptionPane.showMessageDialog(null, "Successfully added");
//                txtsuppliercode.setText("");
//                txtsupplier.setText("");
//                loadsupplier();
//            } catch (SQLException err) {
//                JOptionPane.showMessageDialog(null, err.getMessage());
//            }
        }
    }//GEN-LAST:event_btnaddsupActionPerformed

    private void txtsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsupplierActionPerformed

    }//GEN-LAST:event_txtsupplierActionPerformed

    private void txtsupplierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupplierKeyPressed
      
    }//GEN-LAST:event_txtsupplierKeyPressed

    private void txtsupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupplierKeyTyped

    }//GEN-LAST:event_txtsupplierKeyTyped

    private void tblsupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblsupplierFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblsupplierFocusGained

    private void tblsupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsupplierMouseClicked

    }//GEN-LAST:event_tblsupplierMouseClicked

    private void tblsupplierMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsupplierMousePressed

        if (btneditsup.getText().equals("Update")) {
            JOptionPane.showMessageDialog(null, "Finish or Cancel last transaction first");
        }
        else{
            if (tblsupplier.getSelectedRowCount()>0) {
              //  btnaddsup.setEnabled(false);
                //btndeletesup.setEnabled(true);
                btneditsup.setEnabled(true);

                sup_id=Integer.parseInt(tblsupplier.getValueAt(tblsupplier.getSelectedRow(), 0).toString());

            }
            else
            {
                btnaddstatus();

            }
        }
    }//GEN-LAST:event_tblsupplierMousePressed

    private void txtsupstreetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsupstreetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupstreetActionPerformed

    private void txtsupstreetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupstreetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupstreetKeyPressed

    private void txtsupstreetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupstreetKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupstreetKeyTyped

    private void txtsupcityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsupcityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupcityActionPerformed

    private void txtsupcityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupcityKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupcityKeyPressed

    private void txtsupcityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupcityKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupcityKeyTyped

    private void txtsupprovinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsupprovinceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupprovinceActionPerformed

    private void txtsupprovinceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupprovinceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupprovinceKeyPressed

    private void txtsupprovinceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupprovinceKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupprovinceKeyTyped

    private void txtsupmobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsupmobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupmobileActionPerformed

    private void txtsupmobileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupmobileKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupmobileKeyPressed

    private void txtsupmobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupmobileKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupmobileKeyTyped

    private void txtsuptelnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsuptelnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsuptelnoActionPerformed

    private void txtsuptelnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsuptelnoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsuptelnoKeyPressed

    private void txtsuptelnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsuptelnoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsuptelnoKeyTyped

    private void txtsupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupplierKeyReleased
        if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{ txtsupplier.setText(BotiqueSystem.toTitleCase(txtsupplier.getText()));}
    }//GEN-LAST:event_txtsupplierKeyReleased

    private void txtsupcityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupcityKeyReleased
         if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{ txtsupcity.setText(BotiqueSystem.toTitleCase(txtsupcity.getText()));}
    }//GEN-LAST:event_txtsupcityKeyReleased

    private void txtsupprovinceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupprovinceKeyReleased
        if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{txtsupprovince.setText(BotiqueSystem.toTitleCase(txtsupprovince.getText()));}
    }//GEN-LAST:event_txtsupprovinceKeyReleased

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
       txtsearch.setText(BotiqueSystem.toTitleCase(txtsearch.getText()));
       DefaultTableModel tblmodel =new DefaultTableModel();
        tblmodel.addColumn("ID");
        tblmodel.addColumn("Code");
        tblmodel.addColumn("Supplier");
        //tblmodel.addColumn("Zone");
        tblmodel.addColumn("Street");
        tblmodel.addColumn("City");
        tblmodel.addColumn("Province");
        tblmodel.addColumn("Mobile no.");
        tblmodel.addColumn("Tel no.");
        
        try {
            if (txtsearch.getText().equals("")) {
                loadsupplier();
            }
            else{
                
            
            sqlcode="select * from tblsupplier where fld_sup_name like '%"+ txtsearch.getText() +"%' order by fld_sup_name asc";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sqlcode);
            
            while (rs.next()) {
                tblmodel.addRow(new Object[]{
                rs.getString("fld_sup_id"),
               rs.getString("fld_sup_code"),
               rs.getString("fld_sup_name"),
               
               //rs.getString("fld_sup_zone"),
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
               hidecolumn() ;
             }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(rootPane, err.getMessage());
        }
       
    }//GEN-LAST:event_txtsearchKeyReleased

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
            java.util.logging.Logger.getLogger(frmSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSupplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddsup;
    private javax.swing.JButton btncancelsup;
    private javax.swing.JButton btneditsup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbltime;
    private javax.swing.JLabel lbluser;
    private javax.swing.JTable tblsupplier;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsupcity;
    private javax.swing.JTextField txtsupmobile;
    private javax.swing.JTextField txtsupplier;
    private javax.swing.JTextField txtsuppliercode;
    private javax.swing.JTextField txtsupprovince;
    private javax.swing.JTextField txtsupstreet;
    private javax.swing.JTextField txtsuptelno;
    // End of variables declaration//GEN-END:variables
}
