package com.gui.ui;

import com.gui.actions.ButtonActions;
import com.gui.styles.*;

import javax.swing.*;
import java.awt.*;

/*
USING COLOR PALETTE:
#3A3335 - darker gray
#FAA916 - light orange
#FBFFFE - white
#6D676E - dark gray
#96031A - dark red
 */

public class AppForm extends JFrame {

    JPanel sidebarWest = new Sidebar();
    JPanel mainPanel = new mainPanel();

    private static int state = 0;
    //TODO CHECK IF VARIABLE BELOW IS USELESS (PROBABLY IT IS)
    private static String loggedPesel = "";

    public AppForm(String title, int width) {
        super(title);
        this.setSize(new Dimension(width, width * 9 / 16));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.getContentPane().add(BorderLayout.WEST, sidebarWest);
        this.getContentPane().add(BorderLayout.CENTER, mainPanel);

        Styles.toolbarContent(sidebarWest, PanelComponents.getWelcomeToolbarButtons());
        Styles.mainPanelComponents(mainPanel, PanelComponents.getWelcomeMainComps());

        PanelComponents.gettField().setName("PESEL!");
        PanelComponents.getpField().setName("Password!");
        PanelComponents.gettNameField().setName("Name!");
        PanelComponents.gettSurnameField().setName("Surname!");
        PanelComponents.getAmount().setName("Amount!");
        PanelComponents.getEnterAccNum().setName("AccountNum!");

        PanelComponents.getbLogin().addActionListener(new ButtonActions.changePanelStyle(mainPanel,
                PanelComponents.getWelcomeMainComps()));

        PanelComponents.getbOpenAcc().addActionListener(new ButtonActions.changePanelStyle(mainPanel,
                PanelComponents.getWelcomeMainCompsReg()));

        PanelComponents.getLogin().addActionListener(new ButtonActions.loggedInForm(mainPanel, sidebarWest,
                PanelComponents.getLoggedToolbarButtons(), PanelComponents.getAccountPanel()));

        PanelComponents.getAccount().addActionListener(new ButtonActions.changeAccountStyle(mainPanel,
                PanelComponents.getAccountPanel()));

        PanelComponents.getSavAccount().addActionListener(new ButtonActions.changeAccountStyle(mainPanel,
                PanelComponents.getSavAccountPanel()));

        PanelComponents.getCashLoan().addActionListener(new ButtonActions.changeAccountStyle(mainPanel,
                PanelComponents.getLoanComps()));

        PanelComponents.getSubmit().addActionListener(new ButtonActions.submit(mainPanel));
    }

    public static int getThisState() {
        return state;
    }

    public static void setThisState(int state) {
        AppForm.state = state;
    }
}
