// FactoryMethodTest.java
public class FactoryMethodTest {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        WordDocument w = (WordDocument) wordFactory.createDocument();
        w.open();
        w.save();
        w.close();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        PdfDocument p= (PdfDocument) pdfFactory.createDocument();
        p.open();
        p.save();
        p.close();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        ExcelDocument e= (ExcelDocument) excelFactory.createDocument();
        e.open();
        e.save();
        e.close();
    }
}