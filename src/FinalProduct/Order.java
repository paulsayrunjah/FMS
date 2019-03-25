package FinalProduct;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Order {
    String productType;
    double quantity;
    double price;
    Connection conn = null;
    PreparedStatement setPrice,insertOrder, getPrice, getOrder,insertSales,viewPrices;
   public ResultSet rs = null;
    public void setPrice(String ptype, int price){
        conn = ConnectDB.connect();
        System.out.println("Called");
        try {
            setPrice = conn.prepareStatement("UPDATE product SET price = ? WHERE type = ?");
            setPrice.setInt(1, price);
            setPrice.setString(2, ptype);
            boolean in = setPrice.execute();
            if(!in){
                JOptionPane.showMessageDialog(null, "Prices Updated");
            }
            else{
                 JOptionPane.showMessageDialog(null, "Failed to Update");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public double getPrice(String type){
        conn = ConnectDB.connect();
        try {
            getPrice = conn.prepareStatement("SELECT * FROM product WHERE type = ?");
            getPrice.setString(1, type);
            rs = getPrice.executeQuery();
            
            while(rs.next()){
                if(rs.getString("type").equals(type)){
                    
                    price = rs.getDouble("price");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
         return price;
    }
    public void viewPrice (){
       conn = ConnectDB.connect();
        try {
            viewPrices = conn.prepareStatement("SELECT * FROM product");
            rs = viewPrices.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
     
    }
    
    public void makeOrder(String cs_is, double cs,double cf,double ps,double pf,String date,String status){
        conn = ConnectDB.connect();
        try {
            
            insertOrder = conn.prepareStatement("INSERT INTO myorder VALUES (?,?,?,?,?,?,?)");
            insertOrder.setString(7,cs_is);
            insertOrder.setDouble(1,cs);
            insertOrder.setDouble(2,cf);
            insertOrder.setDouble(3,ps);
            insertOrder.setDouble(4,pf);
            insertOrder.setString(5, date);
            insertOrder.setString(6, status);
           boolean in = insertOrder.execute();
           if(!(in)){
               JOptionPane.showMessageDialog(null, "ORDER INSERTED");
           }else{
               JOptionPane.showMessageDialog(null,"FAILED");
           }
  
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void computeSales(String cs_id,double paid){
      conn = ConnectDB.connect();
        try {
            getOrder = conn.prepareStatement("SELECT coffee_s,coffee_f,passion_s,passion_f,order_date FROM myorder WHERE cs_id = ?");
            getOrder.setString(1, cs_id);
            rs = getOrder.executeQuery();
            double total,balance,coffee_s = 0,coffee_f=0,passion_s=0,passion_f=0;
            String date = null;
            while(rs.next()){
                coffee_s = rs.getDouble("coffee_s");
                coffee_f =rs.getDouble("coffee_f");
                passion_s = rs.getDouble("passion_s");
                passion_f = rs.getDouble("passion_f");
                date = rs.getString("order_date");
            }
            total = (coffee_s * getPrice("coffee Seedlings"))+(coffee_f *getPrice("coffee fruits")) +(passion_s*getPrice("passion fruit Seedlings"))+(passion_f*getPrice("passion fruits"));
            balance = total - paid;
            
            insertSales = conn.prepareStatement("INSERT INTO sales VALUES (?,?,?,?,?)");
            insertSales.setString(5, cs_id);
            insertSales.setDouble(1, paid);
            insertSales.setDouble(2, balance);
            insertSales.setDouble(3, total);
            insertSales.setString(4, date);
            boolean in = insertSales.execute();
            if(!(in)){
                JOptionPane.showMessageDialog(null, "Data Inserted");
            }else{
                JOptionPane.showMessageDialog(null, "Process Failed");
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
