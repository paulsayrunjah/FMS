
package FinalProduct;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Garden {
    public String garden,comment,condition;
    public Connection conn;
    public ResultSet rs;
    public PreparedStatement insert ,view;
    
    public void insertData(String garden,String comment,String condition){
        conn = ConnectDB.connect();
        try {
            insert = conn.prepareStatement("insert into garden values (?,?,?)");
            insert.setString(1, garden);
            insert.setString(2, comment);
            insert.setString(3, condition);
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
            view = conn.prepareStatement("select * from garden");
            rs = view.executeQuery();
            while(rs.next()){
                garden = rs.getString("garden");
                condition = rs.getString("condition");
                comment = rs.getString("comment");
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
}
