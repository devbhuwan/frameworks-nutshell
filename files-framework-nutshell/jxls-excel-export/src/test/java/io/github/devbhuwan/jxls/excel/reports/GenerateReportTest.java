package io.github.devbhuwan.jxls.excel.reports;

import io.github.devbhuwan.jxls.excel.model.Client;
import io.github.devbhuwan.jxls.excel.model.ClientAccount;
import io.github.devbhuwan.jxls.excel.model.Payment;
import org.junit.Before;
import org.junit.Test;
import org.jxls.common.Context;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public class GenerateReportTest {

    private final Random random = new Random(10);
    String pdfOutPath = "./target/out.pdf";
    String xlsxOutPath = "./target/out.xlsx";
    private PaymentReportGenerator generator;
    private List<Client> clients;

    @Before
    public void setUp() throws Exception {
        generator = new PaymentReportGenerator(xlsxOutPath, pdfOutPath);
        clients = new ArrayList<>();
        clients.add(buildClient("P111"));
        clients.add(buildClient("Q222"));
    }

    private Client buildClient(String s) {
        Client client = new Client();
        client.setClientName("NAME-" + s);
        List<ClientAccount> clientAccounts = new ArrayList<>();
        clientAccounts.add(buildClientAccount("X" + s));
        clientAccounts.add(buildClientAccount("Y" + s));
        client.setClientAccounts(clientAccounts);
        return client;
    }

    private ClientAccount buildClientAccount(String s) {
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setIban("IBAN-" + s);
        List<Payment> payments = new ArrayList<>();
        payments.add(buildPayment("A" + s));
        payments.add(buildPayment("B" + s));
        clientAccount.setPayments(payments);
        return clientAccount;
    }

    private Payment buildPayment(String s) {
        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(random.nextInt(5000)));
        payment.setDescription("DESC-" + s);
        payment.setFromAccount("AA0023230BG34" + s);
        payment.setPaymentDate(buildRandomDate(random.nextInt(30)));
        payment.setPaymentStatus("STAT-" + s);
        payment.setPaymentType("TYPE-" + s);
        payment.setReferenceId("REF0001-" + s);
        payment.setToAccount("BB90452GDS" + s);
        return payment;
    }

    private Date buildRandomDate(int i) {
        Calendar calendar = Calendar.getInstance();
        if (i % 2 == 0)
            calendar.add(Calendar.DAY_OF_MONTH, i);
        else
            calendar.add(Calendar.DAY_OF_MONTH, -i);
        return calendar.getTime();
    }

    @Test
    public void generateReport() throws Exception {
        byte[] bytes = generator.generatePDFBytes(buildContext(new Context()));
        assertNotNull(bytes);
        assertTrue(Files.exists(Paths.get(generator.getPdfOutPath())));
        assertTrue(Files.exists(Paths.get(generator.getXlsxOutPath())));
    }

    private Context buildContext(Context context) {
        context.putVar("filterPaymentType", "ALL");
        context.putVar("filterPaymentStatus", "ALL");
        context.putVar("filterDateFrom", "12/12/2016");
        context.putVar("filterDateTo", "12/12/2016");
        context.putVar("reportDisclaimer", "I am done.");
        context.putVar("printedDateTime", new Date().toString());
        context.putVar("username", "XYZ");
        context.putVar("clients", clients);
        context.putVar("sheetNames", buildSheetNames(clients));
        return context;
    }

    private List<String> buildSheetNames(List<Client> clients) {
        if (clients == null) return Collections.emptyList();
        return clients.stream().map(client -> client.getClientName()).collect(Collectors.toList());
    }

    /*
        jx:each(items="homeDwellers" var="homeDweller" lastCell="B3")
        jx:each(items="homeDweller.chores" var="chore" lastCell="B2")

     */

}