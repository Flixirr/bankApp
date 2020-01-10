package com.observers.labelObserver;

import com.gui.actions.ButtonActions;
import com.observers.Observer;

import javax.swing.*;

public class AccountBalance implements Observer {

    private static JLabel txt = new JLabel();

    public AccountBalance()
    {
        txt.setText(ButtonActions.getLoggedUser().getbAcc().getBalance() + " PLN");
    }

    public void update() {
        txt.setText(ButtonActions.getLoggedUser().getbAcc().getBalance() + " PLN");
    }

    public static JLabel getTxt() {
        return txt;
    }
}
