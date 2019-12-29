package com.gui.ui;

import com.gui.actions.ButtonActions;
import com.gui.styles.*;
import javafx.scene.layout.Pane;

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
        PanelComponents.getAmount().setName("AMOUNT");
        PanelComponents.getEnterAccNum().setName("SELECTEDACC");
        PanelComponents.getDesc().setName("DESC");
        PanelComponents.getTitle().setName("TITLE");
        PanelComponents.getDepositCode().setName("DEPOCODE");

        PanelComponents.getbLogin().addActionListener(new ButtonActions.changePanelStyle(mainPanel,
                PanelComponents.getWelcomeMainComps()));

        PanelComponents.getbOpenAcc().addActionListener(new ButtonActions.changePanelStyle(mainPanel,
                PanelComponents.getWelcomeMainCompsReg()));

        PanelComponents.getLogin().addActionListener(new ButtonActions.loggedInForm(mainPanel, sidebarWest,
                PanelComponents.getLoggedToolbarButtons(), PanelComponents.getAccountPanel()));

        PanelComponents.getAccount().addActionListener(new ButtonActions.changeAccountStyle(mainPanel,
                PanelComponents.getAccountPanel(), 1));

        PanelComponents.getSavAccount().addActionListener(new ButtonActions.changeAccountStyle(mainPanel,
                PanelComponents.getSavAccountPanel(), 3));

        PanelComponents.getCashLoan().addActionListener(new ButtonActions.changeAccountStyle(mainPanel,
                PanelComponents.getLoanComps(), 5));

        PanelComponents.getSubmit().addActionListener(new ButtonActions.submit(mainPanel));

        PanelComponents.getTransaction().addActionListener(new ButtonActions.transactionButton(mainPanel));
    }

    public static int getThisState() {
        return state;
    }

    public static void setThisState(int state) {
        AppForm.state = state;
    }

}
