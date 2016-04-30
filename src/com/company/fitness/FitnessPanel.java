package com.company.fitness;

import SwingX.XTabbedPane;
import com.company.PanelModel;

import java.awt.*;

/**
 * Created by Josh on 4/30/2016.
 */
public class FitnessPanel extends XTabbedPane implements PanelModel{

    @Override
    public void init() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

    }
}
