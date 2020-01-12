package com.userfiles;

import com.algorithms.FileAlgorithms;
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

        tUser = FileAlgorithms.getNumUserPair().get(target.getNumber());
        target.addToBalance(this);

        if(target.getNumber().charAt(0) == '2') {
            tUser.setsAcc(target);
        } else {
            tUser.setbAcc(target);
        }

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
