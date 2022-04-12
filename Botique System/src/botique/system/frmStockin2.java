/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;

import static botique.system.frmPO.txtlotdetails;
import static botique.system.frmPO.txtlotno;
import static botique.system.frmPO.txtpono;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.joda.time.DateTime;
import org.joda.time.Months;


/**
 *
 * @author Admin
 */
public class frmStockin2 extends javax.swing.JFrame {
 public static String pono;
 String sqlcode,fieldVar, sqlcode2,sqlcode3;
 long po_no, ref_id;
    int refid;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    /**
     * Creates new form frmStockin2
     */
    public frmStockin2() {
        initComponents();
        conn=BotiqueSystem.conn_db();
        loadstocks();
        Timer t= new Timer(refid, dateTime);
          t.start();
          txtpono.requestFocus();
          tblsetup();
          txtdateexpiry.setText(BotiqueSystem.strdatenow());
          txtdaterecieved.setText(BotiqueSystem.strdatenow());
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
    public void postatus(){
        sqlcode="select fld_po_status, fld_po_invoice from tblcreatedpo where fld_po_no = "+ po_no +"";
       boolean value=false;
        try {
                rs=BotiqueSystem.selectquery(sqlcode, conn);
                if (rs.next()) {
                    txtpoinvoice.setText(rs.getString("fld_po_invoice"));
                    String status= rs.getString("fld_po_status");
                    
                    if (status.equals("Closed")) {
                        
                        value=false;
                    }
                    else{
                        value=true;
                    }
                 

                }
                        btnaddpo.setEnabled(value);
                        btnsubmit.setEnabled(value);
                        btncancelpo.setEnabled(value);
                        tblPO.setFocusable(value);
                        cmbstatus.setEnabled(value);
                
        } catch (Exception e) {
        }
         
       
    }
    
    public void loadpo(){
        
//      double gtotal=0;
//      String strgtotal, strsubtotal, strtax;
        DefaultTableModel model = new DefaultTableModel(){
        public boolean isCellEditable(int rowIndex, int colIndex){return false;}};
        
        model.addColumn("ID");
        model.addColumn("Qty");
        model.addColumn("Lot No.");
        model.addColumn("Description");
        model.addColumn("Manufacturer");
        model.addColumn("Category");
        model.addColumn("Recieved");
        
        sqlcode="select * from tblpo inner join tblitemdetails"
                + " on tblpo.fld_lot_no=tblitemdetails.fld_lot_no inner"
                + " join tblcreatedpo on tblpo.fld_po_no = "
                + "tblcreatedpo.fld_po_no where tblcreatedpo.fld_po_no="+ po_no +" order by fld_item_desc asc";
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sqlcode);
            while (rs.next()) {
                int bool=Integer.parseInt(rs.getString("fld_item_status"));
                String status;
                if (bool==0) {
                    status="NO";
                }
                else{
                    status="YES";
                }
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
                    status
                    
                    
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
    
//    public void get_lotno(){
//        if (!txtlotno.getText().isEmpty()) {
//            sqlcode = "select * from tblitemdetails where fld_lot_no="+ txtlotno.getText().trim() ;
//        try {
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(sqlcode);
//            while (rs.next()) {
//                    //txtsellingprice.setText(rs.getString("fld_item_price"));
//                    txtlotdetails.setText(rs.getString("fld_catname")+"\n"
//                            + ""+rs.getString("fld_item_desc")+"\n"
//                                        +rs.getString("fld_manu_name"));
//                   
//         //txtprice.setText(rs.getString("fld_ven_price"));
//
//            }
//            txtqty.requestFocus();
//         rs.close();
//         stmt.close();
//         
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }        
//        }
//    }
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
                    
                    //txtlotno.requestFocus();
             
//                    txtsupcity.setText(rs.getString("fld_sup_city"));
//                    txtsupprovince.setText(rs.getString("fld_sup_province"));
         

            }
            
