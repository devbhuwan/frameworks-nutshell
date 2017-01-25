package io.github.devbhuwan.apachepoi.itextpdf.exceltopdf;

import com.smartxls.WorkBook;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public class ExcelToPdfSmartXlsTest {

    @Test
    public void exportToPdfFromExcel() throws Exception {
        WorkBook workBook = new WorkBook();
        workBook.readXLSX("./src/test/resources/exceltemplate1.xlsx");
        workBook.setPrintHeader("My header");
        workBook.setPrintHeader("");
        workBook.setPrintFooter("Page &P");
        workBook.setPrintPaperSize(11906, 16838);
        workBook.setPrintTopMargin(1.01); //1.05
        workBook.setPrintBottomMargin(0.99); //1.03
        workBook.setPrintHeaderMargin(0);
        workBook.setPrintLeftMargin(0);
        workBook.setPrintRightMargin(0);
        workBook.setPrintHCenter(true);
        String outLoc = "./target/smartxls_out.pdf";
        workBook.exportPDF(outLoc, null, false);
        assertTrue(Files.exists(Paths.get(outLoc)));
    }

}
