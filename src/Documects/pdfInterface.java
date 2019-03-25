package Documects;
public interface pdfInterface {
    public void start(String pdfName);
    public void header(String docName,String title);
    public void table(String title,int getTask);
    public void end();
}
