package FinalProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Labour {
     String typeOfProduct,lDate,wName;
    public Connection conn;
    public ResultSet rs;
    public PreparedStatement insert ,view;
    
    public void insertData(String tOp,String lDate,String wName){
        conn = ConnectDB.connect();
        try {
            insert = conn.prepareStatement("insert into labour values (?,?,?)");
            insert.setString(1, tOp);
            insert.setString(3, lDate);
            insert.setString(2, wName);
            boolean in = insert.execute();
            if(!in){
                JOptionPane.showMessageDialog(null, "Data inserted");
            }
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
    }
    
     public void ViewData(){
      conn = ConnectDB.connect();
        try {
            view = conn.prepareStatement("select * from labour");
            rs = view.executeQuery();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
     
   
}
