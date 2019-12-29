package com.gui.styles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelComponents {

    private static JButton bLogin = new JButton("Login");
    private static JButton bOpenAcc = new JButton("Open an account");
    private static JButton login = new JButton("Login");
    private static JButton submit = new JButton("Submit");
    private static JButton transaction = new JButton("Make transaction");
    private static JButton history = new JButton("Transaction history");
    private static JButton deposit = new JButton("Deposit funds");


    private static JButton cashLoan = new JButton("Cash loan");
    private static JButton account = new JButton("Account");
    private static JButton savAccount = new JButton("Savings account");

    static JPasswordField pField = new JPasswordField();

    static JTextField tField = new JTextField(), tSurnameField = new JTextField(), tNameField = new JTextField(),
            amount = new JTextField(), enterAccNum = new JTextField(), depositCode = new JTextField(), title =
            new JTextField(), desc = new JTextField();

    static JLabel pesel = new JLabel("PESEL"), password = new JLabel("PASSWORD"),
            surname = new JLabel("SURNAME"), name = new JLabel("NAME"), cBalance = new JLabel("Current balance: "),
            cBalanceInt = new JLabel("0"), enterAmount = new JLabel("Enter amount "), accNum = new JLabel(
                    "Account number: "), accNumInt = new JLabel(), sAccNumInt = new JLabel("0"), warning =
            new JLabel("WARNING! "), warnMsg = new JLabel("Transactions can only be made to main account"), titleLab
            = new JLabel("Title"), descLab = new JLabel("Description");

    private static ArrayList<JButton> welcomeToolbarButtons = new ArrayList<>() {
        {
            add(bLogin);
            add(bOpenAcc);
        }
    };

    private static ArrayList<Component> transactionAcc = new ArrayList<>(){
        {
            add(accNum);
            add(enterAccNum);
            add(enterAmount);
            add(amount);
            add(titleLab);
            add(title);
            add(descLab);
            add(desc);
            add(submit);
        }
    };

    private static ArrayList<Component> transactionSavAcc = new ArrayList<>(){
        {
            add(warning);
            add(warnMsg);
            add(enterAmount);
            add(amount);
            add(titleLab);
            add(title);
            add(descLab);
            add(desc);
            add(submit);
        }
    };

    private static ArrayList<JButton> loggedToolbarButtons = new ArrayList<>() {
        {
            add(account);
            add(savAccount);
            add(history);
        }
    };

    private static ArrayList<Component> accountPanel = new ArrayList<>()
    {
        {
            add(accNum);
            add(accNumInt);
            add(cBalance);
            add(cBalanceInt);
            add(transaction);
            add(deposit);
        }
    };

    private static ArrayList<Component> savAccountPanel = new ArrayList<>()
    {
        {
            add(accNum);
            add(sAccNumInt);
            add(cBalance);
            add(cBalanceInt);
            add(transaction);
            add(deposit);
        }
    };

    private static ArrayList<Component> welcomeMainComps = new ArrayList<>() {
        {
            add(pesel);
            add(tField);
            add(password);
            add(pField);
            add(login);
        }
    };

    private static ArrayList<Component> welcomeMainCompsReg = new ArrayList<>() {
        {
            add(name);
            add(tNameField);
            add(surname);
            add(tSurnameField);
            add(pesel);
            add(tField);
            add(password);
            add(pField);
            add(submit);
        }
    };

    public static ArrayList<Component> getWelcomeMainComps() {
        return welcomeMainComps;
    }

    public static ArrayList<Component> getWelcomeMainCompsReg() {
        return welcomeMainCompsReg;
    }

    public static JButton getAccount() {
        return account;
    }

    public static JButton getCashLoan() {
        return cashLoan;
    }

    public static JButton getDeposit() {
        return deposit;
    }

    public static JButton getHistory() {
        return history;
    }

    public static JButton getSavAccount() {
        return savAccount;
    }

    public static JButton getTransaction() {
        return transaction;
    }

    public static JLabel getcBalanceInt() {
        return cBalanceInt;
    }

    public static ArrayList<JButton> getLoggedToolbarButtons() {
        return loggedToolbarButtons;
    }

    public static ArrayList<JButton> getWelcomeToolbarButtons() {
        return welcomeToolbarButtons;
    }

    public static JButton getbLogin() {
        return bLogin;
    }

    public static JButton getbOpenAcc() {
        return bOpenAcc;
    }

    public static JButton getSubmit() {
        return submit;
    }

    public static JButton getLogin() {
        return login;
    }

    public static ArrayList<Component> getAccountPanel() {
        return accountPanel;
    }

    public static ArrayList<Component> getSavAccountPanel() {
        return savAccountPanel;
    }

    public static JPasswordField getpField() {
        return pField;
    }

    public static JLabel getName() {
        return name;
    }

    public static JTextField getAmount() {
        return amount;
    }

    public static JTextField getEnterAccNum() {
        return enterAccNum;
    }

    public static JTextField gettSurnameField() {
        return tSurnameField;
    }

    public static JTextField gettField() {
        return tField;
    }

    public static JTextField gettNameField() {
        return tNameField;
    }

    public static JLabel getAccNumInt() {
        return accNumInt;
    }

    public static JLabel getsAccNumInt() {
        return sAccNumInt;
    }

    public static ArrayList<Component> getTransactionAcc() {
        return transactionAcc;
    }

    public static ArrayList<Component> getTransactionSavAcc() {
        return transactionSavAcc;
    }

    public static JTextField getDepositCode() {
        return depositCode;
    }

    public static JTextField getDesc() {
        return desc;
    }

    public static JTextField getTitle() {
        return title;
    }
}
