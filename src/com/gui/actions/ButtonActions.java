package com.gui.actions;

import com.algorithms.FileAlgorithms;
import com.gui.styles.Styles;
import com.gui.ui.AppForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        public loggedInForm(JPanel panel, JPanel sidebar, ArrayList<JButton> btns, ArrayList<Component> comps)
        {
            this.panel = panel;
            this.comps = comps;
            this.sidebar = sidebar;
            this.btns = btns;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
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
    }

    public static class submit implements ActionListener
    {
        JPanel main;
        Map<String, String> fields = new HashMap<String, String>();

        public submit(JPanel panel)
        {
            main = panel;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(AppForm.getThisState() == 0)
            {
                for(Component co: main.getComponents())
                {
                    if(co instanceof JTextField)
                    {
                        fields.put(co.getName(), ((JTextField) co).getText());
                        ((JTextField) co).setText("");
                    }
                }

                FileAlgorithms.accountRegFile(fields);
            }
            else
            {
                //TODO: MAKE THIS AVAILABLE FOR LOANS
            }
        }
    }

}
