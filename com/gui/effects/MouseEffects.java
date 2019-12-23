package com.gui.effects;

import com.gui.styles.Styles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEffects {

    public static MouseAdapter onHover = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            ((JButton) e.getSource()).setBackground(Styles.colors[4]);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            ((JButton) e.getSource()).setForeground(Color.BLACK);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            ((JButton) e.getSource()).setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((JButton) e.getSource()).setBackground(Styles.colors[1]);
        }
    };


}
