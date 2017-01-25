package io.github.devbhuwan.apachepoi.itextpdf.exceltopdf;

//POI libraries to read Excel File

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.junit.Assert.assertTrue;

//itext libraries to write PDF file

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/22/2017
 */
public class ExcelToPdfPoiAndITextPdfTest {

    @Test
    public void exportToPdfFromExcel() throws Exception {
        String outLoc = "./target/apachepoi_itextpdf_out.pdf";
        FileInputStream inputStream = new FileInputStream(new File("./src/test/resources/exceltemplate1.xlsx"));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();

        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    System.out.print(cell.getStringCellValue() + "\t");
                    //Adequate for our requirement

                }
            }
        }

        workbook.close();
        inputStream.close();
        assertTrue(Files.exists(Paths.get(outLoc)));
    }

    /*
        Converts xls files (97-2007) to HTML file.
     */
    @Ignore
    @Test
    public void excelToHtml() throws Exception {
        Document doc = ExcelToHtmlConverter.process(new File("./src/test/resources/exceltemplate1.xlsx"));
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File("./target/out.html"));

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "no");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
    }
}
