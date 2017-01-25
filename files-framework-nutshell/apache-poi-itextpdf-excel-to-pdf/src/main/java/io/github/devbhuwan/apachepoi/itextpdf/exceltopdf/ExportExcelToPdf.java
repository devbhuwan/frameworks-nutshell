package io.github.devbhuwan.apachepoi.itextpdf.exceltopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/22/2017
 */
public class ExportExcelToPdf {

    public static final String EXCEL_LOC = System.getProperty("excelLoc");
    public static final String PDF_LOC = System.getProperty("pdfLoc");

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        new ExportExcelToPdf().export();
    }

    private void export() throws FileNotFoundException, DocumentException {
        FileInputStream excelFileInputStream = new FileInputStream(new File(EXCEL_LOC));
        HSSFWorkbook workbook = null;
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(PDF_LOC));
    }

}
