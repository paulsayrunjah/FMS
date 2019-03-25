
package View;

import javax.swing.SwingWorker;
import java.sql.*;
import FinalProduct.Order;
import FinalProduct.ConnectDB;

public class ComputingProgress {
    
    public Connection conn;
    public ResultSet rs;
    public Order computingSales;
    
    class Task extends SwingWorker<Void,Void>{

        @Override
        protected Void doInBackground(){
            
            try{
               Thread.sleep(1000);
              // computingSales.computeSales(cs_id, 0);
                
            }catch(InterruptedException ie){
                
            }
            
        return null;
        }
        
        public void done(){
        }
    }
    
    public ComputingProgress(){
    }
    
}
