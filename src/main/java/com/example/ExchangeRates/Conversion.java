package com.example.ExchangeRates;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conversion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long transactionId;
    private String sourceCurrency;
    private String targetCurrency;
    private String monetaryValue;

    public Conversion(long transactionId, String sourceCurrency, String targetCurrency, String monetaryValue) {
        this.transactionId = transactionId;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.monetaryValue = monetaryValue;
    }

    public Conversion() {
        super();
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long employeeId) {
        this.transactionId = employeeId;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(String monetaryValue) {
        this.monetaryValue = monetaryValue;
    }
}