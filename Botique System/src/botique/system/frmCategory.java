

package botique.system;

import com.sun.glass.events.KeyEvent;
import java.awt.AWTEvent;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
public class frmCategory extends javax.swing.JFrame {
    String sqlcode;
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
    
    int cat_id;
    public void loadcat(){
        DefaultTableModel tblmodel = new DefaultTableModel(){public boolean isCellEditable(int row, int column){return false;}};
        tblmodel.addColumn("Category ID");
        tblmodel.addColumn("Category Name");
     
        tblCategory.setModel(tblmodel);
        try {
            
             sqlcode="select * from tblcategory";
       stmt=conn.createStatement();
        rs= stmt.executeQuery(sqlcode);
        
        while (rs.next()) {
               tblmodel.addRow(new Object[]{
                  rs.getObject(1),
                   rs.getObject(2)
                     
               });
                }
                tblCategory.setModel(tblmodel);
                
                  
        rs.close();
        stmt.close();
         
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(rootPane, err.getMessage());
        }
       
        
    }
    
    
     public void btnaddstatus(){
         if (btneditcat.getText().equals("Update")) {
             btnaddcat.setEnabled(false);
         }
         else{
              btnaddcat.setEnabled(true);
             btndeletecat.setEnabled(false);
             btneditcat.setEnabled(false);
             
         }
            
        
}
   
    public frmCategory() {
        initComponents();
        conn=BotiqueSystem.conn_db();
        loadcat();
        btndeletecat.setEnabled(false);
        btneditcat.setEnabled(false);
        Timer t= new Timer(1000, dateTime);  
        t.start();
         lbluser.setText(BotiqueSystem.user);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnaddcat = new javax.swing.JButton();
        txtcatname = new javax.swing.JTextField();
        btneditcat = new javax.swing.JButton();
        btndeletecat = new javax.swing.JButton();
        btncancelcat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategory = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbltime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Add Category", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Category name");
        jLabel1.setAlignmentX(0.5F);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 330, -1));

        btnaddcat.setText("Add");
        btnaddcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddcatActionPerformed(evt);
            }
        });
        jPanel1.add(btnaddcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 330, 40));

        txtcatname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcatname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcatnameActionPerformed(evt);
            }
        });
        txtcatname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcatnameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcatnameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcatnameKeyTyped(evt);
            }
        });
        jPanel1.add(txtcatname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 330, 40));

        btneditcat.setText("Edit");
        btneditcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditcatActionPerformed(evt);
            }
        });
        jPanel1.add(btneditcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 330, 40));

        btndeletecat.setText("Delete");
        btndeletecat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletecatActionPerformed(evt);
            }
        });
        jPanel1.add(btndeletecat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 330, 40));

        btncancelcat.setText("Cancel");
        btncancelcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelcatActionPerformed(evt);
            }
        });
        jPanel1.add(btncancelcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 330, 40));

        tblCategory.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCategory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblCategoryFocusGained(evt);
            }
        });
        tblCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoryMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCategoryMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategory);
        if (tblCategory.getColumnModel().getColumnCount() > 0) {
            tblCategory.getColumnModel().getColumn(0).setResizable(false);
            tblCategory.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 254, 260));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 660, 330));

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("ADD CATEGORY");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbluser.setText("Welcome");
        jPanel4.add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbldate.setText("Welcome");
        jPanel4.add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Welcome");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        lbltime.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbltime.setText("Welcome");
        jPanel4.add(lbltime, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 720, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     ActionListener dateTime =new  ActionListener() {
            
          
      @Override
      public void actionPerformed(ActionEvent e) {
          SimpleDateFormat df=new SimpleDateFormat("M-dd-yyyy");
          SimpleDateFormat df2 = new SimpleDateFormat("EEEE");
          SimpleDateFormat df3 = new SimpleDateFormat("hh:mm:ss a");
           Date now= new Date();
          String strdate= df.format(new Date());
          String strday= df2.format(now);
          
          String strtime= df3.format(now);
          
          lbltime.setText(strtime);
           lbldate.setText(strdate+", "+strday);//To change body of generated methods, choose Tools | Templates.
      }
  };
    private void btnaddcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddcatActionPerformed
        if (txtcatname.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Null input not allowed");
            
        }
        else{
             sqlcode="insert into tblcategory(fld_catname)values('"+ txtcatname.getText() +"')";
            if (BotiqueSystem.addupdatedel(sqlcode, conn)==true) {
                JOptionPane.showMessageDialog(null, "Saved!");
                loadcat();
                btncancelcatActionPerformed(evt);
            }
            
//            try {
//                sqlcode="insert into tblcategory(fld_catname)values('"+ txtcatname.getText() +"')";
//                stmt=conn.prepareStatement(sqlcode);
//                stmt.executeUpdate(sqlcode);
//                JOptionPane.showMessageDialog(null, "Successfully added");
//                txtcatname.setText(null);
//              loadcat();
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage());
//            }
        }
    }//GEN-LAST:event_btnaddcatActionPerformed

    private void txtcatnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcatnameActionPerformed
        
    }//GEN-LAST:event_txtcatnameActionPerformed

    private void txtcatnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcatnameKeyPressed
       btnaddstatus();
    }//GEN-LAST:event_txtcatnameKeyPressed

    private void txtcatnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcatnameKeyTyped
        
    }//GEN-LAST:event_txtcatnameKeyTyped

    private void tblCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoryMouseClicked
        
    }//GEN-LAST:event_tblCategoryMouseClicked

    private void btndeletecatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletecatActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this data?", "Deleting...", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    sqlcode="delete from tblcategory where fld_cat_id="+ cat_id ;

               if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                   JOptionPane.showMessageDialog(null, "Deleted");
                          btncancelcatActionPerformed(evt);
                           loadcat();
               }
        }

       

