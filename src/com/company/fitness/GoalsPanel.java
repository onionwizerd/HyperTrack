package com.company.fitness;

import SwingX.components.XPanel;
import SwingX.components.XScrollPanel;
import com.company.PanelModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by josh on 2016/07/29.
 */
public class GoalsPanel extends XScrollPanel implements PanelModel {

    private XPanel viewPortPanel;

    private FitnessPanel parentPanel;

    public GoalsPanel() {
        init();
    }

    @Override
    public void init() {

        setBackground(Color.WHITE);

        viewPortPanel = new XPanel();
        viewPortPanel.setLayout(new BoxLayout(viewPortPanel, BoxLayout.Y_AXIS));
        viewPortPanel.setBackground(Color.WHITE);

        setViewportView(viewPortPanel);


    }

    public void setParentPanel(FitnessPanel parentPanel){
        this.parentPanel = parentPanel;
    }
}
