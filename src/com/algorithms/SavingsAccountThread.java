package com.algorithms;

import com.gui.actions.ButtonActions;
import com.observers.Observer;
import com.observers.Subject;

import java.io.IOException;
import java.util.ArrayList;

public class SavingsAccountThread implements Runnable, Subject {
    String name;
    ArrayList<Observer> observers = new ArrayList<>();

    public SavingsAccountThread(String name) {
        this.name = name;
    }

    private void balanceAfterTime()
    {
        double bal = FileAlgorithms.readBalance("accounts/"+ButtonActions.getPESEL()+"/savacc.txt");
        double amount = Math.round(bal * 0.5)/100.0;
        if(amount == 0 && bal > 0) amount = 0.01;
        try {
            FileAlgorithms.addBalance("accounts/"+ButtonActions.getPESEL()+"/savacc.txt", amount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            registerObserver(ButtonActions.getsAccB());
            while(true) {
                balanceAfterTime();
                notifyObservers();
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
            o.update(ButtonActions.getPESEL());
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
}
