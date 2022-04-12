/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botique.system;

import static botique.system.BotiqueSystem.conn_db;
import com.sun.javafx.print.Units;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.*;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.ws.Service;
import java.time.Duration;




/**
 *
 * @author Admin
 */
public class frmcreatepo extends javax.swing.JFrame {
  
static String supcode;
static String supname;
static String supzone;
static String supstreet;
static String supcity;
static String supprov;

String ref_code=null;

    String sqlcode;
    int refid;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;

    public frmcreatepo() {
        initComponents();
        conn = BotiqueSystem.conn_db();
        loadpono();
        
        startup();
        btnponocancelActionPerformed(null);
        
        
    }
        
    
    public Date dateformat(String datestring) {
        Date thisdate = null;
        try {
            DateFormat a = new SimpleDateFormat("MM-dd-yyyy");
            thisdate = a.parse(datestring);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return thisdate;
    }

    public void loadpono() {
        DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int colIndex){return false;}};

        model.addColumn("P.O. No.");
        model.addColumn("P.O. Date");
        model.addColumn("Supplier");
        model.addColumn("Code");
        model.addColumn("Date Expected");
        
        
        sqlcode = "select tblpono.fld_po_no, date_format(tblpono.fld_po_date, '%m-%d-%Y'),"
                + "date_format(tblpono.fld_expected_date, '%m-%d-%Y'),tblpono.fld_sup_code,"
                + " tblsupplier.fld_sup_name from tblpono inner join tblsupplier"
                + " on tblpono.fld_sup_code=tblsupplier.fld_sup_code "
                + "order by tblpono.fld_po_date desc";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlcode);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("tblpono.fld_po_no"),
                    rs.getString("date_format(tblpono.fld_po_date, '%m-%d-%Y')"),
                    rs.getString("tblsupplier.fld_sup_name"),
                     rs.getString("tblpono.fld_sup_code"),
                    rs.getString("date_format(tblpono.fld_expected_date, '%m-%d-%Y')")
                   
                    
                
                
                });

            }
            tblpono.setModel(model);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void loadtblsup() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("P.O. No.");
        model.addColumn("P.O. Date");
        model.addColumn("Date Expected");
        model.addColumn("Supplier");
        sqlcode = "select * from tblpono";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlcode);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("fld_po_no"),
                    rs.getString("fld_po_date"),
                    rs.getString("fld_expected_date"),
                    rs.getString("fld_sup_code"),});

            }
            tblpono.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void startup() {
        if (btnponoedit.getText().equals("Update")) {
        btncreatepoadd.setEnabled(false);
        btnselectsup.setEnabled(false);
        }
        else{
         btncreatepoadd.setEnabled(true);
          btnselectsup.setEnabled(true);
        btnponocancel.setEnabled(true);
        btnponodelete.setEnabled(false);
        btnponoedit.setEnabled(false);
        btnponoedit.setText("Edit");
        }
        txtpodate.setText(BotiqueSystem.strdatenow());
        txtpoexpected.setText(BotiqueSystem.strdatenow());
    
    }

    public void cleartext() {
        txtposuppliercode.setText("");
        txtpodate.setText("");
        txtpoexpected.setText("");
        txtpono.setText("");
        txtsupdetails.setText("");
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
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpono = new javax.swing.JTable();
        btnselectpono = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtpodate = new javax.swing.JFormattedTextField();
        txtpono = new javax.swing.JTextField();
        btnponoedit = new javax.swing.JButton();
        btnponodelete = new javax.swing.JButton();
        btnponocancel = new javax.swing.JButton();
        txtpoexpected = new javax.swing.JFormattedTextField();
        txtposuppliercode = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnselectsup = new javax.swing.JButton();
        btncreatepoadd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtsupdetails = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel17.setText("Select/Create p.O. no.");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 860, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Select P.O. no."));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblpono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblpono.setModel(new javax.swing.table.DefaultTableModel(
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
        tblpono.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblpono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblponoFocusLost(evt);
            }
        });
        tblpono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblponoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblponoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblpono);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 450, 260));

        btnselectpono.setText("Select");
        btnselectpono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselectponoActionPerformed(evt);
            }
        });
        jPanel1.add(btnselectpono, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 191, 30));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("P.O. Nos");
        jLabel15.setAlignmentX(0.5F);
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 127, 20));

        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsearchKeyTyped(evt);
            }
        });
        jPanel1.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 130, -1));

        jLabel2.setText("Search");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 490, 380));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Create P.O. no."));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("P.O No.");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("P.O Date");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Expected Date");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, -1));

        txtpodate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("mM-dd-yyyy"))));
        txtpodate.setFocusable(false);
        txtpodate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpodateFocusLost(evt);
            }
        });
        txtpodate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpodateActionPerformed(evt);
            }
        });
        jPanel2.add(txtpodate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 180, -1));

        txtpono.setEditable(false);
        txtpono.setFocusable(false);
        txtpono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtponoActionPerformed(evt);
            }
        });
        txtpono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtponoKeyPressed(evt);
            }
        });
        jPanel2.add(txtpono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 180, -1));

        btnponoedit.setText("Edit");
        btnponoedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnponoeditActionPerformed(evt);
            }
        });
        jPanel2.add(btnponoedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 191, -1));

        btnponodelete.setText("Delete");
        btnponodelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnponodeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnponodelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 191, -1));

        btnponocancel.setText("Cancel");
        btnponocancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnponocancelActionPerformed(evt);
            }
        });
        jPanel2.add(btnponocancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 191, -1));

        txtpoexpected.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("mm-dd-yyyy"))));
        txtpoexpected.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpoexpectedFocusLost(evt);
            }
        });
        jPanel2.add(txtpoexpected, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 180, -1));

        txtposuppliercode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtposuppliercodeFocusLost(evt);
            }
        });
        txtposuppliercode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtposuppliercodeKeyPressed(evt);
            }
        });
        jPanel2.add(txtposuppliercode, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 180, -1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Supplier Code");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 70, -1));

        btnselectsup.setText("...");
        btnselectsup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselectsupActionPerformed(evt);
            }
        });
        jPanel2.add(btnselectsup, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 30, -1));

        btncreatepoadd.setText("Create");
        btncreatepoadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreatepoaddActionPerformed(evt);
            }
        });
        jPanel2.add(btncreatepoadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 190, -1));

        txtsupdetails.setEditable(false);
        txtsupdetails.setColumns(20);
        txtsupdetails.setLineWrap(true);
        txtsupdetails.setRows(5);
        txtsupdetails.setAutoscrolls(false);
        txtsupdetails.setFocusable(false);
        jScrollPane2.setViewportView(txtsupdetails);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 189, 80));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Supplier Details");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 360, 380));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 860, 40));
        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 120, 30, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtpodateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpodateFocusLost

        if ((txtpodate.getText().trim() == null )||( txtpodate.getText().trim().length()>10)) {
            JOptionPane.showMessageDialog(null, "Enter mm-dd-yyyy format!");
            txtpodate.requestFocus();
           
        }
        else
        {
            
            if (BotiqueSystem.validdate(txtpodate.getText())) {
                        try {
                        SimpleDateFormat sdf= new SimpleDateFormat("MM-dd-yyyy");
                        Date date=null;
                        Date date2day=null;
                        date2day = sdf.parse(BotiqueSystem.strdatenow());
                        date=sdf.parse(txtpodate.getText());
                           
                           
                                  //  JOptionPane.showMessageDialog(null, (date.getTime()- date2day.getTime())/(1000*60*60*24));
                       
                        if (date.after(date2day) || (date.before(date2day))) {
                            JOptionPane.showMessageDialog(null, "P.O. should be dated today");
                            txtpodate.setText(BotiqueSystem.strdatenow());
                            txtpodate.requestFocus();
                        }
                        else{
                            txtpoexpected.setText(BotiqueSystem.strdatenow());
                        }

                    } catch (ParseException e) {
                       txtpodate.setText(BotiqueSystem.strdatenow());
                       txtpodate.requestFocus();
                    }
            }
            else{
                JOptionPane.showMessageDialog(null, "Enter mm-dd-yyyy format!");
               txtpodate.requestFocus();
                txtpodate.setText(BotiqueSystem.strdatenow());
            }
            
        }

    }//GEN-LAST:event_txtpodateFocusLost

    private void btnponodeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnponodeleteActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this data?", "Deleting...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            sqlcode = "delete from tblpono where fld_po_no = " + refid;

            if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                JOptionPane.showMessageDialog(null, "Deleted!");
                btnponocancelActionPerformed(evt);

            }

        }
        else{
            btnponocancelActionPerformed(evt);
        }
    }//GEN-LAST:event_btnponodeleteActionPerformed

    private void btncreatepoaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreatepoaddActionPerformed
      
                if (txtpodate.getText().trim().equals("") || txtpoexpected.getText().trim().equals("") || txtpono.getText().trim().equals("") || txtposuppliercode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty input not allowed!");
        } else {
            sqlcode = "insert into tblpono(fld_po_date, fld_expected_date, fld_sup_code)values(STR_TO_DATE('" + txtpodate.getText() + "','%m-%d-%Y')"
                    + ",str_to_date('" + txtpoexpected.getText() + "','%m-%d-%Y'),'" + txtposuppliercode.getText().trim() + "')";
           
            if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                JOptionPane.showMessageDialog(null, "Added!");
                btnponocancelActionPerformed(evt);
                
            }
        }
                
    }//GEN-LAST:event_btncreatepoaddActionPerformed

    private void btnponocancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnponocancelActionPerformed
        cleartext();
        btnponoedit.setText("Edit");
          
        loadpono();
        startup();
        txtpono.setText(nextID());
       
       
    }//GEN-LAST:event_btnponocancelActionPerformed
