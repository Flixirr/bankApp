package com.gui.actions;

import com.algorithms.Algs;
import com.algorithms.FileAlgorithms;
import com.algorithms.NumGenerator;
import com.algorithms.checkDataCorrectness;
import com.gui.styles.PanelComponents;
import com.gui.styles.Styles;
import com.gui.ui.AppForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

public class ButtonActions {

    public static class changePanelStyle implements ActionListener
    {
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
        }
    }

    public static class changeAccountStyle implements ActionListener
    {
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
            PanelComponents.getcBalanceInt().setText(
                    Double.toString(FileAlgorithms.readBalance("accounts/"+AppForm.getLoggedPesel()+"/"+accType))
            );
            panel.repaint();
            panel.revalidate();
        }
    }

    public static class transactionButton implements ActionListener
    {
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

    public static class loggedInForm implements ActionListener
    {
        private JPanel panel;
        private JPanel sidebar;
        private ArrayList<JButton> btns;
        private ArrayList<Component> comps;
        String PESEL, password;

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
            if(checkDataCorrectness.passwordPeselMatch(PESEL, password)) {
                FileAlgorithms.setNumPeselPair();
                PanelComponents.getAccNumInt().setText(FileAlgorithms.getAccNum().get(PESEL));
                PanelComponents.getsAccNumInt().setText(FileAlgorithms.getsAccNum().get(PESEL));
                PanelComponents.getcBalanceInt().setText(
                        Double.toString(FileAlgorithms.readBalance("accounts/"+PESEL+"/acc.txt"))
                );
                AppForm.setLoggedPesel(PESEL);
                AppForm.setThisState(1);
                panel.removeAll();
                sidebar.removeAll();
                Styles.toolbarContent(sidebar, btns);
                Styles.accountPanelComps(panel, comps);
                sidebar.repaint();
                panel.repaint();
                sidebar.revalidate();
                panel.revalidate();
            }
            else
            {
                ((JTextField) Algs.getComponentByName(panel, "PESEL!")).setText("");
                ((JTextField) Algs.getComponentByName(panel, "Password!")).setText("");
                showMessageDialog(null, "PESEL or password incorrect.");
            }
        }
    }

    public static class submit implements ActionListener
    {
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
            if(state == 0)
            {
                for(Component co: main.getComponents())
                {
                    if(co instanceof JTextField)
                    {
                        fields.put(co.getName(), ((JTextField) co).getText());
                        ((JTextField) co).setText("");
                    }
                }
                fields.put("ANUM!", NumGenerator.generateAccNum());
                fields.put("SANUM!", NumGenerator.generateSAccNum());
                FileAlgorithms.accountRegFile(fields);
                FileAlgorithms.setNumPeselPair();
            }
            else if(state == 2)
            {
                String desc = ((JTextField) Algs.getComponentByName(main, "DESC")).getText();
                String title = ((JTextField) Algs.getComponentByName(main, "TITLE")).getText();
                String amount = ((JTextField) Algs.getComponentByName(main, "AMOUNT")).getText();
                String target =
                        Algs.accNumCorrectFormat(((JTextField) Algs.getComponentByName(main, "SELECTEDACC")).getText());
                if(checkDataCorrectness.checkAccNum(target))
                {
                    String id = NumGenerator.generateTransactionNum(PanelComponents.getAccNumInt().getText());
                    FileAlgorithms.addTransactionFile(id, amount, desc, title, target, PanelComponents.getAccNumInt().getText());
                    Algs.clearComps(main);
                }
                else {
                    showMessageDialog(null, "Provided account number is incorrect");
                }
            }
            else if(state == 4) {
                String desc = ((JTextField) Algs.getComponentByName(main, "DESC")).getText();
                String title = ((JTextField) Algs.getComponentByName(main, "TITLE")).getText();
                String amount = ((JTextField) Algs.getComponentByName(main, "AMOUNT")).getText();
                String target = PanelComponents.getAccNumInt().getText();
                String id = NumGenerator.generateTransactionNum(PanelComponents.getsAccNumInt().getText());
                FileAlgorithms.addTransactionFile(id, amount, desc, title, target, PanelComponents.getAccNumInt().getText());
                Algs.clearComps(main);
            }
        }
    }

    //TODO CREATE LOG OUT BUTTON
    //TODO CREATE DEPOSIT

}
