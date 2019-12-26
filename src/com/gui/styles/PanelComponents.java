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
    private static JButton history = new JButton("History");
    private static JButton deposit = new JButton("Deposit funds");
    private static JButton buy = new JButton("Buy");
    private static JButton sell = new JButton("Sell");
    private static JButton availableOptions = new JButton("Show available options");
    private static JButton ownedOptions = new JButton("Show owned options");

    private static JButton cashLoan = new JButton("Cash loan");
    private static JButton account = new JButton("Account");
    private static JButton savAccount = new JButton("Savings account");
    private static JButton brokerageAccount = new JButton("Brokerage account");

    static JPasswordField pField = new JPasswordField();

    static JTextField tField = new JTextField(), tSurnameField = new JTextField(), tNameField = new JTextField(),
            amount = new JTextField(), enterAccNum = new JTextField();

    static JLabel pesel = new JLabel("PESEL"), password = new JLabel("PASSWORD"),
            surname = new JLabel("SURNAME"), name = new JLabel("NAME"), cBalance = new JLabel("Current balance: "),
            cBalanceInt = new JLabel("0"), enterAmount = new JLabel("Enter amount "), accNum = new JLabel(
                    "Account number: "), accNumInt = new JLabel(), sAccNumInt = new JLabel("0");

    private static ArrayList<JButton> welcomeToolbarButtons = new ArrayList<>() {
        {
            add(bLogin);
            add(bOpenAcc);
        }
    };

    private static ArrayList<JButton> loggedToolbarButtons = new ArrayList<>() {
        {
            add(account);
            add(savAccount);
            add(cashLoan);
            add(brokerageAccount);
        }
    };

    private static ArrayList<Component> accountPanel = new ArrayList<>()
    {
        {
            add(accNum);
            add(accNumInt);
            add(cBalance);
            add(cBalanceInt);
            add(history);
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
            add(history);
            add(transaction);
            add(deposit);
        }
    };

    private static ArrayList<Component> loanComps = new ArrayList<>()
    {
        {
            add(enterAmount);
            add(amount);
            add(accNum);
            add(enterAccNum);
            add(submit);
        }
    };

    private static ArrayList<Component> brokerageAccountPanel = new ArrayList<>()
    {
        {
            add(accNum);
            add(accNumInt);
            add(cBalance);
            add(cBalanceInt);
            add(ownedOptions);
            add(availableOptions);
            add(buy);
            add(sell);
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

    public static JButton getBrokerageAccount() {
        return brokerageAccount;
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

    public static ArrayList<Component> getBrokerageAccountPanel() {
        return brokerageAccountPanel;
    }

    public static ArrayList<Component> getLoanComps() {
        return loanComps;
    }

    public static JButton getAvailableOptions() {
        return availableOptions;
    }

    public static JButton getBuy() {
        return buy;
    }

    public static JButton getOwnedOptions() {
        return ownedOptions;
    }

    public static JButton getSell() {
        return sell;
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
}