public static String nextID(){
        String nextnum="2000";
        try {
            String sqlcode="";
            
            ResultSet rs=null;
            Connection conn=BotiqueSystem.conn_db();
                 sqlcode=" SELECT auto_increment FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'tblpono' ";
                 rs=BotiqueSystem.selectquery(sqlcode, conn);
                 if (rs.next()) {
                nextnum= String.valueOf(rs.getInt(1));
            }
                 
        } catch (SQLException  e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return  nextnum;
    }
    private void btnponoeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnponoeditActionPerformed
        if (tblpono.getSelectedRowCount() > 0 && btnponoedit.getText().equals("Edit")) {
            btnponoedit.setText("Update");
            btnponodelete.setEnabled(false);
            txtpono.setText(tblpono.getValueAt(tblpono.getSelectedRow(), 0).toString());
            txtpodate.setText(tblpono.getValueAt(tblpono.getSelectedRow(), 1).toString());
            txtpoexpected.setText(tblpono.getValueAt(tblpono.getSelectedRow(), 2).toString());
            txtposuppliercode.setText(tblpono.getValueAt(tblpono.getSelectedRow(), 3).toString());
            btnselectsup.setEnabled(true);
        } else if (btnponoedit.getText().equals("Update")) {
            if (txtpodate.getText().trim().equals(null) || txtpoexpected.getText().trim().equals(null) || txtpono.getText().trim().equals(null) || txtposuppliercode.getText().trim().equals(null)) {
                JOptionPane.showConfirmDialog(null, "Null input invalid!");
            } else {
                sqlcode = "update tblpono set fld_po_date='" + txtpodate.getText() + "', fld_expected_date='" + txtpoexpected.getText() + "', fld_supplier='" + txtposuppliercode.getText() + "' where fld_po_no=" + refid;
                if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                    JOptionPane.showMessageDialog(null, "Updated");
                    
                    btnponocancelActionPerformed(evt);

                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "No Item selected");

        }
    }//GEN-LAST:event_btnponoeditActionPerformed

    private void txtpoexpectedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpoexpectedFocusLost

         if ((txtpoexpected.getText().trim() == null )||( txtpoexpected.getText().trim().length()>10)) {
            JOptionPane.showMessageDialog(null, "Enter mm-dd-yyyy format only");
            txtpoexpected.setText(BotiqueSystem.strdatenow());
            txtpoexpected.requestFocus();
         }
         else{
                    if (BotiqueSystem.validdate(txtpoexpected.getText())) {

                          try {
                          SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                          Date podate = formatter.parse(txtpodate.getText());
                          Date expceteddate = formatter.parse(txtpoexpected.getText());
                     
                          if (expceteddate.before(podate)) {
                              JOptionPane.showMessageDialog(null, "Expected date should be after or same with P.O. date");
                              txtpoexpected.setText(BotiqueSystem.strdatenow());
                              txtpoexpected.requestFocus();
                          }
                      } catch (Exception e) {
                          JOptionPane.showMessageDialog(null, e.getMessage());
                          txtpoexpected.setText(BotiqueSystem.strdatenow());
                              txtpoexpected.requestFocus();
                      }
              }
              else{
                  JOptionPane.showMessageDialog(null, "Enter mm-dd-yyyy format only");
                  txtpoexpected.setText(BotiqueSystem.strdatenow());
                  txtpoexpected.requestFocus();
              }
        
         }
       
       

