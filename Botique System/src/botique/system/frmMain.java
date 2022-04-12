/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package botique.system;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JDesktopPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author KARLA
 */
public class frmMain extends javax.swing.JFrame {
Connection conn=null;
ResultSet rs= null;
    /**
     * Creates new form frmMain
     */
    public frmMain() {
        initComponents();
        conn=BotiqueSystem.conn_db();
        
        
        loadrop();
        loadinvoice();
        loadlackingitems();
        unsubmitterinvoice();
        loadexpiry();
        
       
//        javax.swing.ImageIcon imageIcon = new javax.swing.ImageIcon(new javax.swing.ImageIcon("D:\\project\\pos\\AUNTIE NI JUNE\\botique-java\\Botique System\\icon\\background.jpg").getImage().getScaledInstance( jLabel1.getWidth(),jLabel1.getHeight(), java.awt.Image.SCALE_SMOOTH)); // transform it back
//        jLabel1.setIcon(imageIcon);
    }
    
    public void unsubmitterinvoice(){
        
    }
    public void loadlackingitems(){
        DefaultTableModel dtm = new DefaultTableModel(){
          public boolean isCellEditable(int rowIndex, int colIndex){return false;};  
        };
        dtm.addColumn("Lot no");
        dtm.addColumn("Desc");
        dtm.addColumn("Category");
        dtm.addColumn("Manufacturer");
        dtm.addColumn("Current Stocks");
        dtm.addColumn("Items Recievable");
    
        
        sqlcode="Select tblitemdetails.fld_lot_no, "
                + "tblitemdetails.fld_item_desc, "
                + "tblitemdetails.fld_catname, "
                + "tblitemdetails.fld_manu_name, "
                //+ "tblitemdetails.fld_item_price,"
                + "tblstocks.fld_stocks_qty, "
                + "tblstocks.fld_lacking "
                + "from tblitemdetails inner join tblstocks"
                + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no "
                + "where fld_lacking > 0 "
                + "order by tblitemdetails.fld_item_desc asc";
         rs=BotiqueSystem.selectquery(sqlcode, conn);
        try {
             while (rs.next()) {
                 dtm.addRow(new Object[]{
                     rs.getString("tblitemdetails.fld_lot_no"),
                     rs.getString("tblitemdetails.fld_item_desc"),
                     rs.getString("tblitemdetails.fld_catname"),
                     rs.getString("tblitemdetails.fld_manu_name"),
                     rs.getString("tblstocks.fld_stocks_qty"),
                     rs.getString("tblstocks.fld_lacking")
                 });

                                }
             tbllacking.setModel(dtm);
             rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
  
    
public void hidecolumn() {
       
        TableColumn col = tblstocks.getColumnModel().getColumn(1);
        col.setPreferredWidth(150);
        col.setMaxWidth(150);
        col.setMinWidth(150);
         col = tblstocks.getColumnModel().getColumn(4);
        col.setPreferredWidth(200);
        col.setMaxWidth(200);
        col.setMinWidth(200);
        
        
}

 public void loadinvoice(){
            DefaultTableModel dtm = new DefaultTableModel(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false;
                }
            };
           
            dtm.addColumn("Invoice no");
            dtm.addColumn("Invoice Date");
            dtm.addColumn("Name");
            
           
            
              dtm.addColumn("Address");
               dtm.addColumn("Customer Code");
                
                
                
                sqlcode="select "
                        + " tblinvoiceno.fld_invoice_no,"
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
                        + "where tblinvoiceno.fld_invoice_no not in( select tblinvoice.fld_invoice_no from tblinvoice)"
                        ;//+ "on tblinvoiceno.fld_invoice_no=tblinvoice.fld_invoice_no) ";
                       // + " order by tblinvoiceno.fld_invoice_no desc";
            try {
                  rs=BotiqueSystem.selectquery(sqlcode, conn);
                  
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        
                        rs.getString("tblinvoiceno.fld_invoice_no"),
                        rs.getString("date_format(tblinvoiceno.fld_invoice_date, '%M-%d-%y')"),
                         
                          rs.getString("tblcustomer.fld_fname")+" "
                                    + ""+rs.getString("tblcustomer.fld_mname") +" "+
                                    rs.getString("tblcustomer.fld_lname"),
                           rs.getString("tblcustomer.fld_zone")+ " "+
                            rs.getString("tblcustomer.fld_st") +" "+
                          
                            rs.getString("tblcustomer.fld_city")+ " "+
                            rs.getString("tblcustomer.fld_province"),
                            rs.getString("tblcustomer.fld_customer_code")
                            
                            
                            
                          
                    
                    });
                                  }
                tblinvoiceno.setModel(dtm);
                hidecolumn();
                rs.close();
                
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

