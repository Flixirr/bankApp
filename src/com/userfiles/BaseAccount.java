package com.userfiles;

import java.io.Serializable;

public class BaseAccount extends Account implements Serializable {

    static final long serialVersionUID = 4L;

    public BaseAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public void addToBalance(Transaction transaction) {
        this.setBalance((this.getBalance()*100+transaction.getAmount()*100)/100);
        this.getTransactions().add(transaction);
    }

    @Override
    public void subFromBalance(Transaction transaction) {
        this.setBalance(-(this.getBalance()*100+transaction.getAmount()*100)/100);
        this.getTransactions().add(transaction);
    }
}
