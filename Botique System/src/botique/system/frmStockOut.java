/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;


import static botique.system.frmPO.txtlotno;
import static botique.system.frmPO.txtpono;
import static botique.system.frmStockOut.invoiceno;
import static botique.system.frmStockOut.loadcart;
import static botique.system.frmStockin2.txtdaterecieved;
import static botique.system.frmStockin2.txtlotdetails;
import static botique.system.frmStockin2.txtlotno;
import static botique.system.frmStockin2.txtpono;
import java.awt.Event;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Admin
 */

public class frmStockOut extends javax.swing.JFrame {
   
    String sqlcode, fieldVar;
    Connection conn=null;
    ResultSet rs=null;
    Statement stmt = null;
    long stocksqty=0;
    long requestqty=0;
    long refid=0;
    static String invoiceno;
    String invoicetype="Private";
    
    
   
    
    
    /**
     * Creates new form frmStockOut
     */
    
    public boolean emptytextboxes(){
        boolean result=false;
        try {
            String textbox[]={txtcustomerdetails.getText().trim(),
        txtcustomerid.getText().trim(),
        txtcustomerdetails.getText().trim(),
        txtinvoiceno.getText().trim(), 
       
        txtlotno.getText().trim(),
     
       
       txtqty.getText().trim(),
        txtsellingprice.getText().trim(),
        txtsellingprice.getText().trim(),
        txtsubtotal.getText().trim(),
                   
        };
            if (BotiqueSystem.isTextEmpty(textbox)) {
                result= true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return result;
    }
    public frmStockOut() {
        BotiqueSystem.frmopened="stockout";
        initComponents();
        conn=BotiqueSystem.conn_db();
        btncancelActionPerformed(null);
        lbluser.setText(BotiqueSystem.user);
        nocart();
        cleartxt();
        Timer t= new Timer(1000, dateTime);  
        t.start();
        btnsubmit.setEnabled(false);
        btnprint.setEnabled(false);
        btnvoidinvoice.setEnabled(false);
        ButtonGroup gb= new ButtonGroup();
        
       
       
        //ROP();
       
        
        
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
    public void nocart(){
//        lblsubtotal.setText("0.00");
//        lbltax.setText("0.00");
        lblgtotal.setText("0.00");
    }
     public void get_lotno(){
        if (!txtlotno.getText().isEmpty()) {
            sqlcode = "select * from tblitemdetails where fld_lot_no="+ txtlotno.getText().trim() ;
            sqlcode="select tblitemdetails.fld_manu_name, "
                    + "tblitemdetails.fld_item_desc, "
                    + "tblitemdetails.fld_catname, "
                    + "tblitemdetails.fld_item_price, "
                    + "tblstocks.fld_stocks_qty from tblitemdetails "
                    + "inner join tblstocks "
                    + "on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no "
                    + "where tblitemdetails.fld_lot_no='"+ txtlotno.getText() +"' ";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlcode);
            if (rs.next()) {
                    txtlotdetails.setText(
               rs.getString("tblitemdetails.fld_manu_name")+"\n"+
                 rs.getString("tblitemdetails.fld_item_desc")+ "\n"+
                   rs.getString("tblitemdetails.fld_catname")
                    
                    );
                    txtsellingprice.setText(rs.getString("fld_item_price"));
                    stocksqty=Integer.parseInt(rs.getString("tblstocks.fld_stocks_qty"));
                    txtqty.requestFocus();
                    //txtsupprovince.setText(rs.getString("fld_sup_province"));
         

            }
            
         rs.close();
         stmt.close();
         
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        }
    }
    
     //carting
     public  static void loadcart(){
         ResultSet rs1=null;
         Connection conn1=BotiqueSystem.conn_db();
          double gtotal=0;
          String strgtotal, strsubtotal, strtax;
          String sqlcode1=" select tblitemdetails.fld_lot_no, "
                 + "tblitemdetails.fld_manu_name, "
                 + "tblitemdetails.fld_catname, "
                 + "tblitemdetails.fld_item_desc, "
                 + "tblitemdetails.fld_item_price, "
                 + "tblcart.fld_qty, "
                    + "DATE_FORMAT(tblcart.fld_expiry,'%m-%d-%Y'),"
                 + "tblcart.fld_subtotal, "
                  + "tblcart.fld_price "
                 + "from tblitemdetails"
                 + " inner join "
                 + "tblcart "
                 + "on "
                 + "tblitemdetails.fld_lot_no"
                 + "="
                 + "tblcart.fld_lot_no "
                 + "where"
                 + " tblcart.fld_invoice_no='"+ invoiceno +"' order by tblitemdetails.fld_item_desc asc";
         
         DefaultTableModel dtm = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int colIndex){return false;}};
         dtm.addColumn("Lot no");
          dtm.addColumn("Description");
           dtm.addColumn("Category");
            
            dtm.addColumn("Manufacturer");
            dtm.addColumn("Price");
             dtm.addColumn("Expiry");
            dtm.addColumn("Qty");
            dtm.addColumn("Total");
            
         try {
             rs1=BotiqueSystem.selectquery(sqlcode1, conn1);
             while(rs1.next()){
                  double a = Double.parseDouble(rs1.getString("tblcart.fld_subtotal"));
//                   double b= Integer.parseInt(rs.getString("fld_po_qty"));
//                    double c= a*b;
                    gtotal=gtotal+a;
                 dtm.addRow(new Object[]{
                 rs1.getString("tblitemdetails.fld_lot_no"),
                     rs1.getString("tblitemdetails.fld_item_desc"),
                     rs1.getString("tblitemdetails.fld_catname"),
                      rs1.getString("tblitemdetails.fld_manu_name"),
                     
                     rs1.getString("tblcart.fld_price"),
                     rs1.getString("DATE_FORMAT(tblcart.fld_expiry,'%m-%d-%Y')"),
                     
                     rs1.getString("tblcart.fld_qty"),
                     rs1.getString("tblcart.fld_subtotal")
                 });
             }
               DecimalFormat df=new DecimalFormat("0.00");
             if (gtotal>=1000) {
                  df = new DecimalFormat("0,000.00");
             }
             else{
                 df = new DecimalFormat("0.00");
             }
             
             //double tax= (gtotal *  0.12);
            
       // strgtotal=String.valueOf(gtotal);
       // strtax=df.format(tax);
        
      //  strsubtotal= String.valueOf(gtotal-tax);
//        lblsubtotal.setText(strsubtotal);
//        lbltax.setText(strtax);
        
        lblgtotal.setText( df.format(gtotal));
             tblcart.setModel(dtm);
             rs1.close();
           TableColumn tc= tblcart.getColumnModel().getColumn(1);
           tc.setPreferredWidth(300);
           tc.setMaxWidth(500);
           tc= tblcart.getColumnModel().getColumn(2);
           tc.setPreferredWidth(100);
            tc.setMaxWidth(500);
            
            
           
             
         } catch (Exception e) {
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

        btnadd = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbltime = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtinvoiceno = new javax.swing.JTextField();
        txtinvoicedate = new javax.swing.JFormattedTextField();
        txtcustomerid = new javax.swing.JTextField();
        btnselectinvoice = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtcustomerdetails = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtlotno = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtlotdetails = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        txtsellingprice = new javax.swing.JFormattedTextField();
        txtqty = new javax.swing.JFormattedTextField();
        txtexpiry = new javax.swing.JFormattedTextField();
        txtsubtotal = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstocks = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        cbsearchstocks = new javax.swing.JComboBox();
        txtsearchstocks = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnprintstocks = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblcart = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        cbsearchstocks1 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtsearchcart = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lblgtotal1 = new javax.swing.JLabel();
        btnprint = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblgtotal = new javax.swing.JLabel();
        btnsubmit = new javax.swing.JButton();
        lblgtotal2 = new javax.swing.JLabel();
        btnvoidinvoice = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Stockout");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnadd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnadd.setText("Add to cart");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        getContentPane().add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 140, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel17.setText("JB Pharma and Trade Center");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbluser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbluser.setText("Welcome");
        jPanel4.add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Welcome");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        lbltime.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbltime.setText("Time");
        jPanel4.add(lbltime, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, -1, 39));

        lbldate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbldate.setText("Date/Day");
        jPanel4.add(lbldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1170, 70));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Invoice Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Invoice #");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jLabel3.setText("Invoice Date");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel4.setText("Customer ID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLabel5.setText("Customer Details");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        txtinvoiceno.setEditable(false);
        txtinvoiceno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtinvoiceno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtinvoicenoActionPerformed(evt);
            }
        });
        txtinvoiceno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtinvoicenoKeyReleased(evt);
            }
        });
        jPanel1.add(txtinvoiceno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 166, -1));

        txtinvoicedate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("M-dd-yyyy"))));
        txtinvoicedate.setFocusable(false);
        jPanel1.add(txtinvoicedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 166, -1));

        txtcustomerid.setFocusable(false);
        jPanel1.add(txtcustomerid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 166, -1));

        btnselectinvoice.setText("...");
        btnselectinvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselectinvoiceActionPerformed(evt);
            }
        });
        jPanel1.add(btnselectinvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 30, -1));

        txtcustomerdetails.setEditable(false);
        txtcustomerdetails.setColumns(20);
        txtcustomerdetails.setLineWrap(true);
        txtcustomerdetails.setRows(5);
        txtcustomerdetails.setFocusable(false);
        jScrollPane3.setViewportView(txtcustomerdetails);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 165, 69));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 340, 220));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Item Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Lot #");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel7.setText("Lot Details");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel8.setText("Selling Price");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel9.setText("Quantity");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel10.setText("Subtotal");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        txtlotno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtlotno.setFocusable(false);
        txtlotno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlotnoActionPerformed(evt);
            }
        });
        jPanel2.add(txtlotno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 166, -1));

        txtlotdetails.setEditable(false);
        txtlotdetails.setColumns(20);
        txtlotdetails.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        txtlotdetails.setLineWrap(true);
        txtlotdetails.setRows(3);
        txtlotdetails.setFocusable(false);
        jScrollPane4.setViewportView(txtlotdetails);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, 68));

        jLabel16.setText("Expiry");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        txtsellingprice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.##"))));
        txtsellingprice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtsellingprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsellingpriceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsellingpriceKeyReleased(evt);
            }
        });
        jPanel2.add(txtsellingprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 170, 30));

        txtqty.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));
        txtqty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtqty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtqtyFocusGained(evt);
            }
        });
        txtqty.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                txtqtyCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtqtyInputMethodTextChanged(evt);
            }
        });
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
        jPanel2.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 170, 30));

        txtexpiry.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM-dd-yyyy"))));
        jPanel2.add(txtexpiry, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 170, 30));

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
        jPanel2.add(txtsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 170, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 340, 300));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Stocks Available", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblstocks.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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
        tblstocks.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblstocks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblstocksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblstocks);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 870, 180));

        jLabel19.setText("by");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, 20));

        cbsearchstocks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DESCRIPTION", "CATEGORY", "MANUFACTURER", "ROP" }));
        cbsearchstocks.setBorder(null);
        cbsearchstocks.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbsearchstocksItemStateChanged(evt);
            }
        });
        jPanel3.add(cbsearchstocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 160, 30));

        txtsearchstocks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchstocksKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsearchstocksKeyTyped(evt);
            }
        });
        jPanel3.add(txtsearchstocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 130, 30));

        jLabel11.setText("Search");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, 20));

        btnprintstocks.setText("Print Stocks");
        btnprintstocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintstocksActionPerformed(evt);
            }
        });
        jPanel3.add(btnprintstocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, 130, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 930, 270));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Customer Cart Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblcart.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblcart.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcart.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblcart.setAutoscrolls(false);
        tblcart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblcartMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblcart);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 710, 260));

        jLabel20.setText("by");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, 20));

        cbsearchstocks1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DESCRIPTION", "CATEGORY", "MANUFACTURER" }));
        cbsearchstocks1.setBorder(null);
        cbsearchstocks1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbsearchstocks1ItemStateChanged(evt);
            }
        });
        jPanel5.add(cbsearchstocks1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 160, 30));

        jLabel12.setText("Search");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, 20));

        txtsearchcart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchcartKeyReleased(evt);
            }
        });
        jPanel5.add(txtsearchcart, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 120, 30));

        btndelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btndelete.setText("Delete from Cart");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 140, 40));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 760, 370));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Payment Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblgtotal1.setText("Customer ID");
        jPanel6.add(lblgtotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, -1, -1));

        btnprint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnprint.setText("Print Invoice");
        btnprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintActionPerformed(evt);
            }
        });
        jPanel6.add(btnprint, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 170, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Total Payment");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblgtotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblgtotal.setForeground(new java.awt.Color(204, 0, 0));
        lblgtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblgtotal.setText("Payment");
        jPanel6.add(lblgtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        btnsubmit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsubmit.setText("Submit");
        btnsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsubmitActionPerformed(evt);
            }
        });
        jPanel6.add(btnsubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 170, 40));

        lblgtotal2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblgtotal2.setForeground(new java.awt.Color(204, 0, 0));
        lblgtotal2.setText("P");
        jPanel6.add(lblgtotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        btnvoidinvoice.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnvoidinvoice.setText("Void Invoice");
        btnvoidinvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvoidinvoiceActionPerformed(evt);
            }
        });
        jPanel6.add(btnvoidinvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 170, 40));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 380, 200, 290));

        btncancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncancel.setText("Clear");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        getContentPane().add(btncancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 630, 150, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static void test(){
    
}
//voiding invoice
public static void voidinvoice(int invoicevar){
    String sqlcart="update tblcart set fld_price=ABS(fld_price) * -1,  fld_subtotal=ABS(fld_subtotal) * -1 where fld_invoice_no="+ invoicevar +"";
   String sqlinvoice="update tblinvoice set fld_invoice_total = ABS(fld_invoice_total) * -1 where fld_invoice_no="+ invoicevar +"";
   String lotno="";
   int addqty=0;
   
    Connection conn=BotiqueSystem.conn_db();
    
    for (int i = 0; i < tblcart.getRowCount(); i++) {
        try {
           lotno=tblcart.getValueAt(i, 0).toString();
        addqty=Integer.parseInt(tblcart.getValueAt(i, 5).toString());
        BotiqueSystem.addqty(lotno, addqty,"Addition", conn); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    
    
     
   if ((BotiqueSystem.addupdatedel(sqlcart, conn))&& (BotiqueSystem.addupdatedel(sqlinvoice, conn))) { // 
        JOptionPane.showMessageDialog(null, "Invoice no "+invoicevar+" is now void");
     loadstocks();
    loadcart();
    }
}
    private void txtinvoicenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtinvoicenoActionPerformed
        if (txtinvoiceno.getText().trim().equals("")) {
            invoiceno="0";
        }
        else{
            invoiceno=txtinvoiceno.getText();
            BotiqueSystem.thisinvoice=txtinvoiceno.getText();
        }
        sqlcode="select "
               // + "tblinvoiceno.fld_invoice_id,"
                        + " tblinvoiceno.fld_invoice_no, "
                + " tblinvoiceno.fld_invoice_type, "        
                + " date_format(tblinvoiceno.fld_invoice_date, '%m-%d-%Y'),"
                        + " tblcustomer.fld_customer_code, "
                        + "tblcustomer.fld_fname, "
                        + "tblcustomer.fld_mname,"
                        + " tblcustomer.fld_lname, "
                        + "tblcustomer.fld_zone, "
                        + "tblcustomer.fld_st, "
                        + "tblcustomer.fld_city, "
                        + "tblcustomer.fld_province "
                        + "from tblinvoiceno inner join"
                        + " tblcustomer on tblinvoiceno.fld_customer_code=tblcustomer.fld_customer_code "
                 + "where tblinvoiceno.fld_invoice_no='"+ invoiceno +"'";
         try {
            rs=BotiqueSystem.selectquery(sqlcode, conn) ;
            if (rs.next()) {
                
             
                         txtinvoiceno.setText(rs.getString("tblinvoiceno.fld_invoice_no"));
                         
                          txtinvoicedate.setText(rs.getString("date_format(tblinvoiceno.fld_invoice_date, '%M-%d-%y')"));
                            txtcustomerid.setText(rs.getString("tblcustomer.fld_customer_code"));
                           txtcustomerdetails.setText( rs.getString("tblcustomer.fld_fname")+" "
                                    + ""+rs.getString("tblcustomer.fld_mname") +" "+
                            rs.getString("tblcustomer.fld_lname")+"\n"+       
                             rs.getString("tblcustomer.fld_zone")+ " "+
                            rs.getString("tblcustomer.fld_st") +" "+
                          
                            rs.getString("tblcustomer.fld_city")+ " "+
                            rs.getString("tblcustomer.fld_province"));
                           txtlotno.requestFocus();
                           invoicetype=rs.getString("tblinvoiceno.fld_invoice_type");
                                        }
            
            else{
               //txtdaterecieved.setText("");
                txtcustomerid.setText("");
                txtcustomerdetails.setText("");
                nocart();
            }
            rs.close();
            loadstocks();
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        loadcart();
        invoicestatus();
         
         
    }//GEN-LAST:event_txtinvoicenoActionPerformed

    public void cleartxt(){
        txtlotno.setText("");
        txtlotdetails.setText("");
        txtqty.setText("");
//        txtexpiry.setText("0.00");
        txtsubtotal.setText("0.00");
        txtsellingprice.setText("0.00");

    }
    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        
        if (emptytextboxes()) {
            JOptionPane.showMessageDialog(null, "Fill all the data needed!");
        }
        else if(subtotal<=0){
            JOptionPane.showMessageDialog(null, "Subtotal should not be less or equal to 0!");
                }
         else{

                        sqlcode="select * from tblcart where fld_lot_no = '"+ txtlotno.getText() +"' and fld_invoice_no = '"+ invoiceno +"'";
                        if (BotiqueSystem.isduplicate(sqlcode, conn)) {
                            JOptionPane.showMessageDialog(null, "Double entry for lot no "+txtlotno.getText());
                            txtlotno.requestFocus();
                        }
                        else{
                            requestqty=Integer.parseInt(txtqty.getText().trim());
                                if (stocksqty < requestqty) {
                                    JOptionPane.showMessageDialog(null, "Insufficient Stocks");
                                    txtqty.requestFocus();
                                }
                                else{

                                        if (!BotiqueSystem.validdate(txtexpiry.getText())) {
                                            JOptionPane.showMessageDialog(null, "Invalid date!");
                                        }else{
                                          
                                                         

                                                           sqlcode="insert into tblcart set "
                                                           + "fld_invoice_no='"+ txtinvoiceno.getText() +"', "
                                                           + "fld_customer_code='"+ txtcustomerid.getText() +"', "
                                                           + "fld_lot_no='"+ txtlotno.getText() +"', "
                                                           + "fld_qty="+ txtqty.getText() +", "
                                                               + "fld_price="+ txtsellingprice.getText().replace(",", "") +", "
                                                                   + "fld_expiry=str_to_date('"+ txtexpiry.getText() +"', '%m-%d-%Y'), "
                                                           + "fld_subtotal="+ txtsubtotal.getText().replace(",", "") +"";
                                                          
                                                           if (BotiqueSystem.addupdatedel(sqlcode, conn) && BotiqueSystem.addqty(txtlotno.getText(),requestqty , "Subtract", conn) ) {
                                                                       JOptionPane.showMessageDialog(null, "Added!");
                                                                       loadcart();
                                                                       loadstocks();
                                                                       ROP();

                                                                       btncancelActionPerformed(evt);


                                                           }
                                   }
                                }


                        }





          }   
        
        
        
    }//GEN-LAST:event_btnaddActionPerformed
frmCreateInvoice selectinvoice= new  frmCreateInvoice();
    private void btnselectinvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselectinvoiceActionPerformed
    
       
        selectinvoice.setVisible(true);
        txtinvoiceno.setText("0");
        
       txtinvoicenoActionPerformed(evt);
       txtinvoiceno.setText("");
       cleartxt();
        

    }//GEN-LAST:event_btnselectinvoiceActionPerformed

    private void txtlotnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlotnoActionPerformed
       get_lotno();
    }//GEN-LAST:event_txtlotnoActionPerformed

    public void invoicestatus(){
        sqlcode="select * from tblinvoice where fld_invoice_no='"+ invoiceno +"'";
        boolean value=false;
        try {
            rs=BotiqueSystem.selectquery(sqlcode, conn);
            if (rs.next()) {
                value=false;
                btnprint.setEnabled(true);
                btnvoidinvoice.setEnabled(true);
               
               
            }
            else{
                 value=true;
                btnprint.setEnabled(false);
                 btnvoidinvoice.setEnabled(false);
                }
            
                btnsubmit.setEnabled(value);
                btnadd.setEnabled(value);
                btndelete.setEnabled(value);
               // btnedit.setEnabled(value);
                btncancel.setEnabled(value);
               
               

        } catch (Exception e) {
            
        }
        
    }
    
    private void btnsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubmitActionPerformed
      sqlcode="insert into tblinvoice set fld_invoice_no='"+invoiceno+"', "
              //+ "fld_invoice_type='"+ BotiqueSystem.invoicetype +"', "
              + "fld_invoice_total="+ lblgtotal.getText().replace(",", "") +" ";
              //+ "fld_invoice_subtotal="+lblsubtotal.getText()+"";
        if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
            JOptionPane.showMessageDialog(null, "Submitted!");
            btnsubmit.setEnabled(false);
            invoicestatus();
            ROP();
            
        }
      
    }//GEN-LAST:event_btnsubmitActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        try {
            
       
        JasperDesign jd=null;
        HashMap param1 = new HashMap();
        if (invoicetype.equals("Gov")) {
            
            //jd= JRXmlLoader.load(BotiqueSystem.rootdir+"rpt_printInvoice-GOV.jrxml");
            param1.put("strinvoice", invoiceno);
            param1.put("user", BotiqueSystem.user);
            BotiqueSystem.openreport(BotiqueSystem.rootdir+"rpt_printInvoice-GOV.jasper", param1,conn);
            
        }
        else{
            for (int i = 1; i <=2; i++) {
                //jd= JRXmlLoader.load(BotiqueSystem.rootdir+"rpt_printInvoice.jrxml");
                
                
                if (i==1) {
                    param1.put("receipt_type", "DELIVERY RECEIPT");
                }
                else{
                    param1.put("receipt_type", "CHARGE INVOICE");
                }
                
                param1.put("strinvoice", invoiceno);
                param1.put("user", BotiqueSystem.user);
                BotiqueSystem.openreport(BotiqueSystem.rootdir+"rpt_printInvoice.jasper", param1,conn);
                
            }
            
            
            
            
        }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnprintActionPerformed

    public void ROP(){
       
            DefaultTableModel NOTIFY = new DefaultTableModel(){
                public boolean isCellEditable(int rowIndex, int colIndex){return false;};
            };
            NOTIFY.addColumn("Lot no");
            NOTIFY.addColumn("Desc");
            NOTIFY.addColumn("Stocks");

            sqlcode="Select tblitemdetails.fld_lot_no, "
            + "tblitemdetails.fld_item_desc, "
            + "tblitemdetails.fld_catname, "
            + "tblitemdetails.fld_manu_name, "
            + "tblitemdetails.fld_item_price,"
            + "tblstocks.fld_stocks_qty "
            + "from tblitemdetails inner join tblstocks"
            + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no where tblitemdetails.fld_reorder_point >=tblstocks.fld_stocks_qty";
            rs=BotiqueSystem.selectquery(sqlcode, conn);
            try {
                while (rs.next()) {
                    NOTIFY.addRow(new Object[]{
                        rs.getString("tblitemdetails.fld_lot_no"),
                        rs.getString("tblitemdetails.fld_item_desc"),
                        rs.getString("tblstocks.fld_stocks_qty")
                    });

                }
                //tblrop.setModel(NOTIFY);

                rs.close();
                //TableColumn tc = tblrop.getColumnModel().getColumn(2);
                //tc.setHeaderRenderer(null);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        
      
    }
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
         dtm.addColumn("Expiry");
            
sqlcode="Select tblitemdetails.fld_lot_no, "
                + "tblitemdetails.fld_item_desc, "
                + "tblitemdetails.fld_catname, "
                + "tblitemdetails.fld_manu_name, "
                + "tblitemdetails.fld_item_price,"
                + "DATE_FORMAT(tblstocks.fld_batch2,'%m-%d-%Y'),"
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
                     rs.getString("tblstocks.fld_stocks_qty"),
                     rs.getString("DATE_FORMAT(tblstocks.fld_batch2,'%m-%d-%Y')")
                    });

                }
                tblstocks.setModel(dtm);

                rs.close();
                columnsize();
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
         dtm.addColumn("Expiry");

         String sqlcode="Select tblitemdetails.fld_lot_no, "
                + "tblitemdetails.fld_item_desc, "
                + "tblitemdetails.fld_catname, "
                + "tblitemdetails.fld_manu_name, "
                + "tblitemdetails.fld_item_price,"
                + "DATE_FORMAT(tblstocks.fld_batch2,'%m-%d-%Y'),"
                + "tblstocks.fld_stocks_qty "
                + "from tblitemdetails inner join tblstocks"
                + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no where "+ fieldVar +" like '%"+ txtsearchstocks.getText() +"%' "
                 
                + "order by tblitemdetails.fld_item_desc asc";
         
