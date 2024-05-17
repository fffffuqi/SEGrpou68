package com.example;

public class Transaction {
    private String userId;
    private double balance;
    private double transactionAmount;

    // Constructors, getters, and setters

    public Transaction() {}

    public Transaction(String userId, double balance, double transactionAmount) {
        this.userId = userId;
        this.balance = balance;
        this.transactionAmount = transactionAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
