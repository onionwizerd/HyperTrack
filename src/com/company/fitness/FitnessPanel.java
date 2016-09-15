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
    private SettingsPanel settingsPanel;

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
        analysisPanel.setParentPanel(this);

        dataPanel = new DataPanel(tableName);
        dataPanel.setParentPanel(this);

        goalsPanel = new GoalsPanel();
        goalsPanel.setParentPanel(this);

        settingsPanel = new SettingsPanel(tableName);
        settingsPanel.setParentPanel(this);

        add("Data", dataPanel);
        add("Analysis", analysisPanel);
        add("Goals", goalsPanel);
        add("Settings", settingsPanel);

        setOpaque(true);

    }

    public void update(){
        analysisPanel.updateGraphs();
    }

    public AnalysisPanel getAnalysisPanel() {
        return analysisPanel;
    }

    public DataPanel getDataPanel() {
        return dataPanel;
    }

    public GoalsPanel getGoalsPanel() {
        return goalsPanel;
    }
}