//            sqlcode="Select tblitemdetails.fld_lot_no, "
//            + "tblitemdetails.fld_item_desc, "
//            + "tblitemdetails.fld_catname, "
//            + "tblitemdetails.fld_manu_name, "
//            + "tblitemdetails.fld_item_price,"
//            + "tblstocks.fld_stocks_qty "
//            + "from tblitemdetails inner join tblstocks"
//            + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no where "+ fieldVar +" like '%"+ txtsearchstocks.getText() +"%' "
//                    + "order by tblitemdetails.fld_item_desc asc ";
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
                     rs.getString("DATE_FORMAT(tblstocks.fld_batch2,'%m-%d-%Y')")
                    });

                }
                tblstocks.setModel(dtm);

                rs.close();
                columnsize();
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

    private void cbsearchstocks1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbsearchstocks1ItemStateChanged
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

    }//GEN-LAST:event_cbsearchstocks1ItemStateChanged
public void getlotnobytable(){
    if (tblstocks.getSelectedRowCount()>0) {
                    txtlotno.setText(tblstocks.getValueAt(tblstocks.getSelectedRow(), 0).toString());
                   txtlotdetails.setText(
                     tblstocks.getValueAt(tblstocks.getSelectedRow(), 2).toString()+"\n"
                     +tblstocks.getValueAt(tblstocks.getSelectedRow(),1).toString()+"\n"
                     +tblstocks.getValueAt(tblstocks.getSelectedRow(),3).toString()
                      /*+tblstocks.getValueAt(tblstocks.getSelectedRow(),5).toString() */ );
                   txtsellingprice.setText(tblstocks.getValueAt(tblstocks.getSelectedRow(),4).toString());
                   txtexpiry.setText(tblstocks.getValueAt(tblstocks.getSelectedRow(),6).toString());
                   txtqty.requestFocus();
                }
            else{
                JOptionPane.showMessageDialog(null, "No Selection");
            }
 }
    private void tblstocksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstocksMouseClicked
         if (evt.getClickCount()==2) {
             //JOptionPane.showMessageDialog(null, tblPO.getValueAt(tblPO.getSelectedRow(), 6));
             if (btnprint.isEnabled()) {
                 JOptionPane.showMessageDialog(null, "This invoice is Closed!");
             }
             else{
                 


                        if (!tblstocks.getValueAt(tblstocks.getSelectedRow(), 5).toString().equals("0")) {

                            getlotnobytable();
           //                 txtlotno.setText(tblstocks.getValueAt(tblstocks.getSelectedRow(), 5).toString());
           //                 get_lotno();
                            stocksqty=Integer.parseInt(tblstocks.getValueAt(tblstocks.getSelectedRow(), 5).toString());
                            txtqtyActionPerformed(null);
                        }
                       else{
                        JOptionPane.showMessageDialog(null, "Out of Stock!");
                       }
                   }
             }
    }//GEN-LAST:event_tblstocksMouseClicked

    private void txtsellingpriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsellingpriceKeyReleased
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
    }//GEN-LAST:event_txtsellingpriceKeyReleased
