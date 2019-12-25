package com.gui.styles;

import com.gui.effects.MouseEffects;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;

public class Styles {

    public static Color[] colors = {Color.decode("#3A3335"), Color.decode("#49393B"), Color.decode("#FBFFFE"), Color.decode("#6D676E"),
    Color.decode("#6C6F7F"), Color.decode("#FAA916")};

    public static void buttonSetFlat(JButton button)
    {
        button.setBackground(colors[1]);
        button.setForeground(colors[2]);

        button.setFont(new Font("Arial", Font.PLAIN, 22));

        button.setBorder(new MatteBorder(0,4,0,0, colors[5]));
        button.setFocusPainted(false);
    }

    public static void buttonSetFlatMain(JButton button)
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

    public static void labelBigStyle(JLabel label)
    {
        label.setForeground(colors[2]);
        label.setFont(new Font("Arial", Font.ITALIC, 22));
    }

    public static void toolbarContent(JPanel toolbar, List<JButton> buttons)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(15,0,15,0);
        c.weightx = 1.0;

        for (JButton button : buttons) {
            toolbar.add(button, c);
            c.gridy++;
            button.addMouseListener(MouseEffects.onHover);
            buttonSetFlat(button);
        }
    }

    public static void mainPanelComponents(JPanel main, List<Component> comp)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 15, 5, 15);
        c.gridy = 0;

        for(Component co: comp)
        {
            if(co instanceof JLabel)
            {
                c.gridx = 0;
                main.add(co, c);
                labelBigStyle((JLabel) co);
            }
            else if(co instanceof JTextField)
            {
                c.gridx = 1;
                main.add(co, c);
                fieldStyle((JTextField) co);
                c.gridy++;
            }
            else if(co instanceof JButton)
            {
                c.gridx = 1;
                main.add(co, c);
                buttonSetFlatMain((JButton) co);
            }
        }
    }

    public static void accountPanelComps(JPanel main, List<Component> comp)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 15, 5, 15);
        c.gridy = 0;
        c.gridx = 0;

        for(Component co: comp)
        {
            if(co instanceof JLabel)
            {
                if(c.gridx == 2)
                {
                    c.gridy++;
                    c.gridx = 0;
                }
                main.add(co, c);
                labelBigStyle((JLabel) co);
                c.gridx++;
            }
            else if(co instanceof JTextField)
            {
                main.add(co, c);
                fieldStyle((JTextField) co);
                c.gridx++;
            }
            else if(co instanceof JButton)
            {
                c.gridy++;
                c.gridx = 1;
                main.add(co, c);
                buttonSetFlatMain((JButton) co);
            }
        }
    }

}
