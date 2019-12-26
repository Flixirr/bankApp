package com.algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ComponentByName {

    public static Component getComponentByName(JPanel panel, String name)
    {
        Map<String, Component> comps = new HashMap<>();
        Component[] arr = panel.getComponents();

        for(Component co : arr)
        {
            comps.put(co.getName(), co);
        }

        return comps.get(name);
    }

}
