package entity;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String transactionUserId;
    private Double transactionAmount;
    private String transactionType;
    private Double InitialBalance;
    private Double finalBalance;
    private String transactionPerfomedBy;

    // contructor
    public Transaction(LocalDate date, String transactionUserId, Double transactionAmount, String transactionType,
            Double InitialBalance, Double finalBalance, String transactionPerfomedBy) {
        this.date = date;
        this.transactionUserId = transactionUserId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.InitialBalance = InitialBalance;
        this.finalBalance = finalBalance;
        this.transactionPerfomedBy = transactionPerfomedBy;
    }

    // getters and setters
    public LocalDate getDate() {
        return date;
    }

    public LocalDate setDate(LocalDate date) {
        this.date = date;
        return date;
    }

    public String getTransactionUserId() {
        return transactionUserId;
    }

    public void setTransactionUserId(String transactionUserId) {
        this.transactionUserId = transactionUserId;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getInitialBalance() {
        return InitialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        InitialBalance = initialBalance;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }

    public String getTransactionPerfomedBy() {
        return transactionPerfomedBy;
    }

    public void setTransactionPerfomedBy(String transactionPerfomedBy) {
        this.transactionPerfomedBy = transactionPerfomedBy;
    }

    @Override
    public String toString() {
        return "Transactions [InitialBalance=" + InitialBalance + ", date=" + date + ", finalBalance=" + finalBalance
                + ", transactionAmount=" + transactionAmount + ", transactionPerfomedBy=" + transactionPerfomedBy
                + ", transactionType=" + transactionType + ", transactionUserId=" + transactionUserId + "]";
    }

}
