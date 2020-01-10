package com.algorithms;

import com.gui.actions.ButtonActions;
import com.observers.Observer;
import com.observers.Subject;

import java.util.ArrayList;

public class SavingsAccountThread implements Runnable, Subject {
    String name;
    ArrayList<Observer> observers = new ArrayList<>();
    static boolean running;

    public SavingsAccountThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        try {
            running = true;
            registerObserver(ButtonActions.getsAccB());
            while(running) {
                System.out.println("Dzialam");
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
