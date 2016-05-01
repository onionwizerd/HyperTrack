package com.company.fitness;

import SwingX.XTabbedPane;
import com.company.PanelModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Josh on 4/30/2016.
 */
public class FitnessPanel extends XTabbedPane implements PanelModel{

    public FitnessPanel() {
        init();
    }

    @Override
    public void init() {
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(0,0,0,0));
        UIManager.put("TabbedPane.selected", Color.white);

        add("Data", new DataPanel("Running"));
        add("Tracking", new TrackingPanel("Running"));

        setOpaque(true);


    }
}
