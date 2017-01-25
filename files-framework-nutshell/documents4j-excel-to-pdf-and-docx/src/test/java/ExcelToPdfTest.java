import com.documents4j.api.DocumentType;
import com.documents4j.job.LocalConverter;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/22/2017
 */
public class ExcelToPdfTest {

    @Test
    public void exportExcelToPdf() throws Exception {
        final String outLoc = "./target/documents4j_out.pdf";
        LocalConverter.builder()
                .build()
                .convert(ExcelToPdfTest.class.getResourceAsStream("exceltemplate.xlsx"))
                .as(DocumentType.MS_EXCEL)
                .to(new File(outLoc)).as(DocumentType.PDF)
                .execute();
        assertTrue(Files.exists(Paths.get(outLoc)));
    }

    @Test
    public void exportExcelToPdf1() throws Exception {
        final String outLoc = "./target/documents4j_out1.pdf";
        LocalConverter.builder()
                .build()
                .convert(ExcelToPdfTest.class.getResourceAsStream("exceltemplate1.xlsx"))
                .as(DocumentType.MS_EXCEL)
                .to(new File(outLoc)).as(DocumentType.PDF)
                .execute();
        assertTrue(Files.exists(Paths.get(outLoc)));
    }

}
