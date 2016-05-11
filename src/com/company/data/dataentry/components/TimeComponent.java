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
public class TimeComponent extends DataEntryComponent implements DataEntryComponentModel {

    private JSpinner hoursSpinner;
    private JSpinner minutesSpinner;
    private JSpinner secondsSpinner;

    public TimeComponent() {

        SpinnerNumberModel hoursSpinnerModel = new SpinnerNumberModel(0, 0,   24,  1);
        hoursSpinner = new JSpinner(hoursSpinnerModel);
        hoursSpinner.setBackground(Color.WHITE);
        hoursSpinner.setMaximumSize(new Dimension(50, 28));
        hoursSpinner.setFont(new Font(hoursSpinner.getName(), Font.PLAIN, 16));
        hoursSpinner.addChangeListener(e -> ComponentManager.calculateSpeed());

        SpinnerNumberModel minutesSpinnerModel = new SpinnerNumberModel(0, 0,   60,  1);
        minutesSpinner = new JSpinner(minutesSpinnerModel);
        minutesSpinner.setBackground(Color.WHITE);
        minutesSpinner.setMaximumSize(new Dimension(50, 28));
        minutesSpinner.setFont(new Font(hoursSpinner.getName(), Font.PLAIN, 16));
        minutesSpinner.addChangeListener(e -> {
            if(Integer.parseInt(minutesSpinner.getValue().toString()) == 60){
                hoursSpinner.setValue(Integer.parseInt(hoursSpinner.getValue().toString()) + 1);
                minutesSpinner.setValue(0);
            }
            ComponentManager.calculateSpeed();
        });

        SpinnerNumberModel secondsSpinnerModel = new SpinnerNumberModel(0, 0,   60,  1);
        secondsSpinner = new JSpinner(secondsSpinnerModel);
        secondsSpinner.setBackground(Color.WHITE);
        secondsSpinner.setMaximumSize(new Dimension(50, 28));
        secondsSpinner.setFont(new Font(secondsSpinner.getName(), Font.PLAIN, 16));
        secondsSpinner.addChangeListener(e -> {
            if(Integer.parseInt(secondsSpinner.getValue().toString()) == 60){
                minutesSpinner.setValue(Integer.parseInt(minutesSpinner.getValue().toString()) + 1);
                secondsSpinner.setValue(0);
            }
            ComponentManager.calculateSpeed();
        });

        add(hoursSpinner);
        add(new XDivider(2, 0));
        add(minutesSpinner);
        add(new XDivider(2, 0));
        add(secondsSpinner);

    }

    @Override
    public Object getValue() {
        return formatTime();
    }

    @Override
    public boolean hasDataIntegrity() {
        boolean dataIntegrity = true;

        if(Integer.parseInt(hoursSpinner.getValue().toString()) == 0
                && Integer.parseInt(minutesSpinner.getValue().toString()) == 0
                && Integer.parseInt(secondsSpinner.getValue().toString()) == 0){
            dataIntegrity = false;
            JOptionPane.showMessageDialog(null, "Please enter valid time");
        }

        return dataIntegrity;
    }

    private String formatTime(){
        String time = "";

        if(Integer.parseInt(hoursSpinner.getValue().toString()) < 10){
            time += ("0" + hoursSpinner.getValue().toString());
        }else{
            time += hoursSpinner.getValue().toString();
        }
        time += ":";
        if(Integer.parseInt(minutesSpinner.getValue().toString()) < 10){
            time += ("0" + minutesSpinner.getValue().toString());
        }else{
            time += minutesSpinner.getValue().toString();
        }
        time += ":";
        if(Integer.parseInt(secondsSpinner.getValue().toString()) < 10){
            time += ("0" + secondsSpinner.getValue().toString());
        }else{
            time += secondsSpinner.getValue().toString();
        }

        return time;
    }

    public double[] getTimeAsArray(){

        double[] time = new double[3];
        time[0] = Double.parseDouble(hoursSpinner.getValue().toString());
        time[1] = Double.parseDouble(minutesSpinner.getValue().toString());
        time[2] = Double.parseDouble(secondsSpinner.getValue().toString());

        return time;
    }

    public void setTime(int[] time){
        hoursSpinner.setValue(time[0]);
        minutesSpinner.setValue(time[1]);
        secondsSpinner.setValue(time[2]);
    }
}
