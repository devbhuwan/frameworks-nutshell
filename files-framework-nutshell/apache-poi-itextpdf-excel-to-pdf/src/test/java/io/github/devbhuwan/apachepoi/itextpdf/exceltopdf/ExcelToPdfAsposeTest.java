package io.github.devbhuwan.apachepoi.itextpdf.exceltopdf;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public class ExcelToPdfAsposeTest {

    @Test
    public void exportToPdfFromExcel() throws Exception {
        Workbook workbook = new Workbook("./src/test/resources/exceltemplate.xlsx");
        String outLoc = "./target/aspose_out.pdf";
        workbook.save(outLoc, SaveFormat.PDF);
        assertTrue(Files.exists(Paths.get(outLoc)));
    }

    @Test
    public void exportToPdfFromExcel2() throws Exception {
        Workbook workbook = new Workbook("./src/test/resources/exceltemplate1.xlsx");
        String outLoc = "./target/aspose_out1.pdf";
        workbook.save(outLoc, SaveFormat.PDF);
        assertTrue(Files.exists(Paths.get(outLoc)));
    }

}
