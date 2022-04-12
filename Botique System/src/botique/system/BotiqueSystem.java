



package botique.system;

import com.sun.javafx.scene.EnteredExitedHandler;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class BotiqueSystem {
    public static String frmopened;
    String sqlcode;
    public static String user="";
    public static  String invoicetype="Private";
     public static String user_privilege="User";
    public static String thisinvoice="";
   StringBuilder t= new StringBuilder();
  public static boolean globalbool=false;  //return to false after use
    public static boolean frmsellstockopen=false;
  public static boolean isNumber(KeyEvent evt){
        boolean result=false;
        
          if ((evt.getKeyChar()==Event.BACK_SPACE )
                || (evt.getKeyCode()==Event.DELETE )
                || (evt.getKeyCode()==KeyEvent.VK_UP)
                || (evt.getKeyCode()==KeyEvent.VK_DOWN)
                || (evt.getKeyCode()==KeyEvent.VK_LEFT)
                || (evt.getKeyCode()==KeyEvent.VK_RIGHT)
                || (evt.getKeyCode()==Event.ENTER)
                  || (evt.getKeyCode()==Event.ESCAPE)
                ) {}
        else if (!(Character.isDigit(evt.getKeyChar())) ) {
          
            JOptionPane.showMessageDialog(null, "Not a number");
            result=true;
        }
        
          return result;
    }
    public static String loginpass="";
    public static String nextInvoiceNo(){
        String nextnum="100003";
        try {
            String sqlcode="";
            
            ResultSet rs=null;
            Connection conn=conn_db();
                 sqlcode=" SELECT auto_increment FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'tblautoinvoiceno' ";
                 rs=BotiqueSystem.selectquery(sqlcode, conn);
                 if (rs.next()) {
                nextnum= String.valueOf(rs.getInt(1));
            }
                 
        } catch (SQLException  e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return  nextnum;
    }
    
    public static String nextCustomerCode(){
        String nextnum="100";
        
        try {
            String sqlcode="";
            
            ResultSet rs=null;
            Connection conn=conn_db();
                 sqlcode=" SELECT auto_increment FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'tblcustomer' ";
                 rs=BotiqueSystem.selectquery(sqlcode, conn);
                 if (rs.next()) {
                nextnum= String.valueOf(rs.getInt(1));
            }
                 
        } catch (SQLException  e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return  nextnum;
    }
    
    public static String nextSupplierCode(){
        String nextnum="100";
        try {
            String sqlcode="";
            
            ResultSet rs=null;
            Connection conn=conn_db();
                 sqlcode=" SELECT auto_increment FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'tblsupplier' ";
                 rs=BotiqueSystem.selectquery(sqlcode, conn);
                 if (rs.next()) {
                nextnum= String.valueOf(rs.getInt(1));
            }
                 
        } catch (SQLException  e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return  nextnum;
    }
    
     public static String toTitleCase(String input) {
         String result=input;
      
         
           

            StringBuilder titleCase = new StringBuilder();

            boolean nextTitleCase = true;

            for (char c : input.toCharArray()) {
                if (Character.isSpaceChar(c)) {
                    nextTitleCase = true;
                } else if (nextTitleCase) {
                    c = Character.toTitleCase(c);
                    nextTitleCase = false;
                }

                titleCase.append(c);
                result=titleCase.toString();
                }

         
       
      

    return result;
}
     
     
     
    public static boolean isTextEmpty(String textboxes[]){
        boolean result=false;
        for (String string : textboxes) {
            if (string.equals("")) {
                result=true;
                break;
            }
        }
        
        return result;
    }
    
    
    public static boolean isduplicate(String sqlcode, Connection conn){
         ResultSet rs= null;

        boolean result=false;
        try {
            rs=selectquery(sqlcode, conn);
            if (rs.next()) {
                result=true;
            }
        } catch (Exception e) {
        }
            
        return result;
    }
 
    public static String strdatenow(){
        String strdate=null;
        Date d = Calendar.getInstance().getTime();
        DateFormat df= new SimpleDateFormat("MM-dd-yyyy");
        strdate=df.format(d);
        return strdate;
    }
    
    public static boolean isLotnovalid(String lotno, Connection conn){
        ResultSet rs=null;
       String sqlcode;
       boolean result=false;
        try {
            sqlcode="select * from tblstocks where fld_lot_no='"+ lotno +"'";
            rs=BotiqueSystem.selectquery(sqlcode, conn);
            if (rs.next()) {
                result=true;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return result;
    }
    
    public static boolean isPOnovalid(long pono, Connection conn){
        ResultSet rs=null;
       String sqlcode;
       boolean result=false;
        try {
            sqlcode="select * from tblpono where fld_po_no='"+ pono +"'";
            rs=BotiqueSystem.selectquery(sqlcode, conn);
            if (rs.next()) {
                result=true;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return result;
    }
    
    
    
    public static boolean validdate(String datestring) {
       
            try {
                
                Date date=null;
               SimpleDateFormat formatter= new SimpleDateFormat("MM-dd-yyyy");
               
               date= formatter.parse(datestring);
                                return true;
            } catch (ParseException e) {
        
                return false;

            }
     
   
     

    } 
    public static Date todate(String passdate){
        Date strdate=null;
        try {
            SimpleDateFormat df= new SimpleDateFormat("mm-dd-yyyy");
            strdate=df.parse(passdate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
     return strdate;   
    }
    public static boolean addupdatedel(String sqlcode, Connection conn){
        ResultSet rs=null;
    Statement stmt=null;
    boolean success;
        try {
             stmt=conn.createStatement();
            stmt.executeUpdate(sqlcode);
            success=true;
            
        stmt.close();
        } catch (SQLException e) {
            success=false;
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        return success;
    }
    
    
    public static Connection conn_db(){
        String host="jdbc:mysql://localhost:3306/jbpharma";
        String Uname="root";
        String Upass="";
        Connection conn=null;
        
        try {
            conn=DriverManager.getConnection(host,Uname, Upass);
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
            System.exit(1);
        }
        
        return conn;
    }
    
    public static ResultSet selectquery(String sqlcode, Connection conn ){
         ResultSet rs=null;
    
    
        try {
            Statement stmt;
             stmt=conn.createStatement();
          rs= stmt.executeQuery(sqlcode);
        
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
         return rs;
    }
   public static String rootdir=System.getProperty("user.dir")+"\\Reports\\";
   //
   
   
   
    public static void openreport(String jd,HashMap param, Connection conn){
        try {
            //JasperDesign jd= JRXmlLoader.load("D:\\project\\pos\\AUNTIE NI JUNE\\botique-java\\Botique System\\src\\botique\\Reports\\rpt_printPO.jrxml");
            
            JasperReport jr = (JasperReport)JRLoader.loadObjectFromFile(jd);//JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, param,  conn);
           JasperViewer.viewReport(jp, false);
            
        } catch (JRException e) {
        }
    }
    
        public static Date strtodate(String strdate){
        Date str_to_date=null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(strdate, Locale.ENGLISH);
           
            str_to_date=df.parse(strdate);
        } catch (ParseException e) {
        }
        return str_to_date;
    }
        
         public static boolean addqty(String lotno,long thisqty,String Method, Connection conn){
        boolean result=false;
        try {
           ResultSet rs=null;
           
           
           rs=selectquery("select * from tblstocks where fld_lot_no='"+ lotno +"'", conn);
            if (rs.next()) {
               long new_qty=0;
               long oldqty=Integer.parseInt(rs.getString("fld_stocks_qty"));
               String foroldqty="update tblstocks set fld_old_stocks="+ oldqty+", fld_new_stocks="+ thisqty +" where  fld_lot_no='"+ lotno +"'";
                addupdatedel(foroldqty, conn);
                
                if (Method.equals("Addition")) {
                    new_qty=oldqty+thisqty;
                }
                else
                {
                     new_qty=oldqty-thisqty;
                }
               
                rs.close();
          
                    String sqlcode="update tblstocks set fld_stocks_qty="+ new_qty +" where fld_lot_no='"+ lotno +"'";
                     if (addupdatedel(sqlcode, conn)) {
                          result=true;
                      }
            }
           //
           
           
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return result;
       
    }
        
         
         public static void transferdate(String lotno, String newexpirydate){
         String sqlgetbatch2="select fld_batch2, fld_old_stocks from tblstocks where fld_lot_no='"+ lotno +"'";  
       
         
        ResultSet rs1=null;
        
        
        Connection conn=conn_db();
             try {
                   rs1=selectquery(sqlgetbatch2, conn);
                    if (rs1.next()) {
                        String   batchdate=rs1.getString("fld_batch2");
                        
                        String sqlupdatedate="update tblstocks set fld_batch1='"+ batchdate +"'  where fld_lot_no='"+ lotno +"'";
                        BotiqueSystem.addupdatedel(sqlupdatedate, conn);
                        
                    }
                    
                    rs1.close();
                         String sqlinsertdate="update tblstocks set fld_batch2=str_to_date('"+ newexpirydate +"', '%m-%d-%Y') where fld_lot_no='"+ lotno +"'";
                        BotiqueSystem.addupdatedel(sqlinsertdate, conn);    
                           
                            
                            
             } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
             }
      
         }
         
    public static void main(String[] args) {
        
      frmLogin login = new frmLogin();
      login.setVisible(true);
        
    }
    
    
    
}