        jMenu6 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jdp_main = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstocks = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbllacking = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblinvoiceno = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblexpiry = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnusers = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        btnsetting = new javax.swing.JButton();
        btnitems = new javax.swing.JButton();
        btncancelinvoice = new javax.swing.JButton();
        btnbackup = new javax.swing.JButton();
        btnreceivepo = new javax.swing.JButton();
        btnpo = new javax.swing.JButton();
        btnsellstock = new javax.swing.JButton();
        btnsalesreport = new javax.swing.JButton();
        lblbg = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_trans = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        menu_reg = new javax.swing.JMenu();
        submenu_item = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        submenu_account = new javax.swing.JMenuItem();
        submenu_account1 = new javax.swing.JMenuItem();
        submenu_account2 = new javax.swing.JMenuItem();
        menuview = new javax.swing.JMenu();
        submenu_report = new javax.swing.JMenuItem();
        submenu_backup = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        menusetting = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        menupo = new javax.swing.JMenu();
        submenu_recieve = new javax.swing.JMenuItem();
        submenu_createpo = new javax.swing.JMenuItem();
        menuaccount = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenu6.setText("jMenu6");

        jMenuItem15.setText("jMenuItem15");

        jMenu11.setText("jMenu11");

        jMenu12.setText("jMenu12");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JB PHARMA AND TRADE CENTER");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jdp_main.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Reorder Point Items", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tblstocks.setOpaque(false);
        jScrollPane1.setViewportView(tblstocks);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 470, 100));

        jdp_main.add(jPanel3);
        jPanel3.setBounds(370, 30, 510, 150);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Items Recievable", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbllacking.setModel(new javax.swing.table.DefaultTableModel(
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
        tbllacking.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(tbllacking);

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 470, 90));

        jdp_main.add(jPanel5);
        jPanel5.setBounds(370, 330, 510, 150);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Invoice not submitted", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblinvoiceno.setModel(new javax.swing.table.DefaultTableModel(
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
        tblinvoiceno.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(tblinvoiceno);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 490, 90));

        jdp_main.add(jPanel6);
        jPanel6.setBounds(370, 180, 510, 150);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Expiry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblexpiry.setModel(new javax.swing.table.DefaultTableModel(
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
        tblexpiry.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane4.setViewportView(tblexpiry);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 470, 90));

        jdp_main.add(jPanel7);
        jPanel7.setBounds(370, 480, 510, 140);

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Quick Access", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnusers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnusers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/User-48.png"))); // NOI18N
        btnusers.setText("Users");
        btnusers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnusers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnusers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnusers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnusersActionPerformed(evt);
            }
        });
        jPanel2.add(btnusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 140, 90));

        btnexit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/Exit Sign-48.png"))); // NOI18N
        btnexit.setText("Log Out");
        btnexit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnexit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnexit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });
        jPanel2.add(btnexit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 140, 90));

        btnsetting.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/Client Company-50.png"))); // NOI18N
        btnsetting.setText("Customer");
        btnsetting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnsetting.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsetting.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnsetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsettingActionPerformed(evt);
            }
        });
        jPanel2.add(btnsetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 140, 90));

        btnitems.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnitems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/Product-48.png"))); // NOI18N
        btnitems.setText("Items");
        btnitems.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnitems.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnitems.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnitems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnitemsActionPerformed(evt);
            }
        });
        jPanel2.add(btnitems, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 140, 90));

        btncancelinvoice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncancelinvoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/Cancel File-48.png"))); // NOI18N
        btncancelinvoice.setText("Cancel Invoice");
        btncancelinvoice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btncancelinvoice.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btncancelinvoice.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btncancelinvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelinvoiceActionPerformed(evt);
            }
        });
        jPanel2.add(btncancelinvoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 140, 90));

        btnbackup.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/Data Backup-48.png"))); // NOI18N
        btnbackup.setText("Data Backup");
        btnbackup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnbackup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnbackup.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(btnbackup, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 140, 90));

        btnreceivepo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnreceivepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/StockPlus.png"))); // NOI18N
        btnreceivepo.setText("Recieve P.O.");
        btnreceivepo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnreceivepo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnreceivepo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnreceivepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreceivepoActionPerformed(evt);
            }
        });
        jPanel2.add(btnreceivepo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 140, 90));

        btnpo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnpo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/Purchase Order-48.png"))); // NOI18N
        btnpo.setText("Create P.O.");
        btnpo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnpo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnpo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpoActionPerformed(evt);
            }
        });
        jPanel2.add(btnpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 140, 90));

        btnsellstock.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsellstock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/Sell Stock-48.png"))); // NOI18N
        btnsellstock.setText("Sell Stock");
        btnsellstock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnsellstock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsellstock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnsellstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsellstockActionPerformed(evt);
            }
        });
        jPanel2.add(btnsellstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 90));

        btnsalesreport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnsalesreport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/Sales Performance-48.png"))); // NOI18N
        btnsalesreport.setText("Sales Report");
        btnsalesreport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnsalesreport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsalesreport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnsalesreport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalesreportActionPerformed(evt);
            }
        });
        jPanel2.add(btnsalesreport, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 140, 90));

        jdp_main.add(jPanel2);
        jPanel2.setBounds(20, 30, 330, 530);

        lblbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botique/system/wallpaper.jpg"))); // NOI18N
        jdp_main.add(lblbg);
        lblbg.setBounds(0, 0, 1350, 740);

        getContentPane().add(jdp_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 720));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        menu_trans.setText("Transaction");

        jMenuItem2.setText("Sell Stock");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_trans.add(jMenuItem2);

        jMenuItem8.setText("Exit");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menu_trans.add(jMenuItem8);

        jMenuBar1.add(menu_trans);

        menu_reg.setText("Register");

        submenu_item.setText("Item");

        jMenuItem11.setText("Mensuration");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        submenu_item.add(jMenuItem11);

        jMenuItem10.setText("Category");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        submenu_item.add(jMenuItem10);

        jMenuItem22.setText("Manufacturer");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        submenu_item.add(jMenuItem22);

        jMenuItem23.setText("Supplier");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        submenu_item.add(jMenuItem23);

        jMenuItem12.setText("Details");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        submenu_item.add(jMenuItem12);

        menu_reg.add(submenu_item);

        submenu_account.setText("Account");
        submenu_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenu_accountActionPerformed(evt);
            }
        });
        menu_reg.add(submenu_account);

        submenu_account1.setText("Customer");
        submenu_account1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenu_account1ActionPerformed(evt);
            }
        });
        menu_reg.add(submenu_account1);

        submenu_account2.setText("Supplier");
        submenu_account2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenu_account2ActionPerformed(evt);
            }
        });
        menu_reg.add(submenu_account2);

        jMenuBar1.add(menu_reg);

        menuview.setText("View");

        submenu_report.setText("Sales Report");
        submenu_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenu_reportActionPerformed(evt);
            }
        });
        menuview.add(submenu_report);

        jMenuBar1.add(menuview);

        submenu_backup.setText("Maintenance");

        jMenu8.setText("Back-up");

        jMenuItem9.setText("Database");
        jMenu8.add(jMenuItem9);

        submenu_backup.add(jMenu8);

        jMenuBar1.add(submenu_backup);

        menusetting.setText("Setting");

        jMenu5.setText("Database");
        menusetting.add(jMenu5);

        jMenuItem7.setText("Report");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menusetting.add(jMenuItem7);

        jMenuBar1.add(menusetting);

        menupo.setText("P.O.");

        submenu_recieve.setText("Recieve");
        submenu_recieve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenu_recieveActionPerformed(evt);
            }
        });
        menupo.add(submenu_recieve);

        submenu_createpo.setText("Create");
        submenu_createpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenu_createpoActionPerformed(evt);
            }
        });
        menupo.add(submenu_createpo);

        jMenuBar1.add(menupo);

        menuaccount.setText("Account");
        menuaccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuaccountActionPerformed(evt);
            }
        });

        jMenuItem5.setText("My profile");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuaccount.add(jMenuItem5);

        jMenuItem6.setText("Logout");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuaccount.add(jMenuItem6);

        jMenuBar1.add(menuaccount);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
