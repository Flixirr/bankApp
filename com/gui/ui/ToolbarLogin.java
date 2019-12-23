package com.gui.ui;

import javax.swing.*;
import java.awt.*;
import com.gui.styles.Styles;
import com.gui.effects.*;

public class ToolbarLogin extends JPanel {

    JButton bLogin = new JButton("Login");
    JButton bOpenAcc = new JButton("Open an account");

    public ToolbarLogin()
    {
        super(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(15,0,15,0);
        c.weightx = 1.0;
        this.add(bLogin, c);
        c.gridy = 1;
        this.add(bOpenAcc, c);

        Styles.buttonSetFlat(bLogin);
        Styles.buttonSetFlat(bOpenAcc);

        bLogin.addMouseListener(MouseEffects.onHover);
        bOpenAcc.addMouseListener(MouseEffects.onHover);

        this.setBackground(Color.decode("#3A3335"));
        this.setMinimumSize(new Dimension(100,-1));
        this.setPreferredSize(new Dimension(300, -1));
        this.setMaximumSize(new Dimension(300, -1));
    }

}
