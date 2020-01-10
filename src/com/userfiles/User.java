package com.userfiles;

import com.algorithms.NumGenerator;

import java.io.Serializable;

public class User implements Serializable {
    static final long serialVersionUID = 1L;

    private String name, surname, PESEL, password;
    private Account bAcc, sAcc;

    public User(String name, String surname, String PESEL, String password) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.password = password;
        bAcc = new BaseAccount(NumGenerator.generateAccNum(), 0);
        sAcc = new SavingsAccount(NumGenerator.generateSAccNum(), 0);
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public Account getbAcc() {
        return bAcc;
    }

    public Account getsAcc() {
        return sAcc;
    }
}
