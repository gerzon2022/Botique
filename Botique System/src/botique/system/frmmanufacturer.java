/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;
import static botique.system.BotiqueSystem.conn_db;
import com.sun.glass.events.KeyEvent;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.runtime.regexp.joni.constants.AnchorType;


public class frmmanufacturer extends javax.swing.JFrame {
String sqlcode;
Connection conn=null;
Statement stmt=null;
ResultSet rs=null;
int manu_id;

    
    public frmmanufacturer() {
        initComponents();
        conn=BotiqueSystem.conn_db();
        loadmanufacturer();
        btnaddstatus();
        btncancelmanuActionPerformed(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btneditmanu = new javax.swing.JButton();
        btndeletemanu = new javax.swing.JButton();
        btncancelmanu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnaddmanu = new javax.swing.JButton();
        txtmanucode = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmanufacturer = new javax.swing.JTable();
        txtmanufacturer = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Manufacturer Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btneditmanu.setText("Edit");
        btneditmanu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditmanuActionPerformed(evt);
            }
        });
        jPanel1.add(btneditmanu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 206, 40));

        btndeletemanu.setText("Delete");
        btndeletemanu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletemanuActionPerformed(evt);
            }
        });
        jPanel1.add(btndeletemanu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 206, 40));

        btncancelmanu.setText("Cancel");
        btncancelmanu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelmanuActionPerformed(evt);
            }
        });
        jPanel1.add(btncancelmanu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 206, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Code");
        jLabel1.setAlignmentX(0.5F);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 206, -1));

        btnaddmanu.setText("Add");
        btnaddmanu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddmanuActionPerformed(evt);
            }
        });
        jPanel1.add(btnaddmanu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 206, 40));

        txtmanucode.setEditable(false);
        txtmanucode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmanucode.setFocusable(false);
        txtmanucode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmanucodeKeyReleased(evt);
            }
        });
        jPanel1.add(txtmanucode, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 206, 30));

        tblmanufacturer.setModel(new javax.swing.table.DefaultTableModel(
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
        tblmanufacturer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblmanufacturerFocusGained(evt);
            }
        });
        tblmanufacturer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmanufacturerMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblmanufacturerMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblmanufacturer);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 360, 300));

        txtmanufacturer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmanufacturer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmanufacturerKeyReleased(evt);
            }
        });
        jPanel1.add(txtmanufacturer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 206, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Name");
        jLabel3.setAlignmentX(0.5F);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 206, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 620, 380));

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Add Manufacturer");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbluser.setText("Welcome");
        jPanel4.add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Welcome");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 640, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static String nextID(){
        String nextnum="2000";
        try {
            String sqlcode="";
            
            ResultSet rs=null;
            Connection conn=BotiqueSystem.conn_db();
                 sqlcode=" SELECT auto_increment FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'tblmanufacturer' ";
                 rs=BotiqueSystem.selectquery(sqlcode, conn);
                 if (rs.next()) {
                nextnum= String.valueOf(rs.getInt(1));
            }
                 
        } catch (SQLException  e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return  nextnum;
    }
    public void btnaddstatus(){
        
        
         if ( btneditmanu.getText().equals("Update") ) {
//             btnaddcat.setEnabled(false);
//             btndeletecat.setEnabled(false);
//             btneditcat.setEnabled(false);
             btneditmanu.setEnabled(true);
             btnaddmanu.setEnabled(false);
            
            btndeletemanu.setEnabled(false);
            
           
             
         } else {
              btnaddmanu.setEnabled(true); 
              btneditmanu.setEnabled(false);
              btndeletemanu.setEnabled(false);
         }
        

      
    }
    public void loadmanufacturer(){
        DefaultTableModel tblmodel =new DefaultTableModel();
        tblmodel.addColumn("ID");
        tblmodel.addColumn("Code");
        tblmodel.addColumn("Manufacturer");
        try {
            sqlcode="select * from tblmanufacturer";
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sqlcode);
            
            while (rs.next()) {
                tblmodel.addRow(new Object[]{
                rs.getString("fld_manu_id"),
               rs.getString("fld_manu_code"),
               rs.getString("fld_manu_name")
                });
            }
            tblmanufacturer.setModel(tblmodel);
              rs.close();
              stmt.close();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(rootPane, err.getMessage());
        }
        
    }
    
    
    String manufacturer="";
    private void btneditmanuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditmanuActionPerformed
        String sqlcod="";
        if (tblmanufacturer.getSelectedRowCount() >0 && btneditmanu.getText().equals("Edit")) {
            btneditmanu.setText("Update");
            btndeletemanu.setEnabled(false);
            txtmanucode.setText(tblmanufacturer.getValueAt( tblmanufacturer.getSelectedRow(),1 ).toString());
            txtmanufacturer.setText(tblmanufacturer.getValueAt( tblmanufacturer.getSelectedRow(),2 ).toString());
            manufacturer=tblmanufacturer.getValueAt( tblmanufacturer.getSelectedRow(),2 ).toString();
           
        }
        else if (btneditmanu.getText().equals("Update")) {
            if (txtmanucode.getText().equals("") || txtmanufacturer.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Null input not allowed");
            }
            else
            {
          sqlcode="update tblmanufacturer set fld_manu_code='"+ txtmanucode.getText() +"', fld_manu_name='"+ txtmanufacturer.getText() +"' where fld_manu_id=" + manu_id ;
          sqlcod="update tblitemdetails set fld_manu_name='"+ txtmanufacturer.getText() +"' where fld_manu_name='"+ manufacturer +"'";
          JOptionPane.showMessageDialog(null, manufacturer);
                if ((BotiqueSystem.addupdatedel(sqlcode, conn))&& (BotiqueSystem.addupdatedel(sqlcod, conn))) {
                   JOptionPane.showMessageDialog(null, "Updated");
                   loadmanufacturer();
                    btncancelmanuActionPerformed(evt);
//                    btneditmanu.setText("Edit");
//                     btneditmanu.setEnabled(false);
//                    txtmanucode.setText("");
//                    txtmanufacturer.setText("");
                   
                    
                   
                }
//                try {
//                    sqlcode="update tblmanufacturer set fld_manu_code='"+ txtmanucode.getText() +"', fld_manu_name='"+ txtmanufacturer.getText() +"' where fld_manu_id=" + manu_id ;
//                    stmt=conn.prepareStatement(sqlcode);
//                    stmt.executeUpdate(sqlcode);
//                    JOptionPane.showMessageDialog(null, "Updated");
//                    btneditmanu.setText("Edit");
//                     btneditmanu.setEnabled(false);
//                    txtmanucode.setText("");
//                    txtmanufacturer.setText("");
//                   
//                    loadmanufacturer();
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
    }//GEN-LAST:event_btneditmanuActionPerformed
String sqlcode2="";
    private void btndeletemanuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletemanuActionPerformed
        manufacturer=tblmanufacturer.getValueAt( tblmanufacturer.getSelectedRow(),2 ).toString();
        JOptionPane.showMessageDialog(null, manufacturer);
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this data?", "Deleting...", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
             
                    sqlcode="delete from tblmanufacturer where fld_manu_id="+ manu_id ;
                    sqlcode2="Update tblitemdetails set fld_manu_name='-' where fld_manu_name='"+ manufacturer +"'";
                    
                if ((BotiqueSystem.addupdatedel(sqlcode, conn)) &&(BotiqueSystem.addupdatedel(sqlcode2, conn))) {
                    JOptionPane.showMessageDialog(null, "Deleted");
                    btncancelmanuActionPerformed(evt);
                    loadmanufacturer();
                }
        }
       
