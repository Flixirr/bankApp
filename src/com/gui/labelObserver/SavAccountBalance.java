package com.gui.labelObserver;

import com.algorithms.FileAlgorithms;
import com.observers.Observer;

import javax.swing.*;

public class SavAccountBalance implements Observer {

    private static JLabel txt = new JLabel();

    public void update(double amount, String PESEL) {
        txt.setText(Double.toString(FileAlgorithms.readBalance("accounts/"+PESEL+"/savacc.txt")));
    }

    public static JLabel getTxt() {
        return txt;
    }
}
