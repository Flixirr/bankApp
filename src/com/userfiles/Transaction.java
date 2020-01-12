package com.userfiles;

import com.algorithms.FileAlgorithms;
import com.algorithms.NumGenerator;
import com.gui.actions.ButtonActions;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Serializable {
    static final long serialVersionUID = 10L;
    Account origin, target;
    private User tUser, oUser;
    private double amount;
    private String title, desc, id;
    Date date = new Date();
    JLabel transactionLabel = new JLabel();

    public Transaction(Account origin, Account target, double amount, String title, String desc)
    {
        this.origin = origin;
        this.target = target;
        this.amount = amount;
        this.title = title;
        this.desc = desc;
        id = NumGenerator.generateTransactionNum(origin);

        tUser = FileAlgorithms.getNumUserPair().get(target.getNumber());
        target.addToBalance(this);

        //easy fix to create this for multiple transactions at one time is to create a queue in user which
        //holds transactions and realizes them with 5 s delay

        //check which number is chosen and set account to have greater balance (if it only adds then it's not working)
        if(target.getNumber().charAt(0) == '2') {
            tUser.setsAcc(target);
        } else {
            tUser.setbAcc(target);
        }


        //overwrite user files to save balance
        FileAlgorithms.saveChanges(tUser, true);

        oUser = FileAlgorithms.readObject(ButtonActions.getLoggedUser().getPESEL());
        origin.subFromBalance(this);
        oUser.setbAcc(origin);

        FileAlgorithms.saveChanges(oUser, true);
    }

    public double getAmount() {
        return amount;
    }
}
