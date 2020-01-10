package com.userfiles;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Account implements Serializable {
    static final long serialVersionUID = 2L;

    private String number;
    private double balance;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(String number, double balance) {
        this.balance = balance;
        this.number = number;
    }

    public abstract void addToBalance(Transaction transaction);

    public abstract void subFromBalance(Transaction transaction);

    public double getBalance() {
        return balance;
    }

    public String getNumber() {
        return number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
