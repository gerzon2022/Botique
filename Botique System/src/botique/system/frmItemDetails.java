/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap ;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//import jdk.nashorn.internal.parser.TokenType;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;
public final class frmItemDetails extends javax.swing.JFrame {
String sqlcode;
int refid;
Connection conn=null;
Statement stmt=null;
ResultSet rs=null;




    /**
     * Creates new form frmItemDetails
     */
    public frmItemDetails() {
        initComponents();
         
        conn=BotiqueSystem.conn_db();
       
        
        btnitemcancelActionPerformed(null);
         
         
         //delete this temp
         
         
         //
        Timer t= new Timer(1000, dateTime);  
        t.start();
       
    }
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
            public final void loadcat(){
                cbitemcat.removeAllItems();
               DefaultComboBoxModel catmod = new DefaultComboBoxModel();
                sqlcode="select * from tblcategory";
                try {
                stmt=conn.createStatement();
                rs=stmt.executeQuery(sqlcode);
                //catmod.addElement(new Object[]{"test", "tes"});
                
                cbitemcat.setModel(catmod);
//                cbitemcat.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
//                    
//                    
//                    
//                    }));
                
                    while (rs.next()) {
                       cbitemcat.addItem(rs.getString("fld_catname"));
                    }
                  
                    stmt.close();
                    rs.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                
            }
            public void loadmanu(){
                cbitemmanu.removeAllItems();
                sqlcode="select * from tblmanufacturer";
                try {
                stmt=conn.createStatement();
                rs=stmt.executeQuery(sqlcode);
                    while (rs.next()) {
                     cbitemmanu.addItem(rs.getString("fld_manu_name"));
                    }
                    stmt.close();
                    rs.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            public void loadtblsup(){
                sqlcode="select * from tblsupplier order by fld_sup_name asc";
                try {
                stmt=conn.createStatement();
                rs=stmt.executeQuery(sqlcode);
                    while (rs.next()) {
                     //cbitemcat.addItem(rs.getString("fld_sup_name"));
                    }
                    stmt.close();
                    rs.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
             public void hidecolumn() {
       
        TableColumn col = tblitemdetails.getColumnModel().getColumn(1);
        col.setPreferredWidth(320);
        col.setMaxWidth(500);
        col.setMinWidth(320);
        col = tblitemdetails.getColumnModel().getColumn(2);
        col.setPreferredWidth(100);
        col.setMaxWidth(500);
        col.setMinWidth(100);
        
      
}
            public void loadtblitems(){
                  try {
                DefaultTableModel model = new DefaultTableModel();
                //model.addColumn("Item ID");
                model.addColumn("Lot No.");
                model.addColumn("Description");
                model.addColumn("Category");
                model.addColumn("Manufacturer");
                //model.addColumn("Vendor's Price");
                 model.addColumn("Price");
                  model.addColumn("Company Price");
                 model.addColumn("Reorder Level");
                sqlcode="select * from tblitemdetails";
              
                    stmt=conn.createStatement();
                    rs=stmt.executeQuery(sqlcode);
                        while (rs.next()) {
                                model.addRow(new Object[]{
                                    //rs.getString("fld_item_id"),
                                    rs.getString("fld_lot_no"),
                                    rs.getString("fld_item_desc"),
                                    rs.getString("fld_catname"),
                                    rs.getString("fld_manu_name"),
                                    //rs.getString("fld_ven_price"),
                                    rs.getString("fld_item_price"),
                                    rs.getString("fld_ven_price"),
                                   rs.getString("fld_reorder_point")

                                });
                        }
                         
                        tblitemdetails.setModel(model);
                        stmt.close();
                    rs.close();
                    hidecolumn();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
                
            }
            public void startup(){
               btnitemadd.setEnabled(true);
               btnitemcancel.setEnabled(true);
               btnitemedit.setEnabled(false);
               //btnitemdelete.setEnabled(false);
               btnitemedit.setText("Edit");
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
        tblitemdetails = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtlotno = new javax.swing.JTextField();
        txtitemdesc = new javax.swing.JTextField();
        cbitemcat = new javax.swing.JComboBox();
        cbitemmanu = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        btnitemadd = new javax.swing.JButton();
        btnitemedit = new javax.swing.JButton();
        btnitemcancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        cbsearch = new javax.swing.JComboBox();
        txtitemprice = new javax.swing.JFormattedTextField();
        txtreorderpoint = new javax.swing.JFormattedTextField();
        txtsuprice = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbltime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Register Item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblitemdetails.setModel(new javax.swing.table.DefaultTableModel(
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
        tblitemdetails.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblitemdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblitemdetailsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblitemdetails);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 890, 460));

        jLabel1.setText("Lot no.");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jLabel2.setText("Description");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, 20));

        jLabel3.setText("Category");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, 23));

        jLabel4.setText("Manufacturer");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, 23));
        jPanel1.add(txtlotno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 80, 30));

        txtitemdesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtitemdescKeyReleased(evt);
            }
        });
        jPanel1.add(txtitemdesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 150, 30));

        cbitemcat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbitemcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 114, 30));

        cbitemmanu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cbitemmanu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 114, 30));

        jLabel5.setText("Selling Price");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, 23));

        btnitemadd.setText("Add");
        btnitemadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnitemaddActionPerformed(evt);
            }
        });
        jPanel1.add(btnitemadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 100, 40));

        btnitemedit.setText("Edit");
        btnitemedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnitemeditActionPerformed(evt);
            }
        });
        jPanel1.add(btnitemedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 100, 40));

        btnitemcancel.setText("Cancel");
        btnitemcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnitemcancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnitemcancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 100, 40));

        jLabel7.setText("Reorder level");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, 23));

        jLabel6.setText("Search");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, -1, -1));

        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsearchKeyTyped(evt);
            }
        });
        jPanel1.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 130, 30));

        cbsearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DESCRIPTION", "CATEGORY", "MANUFACTURER" }));
        cbsearch.setBorder(null);
        jPanel1.add(cbsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 40, 160, 30));

        txtitemprice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtitemprice.setText("1");
        txtitemprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtitempriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtitempriceKeyReleased(evt);
            }
        });
        jPanel1.add(txtitemprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 150, 30));

        txtreorderpoint.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));
        txtreorderpoint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtreorderpointKeyReleased(evt);
            }
        });
        jPanel1.add(txtreorderpoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 150, 30));

        txtsuprice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtsuprice.setText("1");
        txtsuprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsupriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsupriceKeyReleased(evt);
            }
        });
        jPanel1.add(txtsuprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 150, 30));

        jLabel8.setText("Campany Price");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, 23));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 1150, 570));

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Register Item");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbluser.setText("Welcome");
        jPanel4.add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbldate.setText("Welcome");
        jPanel4.add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Welcome");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        lbltime.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbltime.setText("Welcome");
        jPanel4.add(lbltime, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 840, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnitemaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnitemaddActionPerformed
        
        if (txtitemdesc.getText().trim().equals("") || txtitemprice.getText().trim().equals("") || txtlotno.getText().trim().equals("") || cbitemcat.getSelectedItem().equals("")) {
         JOptionPane.showMessageDialog(null, "Null imput not allowed!");
        }
        else if (btnitemedit.getText().equals("Update")) {
            JOptionPane.showMessageDialog(null, "Finish or Cancel update first!");
        }
        else{
            sqlcode="select * from tblitemdetails where fld_lot_no='"+ txtlotno.getText().trim() +"'";
            if (BotiqueSystem.isduplicate(sqlcode, conn)) {
                JOptionPane.showMessageDialog(null, "This item is already registered");
            }
            else{
                
          
                sqlcode="insert into tblitemdetails set fld_lot_no='" + txtlotno.getText().trim() + "', "
                        + "fld_item_desc='" + txtitemdesc.getText().trim() + "', "
                        + "fld_catname='" + cbitemcat.getSelectedItem().toString() + "' , "
                        + "fld_manu_name='"+ cbitemmanu.getSelectedItem().toString() +"', "
                        + "fld_item_price='"+ txtitemprice.getText() +"', "
                        + "fld_ven_price='"+ txtsuprice.getText() +"', "
                        + "fld_reorder_point= "+txtreorderpoint.getText().trim()+"";
                       
                
                if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                        sqlcode="insert into tblstocks set fld_lot_no='"+ txtlotno.getText().trim() +"'"
                                + ", fld_stocks_qty=0";
                        if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                        JOptionPane.showMessageDialog(null, "Added!");
                    btnitemcancelActionPerformed(evt);
                    }
                   
                    
               }   
               }  
        }
        
    }//GEN-LAST:event_btnitemaddActionPerformed

    private void btnitemcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnitemcancelActionPerformed
        
        
        loadtblitems();
        loadcat();
        loadmanu();
        loadtblsup();
        startup();
        cleartext();
        txtitemprice.setText("1");
         txtreorderpoint.setText("10");
        
    }//GEN-LAST:event_btnitemcancelActionPerformed

    public void cleartext(){
        txtitemdesc.setText(null);
        txtitemprice.setText(null);
        txtlotno.setText(null);
        
        txtreorderpoint.setText(null);
    }
    String oldlotno="", sqlcode1="", sqlcode2="", sqlcode3="", sqlcode4="";
    
    private void btnitemeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnitemeditActionPerformed
        if (btnitemedit.getText().equals("Edit")) {
           // btnitemdelete.setEnabled(false);
            txtlotno.setText(tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(), 0).toString());
            txtitemdesc.setText(tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(), 1).toString());
            cbitemcat.setSelectedItem(tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(),2).toString());
            cbitemmanu.setSelectedItem(tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(),3).toString());
            //txtvenprice.setText(tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(), 5).toString());
            
            txtitemprice.setText(tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(),4).toString());
            txtsuprice.setText(tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(),5).toString());
            txtreorderpoint.setText(tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(),6).toString());
            oldlotno=txtlotno.getText().trim();
            btnitemedit.setText("Update");
            
        }
        else{
                    if (txtitemdesc.getText().trim().equals("") || txtitemprice.getText().trim().equals("") || txtlotno.getText().trim().equals("") || cbitemcat.getSelectedItem().equals("")) {
                     JOptionPane.showMessageDialog(null, "Null imput not allowed!");
                    }
                   else {
                            sqlcode="update tblitemdetails set "
                                    + "fld_lot_no='"+ txtlotno.getText().trim() +"',"
                                    + " fld_item_desc='"+ txtitemdesc.getText().trim() +"',"
                                    + " fld_catname='"+ cbitemcat.getSelectedItem().toString() +"', "
                                    + " fld_manu_name='"+ cbitemmanu.getSelectedItem().toString() +"', "
                                    + "fld_item_price="+ txtitemprice.getText().trim() +", "
                                    + "fld_reorder_point="+ txtreorderpoint.getText().trim() +", "
                                    + "fld_ven_price="+ txtsuprice.getText().trim() +" "
                                    + "where fld_lot_no="+refid;
                            
                            sqlcode1="update tblstocks set fld_lot_no='"+ txtlotno.getText().trim() +"' "
                                    + "where fld_lot_no='"+ oldlotno +"'";
                            
                            sqlcode2="update tblcart set fld_lot_no='"+ txtlotno.getText().trim() +"' "
                                    + "where fld_lot_no='"+ oldlotno +"'";
                            
                            sqlcode3="update tblpo set fld_lot_no='"+ txtlotno.getText().trim() +"' "
                                    + "where fld_lot_no='"+ oldlotno +"'";
                            
                           
                            
                          //  JOptionPane.showMessageDialog(null,sqlcode);
                            if ((BotiqueSystem.addupdatedel(sqlcode, conn)) 
                                    &&(BotiqueSystem.addupdatedel(sqlcode1, conn)) 
                                    && (BotiqueSystem.addupdatedel(sqlcode2, conn))
                                    && (BotiqueSystem.addupdatedel(sqlcode3, conn))
                                    ) {
                               JOptionPane.showMessageDialog(null, "Updated!");
                                btnitemcancelActionPerformed(evt);
                           }   
                    }

        }
        
    }//GEN-LAST:event_btnitemeditActionPerformed

    private void tblitemdetailsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblitemdetailsMousePressed
        try {
            
             if (tblitemdetails.getSelectedRowCount() >0 && btnitemedit.getText().equals("Edit")) 
                {
                  refid= Integer.parseInt((tblitemdetails.getValueAt(tblitemdetails.getSelectedRow(), 0)).toString())  ;
                 // btnitemdelete.setEnabled(true);
                  btnitemedit.setEnabled(true);
//                  btnitemadd.setEnabled(false);
                }
             else{
                 JOptionPane.showMessageDialog(null, "Finish/Cancel your last transaction first");
             }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
       
    }//GEN-LAST:event_tblitemdetailsMousePressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       this.dispose();
    }//GEN-LAST:event_formWindowClosed