public void add(){
        try {
                   
              String sqlcode2="select * from tblstocks where fld_lot_no='"+ txtlotno.getText().trim() +"'";
               //String sqlcode3="Update tblpo set fld_item_status=1 where fld_po_id="+refid+"";
              //JOptionPane.showMessageDialog(null, sqlcode3);
               int addqty;
                addqty = cartqty;
               
               if (BotiqueSystem.addqty(lotno, addqty,"Addition", conn))
                        {
                  
                    //JOptionPane.showMessageDialog(null, "Added!");
                    loadstocks();
                    cleartxt();
                 
               }
        } catch (NumberFormatException | HeadlessException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
public void del(){
        try {
                   
              String sqlcode2="select * from tblstocks where fld_lot_no='"+ txtlotno.getText().trim() +"'";
               //String sqlcode3="Update tblpo set fld_item_status=1 where fld_po_id="+refid+"";
              //JOptionPane.showMessageDialog(null, sqlcode3);
               int addqty;
                addqty = Integer.parseInt(txtqty.getText().trim());
               
               if (BotiqueSystem.addqty(lotno, addqty,"Subtraction", conn))
                        {
                  
                    //JOptionPane.showMessageDialog(null, "Added!");
                    loadstocks();
                    cleartxt();
                 
               }
        } catch (NumberFormatException | HeadlessException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    int cartqty=0;
    String lotno="";
    private void tblcartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcartMousePressed
        if (tblcart.getSelectedRowCount()>0) {
            refid=Integer.parseInt(tblcart.getValueAt(tblcart.getSelectedRow(),0).toString());
            cartqty=Integer.parseInt(tblcart.getValueAt(tblcart.getSelectedRow(),6).toString());
            lotno=tblcart.getValueAt(tblcart.getSelectedRow(),0).toString();
        }
        else{
            refid=0;
        }
    }//GEN-LAST:event_tblcartMousePressed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        if ((tblcart.getSelectedRowCount()>0) ){
                
                if (JOptionPane.showConfirmDialog(null, "Remove item from cart?", "Deleting...", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
        
                sqlcode="delete from tblcart where fld_lot_no='"+ refid +"'";
                if (BotiqueSystem.addupdatedel(sqlcode, conn)) {
                    JOptionPane.showMessageDialog(null, "Deleted");
                    add();
                    loadcart();

                    btncancelActionPerformed(evt);
                }
    
                }
        }
        
        else{
            JOptionPane.showMessageDialog(null, "No cart item selected!");
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       
    }//GEN-LAST:event_formWindowActivated

    private void txtsellingpriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsellingpriceKeyPressed
        
    }//GEN-LAST:event_txtsellingpriceKeyPressed

    private void txtinvoicenoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinvoicenoKeyReleased
//        if (BotiqueSystem.isNumber(evt)) {
//            
//            txtinvoiceno.requestFocus();
//        }
    }//GEN-LAST:event_txtinvoicenoKeyReleased
double subtotal=0;
    private void txtqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyReleased
      
        DecimalFormat df = new DecimalFormat("0,000.00");
        if (BotiqueSystem.isNumber(evt)) {
            txtqty.setText("");
            txtexpiry.setText("");
            txtsubtotal.setText("");
            txtqty.requestFocus();
           
        }
        else{
            try {
                 int qty;
                if (txtqty.getText().equals("")) {
                  qty=0;  
                }
                else{
                    qty=Integer.parseInt(txtqty.getText());
                }
                 double price;
                if (txtsellingprice.getText().equals("")) {
                    price=0;
                }
                else{
                    Number thisnum = df.parse(txtsellingprice.getText());
                    price=thisnum.doubleValue();
                }
                
               
                
                subtotal= qty*price;
                
                if (subtotal >=1000) {
                   txtsubtotal.setText(df.format(subtotal)); 
                }
                else{
                    txtsubtotal.setText(String.valueOf(subtotal));
                }
                
                
                
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtqtyKeyReleased

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        if (!btnadd.isEnabled()) {
            add();
        }
        cleartxt();
//        btnedit.setText("Edit Cart Item");
//        btnedit.setEnabled(true);
        btnadd.setEnabled(true);
        btndelete.setEnabled(true);
        loadcart();
        loadstocks();
        
    }//GEN-LAST:event_btncancelActionPerformed

    private void txtqtyInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtqtyInputMethodTextChanged
    
        
           
        
    }//GEN-LAST:event_txtqtyInputMethodTextChanged

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqtyActionPerformed
     
            try {
                int qty=Integer.parseInt(txtqty.getText());
                double price=Double.parseDouble(txtsellingprice.getText());
                double result= qty*price;
                txtsubtotal.setText(String.valueOf(result));
                
            } catch (Exception e) {
            }
        
    }//GEN-LAST:event_txtqtyActionPerformed

    private void btnvoidinvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvoidinvoiceActionPerformed
        double total = Double.parseDouble(lblgtotal.getText());
        if (total <0) {
            JOptionPane.showMessageDialog(null, "Already Void!");
        }
        else{
            
           if (BotiqueSystem.user_privilege.equals("Admin")) {
                 frmVoidInvoice invoice = new frmVoidInvoice();
            invoice.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Please login as Admin to void this Invoice.");
            }
        }
    }//GEN-LAST:event_btnvoidinvoiceActionPerformed

    private void txtsearchcartKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchcartKeyReleased
        String cb= cbsearchstocks1.getSelectedItem().toString();

        if (cb.equals("DESCRIPTION")) {
            fieldVar="fld_item_desc";

        }
        else if(cb.equals("MANUFACTURER")){
            fieldVar="fld_manu_name";
        }
        else if(cb.equals("CATEGORY")){

            fieldVar="fld_catname";
        }

        if (txtsearchcart.getText().equals("")) {
            loadcart();
        }
        else{

        
        DefaultTableModel dtm = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int colIndex){return false;};
        };
        
         
        //
        dtm.addColumn("Lot no");
        dtm.addColumn("Description");
        dtm.addColumn("Category");
        dtm.addColumn("Manufacturer");
        dtm.addColumn("Price");
        dtm.addColumn("Expiry");
        dtm.addColumn("Qty");
        dtm.addColumn("Total");
        

        String sqlcode1=" select tblitemdetails.fld_lot_no, "
        + "tblitemdetails.fld_manu_name, "
        + "tblitemdetails.fld_catname, "
        + "tblitemdetails.fld_item_desc, "
        + "tblitemdetails.fld_item_price, "
        + "tblcart.fld_qty, "
        + "tblcart.fld_subtotal, "
        + "DATE_FORMAT(tblcart.fld_expiry,'%m-%d-%Y'),"
        + "tblcart.fld_price "
        + "from tblitemdetails"
        + " inner join "
        + "tblcart "
        + "on "
        + "tblitemdetails.fld_lot_no"
        + "="
        + "tblcart.fld_lot_no "
        + "where"
        + " tblcart.fld_invoice_no='"+ invoiceno +"' and "
        + "  "+ fieldVar +" like '%"+ txtsearchcart.getText() +"%' "
        + "order by tblitemdetails.fld_item_desc asc";;


        try {
            rs=BotiqueSystem.selectquery(sqlcode1, conn);
            while(rs.next()){
                double a = Double.parseDouble(rs.getString("tblcart.fld_subtotal"));
                //                   double b= Integer.parseInt(rs.getString("fld_po_qty"));
                //                    double c= a*b;

                dtm.addRow(new Object[]{
                    
                    rs.getString("tblitemdetails.fld_lot_no"),
                    rs.getString("tblitemdetails.fld_item_desc"),
                    rs.getString("tblitemdetails.fld_catname"),
                    rs.getString("tblitemdetails.fld_manu_name"),

                    rs.getString("tblcart.fld_price"),
                     rs.getString("DATE_FORMAT(tblcart.fld_expiry,'%m-%d-%Y')"),
                    rs.getString("tblcart.fld_qty"),
                    rs.getString("tblcart.fld_subtotal")
                   
                });
            }

            //
            tblcart.setModel(dtm);
            rs.close();
            TableColumn tc= tblcart.getColumnModel().getColumn(1);
            tc.setPreferredWidth(300);
            tc.setMaxWidth(500);
            tc= tblcart.getColumnModel().getColumn(2);
            tc.setPreferredWidth(100);
            tc.setMaxWidth(500);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
    }//GEN-LAST:event_txtsearchcartKeyReleased

    private void btnprintstocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintstocksActionPerformed
        BotiqueSystem.openreport(BotiqueSystem.rootdir+"rptPrintStocks.jasper", null,conn);
       
    }//GEN-LAST:event_btnprintstocksActionPerformed

    private void txtsubtotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsubtotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalKeyPressed

    private void txtsubtotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsubtotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalKeyReleased

    private void txtqtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtyKeyPressed
     
    }//GEN-LAST:event_txtqtyKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       BotiqueSystem.frmsellstockopen=true;
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      BotiqueSystem.frmsellstockopen=false;
    }//GEN-LAST:event_formWindowClosing

    private void txtqtyCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtqtyCaretPositionChanged
       
    }//GEN-LAST:event_txtqtyCaretPositionChanged

    private void txtqtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtqtyFocusGained
        txtqtyActionPerformed(null);
    }//GEN-LAST:event_txtqtyFocusGained
    //stocking   
    public static void loadstocks(){
        DefaultTableModel dtm = new DefaultTableModel(){
          public boolean isCellEditable(int rowIndex, int colIndex){return false;};  
        };
        
        dtm.addColumn("Lot no");
        dtm.addColumn("Desc");
        dtm.addColumn("Category");
        dtm.addColumn("Manufacturer");
        dtm.addColumn("Price");
        dtm.addColumn("Stocks");
         dtm.addColumn("Expiry");
    Connection conn= BotiqueSystem.conn_db();
        ResultSet rs=null;
       String sqlcode="Select tblitemdetails.fld_lot_no, "
                + "tblitemdetails.fld_item_desc, "
                + "tblitemdetails.fld_catname, "
                + "tblitemdetails.fld_manu_name, "
                + "tblitemdetails.fld_item_price,"
                + "DATE_FORMAT(tblstocks.fld_batch2,'%m-%d-%Y'),"
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
                     rs.getString("DATE_FORMAT(tblstocks.fld_batch2,'%m-%d-%Y')")
                 });

                                }
             tblstocks.setModel(dtm);
             rs.close();
             columnsize();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
  
    public static void columnsize(){
        TableColumn TC = tblstocks.getColumnModel().getColumn(1);
        TC.setPreferredWidth(320);
        TC.setMaxWidth(500);
        TC.setMinWidth(200);
        TC=tblstocks.getColumnModel().getColumn(2);
        TC.setPreferredWidth(100);
        TC.setMaxWidth(320);
        TC.setMinWidth(100);
         TC=tblstocks.getColumnModel().getColumn(6);
        TC.setPreferredWidth(100);
        TC.setMaxWidth(320);
        TC.setMinWidth(100);
    }
    
    
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
            java.util.logging.Logger.getLogger(frmStockOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmStockOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmStockOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmStockOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmStockOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnprint;
    private javax.swing.JButton btnprintstocks;
    private javax.swing.JButton btnselectinvoice;
    private javax.swing.JButton btnsubmit;
    private javax.swing.JButton btnvoidinvoice;
    private javax.swing.JComboBox cbsearchstocks;
    private javax.swing.JComboBox cbsearchstocks1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbldate;
    public static javax.swing.JLabel lblgtotal;
    private javax.swing.JLabel lblgtotal1;
    public static javax.swing.JLabel lblgtotal2;
    private javax.swing.JLabel lbltime;
    private javax.swing.JLabel lbluser;
    public static javax.swing.JTable tblcart;
    public static javax.swing.JTable tblstocks;
    public static javax.swing.JTextArea txtcustomerdetails;
    public static javax.swing.JTextField txtcustomerid;
    private javax.swing.JFormattedTextField txtexpiry;
    public static javax.swing.JFormattedTextField txtinvoicedate;
    public static javax.swing.JTextField txtinvoiceno;
    public static javax.swing.JTextArea txtlotdetails;
    public static javax.swing.JTextField txtlotno;
    private javax.swing.JFormattedTextField txtqty;
    private javax.swing.JTextField txtsearchcart;
    private javax.swing.JTextField txtsearchstocks;
    private javax.swing.JFormattedTextField txtsellingprice;
    private javax.swing.JFormattedTextField txtsubtotal;
    // End of variables declaration//GEN-END:variables
}