//        try {
//            sqlcode="delete from tblcategory where fld_cat_id="+ cat_id ;
//       stmt=conn.prepareStatement(sqlcode);
//       stmt.executeUpdate(sqlcode);
//       JOptionPane.showMessageDialog(null, "Deleted");
//        
//       btnaddstatus();
//       loadcat();
//        } catch (SQLException e) {
//        JOptionPane.showMessageDialog(null, e.getMessage());
//        }
        
       
    }//GEN-LAST:event_btndeletecatActionPerformed
String sqlcode1="", oldcatname="";

    private void btneditcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditcatActionPerformed
        if (tblCategory.getSelectedRowCount() >0 && btneditcat.getText().equals("Edit")) {
            btneditcat.setText("Update");
            btndeletecat.setEnabled(false);
            txtcatname.setText(tblCategory.getValueAt( tblCategory.getSelectedRow(),1 ).toString());
             oldcatname=txtcatname.getText().trim();
        }
        else if (btneditcat.getText().equals("Update")) {
            if (txtcatname.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Null input not allowed");
            }
            else
            {
                sqlcode="update tblcategory set fld_catname='"+ txtcatname.getText() +"' where fld_cat_id=" + cat_id +"; ";
                sqlcode1="update tblitemdetails set fld_catname='"+ txtcatname.getText() +"' where fld_catname='"+ oldcatname+"' ;" ;
                JOptionPane.showMessageDialog(null, sqlcode+sqlcode1);
                if ((BotiqueSystem.addupdatedel(sqlcode+sqlcode1, conn)) ) {//&& (BotiqueSystem.addupdatedel(sqlcode1, conn))
                    JOptionPane.showMessageDialog(null, "Updated");
                    btncancelcatActionPerformed(evt);
                    loadcat();
                    btnaddstatus();
                }

            }
           
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No Item selected");
            
        }
    }//GEN-LAST:event_btneditcatActionPerformed

    private void btncancelcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelcatActionPerformed
       
        btneditcat.setText("Edit");
        txtcatname.setText("");
        txtcatname.requestFocus();
         btnaddstatus();
       
    }//GEN-LAST:event_btncancelcatActionPerformed

    private void tblCategoryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCategoryFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCategoryFocusGained

    private void tblCategoryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoryMousePressed
      if (btneditcat.getText().equals("Update")) {
            JOptionPane.showMessageDialog(null, "Finish or Cancel last transaction first");
        }
        else{
                    if (tblCategory.getSelectedRowCount()>0) {
                   btnaddcat.setEnabled(false);
                   btndeletecat.setEnabled(true);
                   btneditcat.setEnabled(true);

                   cat_id=(int)tblCategory.getValueAt(tblCategory.getSelectedRow(), 0);
               }
               else
               {
                   btnaddstatus();

               }
        }
       
    }//GEN-LAST:event_tblCategoryMousePressed

    private void txtcatnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcatnameKeyReleased
        
        if ((evt.getKeyCode()==KeyEvent.VK_LEFT) || (evt.getKeyCode()==KeyEvent.VK_RIGHT)|| (evt.getKeyCode()==KeyEvent.VK_BACKSPACE)) {
         //do nothing
         }
        else{
             txtcatname.setText(BotiqueSystem.toTitleCase(txtcatname.getText()));
        }
                
       
        
    }//GEN-LAST:event_txtcatnameKeyReleased

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCategory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddcat;
    private javax.swing.JButton btncancelcat;
    private javax.swing.JButton btndeletecat;
    private javax.swing.JButton btneditcat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbltime;
    private javax.swing.JLabel lbluser;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTextField txtcatname;
    // End of variables declaration//GEN-END:variables
}
