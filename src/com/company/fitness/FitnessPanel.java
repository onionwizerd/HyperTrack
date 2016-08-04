package com.company.fitness;

import SwingX.components.XTabbedPane;
import com.company.PanelModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Josh on 4/30/2016.
 */
public class FitnessPanel extends XTabbedPane implements PanelModel{

    private AnalysisPanel analysisPanel;
    private DataPanel dataPanel;
    private GoalsPanel goalsPanel;
    private String tableName;

    public FitnessPanel(String tableName) {
        this.tableName = tableName;
        init();
    }

    @Override
    public void init() {

        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(0,0,0,0));
        setUI();

        analysisPanel = new AnalysisPanel(tableName);
        dataPanel = new DataPanel(tableName);
        goalsPanel = new GoalsPanel();


        add("Data", dataPanel);
        add("Analysis", analysisPanel);
        add("Goals", goalsPanel);

        setOpaque(true);

    }

    public void update(){
        analysisPanel.updateGraphs();
    }

}
