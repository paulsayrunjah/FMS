package Documects;
import com.itextpdf.text.pdf.*;
public class HarvestReportPdf{
   
   HarvestFunctions hf = new HarvestFunctions();
   String title,docName,tableTitle;
   PdfPTable table;
   int getTask;
  
   public HarvestReportPdf(){
   }
   
    public HarvestReportPdf(String title,String tableTitle){
        this.title = title;
        this.tableTitle = tableTitle;
    }
    public void createPdf(int getTask,String pType,String date){
        hf.header(title);
        hf.table(tableTitle,getTask,pType,date);
    }
}
