package com.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Algs {

    public static Component getComponentByName(JPanel panel, String name)
    {
        Map<String, Component> comps = new HashMap<>();
        Component[] arr = panel.getComponents();

        for(Component co : arr)
        {
            if(co.getName() != null)
            {
                comps.put(co.getName(), co);
            }
        }

        return comps.get(name);
    }

    public static void clearComps(JPanel panel)
    {
        for(Component co: panel.getComponents())
        {
            if(co instanceof JTextField)
            {
                ((JTextField) co).setText("");
            }
        }
    }

    public static String accNumCorrectFormat(String num)
    {
        String result = num.replace(" ", "");
        result = new StringBuilder(result).insert(1, " ").toString();
        result = new StringBuilder(result).insert(result.length()-4, " ").toString();

        return result;
    }
}
