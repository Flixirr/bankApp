package com.userfiles;

import com.algorithms.FileAlgorithms;
import com.algorithms.NumGenerator;
import com.gui.actions.ButtonActions;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Transaction implements Serializable {
    static final long serialVersionUID = 10L;
    private double amount;
    private JLabel transactionLabel = new JLabel();

    public Transaction(Account origin, Account target, double amount, String title, String desc)
    {
        this.amount = amount;
        User tUser = FileAlgorithms.getNumUserPair().get(target.getNumber());
        target.addToBalance(this);
        transactionLabel.setFont(new Font("Arial", Font.PLAIN, 9));
        /*
        easy fix to create this for multiple transactions at one time is to create a queue in user which
        holds transactions and realizes them with 5 s delay but it is not necessary since it is only for one device

        check which number is chosen and set account to have greater balance (if it only adds then it's not working)
        */

        if(target.getNumber().charAt(0) == '2') {
            tUser.setsAcc(target);
        } else {
            tUser.setbAcc(target);
        }

        if(origin.getNumber().equals("DEPO")) {
            String id = NumGenerator.generateTransactionNum(target);
            transactionLabel.setText("DEPOID " + id + " ON ACCOUNT " + target.getNumber() + " FOR AMOUNT " + amount);
            FileAlgorithms.saveChanges(tUser, true);
        }
        else if(origin.getNumber().equals("SAVACC")) {
            tUser = FileAlgorithms.readObject(ButtonActions.getLoggedUser().getPESEL());
            assert tUser != null;
            tUser.setsAcc(target);
            transactionLabel.setText("SAVINGS FOR " + amount);
            FileAlgorithms.saveChanges(tUser, true);
        }
        else {
            FileAlgorithms.saveChanges(tUser, true);
            String id = NumGenerator.generateTransactionNum(origin);

            User oUser = FileAlgorithms.readObject(ButtonActions.getLoggedUser().getPESEL());
            origin.subFromBalance(this);
            assert oUser != null;
            oUser.setbAcc(origin);

            transactionLabel.setText("ID " + id + " AMOUNT " + amount + " TITLE " + title + " DESC " + desc + " PAYERS DATA " +
                    origin.getNumber() + ' ' + oUser.getName() +  ' ' + oUser.getSurname() + " PAYEE'S DATA " + target.getNumber() +
                    ' ' + tUser.getName() + ' ' + tUser.getSurname());
            FileAlgorithms.saveChanges(oUser, true);
            if(!oUser.getPESEL().equals(tUser.getPESEL()))FileAlgorithms.saveChanges(tUser, true);
        }
    }

    public JLabel getTransactionLabel() {
        return transactionLabel;
    }

    double getAmount() {
        return amount;
    }
}