frmCategory regcat=new frmCategory();
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
       
       regcat.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed
 frmItemDetails itemdetails = new frmItemDetails();
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
       
        itemdetails.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed
 frmmanufacturer manufacturer= new frmmanufacturer();
    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
     
      manufacturer.setVisible(true);
    }//GEN-LAST:event_jMenuItem22ActionPerformed
frmSupplier supplier = new frmSupplier();
    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
       
       supplier.setVisible(true);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
     
      
      sell.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void submenu_recieveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenu_recieveActionPerformed
       frmStockin2 recieve = new frmStockin2();
       recieve.setVisible(true);
    }//GEN-LAST:event_submenu_recieveActionPerformed

    private void menuaccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuaccountActionPerformed
       
    }//GEN-LAST:event_menuaccountActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
frmMyProfile profile =new frmMyProfile();
profile.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
         frmLogin login= new frmLogin();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed
  frmCreateUser createuser = new frmCreateUser();
    private void submenu_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenu_accountActionPerformed
     
       createuser.setVisible(true);
    }//GEN-LAST:event_submenu_accountActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       frmReportSetting setting = new frmReportSetting();
       setting.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void submenu_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenu_reportActionPerformed
        frmReports sales  = new frmReports();
        sales.setVisible(true);
    }//GEN-LAST:event_submenu_reportActionPerformed

    private void submenu_createpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenu_createpoActionPerformed
        frmPO po = new frmPO();
        po.setVisible(true);
        
    }//GEN-LAST:event_submenu_createpoActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
            this.dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed
 frmStockOut sell = new frmStockOut();

    private void btnsellstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsellstockActionPerformed
     
               btncancelinvoiceActionPerformed(evt);

        
        
    }//GEN-LAST:event_btnsellstockActionPerformed