         rs.close();
         stmt.close();
         
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        
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
        jPanel6 = new javax.swing.JPanel();
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
        txtsearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbsearch = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbstatus = new javax.swing.JComboBox();
        btnsubmit = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtdaterecieved = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtpoinvoice = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtlotno = new javax.swing.JTextField();
        txtqty = new javax.swing.JTextField();
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
        txtdateexpiry = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel12.setText("Stockin/receive p.o.");
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
        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 100, 40, 630));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "P.O. Received Details", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setName("d"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Invoice no.");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

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
        jPanel1.add(txtpono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 187, -1));

        btnselectpo.setText("Create/select");
        btnselectpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselectpoActionPerformed(evt);
            }
        });
        jPanel1.add(btnselectpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 30, -1));

        txtpodate.setEditable(false);
        txtpodate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("M/d/yyyy"))));
        txtpodate.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtpodate.setFocusable(false);
        txtpodate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpodateFocusLost(evt);
            }
        });
        jPanel1.add(txtpodate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 190, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("P.O Date");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        txtpoexpected.setEditable(false);
        txtpoexpected.setFocusable(false);
        jPanel1.add(txtpoexpected, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 187, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Expected Date");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 80, -1));

        txtsupcode.setEditable(false);
        txtsupcode.setFocusable(false);
        jPanel1.add(txtsupcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 187, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Supplier Code");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, 20));

        txtsupdetails.setEditable(false);
        txtsupdetails.setColumns(20);
        txtsupdetails.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtsupdetails.setRows(3);
        txtsupdetails.setFocusable(false);
        jScrollPane4.setViewportView(txtsupdetails);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 190, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Supplier Details");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPOMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPOMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPO);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 540, 180));

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

        cmbstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Open", "Closed" }));
        jPanel1.add(cmbstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 130, 30));

        btnsubmit.setText("Submit");
        btnsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsubmitActionPerformed(evt);
            }
        });
        jPanel1.add(btnsubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(735, 250, 140, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 100, 30));

        jLabel17.setText("Remarks");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, -1, -1));

        txtdaterecieved.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtdaterecieved.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdaterecievedFocusLost(evt);
            }
        });
        jPanel1.add(txtdaterecieved, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 187, -1));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Date Received");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 80, -1));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("P.O No.");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        txtpoinvoice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpoinvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpoinvoiceActionPerformed(evt);
            }
        });
        txtpoinvoice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpoinvoiceKeyReleased(evt);
            }
        });
        jPanel1.add(txtpoinvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 920, 320));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Add to stocks", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Lot No.");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Details");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        txtlotno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        jPanel2.add(txtlotno, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 200, -1));

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
        });
        jPanel2.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 100, -1));

        btncancelpo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncancelpo.setText("Cancel");
        btncancelpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelpoActionPerformed(evt);
            }
        });
        jPanel2.add(btncancelpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 210, 30));

        btnaddpo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnaddpo.setText("Receive");
        btnaddpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddpoActionPerformed(evt);
            }
        });
        jPanel2.add(btnaddpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 210, 30));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Expiry");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        txtlotdetails.setEditable(false);
        txtlotdetails.setColumns(20);
        txtlotdetails.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtlotdetails.setRows(3);
        txtlotdetails.setFocusable(false);
        jScrollPane5.setViewportView(txtlotdetails);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 200, -1));

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

        txtdateexpiry.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM-dd-yyyy"))));
        txtdateexpiry.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtdateexpiry, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 100, -1));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Quantity");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 920, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtponoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtponoFocusLost

    }//GEN-LAST:event_txtponoFocusLost

    private void txtponoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtponoActionPerformed
        if (txtpono.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Empty");
        }
        else{
            po_no=Integer.parseInt(txtpono.getText().trim());
            get_pono();
            loadpo();
            postatus();
            
        }

    }//GEN-LAST:event_txtponoActionPerformed

    private void txtponoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtponoPropertyChange

    }//GEN-LAST:event_txtponoPropertyChange

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

    private void btnselectpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselectpoActionPerformed
        txtpono.setText("");
        txtpodate.setText("");
        txtpoexpected.setText("");
        txtsupcode.setText("");
        txtsupdetails.setText("");
        tblsetup();
        BotiqueSystem.frmopened="Stockin";
        frmcreatepo create = new frmcreatepo();
        create.setVisible(true);

        //       frmSelectPOno selectpo = new frmSelectPOno();
        //        selectpo.setVisible(true);
    }//GEN-LAST:event_btnselectpoActionPerformed

    private void txtpodateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpodateFocusLost

    }//GEN-LAST:event_txtpodateFocusLost

    private void tblPOMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPOMousePressed
        if (tblPO.getSelectedRowCount()>0) {
             ref_id=Integer.parseInt(tblPO.getValueAt(tblPO.getSelectedRow(),0).toString());
        }
        else{
            ref_id=0;
        }
