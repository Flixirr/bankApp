package com.userfiles;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    static final long serialVersionUID = 10L;
    Account origin, target;
    private double amount;
    private String title, desc;
    Date date = new Date();
    JLabel transactionLabel = new JLabel();

    public Transaction(Account origin, Account target, double amount, String title, String desc)
    {
        this.origin = origin;
        this.target = target;
        this.amount = amount;
        this.title = title;
        this.desc = desc;
    }

    public double getAmount() {
        return amount;
    }
}