frmReports report = new frmReports();
    private void btnsalesreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalesreportActionPerformed
       
       report.setVisible(true);
    }//GEN-LAST:event_btnsalesreportActionPerformed
frmPO po = new frmPO();
    private void btnpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpoActionPerformed
        
        po.setVisible(true);
    }//GEN-LAST:event_btnpoActionPerformed
    String sqlcode=null;
    
    public void loadrop(){
    
    DefaultTableModel dtm = new DefaultTableModel(){
                public boolean isCellEditable(int rowIndex, int colIndex){return false;};
            };
            dtm.addColumn("Lot no");
            dtm.addColumn("Desc");
            dtm.addColumn("Category");
            dtm.addColumn("Manufacturer");
            //dtm.addColumn("Price");
            dtm.addColumn("Stocks");
//            if (txtsearchstocks.getText().trim().equals("")) {
//        sqlcode="Select tblitemdetails.fld_lot_no, "
//            + "tblitemdetails.fld_item_desc, "
//            + "tblitemdetails.fld_catname, "
//            + "tblitemdetails.fld_manu_name, "
//            + "tblitemdetails.fld_item_price,"
//            + "tblstocks.fld_stocks_qty "
//            + "from tblitemdetails inner join tblstocks"
//            + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no"
//                    + " where tblitemdetails.fld_reorder_point >=tblstocks.fld_stocks_qty ";
//                  
//               }
//            else{
                sqlcode="Select tblitemdetails.fld_lot_no, "
            + "tblitemdetails.fld_item_desc, "
            + "tblitemdetails.fld_catname, "
            + "tblitemdetails.fld_manu_name, "
            + "tblitemdetails.fld_item_price,"
            + "tblstocks.fld_stocks_qty "
            + "from tblitemdetails inner join tblstocks"
            + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no"
                    + " where tblitemdetails.fld_reorder_point >=tblstocks.fld_stocks_qty";
           // }
            
            rs=BotiqueSystem.selectquery(sqlcode, conn);
            try {
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        rs.getString("tblitemdetails.fld_lot_no"),
                        rs.getString("tblitemdetails.fld_item_desc"),
                        rs.getString("tblitemdetails.fld_catname"),
                        rs.getString("tblitemdetails.fld_manu_name"),
                       // rs.getString("tblitemdetails.fld_item_price"),
                        rs.getString("tblstocks.fld_stocks_qty")
                    });

                }
                tblstocks.setModel(dtm);
                hidecolumn();
                rs.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
        
     public void loadexpiry(){
    
         try {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
             
         } catch (Exception e) {
         }
         
    DefaultTableModel dtm = new DefaultTableModel(){
                public boolean isCellEditable(int rowIndex, int colIndex){return false;};
            };
            dtm.addColumn("Lot no");
            dtm.addColumn("Desc");
            dtm.addColumn("Category");
            dtm.addColumn("Manufacturer");
            dtm.addColumn("1rst Batch");
            dtm.addColumn("Old Stocks");
            dtm.addColumn("2nd Batch");
            dtm.addColumn("New Stocks");

          
        sqlcode="Select DATE_SUB( fld_batch1, INTERVAL 3 MONTH ) as exp,  tblitemdetails.fld_lot_no, "
            + "tblitemdetails.fld_item_desc, "
            + "tblitemdetails.fld_catname, "
            + "tblitemdetails.fld_manu_name, "
            + "tblitemdetails.fld_item_price, "
                + "tblstocks.fld_batch1, "
                + "tblstocks.fld_old_stocks, "
                + "tblstocks.fld_batch2, "
                + "tblstocks.fld_new_stocks, "
                
               
            + "tblstocks.fld_stocks_qty "
            + "from tblitemdetails inner join tblstocks"
            + " on tblitemdetails.fld_lot_no=tblstocks.fld_lot_no"
                    + " where "
                + "((now() between DATE_SUB(tblstocks.fld_batch1, INTERVAL 3 MONTH ) and tblstocks.fld_batch1) "
                + "and ((tblstocks.fld_new_stocks-tblstocks.fld_old_stocks)>0)) OR "
                
                + "((now() between DATE_SUB(tblstocks.fld_batch2, INTERVAL 3 MONTH ) and tblstocks.fld_batch2) "
                + "and (tblstocks.fld_stocks_qty>0))";
                  
               
            
            rs=BotiqueSystem.selectquery(sqlcode, conn);
            try {
                
                while (rs.next()) {
                                  
                   
                    dtm.addRow(new Object[]{
                        rs.getString("tblitemdetails.fld_lot_no"),
                        rs.getString("tblitemdetails.fld_item_desc"),
                        rs.getString("tblitemdetails.fld_catname"),
                        rs.getString("tblitemdetails.fld_manu_name"),
                        rs.getString("tblstocks.fld_batch1"),
                        rs.getString("tblstocks.fld_old_stocks"),
                        rs.getString("tblstocks.fld_batch2"),
                        rs.getString("tblstocks.fld_new_stocks")
                    });
                            }
                
                tblexpiry.setModel(dtm);
                //hidecolumn();
                rs.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
        


    private void btnreceivepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreceivepoActionPerformed
       frmStockin2 recieve = new frmStockin2();
       recieve.setVisible(true);
    }//GEN-LAST:event_btnreceivepoActionPerformed
frmCreateUser user = new frmCreateUser();
    private void btnusersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnusersActionPerformed
       
       user.setVisible(true);
    }//GEN-LAST:event_btnusersActionPerformed

    private void btnsettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsettingActionPerformed
 cd.setVisible(true);       
    }//GEN-LAST:event_btnsettingActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        jMenuItem6ActionPerformed(evt);
    }//GEN-LAST:event_btnexitActionPerformed

    private void btnitemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnitemsActionPerformed
        jMenuItem12ActionPerformed(evt);
    }//GEN-LAST:event_btnitemsActionPerformed