//        try {
//            sqlcode="delete from tblmanufacturer where fld_manu_id="+ manu_id ;
//            stmt=conn.prepareStatement(sqlcode);
//            stmt.executeUpdate(sqlcode);
//            JOptionPane.showMessageDialog(null, "Deleted");
//
//            btnaddstatus();
//            loadmanufacturer();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }

    }//GEN-LAST:event_btndeletemanuActionPerformed

    private void btncancelmanuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelmanuActionPerformed
        
        btneditmanu.setText("Edit");
         btneditmanu.setEnabled(false);
        txtmanucode.setText("");
        txtmanufacturer.setText("");
        txtmanufacturer.requestFocus();
        btnaddstatus();
        txtmanucode.setText("MANU"+nextID());

    }//GEN-LAST:event_btncancelmanuActionPerformed

    private void btnaddmanuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddmanuActionPerformed
        if ((txtmanucode.getText().trim().equals("")) || (txtmanufacturer.getText().trim().equals("") )) {
            JOptionPane.showMessageDialog(null, "Null input not allowed");

        }
        else{
        sqlcode="insert into tblmanufacturer(fld_manu_code, fld_manu_name)values('"+ txtmanucode.getText() +"', '"+ txtmanufacturer.getText() +"')";

            if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                JOptionPane.showMessageDialog(null, "Successfully added");
                btncancelmanuActionPerformed(evt);
//                txtmanucode.setText("");
//                txtmanufacturer.setText("");
               loadmanufacturer();
            }
