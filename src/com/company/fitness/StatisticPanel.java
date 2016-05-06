package com.company.fitness;

import SwingX.components.XPanel;
import com.company.PanelModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josh on 5/2/2016.
 */
public class StatisticPanel extends XPanel implements PanelModel{

    private String statistic;
    private double value;
    private String unit;


    public StatisticPanel(String statistic, double value, String unit) {
        this.statistic = statistic;
        this.unit = unit;
        this.value = value;
        init();
    }

    @Override
    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(new Dimension(1000, 28));

        JLabel statisticLabel = new JLabel(statistic + ": ");
        statisticLabel.setFont(new Font(statisticLabel.getName(), Font.BOLD, 16));

        JLabel valueLabel = new JLabel(value + " ");
        valueLabel.setFont(new Font(valueLabel.getName(), Font.PLAIN, 16));

        JLabel unitLabel = new JLabel("");
        if(unit!=null){
            unitLabel.setText("(" + unit + ")");
            unitLabel.setFont(new Font(unitLabel.getName(), Font.PLAIN, 16));
        }

        add(statisticLabel);
        add(valueLabel);
        add(unitLabel);

    }
}
