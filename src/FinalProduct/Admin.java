package FinalProduct;
import View.FarmGui;
import View.LoginGUi;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Admin {
    
    // initialize variables
    private String userId;
    private String password;
    public Connection conn = null;
    PreparedStatement login;
    public ResultSet rs = null;

    //LoginGUi lg = new LoginGUi();
    //method to athenticate user
    public void login(String userId, String password){
      conn = ConnectDB.connect();
        try {
            login = conn.prepareStatement("SELECT * FROM admin WHERE user_id = ? AND password = ?");
            login.setString(1, userId);
            login.setString(2, password);
          rs =  login.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
