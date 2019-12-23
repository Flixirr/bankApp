package com.gui.styles;

import javax.swing.*;
import java.awt.*;

public class Styles {

    public static Color[] colors = {Color.decode("#3A3335"), Color.decode("#49393B"), Color.decode("#FBFFFE"), Color.decode("#6D676E"),
    Color.decode("#6C6F7F")};

    public static void buttonSetFlat(JButton button)
    {
        button.setBackground(colors[1]);
        button.setForeground(colors[2]);

        button.setFont(new Font("Arial", Font.PLAIN, 22));

        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    public static void fieldStyle(JTextField field)
    {
        field.setPreferredSize(new Dimension(250, 28));
    }

}
