/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;


import com.sun.media.jfxmediaimpl.NativeMediaPlayer;
import groovyjarjarasm.asm.tree.TryCatchBlockNode;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


/**
 *
 * @author Admin
 */
public class frmPO extends javax.swing.JFrame {
  public static String podate, poexpected, posup,pono;
   public static String lotno, desc, manu,price;
  double iprice, qty;
  String sqlcode;
  String fieldVar;
  
  long po_no;
    int refid;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    static String username="";
    
     
    /**
     * Creates new form frmPO
     */
    public void hidecolumn() {
       
        TableColumn col = tblPO.getColumnModel().getColumn(0);
        col.setPreferredWidth(0);
        col.setMaxWidth(0);
        col.setMinWidth(0);
        
        col.setPreferredWidth(0);
        col.setMaxWidth(0);
        col.setMinWidth(0);
        
        col = tblPO.getColumnModel().getColumn(3);
        col.setPreferredWidth(320);
        col.setMaxWidth(500);
        col.setMinWidth(320);
        
        col = tblstocks.getColumnModel().getColumn(1);
        col.setPreferredWidth(320);
        col.setMaxWidth(500);
        col.setMinWidth(320);
        
        col = tblstocks.getColumnModel().getColumn(2);
        col.setPreferredWidth(120);
        col.setMaxWidth(500);
        col.setMinWidth(120);
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
    
     public frmPO() {
        initComponents();
        lbluser.setText(BotiqueSystem.user);
       txtpono.requestFocus();
        
        
        Timer t= new Timer(1000, dateTime);  
        t.start();
        
        
        
        
        BotiqueSystem.frmopened="po";
       
        conn=BotiqueSystem.conn_db();
        
        loadpo();
        loadstocks();
        tblsetup();
        //
        
         
        
         
         //
        //txtPOdate.setText(date.toString());
    }
     public void tblsetup(){
          DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Qty");
        model.addColumn("Lot No.");
        model.addColumn("Description");
        model.addColumn("Manufacturer");
        model.addColumn("Category");
        
         tblPO.setModel(model);
          hidecolumn();
     }
    
    public void loadstocks(){
        DefaultTableModel dtm = new DefaultTableModel(){
          public boolean isCellEditable(int rowIndex, int colIndex){return false;};  
        };
        dtm.addColumn("Lot no");
        dtm.addColumn("Desc");
        dtm.addColumn("Category");
        dtm.addColumn("Manufacturer");
        dtm.addColumn("Price");
        dtm.addColumn("Stocks");
         dtm.addColumn("Price");
    
        
        sqlcode="Select tblitemdetails.fld_lot_no, "
                + "tblitemdetails.fld_item_desc, "
                + "tblitemdetails.fld_catname, "
                + "tblitemdetails.fld_manu_name, "
                + "tblitemdetails.fld_item_price, "
               + "tblitemdetails.fld_ven_price, "
                + "tblstocks.fld_stocks_qty "
                + "from tblitemdetails inner join tblstocks"
                + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no "
                + "order by tblitemdetails.fld_item_desc asc";
         rs=BotiqueSystem.selectquery(sqlcode, conn);
        try {
             while (rs.next()) {
                 dtm.addRow(new Object[]{
                     rs.getString("tblitemdetails.fld_lot_no"),
                     rs.getString("tblitemdetails.fld_item_desc"),
                     rs.getString("tblitemdetails.fld_catname"),
                     rs.getString("tblitemdetails.fld_manu_name"),
                     rs.getString("tblitemdetails.fld_item_price"),
                     rs.getString("tblstocks.fld_stocks_qty"),
                     rs.getString("tblitemdetails.fld_ven_price")
                 });

                                }
             tblstocks.setModel(dtm);
             
             rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
   

   
    public void loadpo(){
        
//      double gtotal=0;
//      String strgtotal, strsubtotal, strtax;
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID");
        model.addColumn("Qty");
        model.addColumn("Lot No.");
        model.addColumn("Description");
        model.addColumn("Manufacturer");
        model.addColumn("Category");
         model.addColumn("Price");
          model.addColumn("Subtotal");
        
        
        sqlcode="select * from tblpo inner join tblitemdetails"
                + " on tblpo.fld_lot_no=tblitemdetails.fld_lot_no where fld_po_no="+ po_no +" order by tblitemdetails.fld_item_desc asc";
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sqlcode);
            while (rs.next()) {
//                    double a = Integer.parseInt(rs.getString("fld_po_price"));
//                    double b= Integer.parseInt(rs.getString("fld_po_qty"));
//                    double c= a*b;
//                    gtotal=gtotal+c;
                model.addRow(new Object[]{
                    rs.getString("fld_po_id"),
                    //rs.getString("fld_po_no"),
                   rs.getString("fld_po_qty"),
                    rs.getString("fld_lot_no"),
                    rs.getString("fld_item_desc"),
                    rs.getString("fld_manu_name"),
                    rs.getString("fld_catname"),
                     rs.getString("fld_po_price"),
                    rs.getString("fld_amount")
                    
                    
                  // Integer.parseInt(rs.getString("fld_po_price")),
                     
//                    rs.getString("fld_discount"),
//                  c
                    
                }
                
                
                
                );
                
            }
             rs.close();
        stmt.close();
         tblPO.setModel(model);
        } catch (Exception e) {
        }
        
        
        
//        double tax= gtotal *  0.12;
//        strgtotal=String.valueOf(gtotal);
//        strtax=String.valueOf(tax);
//        strsubtotal= String.valueOf(gtotal-tax);
//        lblsubtotal.setText(strsubtotal);
//        
//        lblgtotal.setText(strgtotal);
       
         hidecolumn();
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtpono = new javax.swing.JTextField();
        btnselectpo = new javax.swing.JButton();
        txtpodate = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtpoexpected = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtsupcode = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtsupdetails = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPO = new javax.swing.JTable(){public boolean IsCellEditable(int rowIndex, int colIndex){return false;}};
        btncreatepo = new javax.swing.JButton();
        btnprintpo = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbsearch = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblpototal = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtlotno = new javax.swing.JTextField();
        txtqty = new javax.swing.JTextField();
        btneditpo = new javax.swing.JButton();
        btndeletepo = new javax.swing.JButton();
        btncancelpo = new javax.swing.JButton();
        btnaddpo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtlotdetails = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblstocks = new javax.swing.JTable(){public boolean IsCellEditable(int rowIndex, int colIndex){return false;}};
        jLabel1 = new javax.swing.JLabel();
        cbsearchstocks = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtsearchstocks = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtsellingprice = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbltime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "P.O. Details", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setName("d"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("P.O No.");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        txtpono.setEditable(false);
        txtpono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtponoFocusLost(evt);
            }
        });
        txtpono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtponoActionPerformed(evt);
            }
        });
        txtpono.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtponoPropertyChange(evt);
            }
        });
        txtpono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtponoKeyPressed(evt);
            }
        });
        jPanel1.add(txtpono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 187, -1));

        btnselectpo.setText("Create/select");
        btnselectpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselectpoActionPerformed(evt);
            }
        });
        jPanel1.add(btnselectpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 30, 30));

        txtpodate.setEditable(false);
        txtpodate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("M/d/yyyy"))));
        txtpodate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtpodate.setFocusable(false);
        txtpodate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpodate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpodateFocusLost(evt);
            }
        });
        jPanel1.add(txtpodate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 190, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("P.O Date");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        txtpoexpected.setEditable(false);
        txtpoexpected.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpoexpected.setFocusable(false);
        jPanel1.add(txtpoexpected, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 187, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Expected Date");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 80, -1));

        txtsupcode.setEditable(false);
        txtsupcode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtsupcode.setFocusable(false);
        jPanel1.add(txtsupcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 187, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Supplier Code");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 20));

        txtsupdetails.setEditable(false);
        txtsupdetails.setColumns(20);
        txtsupdetails.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtsupdetails.setRows(3);
        txtsupdetails.setFocusable(false);
        jScrollPane4.setViewportView(txtsupdetails);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 190, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Supplier Details");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        tblPO.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblPO.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPO.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblPO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPOMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPO);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 540, 180));

        btncreatepo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncreatepo.setText("Submit P.O.");
        btncreatepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncreatepoActionPerformed(evt);
            }
        });
        jPanel1.add(btncreatepo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 191, 40));

        btnprintpo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnprintpo.setText("Print P.O.");
        btnprintpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintpoActionPerformed(evt);
            }
        });
        jPanel1.add(btnprintpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 191, 40));

        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsearchKeyTyped(evt);
            }
        });
        jPanel1.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 130, 30));

        jLabel2.setText("Search");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        cbsearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DESCRIPTION", "CATEGORY", "MANUFACTURER" }));
        cbsearch.setBorder(null);
        jPanel1.add(cbsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 18, 160, 30));

        jLabel6.setText("by");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel5.setText("P.O. Items");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("P");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, -1, -1));

        lblpototal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblpototal.setForeground(new java.awt.Color(255, 0, 0));
        lblpototal.setText("Total");
        jPanel1.add(lblpototal, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Total");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 250, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 920, 300));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Add to P.O.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Lot No.");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Details");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        txtlotno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtlotno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtlotnoFocusLost(evt);
            }
        });
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
        jPanel2.add(txtlotno, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 200, -1));

        txtqty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtqtyActionPerformed(evt);
            }
        });
        txtqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtqtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtqtyKeyReleased(evt);
            }
        });
        jPanel2.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 90, 30));

        btneditpo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btneditpo.setText("Edit");
        btneditpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditpoActionPerformed(evt);
            }
        });
        jPanel2.add(btneditpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 120, 40));

        btndeletepo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btndeletepo.setText("Delete");
        btndeletepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletepoActionPerformed(evt);
            }
        });
        jPanel2.add(btndeletepo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 120, 40));

        btncancelpo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncancelpo.setText("Cancel");
        btncancelpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelpoActionPerformed(evt);
            }
        });
        jPanel2.add(btncancelpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 120, 40));

        btnaddpo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnaddpo.setText("Add");
        btnaddpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddpoActionPerformed(evt);
            }
        });
        jPanel2.add(btnaddpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 120, 40));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Subtotal");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        txtlotdetails.setEditable(false);
        txtlotdetails.setColumns(20);
        txtlotdetails.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtlotdetails.setRows(3);
        txtlotdetails.setFocusable(false);
        jScrollPane5.setViewportView(txtlotdetails);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 200, -1));

        tblstocks.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblstocks.setModel(new javax.swing.table.DefaultTableModel(
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
        tblstocks.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblstocks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblstocksMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblstocksMousePressed(evt);
            }
        });
        tblstocks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblstocksKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblstocks);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 520, 250));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Stocks");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 50, 20));

        cbsearchstocks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DESCRIPTION", "CATEGORY", "MANUFACTURER", "ROP" }));
        cbsearchstocks.setBorder(null);
        cbsearchstocks.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbsearchstocksItemStateChanged(evt);
            }
        });
        jPanel2.add(cbsearchstocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 160, 30));

        jLabel9.setText("Search");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, 20));

        txtsearchstocks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchstocksKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsearchstocksKeyTyped(evt);
            }
        });
        jPanel2.add(txtsearchstocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 130, 30));

        jLabel15.setText("by");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, -1, 20));

        txtsellingprice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.##"))));
        txtsellingprice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtsellingprice.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtsellingpriceInputMethodTextChanged(evt);
            }
        });
        txtsellingprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsellingpriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsellingpriceKeyReleased(evt);
            }
        });
        jPanel2.add(txtsellingprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 170, 30));

        jLabel17.setText("Price");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtsubtotal.setEditable(false);
        txtsubtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtsubtotal.setFocusable(false);
        txtsubtotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtsubtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsubtotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsubtotalKeyReleased(evt);
            }
        });
        jPanel2.add(txtsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 110, 30));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Quantity");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 920, 390));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 110, 40, 630));

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Purchase Order");
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

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 920, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndeletepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletepoActionPerformed
        if (tblPO.getSelectedRowCount() >0) {
             
                    sqlcode="delete from tblpo where fld_po_id="+ refid +"";
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this data?", "Deleting...", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {

                    if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                        JOptionPane.showMessageDialog(null, "Successfully Deleted");
                        btncancelpoActionPerformed(evt);
                    }

                }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please select from P.O. items");
        }
        
       
       
    }//GEN-LAST:event_btndeletepoActionPerformed

    private void btnselectpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselectpoActionPerformed
        txtpono.setText("");
        txtpodate.setText("");
        txtpoexpected.setText("");
        txtsupcode.setText("");
        txtsupdetails.setText("");
        BotiqueSystem.frmopened="P.O.";
        frmcreatepo create = new frmcreatepo();
        create.setVisible(true);