//frmStockOut voidthis = new frmStockOut();
    private void btncancelinvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelinvoiceActionPerformed
        sell.setVisible(true);
    }//GEN-LAST:event_btncancelinvoiceActionPerformed
 frmCustomerDetails cd = new frmCustomerDetails();
    private void submenu_account1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenu_account1ActionPerformed
            cd.setVisible(true);
       
      
    }//GEN-LAST:event_submenu_account1ActionPerformed
 public frmSupplier frmsupplier = new frmSupplier();
    private void submenu_account2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenu_account2ActionPerformed
       frmsupplier.setVisible(true);
    }//GEN-LAST:event_submenu_account2ActionPerformed

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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbackup;
    private javax.swing.JButton btncancelinvoice;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnitems;
    private javax.swing.JButton btnpo;
    private javax.swing.JButton btnreceivepo;
    private javax.swing.JButton btnsalesreport;
    private javax.swing.JButton btnsellstock;
    private javax.swing.JButton btnsetting;
    private javax.swing.JButton btnusers;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JDesktopPane jdp_main;
    private javax.swing.JLabel lblbg;
    private javax.swing.JMenu menu_reg;
    private javax.swing.JMenu menu_trans;
    private javax.swing.JMenu menuaccount;
    private javax.swing.JMenu menupo;
    private javax.swing.JMenu menusetting;
    private javax.swing.JMenu menuview;
    private javax.swing.JMenuItem submenu_account;
    private javax.swing.JMenuItem submenu_account1;
    private javax.swing.JMenuItem submenu_account2;
    private javax.swing.JMenu submenu_backup;
    private javax.swing.JMenuItem submenu_createpo;
    private javax.swing.JMenu submenu_item;
    private javax.swing.JMenuItem submenu_recieve;
    private javax.swing.JMenuItem submenu_report;
    private javax.swing.JTable tblexpiry;
    private javax.swing.JTable tblinvoiceno;
    private javax.swing.JTable tbllacking;
    private javax.swing.JTable tblstocks;
    // End of variables declaration//GEN-END:variables
}