//        
//           if (!(validdate(txtpoexpected.getText())) || txtpoexpected.getText().trim().equals(null)) {
//                JOptionPane.showMessageDialog(null, "mm-dd-yyyy format only!");
//                txtpoexpected.requestFocus();
//            } else {
//               if (txtpoexpected.getText().before(txtpodate.getText())) {
//                   JOptionPane.showMessageDialog(null, );
//               }
//            }
    }//GEN-LAST:event_txtpoexpectedFocusLost
 
    public void selectfromtbl(){
        btncreatepoadd.setEnabled(false);
        btnponoedit.setEnabled(true);
        btnponodelete.setEnabled(true);
        btnselectsup.setEnabled(false);
    }
 
    
    
    private void tblponoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblponoMousePressed

        if (btnponoedit.getText().equals("Update")) {
            JOptionPane.showMessageDialog(null, "Finish or Cancel Update");
        }
        else{
                    if (tblpono.getSelectedRowCount() >0) {
                   selectfromtbl();
                        }
                        else{
                            btnponocancelActionPerformed(null);
                        }
        }
       
    }//GEN-LAST:event_tblponoMousePressed

    private void tblponoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblponoFocusLost
     
    }//GEN-LAST:event_tblponoFocusLost

    private void txtponoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtponoKeyPressed
        char lang=evt.getKeyChar();
        startup();
        if (BotiqueSystem.isNumber(evt)) {
            txtpono.setText("");
            txtpono.requestFocus();
        }
        
    }//GEN-LAST:event_txtponoKeyPressed

    private void btnselectsupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselectsupActionPerformed
               
                frmselectsup selectsup = new frmselectsup();
               selectsup.setVisible(true); 
              
    }//GEN-LAST:event_btnselectsupActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
      // txtposuppliercode.setText(supcode);
