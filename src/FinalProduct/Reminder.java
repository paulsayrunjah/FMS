package FinalProduct;
import View.ReminderDialog;
import java.sql.*;
import javax.swing.JOptionPane;

public class Reminder {
    public String  date,desc;
    public String status;
      Connection conn;
      ResultSet rs;
      PreparedStatement getDate,insertReminder;
 
    
    public void createReminder(String r_desc,String r_date){
        conn = ConnectDB.connect();
        try {
            insertReminder = conn.prepareStatement("insert into reminder values(?,?)");
            insertReminder.setString(1, r_desc);
            insertReminder.setString(2, r_date);
            boolean in = insertReminder.execute();
            if(!in){
                JOptionPane.showMessageDialog(null, "Reminder Created");
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
   }
    
    public void isTime(String pcdate){
        conn = ConnectDB.connect();
        try {
            getDate = conn.prepareStatement("select * from reminder where r_time = ?");
            getDate.setString(1, pcdate);
            rs = getDate.executeQuery();
            while(rs.next()){
                date = rs.getString("r_time");
                desc = rs.getString("description");
                if(pcdate.equals(pcdate)){
               ReminderDialog myR = new ReminderDialog();
                   myR.jLabel1.setText(desc);
                   myR.setVisible(true);
                   myR.setLocationRelativeTo(null);
                   myR.setResizable(false);
                   myR.setAlwaysOnTop(true);
                   System.out.print("Time up");
                }
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
}
