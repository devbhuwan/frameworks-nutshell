package io.github.devbhuwan.jxls.excel.reports;

import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public abstract class ReportGenerator {

    private final String xlsxOutPath;
    private final String pdfOutPath;

    public ReportGenerator(String xlsxOutPath, String pdfOutPath) {
        this.xlsxOutPath = xlsxOutPath;
        this.pdfOutPath = pdfOutPath;
    }

    private void generateReport(Context context) throws Exception {
        try (InputStream is = getXLSTemplate()) {
            try (OutputStream os = new FileOutputStream(getXlsxOutPath())) {
                JxlsHelper.getInstance()
                        .setUseFastFormulaProcessor(false)
                        .processTemplate(is, os, context);
            }
        }
    }

    public byte[] generatePDFBytes(Context context) {
        try {
            this.generateReport(context);
            this.generateXLSXToPdf();
            return Files.readAllBytes(Paths.get(getPdfOutPath()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateXLSXToPdf() throws Exception {
        Workbook workbook = new Workbook(getXlsxOutPath());
        workbook.save(new FileOutputStream(getPdfOutPath()), FileFormatType.PDF);
    }

    protected abstract InputStream getXLSTemplate() throws FileNotFoundException;

    public String getXlsxOutPath() {
        return xlsxOutPath;
    }

    public String getPdfOutPath() {
        return pdfOutPath;
    }
}
