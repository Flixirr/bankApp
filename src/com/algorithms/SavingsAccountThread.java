package com.algorithms;

import com.gui.actions.ButtonActions;
import com.observers.Observer;
import com.observers.Subject;
import com.userfiles.BaseAccount;
import com.userfiles.Transaction;

import java.util.ArrayList;

public class SavingsAccountThread implements Runnable, Subject {
    private ArrayList<Observer> observers = new ArrayList<>();
    public static boolean lIn = false;

    @Override
    public void run() {

        try {
            registerObserver(ButtonActions.getsAccB());
            double amount;
            while (lIn) {
                amount = Math.round(ButtonActions.getLoggedUser().getsAcc().getBalance()/100);
                if(ButtonActions.getLoggedUser().getsAcc().getBalance() != 0) {
                    if (amount < 0.01) amount = 0.01;
                    new Transaction(new BaseAccount("SAVACC", amount), ButtonActions.getLoggedUser().getsAcc(), amount, "SAV",
                            "SAV");
                    notifyObservers();
                }
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            removeObserver(ButtonActions.getsAccB());
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers) {
            o.update();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
}
