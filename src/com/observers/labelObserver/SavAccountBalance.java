package com.observers.labelObserver;

import com.gui.actions.ButtonActions;
import com.observers.Observer;

import javax.swing.*;

public class SavAccountBalance implements Observer {

    private static JLabel txt = new JLabel();

    public SavAccountBalance()
    {
        txt.setText(ButtonActions.getLoggedUser().getsAcc().getBalance() + " PLN");
    }

    public void update() {
        txt.setText(ButtonActions.getLoggedUser().getsAcc().getBalance() + " PLN");
    }

    public static JLabel getTxt() {
        return txt;
    }
}
