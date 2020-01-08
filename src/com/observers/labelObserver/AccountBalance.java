package com.observers.labelObserver;

import com.algorithms.FileAlgorithms;
import com.observers.Observer;

import javax.swing.*;

public class AccountBalance implements Observer {

    private static JLabel txt = new JLabel();

    public AccountBalance(String PESEL)
    {
        txt.setText(Double.toString(FileAlgorithms.readBalance("accounts/"+PESEL+"/acc.txt")));
    }

    public void update(double amount, String PESEL) {
        txt.setText(Double.toString(FileAlgorithms.readBalance("accounts/"+PESEL+"/acc.txt")));
    }

    public static JLabel getTxt() {
        return txt;
    }
}