//       frmSelectPOno selectpo = new frmSelectPOno();
//        selectpo.setVisible(true);
    }//GEN-LAST:event_btnselectpoActionPerformed

    private void txtpodateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpodateFocusLost
      
    }//GEN-LAST:event_txtpodateFocusLost
    public boolean text_is_empty(){
         Boolean result=false;
        if ((txtpono.getText().trim().isEmpty()) || (txtlotno.getText().trim().isEmpty()) || (txtqty.getText().trim().isEmpty())|| (txtsellingprice.getText().trim().isEmpty())) {
            result= true;
        }
       
        return result;
    }
    private void btnaddpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddpoActionPerformed
        if (text_is_empty()) {
            JOptionPane.showMessageDialog(null, "Empty input not allowed!");
            txtqty.requestFocus();
        }
        else{
//            if (txtprice.getText().isEmpty()) {
//                txtprice.setText("NULL");
//                
//            }
//            if (txtsellingprice.getText().isEmpty()) {
//                txtsellingprice.setText("Null");
//            }
            sqlcode="select * from tblpo where fld_lot_no='"+ txtlotno.getText().trim() +"' and fld_po_no="+ po_no +"";
            if (BotiqueSystem.isduplicate(sqlcode, conn)) {
                JOptionPane.showMessageDialog(null, "Duplicate Entry of "+txtlotno.getText());
                txtlotno.requestFocus();
            }
            else{
                

                    sqlcode="insert into tblpo(fld_po_no, fld_lot_no, fld_po_qty, fld_amount ,fld_po_price)values('"+ po_no +"'"
                     + ", '"+ txtlotno.getText().trim() +"'"
                     + ", "+ txtqty.getText().trim() +","+ txtsubtotal.getText() +", "+ txtsellingprice.getText() +" )";

       //             "
       //              + ", "+ txtprice.getText() +""
       //              + ", "+ txtsubtotal.getText() +""
       //              + ", "+ txtdiscount.getText() +"


               if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                       //JOptionPane.showMessageDialog(null, "Successfully added"); 
                       btncancelpoActionPerformed(evt);
                      getTotalpo();
                   }
            }
        }
        
        
       
    }//GEN-LAST:event_btnaddpoActionPerformed
    public void getTotalpo(){
         int rowcount = tblPO.getRowCount();
         double subt=0.00, gTotal=0.00;
         for (int i = 0; i < rowcount; i++) {
            subt=Double.parseDouble(tblPO.getValueAt(i, 7).toString());
            gTotal=gTotal+subt;
        }
           DecimalFormat nf;
         if (gTotal >=1000) {
             nf= new DecimalFormat("0,000.00");
        }
         else{
              nf= new DecimalFormat("0.00");
         }
         String strdec=nf.format(gTotal);
        lblpototal.setText(strdec);
        
    }
    private void txtlotnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlotnoKeyPressed
        
    }//GEN-LAST:event_txtlotnoKeyPressed

    private void btneditpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditpoActionPerformed
        if (tblPO.getSelectedRowCount()>0) {
                            if (btneditpo.getText().equals("Edit")) {
                                btneditpo.setText("Update");
                                btndeletepo.setEnabled(false);
                                tblstocks.setFocusable(false);
                               //btnstart();
                                txtlotno.setText(tblPO.getValueAt(tblPO.getSelectedRow(), 2).toString());
                                txtqty.setText(tblPO.getValueAt(tblPO.getSelectedRow(), 1).toString());
                                txtlotdetails.setText(
                                        tblPO.getValueAt(tblPO.getSelectedRow(), 5).toString()+"\n"
                                        +tblPO.getValueAt(tblPO.getSelectedRow(), 3).toString()+"\n"
                                        +tblPO.getValueAt(tblPO.getSelectedRow(), 4).toString()
                                        
                                );
                                
                                
//                                
//                                txtlotno.setText(tblPO.getValueAt(tblPO.getSelectedRow(), 2).toString());
//                                //txtprice.setText(tblPO.getValueAt(tblPO.getSelectedRow(), 3).toString());
//                                txtqty.setText(tblPO.getValueAt(tblPO.getSelectedRow(),4).toString());
//                                //txtsubtotal.setText(tblPO.getValueAt(tblPO.getSelectedRow(),6).toString());
//                               // cbmensuration.setText(tblPO.getValueAt(tblPO.getSelectedRow(), 2).toString());

                }
                else{
                      if (text_is_empty()) {
                    JOptionPane.showMessageDialog(null, "Empty input not allowed!");
                             }

                      else{
                          
                          
                          sqlcode="update tblpo set "
                                  + "fld_lot_no='"+ txtlotno.getText().trim() +"', "
                                  //+ "fld_po_price='"+ txtprice.getText().trim() +"',"
                                  + "fld_po_qty="+ txtqty.getText().trim() +" "
                                  //+ "fld_discount="+ txtdiscount.getText().trim() +", "
                                  //+ "fld_amount="+ txtsubtotal.getText() +" "
                                  + "where fld_po_id="+ refid +"";
                          String texts[]={txtlotno.getText(), txtpono.getText(), txtqty.getText()};
                          if((!BotiqueSystem.isTextEmpty(texts))&&(BotiqueSystem.addupdatedel(sqlcode, conn)) && (JOptionPane.showConfirmDialog(null, "Update will replace the current data, continue?", "Updating...", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)) {
                            JOptionPane.showMessageDialog(null, "Updated");
                            tblstocks.setFocusable(true);
                            btncancelpoActionPerformed(evt);
                        }
                      }
                }


        }
        else{
            JOptionPane.showMessageDialog(null, "Please select from P.O. Items");
        }

        
    }//GEN-LAST:event_btneditpoActionPerformed
        
        
        public void cleartext(){
                    //txtprice.setText("");
                    txtqty.setText("");
                    txtlotno.setText("");
                    //txtsubtotal.setText("");
                    txtlotdetails.setText("");
                    //txtsellingprice.setText("");
                    //txtdiscount.setText("0.00");
        }
            
    
        public void selecting(){
            btnaddpo.setEnabled(false);
            btndeletepo.setEnabled(true);
            btneditpo.setEnabled(true);
       }
        
        public void btnstart(){
        if (btneditpo.getText().equals("Update")) {
           btnaddpo.setEnabled(false);
           btndeletepo.setEnabled(false);
        }
        else
        {
            btnaddpo.setEnabled(true);
            btndeletepo.setEnabled(false);
            btneditpo.setEnabled(false);
            
        }
    }
    private void btncancelpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelpoActionPerformed
       
        cleartext();
      loadpo();
       btneditpo.setText("Edit");
       tblstocks.setFocusable(true);
      
      btnstart();
    }//GEN-LAST:event_btncancelpoActionPerformed

    private void txtlotnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtlotnoFocusLost
        
        
    }//GEN-LAST:event_txtlotnoFocusLost

    private void txtponoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtponoFocusLost
       
    }//GEN-LAST:event_txtponoFocusLost
    public void editandfocustxtbox(boolean valuehere){
        txtqty.setEditable(valuehere);
        txtqty.setFocusable(valuehere);

        txtlotno.setEditable(valuehere);
        txtlotno.setFocusable(valuehere);
        
        txtlotdetails.setEditable(valuehere);
        txtlotdetails.setFocusable(valuehere);
    }
    public void postatus(){
        
        try {
            
                
                sqlcode="select * from tblcreatedpo where fld_po_no="+ po_no +"";
                rs=BotiqueSystem.selectquery(sqlcode, conn);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "P.O. status:Closed, Transaction:Print Only");
                        btncreatepo.setEnabled(false);
                        btnaddpo.setEnabled(false);
                        btndeletepo.setEnabled(false);
                        btneditpo.setEnabled(false);
                        btncancelpo.setEnabled(false);
                        btnprintpo.setEnabled(true);
                        editandfocustxtbox(false);
                        
                        txtpono.requestFocus();
                    }
                    else{
                      
                        btncreatepo.setEnabled(true);
                        btnprintpo.setEnabled(false);
                        btnaddpo.setEnabled(true);
                        btndeletepo.setEnabled(true);
                        btneditpo.setEnabled(true);
                        btncancelpo.setEnabled(true);
                        editandfocustxtbox(true);
                        txtlotno.requestFocus();
                    }
        rs.close();
        stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
     
    }
    public void get_lotno(){
        if (!txtlotno.getText().isEmpty()) {
            sqlcode = "select * from tblitemdetails where fld_lot_no="+ txtlotno.getText().trim() +" order by fld_item_desc asc" ;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlcode);
            while (rs.next()) {
                    //txtsellingprice.setText(rs.getString("fld_item_price"));
                    txtlotdetails.setText(rs.getString("fld_catname")+"\n"
                            + ""+rs.getString("fld_item_desc")+"\n"
                                        +rs.getString("fld_manu_name"));
                   
         //txtprice.setText(rs.getString("fld_ven_price"));

            }
            txtqty.requestFocus();
         rs.close();
         stmt.close();
         
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        }
    }
    public void get_pono(){
        
             sqlcode = "select * from tblpono inner join tblsupplier"
                    + " on tblpono.fld_sup_code=tblsupplier.fld_sup_code"
                    + " where fld_po_no="+ po_no ;
//            sqlcode = "select * from tblpono inner join tblsupplier"
//                    + " on tblpono.fld_sup_code=tblsupplier.fld_sup_code"
//                    + " where fld_po_no="+ po_no ;
        try {
          
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlcode);
            while (rs.next()) {
                
                    txtpodate.setText(rs.getString("fld_po_date"));
                   txtpoexpected.setText(rs.getString("fld_expected_date"));
                    txtsupcode.setText(rs.getString("fld_sup_code"));
                    txtsupdetails.setText(
                    rs.getString("fld_sup_name")+"\n"
                            +rs.getString("fld_sup_zone")+" "
                            +rs.getString("fld_sup_street")+"\n"
                            +rs.getString("fld_sup_city")+", "
                            +rs.getString("fld_sup_province")
                    
                    );
                    
                    txtlotno.requestFocus();
             
//                    txtsupcity.setText(rs.getString("fld_sup_city"));
//                    txtsupprovince.setText(rs.getString("fld_sup_province"));
         

            }
            
         rs.close();
         stmt.close();
         
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
    }
    private void tblPOMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPOMousePressed
       
        if (btneditpo.getText().equals("Update")) {
            JOptionPane.showMessageDialog(null, "Finish or Cancel Update");
            btncancelpo.requestFocus();
        }
        else if (tblPO.getSelectedRowCount() >0 ) {
            refid= Integer.parseInt(tblPO.getValueAt(tblPO.getSelectedRow(), 0).toString());
            selecting();
        }
    }//GEN-LAST:event_tblPOMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       btnstart();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
      
     
    }//GEN-LAST:event_formWindowGainedFocus
    public static  void txtponoactionperform(){
        if (txtpono.getText().trim().equals("")) {JOptionPane.showMessageDialog(null, "Empty not allowed!"); txtpono.requestFocus();}
         else{
            
            
        }
    }
    
    private void txtponoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtponoPropertyChange
      
        
    }//GEN-LAST:event_txtponoPropertyChange

    private void txtlotnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlotnoActionPerformed
        get_lotno();
    }//GEN-LAST:event_txtlotnoActionPerformed

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqtyActionPerformed
      btnaddpo.requestFocus();
    }//GEN-LAST:event_txtqtyActionPerformed

    private void btncreatepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreatepoActionPerformed
      
        if ((txtpono.getText().trim().isEmpty()||(tblPO.getRowCount()<1))) {
            JOptionPane.showMessageDialog(null, "Empty P.O. Can not be created!");
        }
        else{
            
        
                if(JOptionPane.showConfirmDialog(null, "Once P.O. is create you can no longer change the items", "Finalizing P.O.", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){

                  sqlcode="insert tblcreatedpo set fld_po_no='"+ po_no +"'";                         
                  if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                      JOptionPane.showMessageDialog(null, "Purchase Order Created!");
                      postatus();

                  }
              }
          }
    }//GEN-LAST:event_btncreatepoActionPerformed

    private void btnprintpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintpoActionPerformed
         if ((txtpono.getText().trim().isEmpty()||(tblPO.getRowCount()<1))||(btncreatepo.isEnabled())) {
            JOptionPane.showMessageDialog(null, "Submit P.O. first to Print!");
        }
         else{
            
             int rptpono = Integer.parseInt(txtpono.getText().trim());
             HashMap param = new HashMap();
             param.put("pono", rptpono);
             BotiqueSystem.openreport(BotiqueSystem.rootdir+"\\rpt_printPO.jasper", param,conn);
             
         }
         
    }//GEN-LAST:event_btnprintpoActionPerformed

    private void txtponoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtponoKeyPressed
      char ch = evt.getKeyChar();
        if (evt.getKeyChar()==Event.ENTER) {
            txtponoActionPerformed(null);
        }
        else if (evt.getKeyChar()==Event.BACK_SPACE 
                || evt.getKeyChar()==Event.DELETE 
                || evt.getKeyChar()==Event.UP
                || evt.getKeyChar()==Event.DOWN
                || evt.getKeyChar()==Event.LEFT
                || evt.getKeyChar()==Event.RIGHT
                ) {}
        else if (!Character.isDigit(ch)) {
            JOptionPane.showMessageDialog(null, "Not a Number!");
            txtpono.setText("");
           
        }
        
    }//GEN-LAST:event_txtponoKeyPressed

    private void txtqtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyPressed
     
    }//GEN-LAST:event_txtqtyKeyPressed

    private void tblstocksMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstocksMousePressed
         
    }//GEN-LAST:event_tblstocksMousePressed

    public void getlotnobytable(){
    if (tblstocks.getSelectedRowCount()>0) {
                    txtlotno.setText(tblstocks.getValueAt(tblstocks.getSelectedRow(), 0).toString());
                   txtlotdetails.setText(
                     tblstocks.getValueAt(tblstocks.getSelectedRow(), 2).toString()+"\n"
                     +tblstocks.getValueAt(tblstocks.getSelectedRow(),1).toString()+"\n"
                     +tblstocks.getValueAt(tblstocks.getSelectedRow(),3).toString()
                      /*+tblstocks.getValueAt(tblstocks.getSelectedRow(),5).toString() */ );
                   txtsellingprice.setText(tblstocks.getValueAt(tblstocks.getSelectedRow(),6).toString());
                   
                   txtqty.requestFocus();
                }
    
            else{
                JOptionPane.showMessageDialog(null, "No Selection");
            }
 }
    
    private void tblstocksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstocksMouseClicked
        if (evt.getClickCount()==2) {
            getlotnobytable();
          txtqty.setText("0");
           txtsubtotal.setText("0.00");
        }
        
    }//GEN-LAST:event_tblstocksMouseClicked

    private void txtponoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtponoActionPerformed
        if (txtpono.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty");
        }
        else{
            po_no=Integer.parseInt(txtpono.getText().trim());
            get_pono();
            loadpo();
            postatus();
            getTotalpo();
        }
      
    }//GEN-LAST:event_txtponoActionPerformed

    private void txtsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyTyped
        
    }//GEN-LAST:event_txtsearchKeyTyped

    private void txtsearchstocksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchstocksKeyTyped
        
    }//GEN-LAST:event_txtsearchstocksKeyTyped

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
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
            loadpo();
        }
        else{
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("ID");
                    model.addColumn("Qty");
                    model.addColumn("Lot No.");
                    model.addColumn("Description");
                    model.addColumn("Manufacturer");
                    model.addColumn("Category");
                    
                    sqlcode="select * from tblpo inner join tblitemdetails"
                            + " on tblpo.fld_lot_no=tblitemdetails.fld_lot_no where fld_po_no="+ po_no +" and "+ fieldVar +" like '%"+ txtsearch.getText() +"%' "
                            + "order by tblitemdetails.fld_item_desc asc";
                    //JOptionPane.showMessageDialog(null, sqlcode);
                    try {
                        stmt = conn.createStatement();
                        rs=stmt.executeQuery(sqlcode);
                        while (rs.next()) {
            //                    double a = Integer.parseInt(rs.getString("fld_po_price"));
            //                    double b= Integer.parseInt(rs.getString("fld_po_qty"));
            //                    double c= a*b;
            //                    gtotal=gtotal+c;
                            model.addRow(new Object[]{
                                rs.getString("fld_po_id"),
                                //rs.getString("fld_po_no"),
                               rs.getString("fld_po_qty"),
                                rs.getString("fld_lot_no"),
                                rs.getString("fld_item_desc"),
                                rs.getString("fld_manu_name"),
                                rs.getString("fld_catname")


                              // Integer.parseInt(rs.getString("fld_po_price")),

            //                    rs.getString("fld_discount"),
            //                  c

                            }



                            );

                        }
                         rs.close();
                    stmt.close();
                     tblPO.setModel(model);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }



            //        double tax= gtotal *  0.12;
            //        strgtotal=String.valueOf(gtotal);
            //        strtax=String.valueOf(tax);
            //        strsubtotal= String.valueOf(gtotal-tax);
            //        lblsubtotal.setText(strsubtotal);
            //        
            //        lblgtotal.setText(strgtotal);

                     hidecolumn();
                    }
        
    }//GEN-LAST:event_txtsearchKeyReleased

    private void txtsearchstocksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchstocksKeyReleased
         String cb= cbsearchstocks.getSelectedItem().toString();
        if (cb.equals("DESCRIPTION")) {
            fieldVar="fld_item_desc";
            
        }
        else if(cb.equals("MANUFACTURER")){
           fieldVar="fld_manu_name";
        }
        else if(cb.equals("CATEGORY")){
           
            fieldVar="fld_catname";
        }
        
        if (txtsearchstocks.getText().equals("")) {
            loadstocks();
        }
        else{
                   DefaultTableModel dtm = new DefaultTableModel(){
          public boolean isCellEditable(int rowIndex, int colIndex){return false;};  
        };
        dtm.addColumn("Lot no");
        dtm.addColumn("Desc");
        dtm.addColumn("Category");
        dtm.addColumn("Manufacturer");
        dtm.addColumn("Price");
        dtm.addColumn("Stocks");
    
        
        sqlcode="Select tblitemdetails.fld_lot_no, "
                + "tblitemdetails.fld_item_desc, "
                + "tblitemdetails.fld_catname, "
                + "tblitemdetails.fld_manu_name, "
                + "tblitemdetails.fld_item_price,"
                + "tblstocks.fld_stocks_qty "
                + "from tblitemdetails inner join tblstocks"
                + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no where "+ fieldVar +" like '%"+ txtsearchstocks.getText() +"%' "
                + "order by tblitemdetails.fld_item_desc asc";
         rs=BotiqueSystem.selectquery(sqlcode, conn);
        try {
             while (rs.next()) {
                 dtm.addRow(new Object[]{
                     rs.getString("tblitemdetails.fld_lot_no"),
                     rs.getString("tblitemdetails.fld_item_desc"),
                     rs.getString("tblitemdetails.fld_catname"),
                     rs.getString("tblitemdetails.fld_manu_name"),
                     rs.getString("tblitemdetails.fld_item_price"),
                     rs.getString("tblstocks.fld_stocks_qty")
                 });

                                }
             tblstocks.setModel(dtm);
             
             rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }


            //        double tax= gtotal *  0.12;
            //        strgtotal=String.valueOf(gtotal);
            //        strtax=String.valueOf(tax);
            //        strsubtotal= String.valueOf(gtotal-tax);
            //        lblsubtotal.setText(strsubtotal);
            //        
            //        lblgtotal.setText(strgtotal);

                     
        }
        
      

    }//GEN-LAST:event_txtsearchstocksKeyReleased

    private void cbsearchstocksItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbsearchstocksItemStateChanged
        if (cbsearchstocks.getSelectedItem().equals("ROP")) {
            txtsearchstocks.setEditable(false);
            txtsearchstocks.setFocusable(false);
        //            sqlcode="select * from tblitemdetails inner join tblstocks on tblitemdetails.fld_lot_no"
        //                    + "=tblstocks.fld_lot_no where tblitemdetails.fld_reorder_point >=tblstocks.fld_stocks_qty";
                    DefaultTableModel dtm = new DefaultTableModel(){
                  public boolean isCellEditable(int rowIndex, int colIndex){return false;};  
                };
                dtm.addColumn("Lot no");
                dtm.addColumn("Desc");
                dtm.addColumn("Category");
                dtm.addColumn("Manufacturer");
                dtm.addColumn("Price");
                dtm.addColumn("Stocks");


                sqlcode="Select tblitemdetails.fld_lot_no, "
                        + "tblitemdetails.fld_item_desc, "
                        + "tblitemdetails.fld_catname, "
                        + "tblitemdetails.fld_manu_name, "
                        + "tblitemdetails.fld_item_price,"
                        + "tblstocks.fld_stocks_qty "
                        + "from tblitemdetails inner join tblstocks"
                        + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no where tblitemdetails.fld_reorder_point >=tblstocks.fld_stocks_qty "
                        + "order by tblitemdetails.fld_item_desc asc";
                 rs=BotiqueSystem.selectquery(sqlcode, conn);
                try {
                     while (rs.next()) {
                         dtm.addRow(new Object[]{
                             rs.getString("tblitemdetails.fld_lot_no"),
                             rs.getString("tblitemdetails.fld_item_desc"),
                             rs.getString("tblitemdetails.fld_catname"),
                             rs.getString("tblitemdetails.fld_manu_name"),
                             rs.getString("tblitemdetails.fld_item_price"),
                             rs.getString("tblstocks.fld_stocks_qty")
                         });

                                        }
                     tblstocks.setModel(dtm);

                     rs.close();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            
        }
        else{
            txtsearchstocks.setEditable(true);
            txtsearchstocks.setFocusable(true);
            txtsearchstocksKeyReleased(null);
        }
        
    }//GEN-LAST:event_cbsearchstocksItemStateChanged

    private void txtsellingpriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsellingpriceKeyPressed

    }//GEN-LAST:event_txtsellingpriceKeyPressed
