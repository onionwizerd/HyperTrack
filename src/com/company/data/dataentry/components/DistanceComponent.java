package com.company.data.dataentry.components;

import SwingX.components.XDivider;
import SwingX.components.XPanel;
import com.company.data.dataentry.ComponentManager;
import com.company.data.dataentry.DataEntryComponent;
import com.company.data.dataentry.DataEntryComponentModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josh on 5/6/2016.
 */
public class DistanceComponent extends DataEntryComponent implements DataEntryComponentModel {

    private JSpinner kilometersSpinner;
    private JSpinner metersSpinner;

    public DistanceComponent() {

        SpinnerNumberModel kilometersSpinnerModel = new SpinnerNumberModel(0, 0,   9999,  1);
        kilometersSpinner = new JSpinner(kilometersSpinnerModel);
        kilometersSpinner.setBackground(Color.WHITE);
        kilometersSpinner.setMaximumSize(new Dimension(75, 28));
        kilometersSpinner.setMinimumSize(new Dimension(75, 28));
        kilometersSpinner.setFont(new Font(kilometersSpinner.getName(), Font.PLAIN, 16));
       kilometersSpinner.addChangeListener(e -> ComponentManager.calculateSpeed());

        SpinnerNumberModel  metersSpinnerModel = new SpinnerNumberModel(0, 0,   1000,  1);
        metersSpinner = new JSpinner(metersSpinnerModel);
        metersSpinner.setBackground(Color.WHITE);
        metersSpinner.setMaximumSize(new Dimension(75, 28));
        metersSpinner.setMinimumSize(new Dimension(75, 28));
        metersSpinner.setFont(new Font(metersSpinner.getName(), Font.PLAIN, 16));
        metersSpinner.addChangeListener(e -> {
            if(Integer.parseInt(metersSpinner.getValue().toString()) == 1000){
                kilometersSpinner.setValue(Integer.parseInt(kilometersSpinner.getValue().toString()) + 1);
                metersSpinner.setValue(0);
            }
            ComponentManager.calculateSpeed();
        });

        add(kilometersSpinner);
        add(new XDivider(2, 0));
        add(metersSpinner);

    }

    @Override
    public Object getValue() {
        return formatDistance();
    }

    public double getValueAsDouble() {
        return formatDistance();
    }

    @Override
    public boolean hasDataIntegrity() {
        boolean dataIntegrity = true;

        if(Integer.parseInt(kilometersSpinner.getValue().toString()) == 0
                && Integer.parseInt(metersSpinner.getValue().toString()) == 0){
            dataIntegrity = false;
            JOptionPane.showMessageDialog(null, "Please enter valid distance");
        }

        return dataIntegrity;
    }

    private double formatDistance(){
        double distance = 0;

        distance += Double.parseDouble(kilometersSpinner.getValue().toString());
        distance += (Double.parseDouble(metersSpinner.getValue().toString())/1000);

        return distance;
    }

    public void setDistance(double kilometers, double meters){
        kilometersSpinner.setValue(kilometers);
        metersSpinner.setValue(meters);
    }

}
