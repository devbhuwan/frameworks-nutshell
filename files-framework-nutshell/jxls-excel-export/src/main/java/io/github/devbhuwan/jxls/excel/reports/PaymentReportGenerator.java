package io.github.devbhuwan.jxls.excel.reports;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public class PaymentReportGenerator extends ReportGenerator {

    public PaymentReportGenerator(String xlsxOutPath, String pdfOutPath) {
        super(xlsxOutPath, pdfOutPath);
    }

    @Override
    public InputStream getXLSTemplate() throws FileNotFoundException {
        return new FileInputStream("./src/test/resources/template.xlsx");
    }

}
