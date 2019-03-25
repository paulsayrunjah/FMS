package FinalProduct;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Customer extends Person {
     public Connection conn = null;
    public ResultSet rs = null;
     public PreparedStatement view,reg,update,delete;
    public void generateReport(){
    
    }
    // Register Customer
    public void registerCustomer(String cs_id,String fname,String lname,String contact,String residence){
        conn = ConnectDB.connect();
         try {
             reg = conn.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?)");
             reg.setString(1, cs_id);
             reg.setString(2, fname);
             reg.setString(3, lname);
             reg.setString(4, contact);
             reg.setString(5, residence);
             boolean in = reg.execute();
             if(!in){
                 JOptionPane.showMessageDialog(null, "Customer Registered");
             }else {
                   JOptionPane.showMessageDialog(null, "Customer Registration Failed");
             }
         
         } catch (SQLException ex) {
             Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
         }
     
     }
    // View Customer
    public void viewCustomer(){
    conn = ConnectDB.connect();
         try {
             view = conn.prepareStatement("SELECT * FROM customer");
             rs = view.executeQuery();
         } catch (SQLException ex) {
             Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    //Update Customer Details
    public void updateCustomer(String cs_id, String contact, String residence){
    conn = ConnectDB.connect();
    
         try {
             update = conn.prepareStatement("UPDATE customer SET contact = ?,residence = ? WHERE cs_id=?");
             update.setString(1, contact);
             update.setString(2, residence);
             update.setString(3, cs_id);
             boolean in = update.execute();
             if(!in){
                 JOptionPane.showMessageDialog(null, "Data Updated");
             }else{
                 JOptionPane.showMessageDialog(null, "Operation Failed");
             }
         } catch (SQLException ex) {
             Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    //Delete customer from database
    public void deleteCustomer(String cs_id){
    conn = ConnectDB.connect();
         try {
             delete = conn.prepareStatement("DELETE FROM customer WHERE cs_id = ?");
             delete.setString(1, cs_id);
             boolean in = delete.execute();
             if(!in){
                 JOptionPane.showMessageDialog(null, "Data Deleted");
             }else{
                 JOptionPane.showMessageDialog(null, "Operation Failed");
             }
         } catch (SQLException ex) {
             Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
