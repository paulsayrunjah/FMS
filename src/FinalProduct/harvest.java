package FinalProduct;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class harvest {
    
    public Connection conn;
    public ResultSet rs;
    public PreparedStatement insertHarvest,viewHarvest,viewPHarvest,viewBDate;
    
    
    public void insert(String tOProduct,String name,double quantity,String date){
        
        conn = ConnectDB.connect();
        try {
            insertHarvest = conn.prepareStatement("insert into harvest values (?,?,?,?)");
            insertHarvest.setString(1, tOProduct);
            insertHarvest.setString(2, name);
            insertHarvest.setDouble(3, quantity);
            insertHarvest.setString(4, date);
            boolean in = insertHarvest.execute();
            if(!in){
                JOptionPane.showMessageDialog(null, "Data has been inserted");
           }else{
            JOptionPane.showMessageDialog(null, "Error smthing went wrong");
        }
        } catch (SQLException ex) {
           
        }
    }
    
    public void view (){
        conn = ConnectDB.connect();
        try {
            viewHarvest = conn.prepareStatement("select * from harvest");
            rs = viewHarvest.executeQuery();
        } catch (SQLException ex) {
          
        }
    }
    public void viewHarvest(String type_of_P){
        conn = ConnectDB.connect();
        try {
            viewPHarvest = conn.prepareStatement("select type_of_product,sum(quantity),date_of_harvest from harvest where type_of_product = ? group by (date_of_harvest)");
            viewPHarvest.setString(1, type_of_P);
            rs = viewPHarvest.executeQuery();
        } catch (SQLException ex) {
           
        }
    }
    public void viewAllDate(String date){
         conn = ConnectDB.connect();
        try {
            viewHarvest = conn.prepareStatement("select * from harvest where date_of_harvest = ?");
            viewHarvest.setString(1, date);
            rs = viewHarvest.executeQuery();
        } catch (SQLException ex) {
          
        }
    }
    
    public void viewHByDate(String type_of_p,String date){
        conn = ConnectDB.connect();
        try {
            viewBDate = conn.prepareStatement("select type_of_product,sum(quantity),date_of_harvest from harvest where type_of_product = ? and date_of_harvest = ? group by (date_of_harvest)");
            viewBDate.setString(1, type_of_p);
            viewBDate.setString(2, date);
            rs =  viewBDate.executeQuery();
        } catch (SQLException ex) {
           
        }
    }
  
    
    
}
