package FinalProduct;
import java.sql.*;
public class Report {
  public Connection conn;
  public ResultSet rs;
  public PreparedStatement view;
  Customer c = new Customer();
  
  
 public void productsQueries(String cs_id,int task){
     conn = ConnectDB.connect();
      switch (task) {
          case 0:
              try {
                  view = conn.prepareStatement("select coffee_s,coffee_f,passion_s,passion_f,order_date from myorder where cs_id = ?");
                  view.setString(1, cs_id);
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
          case 1:
              try {
                  view = conn.prepareStatement("select coffee_f,order_date from myorder where cs_id = ? and coffee_f > 0");
                  view.setString(1, cs_id);
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
          case 2:
              try {
                  view = conn.prepareStatement("select coffee_s,order_date from myorder where cs_id = ? and coffee_s > 0");
                  view.setString(1, cs_id);
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
          case 3:
              try {
                  view = conn.prepareStatement("select passion_f,order_date from myorder where cs_id = ? and passion_f > 0");
                  view.setString(1, cs_id);
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
          case 4:
              try {
                  view = conn.prepareStatement("select passion_s,order_date from myorder where cs_id = ? and passion_s > 0");
                  view.setString(1, cs_id);
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
          default:
              break;
      }
 }
 
 public void productHarvest(String tprod){
     conn = ConnectDB.connect();
     try {
                  view = conn.prepareStatement("select * from harvest where type_of_product = ?");
                  view.setString(1, tprod);
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }
 }
  public void productOrder(int task){
       conn = ConnectDB.connect();
     switch (task) {
          case 0:
              try {
                  view = conn.prepareStatement("select coffee_f,order_date from myorder");
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
          case 1:
              try {
                  view = conn.prepareStatement("select coffee_s,order_date from myorder ");
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
          case 2:
              try {
                  view = conn.prepareStatement("select passion_f,order_date from myorder ");
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
          case 3:
              try {
                  view = conn.prepareStatement("select passion_s,order_date from myorder");
                  rs = view.executeQuery();
              } catch (SQLException ex) {
                  ex.printStackTrace();
              }    break;
       
      }
  }
  public void viewOrder(String status){
      conn = ConnectDB.connect();
      try {
          view = conn.prepareStatement("select customer.fname,customer.lname,myorder.coffee_s,myorder.coffee_f,myorder.passion_f,myorder.passion_s,myorder.order_date from myorder"
                  + " inner join customer on myorder.cs_id = customer.cs_id where status = ?");
          view.setString(1, status);
          rs = view.executeQuery();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
  }
  public void viewOrderByDate(String status,String date){
       conn = ConnectDB.connect();
      try {
          view = conn.prepareStatement("select customer.fname,customer.lname,myorder.coffee_s,myorder.coffee_f,myorder.passion_f,myorder.passion_s,myorder.order_date from myorder"
                  + " inner join customer on myorder.cs_id = customer.cs_id where status = ? and order_date = ?");
          view.setString(1, status);
          view.setString(2, date);
          rs = view.executeQuery();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
  }
}
