package com.company.data.dataentry.components;

import SwingX.components.XPanel;
import com.company.data.dataentry.DataEntryComponent;
import com.company.data.dataentry.DataEntryComponentModel;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Josh on 5/6/2016.
 */
public class SpeedComponent extends DataEntryComponent implements DataEntryComponentModel {

    private JTextField speedField;

    public SpeedComponent() {
        speedField = new JTextField();
        speedField.setMaximumSize(new Dimension(150, 28));
        speedField.setEditable(false);

        add(speedField);
    }

    @Override
    public Object getValue() {
        return speedField.getText();
    }

    @Override
    public boolean hasDataIntegrity() {
        return true;
    }

    public double calculateSpeed(double time[], double distance){
        double speed;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        speed = distance/(time[0] + (time[1]/60) +  ((time[2]/60)/60));

        if(speed < 1){
            speedField.setText("0" + decimalFormat.format(speed));
        }else {
            speedField.setText(decimalFormat.format(speed));
        }

        return speed;
    }
}
