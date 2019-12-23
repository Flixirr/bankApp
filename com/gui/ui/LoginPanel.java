package com.gui.ui;

import com.gui.styles.Styles;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel()
    {
        super(new GridBagLayout());

        JLabel PESEL = new JLabel("PESEL");
        JLabel password = new JLabel("PASSWORD");

        JPasswordField pField = new JPasswordField();
        JTextField tField = new JTextField();

        JButton login = new JButton("Login");

        this.setBackground(Styles.colors[3]);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 15, 5, 15);
        this.add(PESEL, c);
        c.gridx = 1;
        this.add(tField, c);
        c.gridx = 0;
        c.gridy = 1;
        this.add(password, c);
        c.gridx = 1;
        this.add(pField, c);
        c.gridy = 2;
        this.add(login, c);

        Styles.buttonSetFlat(login);

        Styles.fieldStyle(pField);
        Styles.fieldStyle(tField);

    }

}
