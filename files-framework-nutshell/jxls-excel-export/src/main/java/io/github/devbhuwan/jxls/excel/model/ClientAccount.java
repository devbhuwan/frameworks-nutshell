package io.github.devbhuwan.jxls.excel.model;

import java.util.List;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public class ClientAccount {

    private String iban;

    private List<Payment> payments;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

}