//       txtcreatposup.setText(supname);
//       txtsupzone.setText(supzone);
//       txtsupstreet.setText(supstreet);
//       txtsupcity.setText(supcity);
//       txtsupprovince.setText(supprov);
    }//GEN-LAST:event_formWindowGainedFocus

    private void txtponoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtponoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtponoActionPerformed

    private void txtpodateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpodateActionPerformed
     
    }//GEN-LAST:event_txtpodateActionPerformed

    private void btnselectponoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselectponoActionPerformed
           
               
        
            if (tblpono.getSelectedRowCount() >0) {
                 if (BotiqueSystem.frmopened.equals("P.O.")) {
                frmPO.txtpono.setText(tblpono.getValueAt(tblpono.getSelectedRow(), 0).toString());
                this.dispose();
                frmPO.txtpono.requestFocus();
                }
                 else{
                frmStockin2.txtpono.setText(tblpono.getValueAt(tblpono.getSelectedRow(), 0).toString());
                this.dispose();
                frmStockin2.txtpono.requestFocus();
                 }
                
                

            }
            else{
                JOptionPane.showMessageDialog(null, "No item selected!");
            }
        
    }//GEN-LAST:event_btnselectponoActionPerformed

    private void tblponoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblponoMouseClicked
        if (evt.getClickCount()==2) {
            btnselectponoActionPerformed(null);
        }
        
    }//GEN-LAST:event_tblponoMouseClicked

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        

        if (txtsearch.getText().equals("")) {
            loadpono();
        }
        else{
           DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int colIndex){return false;}};

        model.addColumn("P.O. No.");
        model.addColumn("P.O. Date");
        model.addColumn("Supplier");
        model.addColumn("Code");
        model.addColumn("Date Expected");
        
        
        sqlcode = "select tblpono.fld_po_no, date_format(tblpono.fld_po_date, '%m-%d-%Y'),"
                + "date_format(tblpono.fld_expected_date, '%m-%d-%Y'),tblpono.fld_sup_code,"
                + " tblsupplier.fld_sup_name from tblpono inner join tblsupplier"
                + " on tblpono.fld_sup_code=tblsupplier.fld_sup_code "
                + "where tblsupplier.fld_sup_name like '%"+ txtsearch.getText() +"%'"
                + "order by tblpono.fld_po_date desc";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlcode);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("tblpono.fld_po_no"),
                    rs.getString("date_format(tblpono.fld_po_date, '%m-%d-%Y')"),
                    rs.getString("tblsupplier.fld_sup_name"),
                     rs.getString("tblpono.fld_sup_code"),
                    rs.getString("date_format(tblpono.fld_expected_date, '%m-%d-%Y')")
                   
                    
                
                
                });

            }
            tblpono.setModel(model);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        }

    }//GEN-LAST:event_txtsearchKeyReleased

    private void txtsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyTyped

    }//GEN-LAST:event_txtsearchKeyTyped

    private void txtposuppliercodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtposuppliercodeKeyPressed
        //ref_code=txtposuppliercode.getText().trim();

    }//GEN-LAST:event_txtposuppliercodeKeyPressed

    private void txtposuppliercodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtposuppliercodeFocusLost

        sqlcode = "select * from tblsupplier where fld_sup_code='"+ txtposuppliercode.getText().trim() +"'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlcode);
            while (rs.next()) {
                //                txtcreatposup.setText(rs.getString("fld_sup_name"));
                //                    txtsupzone.setText(rs.getString("fld_sup_code"));
                //                    txtsupstreet.setText(rs.getString("fld_sup_street"));
                //                    txtsupcity.setText(rs.getString("fld_sup_city"));
                //                    txtsupprovince.setText(rs.getString("fld_sup_province"));

            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_txtposuppliercodeFocusLost

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
            java.util.logging.Logger.getLogger(frmcreatepo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmcreatepo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmcreatepo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmcreatepo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmcreatepo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncreatepoadd;
    private javax.swing.JButton btnponocancel;
    private javax.swing.JButton btnponodelete;
    private javax.swing.JButton btnponoedit;
    private javax.swing.JButton btnselectpono;
    private javax.swing.JButton btnselectsup;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblpono;
    private javax.swing.JFormattedTextField txtpodate;
    private javax.swing.JFormattedTextField txtpoexpected;
    private javax.swing.JTextField txtpono;
    public static javax.swing.JTextField txtposuppliercode;
    private javax.swing.JTextField txtsearch;
    public static javax.swing.JTextArea txtsupdetails;
    // End of variables declaration//GEN-END:variables
}
