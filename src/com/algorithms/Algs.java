package com.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Algs {

    //use to get specific component from panel by name
    public static Component getComponentByName(JPanel panel, String name) {
        Map<String, Component> comps = new HashMap<>();
        Component[] arr = panel.getComponents();

        for(Component co : arr) {
            if(co.getName() != null) {
                comps.put(co.getName(), co);
            }
        }

        return comps.get(name);
    }
    //clear all textfields in panel
    public static void clearComps(JPanel panel) {
        for(Component co: panel.getComponents()) {
            if(co instanceof JTextField) {
                ((JTextField) co).setText("");
            }
        }
    }
    //check if account number is corrects
    public static String accNumCorrectFormat(String num) {
        String result = num.replace(" ", "");
        result = new StringBuilder(result).insert(1, " ").toString();
        result = new StringBuilder(result).insert(result.length()-4, " ").toString();

        return result;
    }
}
