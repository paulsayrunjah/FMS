
package FinalProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Worker extends Person {
    String imageSource = "";
    double salary ;
    
      
   public  Connection conn = null;
     public ResultSet rs = null;
     public PreparedStatement view,reg,update,delete;
    public void generateReport(){
    
    }
    // Register Customer
    public void registerWorker(String fname,String lname,String contact,String residence,String image){
        conn = ConnectDB.connect();
         try {
             reg = conn.prepareStatement("INSERT INTO worker VALUES (null,?,?,?,?,?)");
             reg.setString(1, fname);
             reg.setString(2, lname);
             reg.setString(3, contact);
             reg.setString(4, residence);
             reg.setString(5, image);
            boolean in = reg.execute();
            if(!in){
              JOptionPane.showMessageDialog(null," Worker Registered");
            }else{
                JOptionPane.showMessageDialog(null, "Failed to register");
            } 
         } catch (SQLException ex) {
             Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
         }
     
     }
    // View Customer
    public void viewCustomer(){
    conn = ConnectDB.connect();
         try {
             view = conn.prepareStatement("SELECT wk_id,fname,lname,contact,residence FROM worker");
             rs = view.executeQuery();
         } catch (SQLException ex) {
             Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    //Update Customer Details
    public void updateWorker(int wk_id, String contact, String residence){
    conn = ConnectDB.connect();
    
         try {
             update = conn.prepareStatement("UPDATE worker SET contact = ?,residence = ? WHERE wk_id = ?");
             update.setString(1, contact);
             update.setString(2, residence);
             update.setInt(3, wk_id);
            boolean in = update.execute();
            if(!in){
                JOptionPane.showMessageDialog(null,"Data updated");
            }else{
                 JOptionPane.showMessageDialog(null,"update Failed");
            }
         } catch (SQLException ex) {
             Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    //Delete customer from database
    public void deleteWorker(int wk_id){
    conn = ConnectDB.connect();
         try {
             delete = conn.prepareStatement("DELETE FROM worker WHERE wk_id = ?");
             delete.setInt(1, wk_id);
             boolean in = delete.execute();
             if(!in){
                 JOptionPane.showMessageDialog(null,"Data Deleted");
             }else{
                 JOptionPane.showMessageDialog(null,"Failed to Delete");
             }
         } catch (SQLException ex) {
             Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
