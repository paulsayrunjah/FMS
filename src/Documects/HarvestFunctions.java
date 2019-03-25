package Documects;
import FinalProduct.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class HarvestFunctions  {

    Document document = new Document();
    public  String pdfName,data,rowTitle;
    harvest myHarvest = new harvest();
    Date date = new Date();
    
    public void header(String title) {
        try {
             start();
            
           
            Image logo =Image.getInstance("src/images/finelogo.png");
            logo.setAlignment(Image.MIDDLE);
            document.add(logo);
            
            Paragraph p1 = new Paragraph(title,FontFactory.getFont(FontFactory.TIMES_BOLD,18,BaseColor.GREEN));
             DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String l = df.format(date);
  
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            PdfPTable text = new PdfPTable(2);
            text.setWidthPercentage(100);
           
            
           
             Paragraph p2 = new Paragraph("-----------"+l+"----------------",FontFactory.getFont(FontFactory.TIMES_BOLD,14,BaseColor.BLUE));
              p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);
            
            Paragraph p3 = new Paragraph("                                                          ");
              p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);
        } catch (DocumentException ex) {
          //JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HarvestFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void table(String title,int getTask,String pType,String date) {
        try {
        PdfPTable myTable = new PdfPTable(3);
        PdfPCell cell = new PdfPCell(new Paragraph(title));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        myTable.addCell(cell);
        
        PdfPCell cell1 = new PdfPCell(new Paragraph("Type of Product",FontFactory.getFont(FontFactory.TIMES,12,BaseColor.WHITE)));
         cell1.setBackgroundColor(BaseColor.GREEN);
        myTable.addCell(cell1);
        PdfPCell cell3 = new PdfPCell(new Paragraph("Quantity",FontFactory.getFont(FontFactory.TIMES,12,BaseColor.WHITE)));
         cell3.setBackgroundColor(BaseColor.GREEN);
        myTable.addCell(cell3);
        PdfPCell cell4 = new PdfPCell(new Paragraph("Date",FontFactory.getFont(FontFactory.TIMES,12,BaseColor.WHITE)));
         cell4.setBackgroundColor(BaseColor.GREEN);
        myTable.addCell(cell4);
      
          
            try {
                
            switch (getTask) {
                case 0:
                    myHarvest.view();
                    while(myHarvest.rs.next()){
                        myTable.addCell(myHarvest.rs.getString(1));
                        myTable.addCell(myHarvest.rs.getString(3));
                        myTable.addCell(myHarvest.rs.getString(4));
                    }   break;
                case 1:
                    myHarvest.viewHarvest(pType);
                    while(myHarvest.rs.next()){
                        myTable.addCell(myHarvest.rs.getString(1));
                        myTable.addCell(myHarvest.rs.getString(2));
                        myTable.addCell(myHarvest.rs.getString(3));
                    }
                    break;
                case 2:
                    myHarvest.viewHByDate(pType, date);
                    while(myHarvest.rs.next()){
                        myTable.addCell(myHarvest.rs.getString(1));
                        myTable.addCell(myHarvest.rs.getString(2));
                        myTable.addCell(myHarvest.rs.getString(3));
                    }
                    break;
                
                case 3:
                    myHarvest.viewAllDate(date);
                      while(myHarvest.rs.next()){
                        myTable.addCell(myHarvest.rs.getString(1));
                        myTable.addCell(myHarvest.rs.getString(2));
                        myTable.addCell(myHarvest.rs.getString(3));
                    }
                    break;
            }
               
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
   
            document.add(myTable);
        } catch (DocumentException ex) {
            //JOptionPane.showMessageDialog(null, ex);
        }
        
        end();
    }

    public void end() {
        document.close();
    }

    public void start() {
         try {
           JFileChooser jc = new JFileChooser();
           int userSelection = jc.showSaveDialog(null);
          pdfName = jc.getSelectedFile().getPath();
           
           if(userSelection == JFileChooser.APPROVE_OPTION){
                PdfWriter.getInstance(document, new FileOutputStream(pdfName+".pdf"));
               open();
           }else{
           JOptionPane.showMessageDialog(null, "Process Cancelled");
           }
             
           
        } catch (DocumentException | FileNotFoundException ex) {
            open();
            //JOptionPane.showMessageDialog(null, "Closed");
        } 
    }
    public void open(){
         document.open();
    }
   
}
