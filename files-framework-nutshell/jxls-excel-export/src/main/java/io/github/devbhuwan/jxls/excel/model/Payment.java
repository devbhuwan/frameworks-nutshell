package io.github.devbhuwan.jxls.excel.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 1/23/2017
 */
public class Payment {

    private final static DateFormat PAYMENT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private String referenceId;
    private String description;
    private Date paymentDate;
    private String paymentDateString;
    private String paymentType;
    private String paymentStatus;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private String amountString;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentDateString() {
        return paymentDate != null ? PAYMENT_DATE_FORMAT.format(paymentDate) : "N/A";
    }

    public String getAmountString() {
        return amount != null ? amount.setScale(3, BigDecimal.ROUND_CEILING).toString() : "0.000";
    }
}
