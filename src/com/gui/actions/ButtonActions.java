package com.gui.actions;

import com.algorithms.ComponentByName;
import com.algorithms.FileAlgorithms;
import com.algorithms.accNumGenerator;
import com.algorithms.checkDataCorrectness;
import com.gui.styles.PanelComponents;
import com.gui.styles.Styles;
import com.gui.ui.AppForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

        public changeAccountStyle(JPanel panel, ArrayList<Component> comps)
        {
            this.panel = panel;
            this.comps = comps;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            panel.removeAll();
            Styles.accountPanelComps(panel, comps);
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
            PESEL = ((JTextField) ComponentByName.getComponentByName(panel, "PESEL!")).getText();
            password = ((JTextField) ComponentByName.getComponentByName(panel, "Password!")).getText();
            if(checkDataCorrectness.passwordPeselMatch(PESEL, password)) {
                FileAlgorithms.setNumPeselPair();
                PanelComponents.getAccNumInt().setText(FileAlgorithms.getAccNum().get(PESEL));
                PanelComponents.getsAccNumInt().setText(FileAlgorithms.getsAccNum().get(PESEL));
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
                ((JTextField) ComponentByName.getComponentByName(panel, "PESEL!")).setText("");
                ((JTextField) ComponentByName.getComponentByName(panel, "Password!")).setText("");
                showMessageDialog(null, "PESEL or password incorrect.");
            }
        }
    }

    public static class submit implements ActionListener
    {
        JPanel main;
        Map<String, String> fields = new LinkedHashMap<String, String>();

        public submit(JPanel panel)
        {
            main = panel;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(AppForm.getThisState() == 0)
            {
                FileAlgorithms.setNumPeselPair();
                for(Component co: main.getComponents())
                {
                    if(co instanceof JTextField)
                    {
                        fields.put(co.getName(), ((JTextField) co).getText());
                        ((JTextField) co).setText("");
                    }
                }
                fields.put("ANUM!", accNumGenerator.generateAccNum());
                fields.put("SANUM!", accNumGenerator.generateSAccNum());
                FileAlgorithms.accountRegFile(fields);
            }
            else
            {
                //TODO: MAKE THIS AVAILABLE FOR LOANS
            }
        }
    }

    //TODO CREATE LOG OUT BUTTON
    //checking if this works

}
