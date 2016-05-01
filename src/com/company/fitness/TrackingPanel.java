package com.company.fitness;

import SwingX.XPanel;
import SwingX.XScrollPanel;
import com.company.PanelModel;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Josh on 4/30/2016.
 */
public class TrackingPanel extends XScrollPanel implements PanelModel{


    private XPanel viewPortPanel;
    private String tableName;

    public TrackingPanel(String tableName) {
        this.tableName = tableName;
        init();
    }

    @Override
    public void init() {

        setBackground(Color.WHITE);

        viewPortPanel = new XPanel();
        viewPortPanel.setLayout(new BoxLayout(viewPortPanel, BoxLayout.Y_AXIS));

        GraphFactory graphFactory = new GraphFactory(tableName);

        viewPortPanel.add(graphFactory.createGraphItem("Distance", "distance", CategoryType.INT ,"Date", "Distance"));
        viewPortPanel.add(graphFactory.createGraphItem("Time", "time", CategoryType.TIME, "Date", "Time"));
        viewPortPanel.add(graphFactory.createGraphItem("Speed", "speed", CategoryType.INT, "Date", "Speed"));

        setViewportView(viewPortPanel);

    }
}