public void proceed(java.awt.event.KeyEvent evt){
    char ch= evt.getKeyChar();

        if (ch=='.') {
            //do nothing
        }
        else if (BotiqueSystem.isNumber(evt)) {
            txtsellingprice.setText("");
            txtsellingprice.requestFocus();
            txtqty.setText("");
            txtsubtotal.setText("");
        }
        else{
            txtqtyKeyReleased(evt);
        }
}
    private void txtsellingpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsellingpriceKeyReleased
        proceed(evt);
      
    }//GEN-LAST:event_txtsellingpriceKeyReleased

    private void txtsubtotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsubtotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalKeyPressed

    private void txtsubtotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsubtotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalKeyReleased

    private void txtqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyReleased
  char ch=evt.getKeyChar();
        
        if (
                 evt.getKeyChar()==Event.DELETE 
                || evt.getKeyChar()==Event.UP
                || evt.getKeyChar()==Event.DOWN
                || evt.getKeyChar()==Event.LEFT
                || evt.getKeyChar()==Event.RIGHT
                ) {}
        else if(ch==Event.ENTER){
            btnaddpoActionPerformed(null);
        }
        
         else if(evt.getKeyChar()==Event.BACK_SPACE || Character.isDigit(ch)){
            try {
                double price=0;
                int qty=0;
                
                if (txtsellingprice.getText().trim().isEmpty()) {
                    price=0;
                }
                else{
                    price=Double.parseDouble(txtsellingprice.getText());
                }
                 if (txtqty.getText().trim().isEmpty()) {
                    qty=0;
                }
                else{
                    qty=Integer.parseInt(txtqty.getText());
                }
                
                
                double result= qty*price;
                txtsubtotal.setText(String.valueOf(result));
                
            } catch (Exception e) {
              //  JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        else if (!Character.isDigit(ch)) {
          
            JOptionPane.showMessageDialog(null, "Not a number");
            txtqty.setText("");
           
            
            
        }
       
              
           
        
    }//GEN-LAST:event_txtqtyKeyReleased

    private void txtsellingpriceInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtsellingpriceInputMethodTextChanged
        
    }//GEN-LAST:event_txtsellingpriceInputMethodTextChanged

    private void tblstocksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblstocksKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblstocksKeyReleased

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
            java.util.logging.Logger.getLogger(frmPO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPO().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddpo;
    private javax.swing.JButton btncancelpo;
    private javax.swing.JButton btncreatepo;
    private javax.swing.JButton btndeletepo;
    private javax.swing.JButton btneditpo;
    private javax.swing.JButton btnprintpo;
    private javax.swing.JButton btnselectpo;
    private javax.swing.JComboBox cbsearch;
    private javax.swing.JComboBox cbsearchstocks;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblpototal;
    private javax.swing.JLabel lbltime;
    private javax.swing.JLabel lbluser;
    private javax.swing.JTable tblPO;
    private javax.swing.JTable tblstocks;
    public static javax.swing.JTextArea txtlotdetails;
    public static javax.swing.JTextField txtlotno;
    public static javax.swing.JFormattedTextField txtpodate;
    public static javax.swing.JTextField txtpoexpected;
    public static javax.swing.JTextField txtpono;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsearchstocks;
    private javax.swing.JFormattedTextField txtsellingprice;
    private javax.swing.JFormattedTextField txtsubtotal;
    public static javax.swing.JTextField txtsupcode;
    public static javax.swing.JTextArea txtsupdetails;
    // End of variables declaration//GEN-END:variables
}