String fieldVar="";
    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased

        
        
        txtsearch.setText(BotiqueSystem.toTitleCase(txtsearch.getText()));
         
        String cb=cbsearch.getSelectedItem().toString();
        if (cb.equals("DESCRIPTION")) {
            fieldVar="fld_item_desc";
        }
        else if(cb.equals("MANUFACTURER")){
            fieldVar="fld_manu_name";
        }
        else if(cb.equals("CATEGORY")){

            fieldVar="fld_catname";
        }

        if (txtsearch.getText().equals("")) {
            loadtblitems();
        }
        else{
            
            
             
           try {
                DefaultTableModel model = new DefaultTableModel();
               // model.addColumn("Item ID");
                model.addColumn("Lot No.");
                model.addColumn("Description");
                model.addColumn("Category");
                model.addColumn("Manufacturer");
                //model.addColumn("Vendor's Price");
                 model.addColumn("Price");
                 model.addColumn("Company Price");
                 model.addColumn("Reorder Level");
                sqlcode="select * from tblitemdetails where "+ fieldVar +" like '%"+ txtsearch.getText().trim() +"%' order by fld_item_desc asc";
              
                    stmt=conn.createStatement();
                    rs=stmt.executeQuery(sqlcode);
                        while (rs.next()) {
                                model.addRow(new Object[]{
                                  //rs.getString("fld_item_id"),
                                    rs.getString("fld_lot_no"),
                                    rs.getString("fld_item_desc"),
                                    rs.getString("fld_catname"),
                                    rs.getString("fld_manu_name"),
                                    //rs.getString("fld_ven_price"),
                                    rs.getString("fld_item_price"),
                                    rs.getString("fld_ven_price"),
                                   rs.getString("fld_reorder_point")

                                });
                        }
                         
                        tblitemdetails.setModel(model);
                        stmt.close();
                    rs.close();
                    hidecolumn();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
    }//GEN-LAST:event_txtsearchKeyReleased
    }
    private void txtsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyTyped

    }//GEN-LAST:event_txtsearchKeyTyped

    private void txtitempriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtitempriceKeyPressed

    }//GEN-LAST:event_txtitempriceKeyPressed

    private void txtitempriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtitempriceKeyReleased
        char ch= evt.getKeyChar();

        if (ch=='.') {
            //do nothing
        }
        else if (BotiqueSystem.isNumber(evt)) {
            txtitemprice.setText("");
            txtitemprice.requestFocus();
           
        }
        
    }//GEN-LAST:event_txtitempriceKeyReleased

    private void txtreorderpointKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreorderpointKeyReleased
        if (BotiqueSystem.isNumber(evt)) {
            txtreorderpoint.setText("");
            txtreorderpoint.requestFocus();

        }
       
    }//GEN-LAST:event_txtreorderpointKeyReleased

   
    private void txtitemdescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtitemdescKeyReleased
       String input=txtitemdesc.getText();
       txtitemdesc.setText(BotiqueSystem.toTitleCase(input));
       
       
    }//GEN-LAST:event_txtitemdescKeyReleased

    private void txtsupriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupriceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupriceKeyPressed

    private void txtsupriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsupriceKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsupriceKeyReleased

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
            java.util.logging.Logger.getLogger(frmItemDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmItemDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmItemDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmItemDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmItemDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnitemadd;
    private javax.swing.JButton btnitemcancel;
    private javax.swing.JButton btnitemedit;
    private javax.swing.JComboBox cbitemcat;
    private javax.swing.JComboBox cbitemmanu;
    private javax.swing.JComboBox cbsearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbltime;
    private javax.swing.JLabel lbluser;
    private javax.swing.JTable tblitemdetails;
    private javax.swing.JTextField txtitemdesc;
    private javax.swing.JFormattedTextField txtitemprice;
    private javax.swing.JTextField txtlotno;
    private javax.swing.JFormattedTextField txtreorderpoint;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JFormattedTextField txtsuprice;
    // End of variables declaration//GEN-END:variables
}
