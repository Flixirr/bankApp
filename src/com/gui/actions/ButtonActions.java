package com.gui.actions;

import com.algorithms.*;
import com.gui.styles.PanelComponents;
import com.gui.styles.Styles;
import com.gui.ui.AppForm;
import com.observers.Observer;
import com.observers.Subject;
import com.observers.labelObserver.AccountBalance;
import com.observers.labelObserver.SavAccountBalance;
import com.userfiles.Account;
import com.userfiles.Transaction;
import com.userfiles.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class ButtonActions {

    private static AccountBalance accB;
    private static SavAccountBalance sAccB;
    private static ArrayList<Observer> observers = new ArrayList<>();
    private static User loggedUser = null;
    private static Thread savLogic;

    public static class changePanelStyle implements ActionListener {
        private JPanel panel;
        private ArrayList<Component> comps;

        public changePanelStyle(JPanel panel, ArrayList<Component> comps)
        {
            this.panel = panel;
            this.comps = comps;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            panel.removeAll();
            Styles.mainPanelComponents(panel, comps);
            panel.repaint();
            panel.revalidate();
            ((JTextField) Algs.getComponentByName(panel, "PESEL!")).setText("");
            ((JTextField) Algs.getComponentByName(panel, "Password!")).setText("");
        }
    }

    public static class changeAccountStyle implements ActionListener {
        private JPanel panel;
        private ArrayList<Component> comps;
        int state;
        String accType; //acctype.txt or savacctype.txt

        public changeAccountStyle(JPanel panel, ArrayList<Component> comps, int state, String accType)
        {
            this.panel = panel;
            this.comps = comps;
            this.state = state;
            this.accType = accType;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AppForm.setThisState(state);
            panel.removeAll();
            Styles.accountPanelComps(panel, comps);
            panel.repaint();
            panel.revalidate();
        }
    }

    public static class transactionButton implements ActionListener {
        private JPanel panel;

        public transactionButton(JPanel panel)
        {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(AppForm.getThisState() == 1)
            {
                panel.removeAll();
                Styles.accountPanelComps(panel, PanelComponents.getTransactionAcc());
                AppForm.setThisState(2);
            }
            else if (AppForm.getThisState() == 3)
            {
                panel.removeAll();
                Styles.accountPanelComps(panel, PanelComponents.getTransactionSavAcc());
                AppForm.setThisState(4);
            }
            panel.repaint();
            panel.revalidate();
        }
    }

    public static class loggedInForm implements ActionListener {
        private JPanel panel;
        private JPanel sidebar;
        private ArrayList<JButton> btns;
        private ArrayList<Component> comps;
        String password, PESEL;

        public loggedInForm(JPanel panel, JPanel sidebar, ArrayList<JButton> btns, ArrayList<Component> comps)
        {
            this.panel = panel;
            this.comps = comps;
            this.sidebar = sidebar;
            this.btns = btns;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PESEL = ((JTextField) Algs.getComponentByName(panel, "PESEL!")).getText();
            password = ((JTextField) Algs.getComponentByName(panel, "Password!")).getText();
            //Compare entered password to read object password
            if(FileAlgorithms.readObject(PESEL) != null) {
                if (Objects.requireNonNull(FileAlgorithms.readObject(PESEL)).getPassword().equals(password)) {
                    loggedUser = FileAlgorithms.readObject(PESEL);
                    FileAlgorithms.setNumUserPair();
                    savLogic = savLogic = new Thread(new SavingsAccountThread("SavAcc"));
                    //savLogic.start();

                    //Reset fields so after logout they don't remember pesel neither password
                    ((JTextField) Algs.getComponentByName(panel, "PESEL!")).setText("");
                    ((JTextField) Algs.getComponentByName(panel, "Password!")).setText("");

                    accB = new AccountBalance();
                    sAccB = new SavAccountBalance();

                    //set state to 1 (logged in)
                    PanelComponents.getAccNumInt().setText(loggedUser.getbAcc().getNumber());
                    PanelComponents.getsAccNumInt().setText(loggedUser.getsAcc().getNumber());
                    AppForm.setThisState(1);

                    panel.removeAll();
                    sidebar.removeAll();

                    Styles.toolbarContent(sidebar, btns);
                    Styles.accountPanelComps(panel, comps);

                    sidebar.repaint();
                    panel.repaint();
                    sidebar.revalidate();
                    panel.revalidate();
                } else {
                    //if password is incorrect)
                    ((JTextField) Algs.getComponentByName(panel, "PESEL!")).setText("");
                    ((JTextField) Algs.getComponentByName(panel, "Password!")).setText("");
                    showMessageDialog(null, "Password incorrect.");
                }
            } else {
                //if pesel doesn't exist
                ((JTextField) Algs.getComponentByName(panel, "PESEL!")).setText("");
                ((JTextField) Algs.getComponentByName(panel, "Password!")).setText("");
                showMessageDialog(null, "Account does not exist!");
            }
        }
    }

    public static class submit implements ActionListener, Subject {
        JPanel main;
        Map<String, String> fields = new LinkedHashMap<>();

        public submit(JPanel panel)
        {
            main = panel;
        }
        //1, 3, 5 acc choice, 0, 2, 4 panels with submit btn
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int state = AppForm.getThisState();
            //check state - 0 means account registration, 2 means transaction from base account, 4 mean transaction from sav acc to base acc
            if(state == 0) {
                for(Component co: main.getComponents()) {
                    if(co instanceof JTextField) {
                        fields.put(co.getName(), ((JTextField) co).getText());
                        ((JTextField) co).setText("");
                    }
                }
                //check data (if everything is correct)
                if(CheckDataCorrectness.checkForDataType(fields.get("PESEL!"), fields.get("Password!"), fields.get("Name!"), fields.get("Surname!"))) {
                    FileAlgorithms.accountRegFile(fields);
                }
            }
            else if(state == 2) {
                String desc = ((JTextField) Algs.getComponentByName(main, "DESC")).getText();
                String title = ((JTextField) Algs.getComponentByName(main, "TITLE")).getText();
                String amount = ((JTextField) Algs.getComponentByName(main, "AMOUNT")).getText();
                String target =
                        Algs.accNumCorrectFormat(((JTextField) Algs.getComponentByName(main, "SELECTEDACC")).getText());
                if(CheckDataCorrectness.checkAccNum(target) && FileAlgorithms.getNumUserPair().containsKey(target)) {
                    //clear comps actually clears text fields
                    Algs.clearComps(main);

                    //transaction object to target acc and origin acc
                    if(target.charAt(0) == '2') {
                        new Transaction(loggedUser.getbAcc(), FileAlgorithms.getNumUserPair().get(target).getsAcc(), Double.parseDouble(amount), title, desc);
                    } else {
                        new Transaction(loggedUser.getbAcc(), FileAlgorithms.getNumUserPair().get(target).getbAcc(), Double.parseDouble(amount), title, desc);
                    }

                    loggedUser = FileAlgorithms.readObject(loggedUser.getPESEL());

                    //notify labels about transaction
                    if(observers.isEmpty()) {
                        registerObserver(accB);
                        registerObserver(sAccB);
                    }
                    notifyObservers();
                }
                else {
                    showMessageDialog(null, "Provided account number is incorrect");
                }
            }
            else if(state == 4) {
                //get amount, desc and title from fields
                String desc = ((JTextField) Algs.getComponentByName(main, "DESC")).getText();
                String title = ((JTextField) Algs.getComponentByName(main, "TITLE")).getText();
                double amount = Double.parseDouble(((JTextField) Algs.getComponentByName(main, "AMOUNT")).getText());
                //check if amount is not greater than balance nor lesser than 0

                if(amount < 0 || amount > loggedUser.getsAcc().getBalance()) {
                    showMessageDialog(null, "Amount is negative or surpasses account balance");
                }
                else {
                    //make transaction object and put it in target account
                    new Transaction(loggedUser.getsAcc(), loggedUser.getbAcc(), amount, title, desc);

                    Algs.clearComps(main);

                    //notify observers to update labels

                    if (observers.isEmpty()) {
                        registerObserver(accB);
                        registerObserver(sAccB);
                    }
                    notifyObservers();
                }
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

    public static class logout implements ActionListener {
        JPanel main, sidebar;

        public logout(JPanel main, JPanel sidebar) {
            this.sidebar = sidebar;
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //set state to 0 (not logged in), reset observers, make thread interrupted so it throws exception and set it to null
            //so it can be started again on different account later
            AppForm.setThisState(0);
            observers.clear();
            loggedUser = null;
            savLogic.interrupt();
            savLogic = null;

            sidebar.removeAll();
            main.removeAll();
            Styles.toolbarContent(sidebar, PanelComponents.getWelcomeToolbarButtons());
            Styles.mainPanelComponents(main, PanelComponents.getWelcomeMainComps());
            main.revalidate();
            sidebar.revalidate();
            main.repaint();
            sidebar.repaint();
        }
    }

    public static class history implements ActionListener {
        JPanel main;

        public history(JPanel main) {
            this.main = main;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            main.removeAll();
            JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            main.add(scrollPane);
            scrollPane.setPreferredSize(new Dimension(600, 500));

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(0,10,0,0);

            scrollPane.setViewportView(panel);

            main.revalidate();
            main.repaint();
        }
    }

    public static class deposit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    public static SavAccountBalance getsAccB() {
        return sAccB;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
}
