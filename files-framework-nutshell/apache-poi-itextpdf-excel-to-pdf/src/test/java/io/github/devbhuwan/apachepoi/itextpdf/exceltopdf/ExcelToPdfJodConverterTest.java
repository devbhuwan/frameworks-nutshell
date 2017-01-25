package io.github.devbhuwan.apachepoi.itextpdf.exceltopdf;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.junit.Test;

import java.io.File;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public class ExcelToPdfJodConverterTest {

    @Test
    public void exportToPdfFromExcel() throws Exception {
        File inputFile = new File("src/test/resources/exceltemplate.xlsx");
        File outputFile = new File("target/jod_converter_output.pdf");

        // connect to an OpenOffice.org instance running on port 8100
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        connection.connect();

        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);

        // close the connection
        connection.disconnect();
    }
}
