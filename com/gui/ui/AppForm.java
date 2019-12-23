package com.gui.ui;

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

    JPanel toolbarWest = new ToolbarLogin();
    JPanel main = new LoginPanel();

    public AppForm(String title, int width) {
        super(title);
        this.setSize(new Dimension(width, width * 9 / 16));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.getContentPane().add(BorderLayout.CENTER, main);
        this.getContentPane().add(BorderLayout.WEST, toolbarWest);
    }

}
