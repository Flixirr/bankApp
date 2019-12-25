package com.gui.ui;

import javax.swing.*;
import java.awt.*;


public class Sidebar extends JPanel {

    public Sidebar()
    {
        super(new GridBagLayout());

        this.setBackground(Color.decode("#3A3335"));
        this.setMinimumSize(new Dimension(100,-1));
        this.setPreferredSize(new Dimension(300, -1));
        this.setMaximumSize(new Dimension(300, -1));
    }
}