//        if (btneditpo.getText().equals("Update")) {
//            JOptionPane.showMessageDialog(null, "Finish or Cancel Update");
//            btncancelpo.requestFocus();
//        }
//        else if (tblPO.getSelectedRowCount() >0 ) {
//            refid= Integer.parseInt(tblPO.getValueAt(tblPO.getSelectedRow(), 0).toString());
//            selecting();
//        }
    }//GEN-LAST:event_tblPOMousePressed

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
             model.addColumn("Recieved");

            sqlcode="select * from tblpo inner join tblitemdetails"
            + " on tblpo.fld_lot_no=tblitemdetails.fld_lot_no where fld_po_no="+ po_no +" and "+ fieldVar +" like '%"+ txtsearch.getText() +"%' order by fld_item_desc asc";
            //JOptionPane.showMessageDialog(null, sqlcode);
            try {
                stmt = conn.createStatement();
                rs=stmt.executeQuery(sqlcode);
                while (rs.next()) {
                     int bool=Integer.parseInt(rs.getString("fld_item_status"));
                String status;
                if (bool==0) {
                    status="NO";
                }
                else{
                    status="YES";
                }
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
                        status

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
    
        
        sqlcode="Select tblitemdetails.fld_lot_no, "
                + "tblitemdetails.fld_item_desc, "
                + "tblitemdetails.fld_catname, "
                + "tblitemdetails.fld_manu_name, "
                + "tblitemdetails.fld_item_price,"
                + "tblstocks.fld_stocks_qty "
                + "from tblitemdetails inner join tblstocks"
                + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no order by fld_item_desc asc";
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
    
    public void get_lotno(){
        if (!txtlotno.getText().isEmpty()) {
            sqlcode = "select * from tblitemdetails where fld_lot_no="+ txtlotno.getText().trim() ;
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
    private void txtsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyTyped

    }//GEN-LAST:event_txtsearchKeyTyped

    private void txtlotnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtlotnoFocusLost

    }//GEN-LAST:event_txtlotnoFocusLost

    private void txtlotnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlotnoActionPerformed
        get_lotno();
    }//GEN-LAST:event_txtlotnoActionPerformed

    private void txtlotnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlotnoKeyPressed

    }//GEN-LAST:event_txtlotnoKeyPressed

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqtyActionPerformed
        btnaddpo.requestFocus();
    }//GEN-LAST:event_txtqtyActionPerformed

    private void txtqtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyPressed
        char ch=evt.getKeyChar();

        if (evt.getKeyChar()==Event.BACK_SPACE
            || evt.getKeyChar()==Event.DELETE
            || evt.getKeyChar()==Event.UP
            || evt.getKeyChar()==Event.DOWN
            || evt.getKeyChar()==Event.LEFT
            || evt.getKeyChar()==Event.RIGHT
        ) {}
        else if(ch==Event.ENTER){
            btnaddpoActionPerformed(null);
        }
        else if (!Character.isDigit(ch)) {

            JOptionPane.showMessageDialog(null, "Not a number");
            txtqty.setText("");

        }
    }//GEN-LAST:event_txtqtyKeyPressed

     public boolean text_is_empty(){
         Boolean result=false;
        if ((txtpono.getText().trim().isEmpty()) || (txtlotno.getText().trim().isEmpty()) || (txtqty.getText().trim().isEmpty())) {
            result= true;
        }
       
        return result;
    }
     public void cleartext(){
                    //txtprice.setText("");
                    txtqty.setText("");
                    txtlotno.setText("");
                    //txtsubtotal.setText("");
                    txtlotdetails.setText("");
                    //txtsellingprice.setText("");
                    //txtdiscount.setText("0.00");
        }
     public void btnstart(){
       
    }
    private void btncancelpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelpoActionPerformed

        cleartext();
        loadpo();
        
        tblstocks.setFocusable(true);

        btnstart();
    }//GEN-LAST:event_btncancelpoActionPerformed

    public void add(){
        try {
                    sqlcode= "insert into tblstocksin set fld_po_no="+ txtpono.getText().trim() +","
                            + "fld_lot_no='"+ txtlotno.getText().trim() +"', "
                     + "fld_date_received=STR_TO_DATE('"+ txtdaterecieved.getText().trim() +"', '%m-%d-%Y'), "
                     + "fld_date_expiry=str_to_date('"+ txtdateexpiry.getText() +"', '%m-%d-%Y'), "
                     + "fld_qty_recieved='"+ txtqty.getText() +"'";
                     //+ "fld_remarks='"+ txtremarks.getText().trim() +"',"
                     //+ "fld_price="+ txtprice.getText() +",fld_selling_price="+ txtsellingprice.getText() +"";
              sqlcode2="select * from tblstocks where fld_lot_no='"+ txtlotno.getText().trim() +"'";
               //sqlcode3="Update tblpo set fld_item_status=1 where fld_po_id="+ref_id+"";
              //JOptionPane.showMessageDialog(null, sqlcode3);
               int addqty;
                addqty = Integer.parseInt(txtqty.getText());
               
               if ((BotiqueSystem.addupdatedel(sqlcode, conn)) 
                       
                       &&(BotiqueSystem.addqty(txtlotno.getText(), addqty,"Addition", conn))
                       )
                        {
                  
                    JOptionPane.showMessageDialog(null, "Added!");
                    loadstocks();

                    loadpo();

                    cleartext();
                 
               }
        } catch (NumberFormatException | HeadlessException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void recievedstatus(){
          sqlcode3="Update tblpo set fld_item_status=1 where fld_po_id="+ref_id+"";
            BotiqueSystem.addupdatedel(sqlcode3, conn);
            txtponoActionPerformed(null);
    }
    public void expirybatch(){
        BotiqueSystem.transferdate(txtlotno.getText().trim(), txtdateexpiry.getText().trim());
    }
    private void btnaddpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddpoActionPerformed
       if (text_is_empty()) {
            JOptionPane.showMessageDialog(null, "Empty input not allowed!");
            txtqty.requestFocus();
        }
       else if(checkexpiry(txtdateexpiry.getText().trim())){
           
           int recievedqty=Integer.parseInt(txtqty.getText().trim().toString());
           if (recievedqty<poqty) {
               int ans=JOptionPane.showConfirmDialog(null, "Quantity recieved less than P.O. Would you like to recieve notification for lacking items? ", "Lacking Items...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
               //JOptionPane.showMessageDialog(null, "Quantity recieved less than to P.O.") ;
                  if (ans==JOptionPane.YES_OPTION) {
                   recievedstatus();
                   int lackqty=poqty-recievedqty;
                   //JOptionPane.showMessageDialog(null, lackqty);
                   lacking(lackqty);
                    expirybatch();
                   add();
                  
                   
               }
                  else if (ans==JOptionPane.NO_OPTION) {
                     expirybatch();
                      add();
                    recievedstatus();
                   
               }
                  else if (ans==JOptionPane.CANCEL_OPTION) {
                   JOptionPane.showMessageDialog(null, "Item not recieved!");
               }
                          
               
           } else if (recievedqty>poqty) {
               JOptionPane.showMessageDialog(null, "Quantity recieved greater than P.O. item") ;
                  // add();
               
           }
           else if (recievedqty==poqty) {
                expirybatch();
               add();
               recievedstatus();
               loadpo();
                
           }
           
       }
        
       
    }//GEN-LAST:event_btnaddpoActionPerformed
    public void lacking(int lackingqty){
        String sqlcode4= "update tblstocks set fld_lacking ="+ lackingqty +" where fld_lot_no='"+ txtlotno.getText() +"'";
        //JOptionPane.showMessageDialog(null, sqlcode4);
        if (BotiqueSystem.addupdatedel(sqlcode4, conn)) {
            JOptionPane.showMessageDialog(null, "Notification is active until lacking items are satisfied");
        }
    }
    
    
    public void getlotnobytable(){
    if (tblPO.getSelectedRowCount()>0) {
                    txtlotno.setText(tblPO.getValueAt(tblPO.getSelectedRow(), 2).toString());
                   txtlotdetails.setText(
                     tblPO.getValueAt(tblPO.getSelectedRow(), 5).toString()+"\n"
                     +tblPO.getValueAt(tblPO.getSelectedRow(),3).toString()+"\n"
                     +tblPO.getValueAt(tblPO.getSelectedRow(),4).toString()
                      /*+tblstocks.getValueAt(tblstocks.getSelectedRow(),5).toString() */ );
                   txtqty.requestFocus();
                }
            else{
                JOptionPane.showMessageDialog(null, "No Selection");
            }
 }
    private void tblstocksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstocksMouseClicked
//        if (evt.getClickCount()==2) {
//            getlotnobytable();
//        }

    }//GEN-LAST:event_tblstocksMouseClicked

    private void tblstocksMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstocksMousePressed

    }//GEN-LAST:event_tblstocksMousePressed

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
            + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no where tblitemdetails.fld_reorder_point >=tblstocks.fld_stocks_qty order by tblitemdetails.fld_item_desc asc";
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
                hidecolumn();

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
            + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no where "+ fieldVar +" like '%"+ txtsearchstocks.getText() +"%' order by tblitemdetails.fld_item_desc asc" ;
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
                hidecolumn();
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

    private void txtsearchstocksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchstocksKeyTyped

    }//GEN-LAST:event_txtsearchstocksKeyTyped

 int poqty;
    private void tblPOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPOMouseClicked
         if (evt.getClickCount()==2) {
             //JOptionPane.showMessageDialog(null, tblPO.getValueAt(tblPO.getSelectedRow(), 6));
             if (btnsubmit.isEnabled()) {
                 
                        if (tblPO.getValueAt(tblPO.getSelectedRow(), 6).equals("NO")) {
                        getlotnobytable();
                        get_lotno();
                        poqty=Integer.parseInt(tblPO.getValueAt(tblPO.getSelectedRow(), 1).toString());
                      }
                    else{
                     JOptionPane.showMessageDialog(null, "Already added!");
                    }
             }
             else{
                 JOptionPane.showMessageDialog(null, "This P.O. was Closed or Canceled!");
             }
             
             
        }
         
        
        
    }//GEN-LAST:event_tblPOMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public boolean checkexpiry(String strdate){
        
        boolean result= false;
        try {
            
            SimpleDateFormat formatter = new SimpleDateFormat("M-dd-yyyy");
            Date dateexpire = formatter.parse(strdate);
            
            SimpleDateFormat sdfM = new SimpleDateFormat("M");
            SimpleDateFormat sdfD = new SimpleDateFormat("dd");
            SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
             
            String strm=sdfM.format(dateexpire);
            String strd=sdfD.format(dateexpire);
            String stry=sdfY.format(dateexpire);
            
            
            int M=Integer.parseInt(strm);
            int D=Integer.parseInt(strd);
            int Y=Integer.parseInt(stry);
           
             DateTime x = new DateTime().withDate(Y, M, D);//EXPIRY DATE
              DateTime y = new DateTime();//TODAY
              
             Months d = Months.monthsBetween(y, x);
             int diff = d.getMonths();
             if (diff <=3 ) {
                JOptionPane.showMessageDialog(null, "Double check the expiry date!");
               
            }
             else{
                 result= true;
             }
        } catch (ParseException | NumberFormatException | HeadlessException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
       
        }
       return result;
    }
    private void txtdaterecievedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdaterecievedFocusLost
         try {
              
                          SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                          Date recdate = formatter.parse(txtdaterecieved.getText());
                          String strd2=formatter.format(new Date())  ;                       
                           Date expceteddate = formatter.parse(strd2);
                     
                          if (expceteddate.before(recdate )) {
                              JOptionPane.showMessageDialog(null, "Date recieved invalid!");
                              txtdaterecieved.requestFocus();
                          }
        } catch (ParseException | HeadlessException e) {
        }
    }//GEN-LAST:event_txtdaterecievedFocusLost

    private void txtpoinvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpoinvoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpoinvoiceActionPerformed

    private void txtpoinvoiceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpoinvoiceKeyReleased
        char ch= evt.getKeyChar();
        if (BotiqueSystem.isNumber(evt)) {
            JOptionPane.showMessageDialog(null, "Not a number!");
            txtpoinvoice.setText("");
            txtpoinvoice.requestFocus();
        }
    }//GEN-LAST:event_txtpoinvoiceKeyReleased

    private void btnsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubmitActionPerformed

        if ((tblPO.getRowCount()>0) && (!txtpoinvoice.getText().trim().equals(""))) {

            sqlcode="update tblcreatedpo set fld_po_status='"+ cmbstatus.getSelectedItem() +"', fld_po_invoice ='"+ txtpoinvoice.getText() +"' where fld_po_no="+po_no+"";

            if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                JOptionPane.showMessageDialog(null, "Success!");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Nothing to submit, empty P.O. details/Invoice no.");
            txtpono.requestFocus();
        }

    }//GEN-LAST:event_btnsubmitActionPerformed

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
            java.util.logging.Logger.getLogger(frmStockin2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmStockin2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmStockin2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmStockin2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmStockin2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaddpo;
    private javax.swing.JButton btncancelpo;
    private javax.swing.JButton btnselectpo;
    private javax.swing.JButton btnsubmit;
    private javax.swing.JComboBox cbsearch;
    private javax.swing.JComboBox cbsearchstocks;
    private javax.swing.JComboBox cmbstatus;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lbltime;
    private javax.swing.JLabel lbluser;
    private javax.swing.JTable tblPO;
    private javax.swing.JTable tblstocks;
    private javax.swing.JFormattedTextField txtdateexpiry;
    public static javax.swing.JTextField txtdaterecieved;
    public static javax.swing.JTextArea txtlotdetails;
    public static javax.swing.JTextField txtlotno;
    public static javax.swing.JFormattedTextField txtpodate;
    public static javax.swing.JTextField txtpoexpected;
    private javax.swing.JTextField txtpoinvoice;
    public static javax.swing.JTextField txtpono;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txtsearchstocks;
    public static javax.swing.JTextField txtsupcode;
    public static javax.swing.JTextArea txtsupdetails;
    // End of variables declaration//GEN-END:variables
}