//            try {
//                sqlcode="insert into tblmanufacturer(fld_manu_code, fld_manu_name)values('"+ txtmanucode.getText() +"', '"+ txtmanufacturer.getText() +"')";
//                stmt=conn.createStatement();
//                stmt.executeUpdate(sqlcode);
//                JOptionPane.showMessageDialog(null, "Successfully added");
//                txtmanucode.setText("");
//                txtmanufacturer.setText("");
//               loadmanufacturer();
//            } catch (SQLException err) {
//                JOptionPane.showMessageDialog(null, err.getMessage());
//            }
        }
    }//GEN-LAST:event_btnaddmanuActionPerformed

    private void tblmanufacturerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblmanufacturerFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblmanufacturerFocusGained

    private void tblmanufacturerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmanufacturerMouseClicked

    }//GEN-LAST:event_tblmanufacturerMouseClicked

    private void tblmanufacturerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmanufacturerMousePressed
        
        if (btneditmanu.getText().equals("Update")) {
            JOptionPane.showMessageDialog(null, "Finish or Cancel last transaction first");
        }
        else{
            if (tblmanufacturer.getSelectedRowCount()>0) {
                btnaddmanu.setEnabled(false);
                btndeletemanu.setEnabled(true);
                btneditmanu.setEnabled(true);

                manu_id=Integer.parseInt(tblmanufacturer.getValueAt(tblmanufacturer.getSelectedRow(), 0).toString());
                
            }
            else
            {
                btnaddstatus();

            }
        }

    }//GEN-LAST:event_tblmanufacturerMousePressed

    private void txtmanufacturerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmanufacturerKeyReleased
        if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{ txtmanufacturer.setText(BotiqueSystem.toTitleCase(txtmanufacturer.getText()));
         btnaddstatus();}
    }//GEN-LAST:event_txtmanufacturerKeyReleased

    private void txtmanucodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmanucodeKeyReleased
        if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{ txtmanucode.setText(BotiqueSystem.toTitleCase(txtmanucode.getText()));
         btnaddstatus();}
    }//GEN-LAST:event_txtmanucodeKeyReleased

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
            java.util.logging.Logger.getLogger(frmmanufacturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmmanufacturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmmanufacturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmmanufacturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmmanufacturer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddmanu;
    private javax.swing.JButton btncancelmanu;
    private javax.swing.JButton btndeletemanu;
    private javax.swing.JButton btneditmanu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbluser;
    private javax.swing.JTable tblmanufacturer;
    private javax.swing.JTextField txtmanucode;
    private javax.swing.JTextField txtmanufacturer;
    // End of variables declaration//GEN-END:variables
}
