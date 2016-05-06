package com.company.calculators;

import SwingX.components.XPanel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.DecimalFormat;

/**
 *
 * @author Josh Beaver
 * @version 0.1
 * @since 2016-03-16
 *
 * <h1>BMI Calculator</h1>
 * <p></p>
 *
 * <h2>Notes</h2>
 *
 *
 */
public class BMICalculator extends XPanel {

    JSpinner heightSpinner;
    JSpinner weightSpinner;

    JLabel bmiLabel;
    JLabel weightRangeLabel;

    DecimalFormat df = new DecimalFormat("#.00");

    public BMICalculator() {

        JLabel weightLabel = new JLabel("Weight (kg): ");
        weightLabel.setFont(new Font(weightLabel.getName(), Font.PLAIN, 20));

        SpinnerNumberModel  weightSpinnerModel = new SpinnerNumberModel(0, 0,   999,  1);

        weightSpinner = new JSpinner(weightSpinnerModel);
        weightSpinner.setBackground(Color.WHITE);
        weightSpinner.setMaximumSize(new Dimension(100, 35));
        weightSpinner.setFont(new Font(weightSpinner.getName(), Font.PLAIN, 18));

        weightSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                calculateBMI((int)(weightSpinner.getValue()), (int)(heightSpinner.getValue()), true);
            }
        });

        XPanel weightContainer = new XPanel();
        weightContainer.setLayout(new BoxLayout(weightContainer, BoxLayout.X_AXIS));
        weightContainer.setTransparent(true);

        weightContainer.add(weightLabel);
        weightContainer.add(weightSpinner);

        JLabel heightLabel = new JLabel("Height (cm):  ");
        heightLabel.setFont(new Font(weightLabel.getName(), Font.PLAIN, 20));

        SpinnerNumberModel heightSpinnerModel = new SpinnerNumberModel(0, 0,   999,  1);

        heightSpinner = new JSpinner(heightSpinnerModel);
        heightSpinner.setBackground(Color.WHITE);
        heightSpinner.setMaximumSize(new Dimension(100, 35));
        heightSpinner.setFont(new Font(weightSpinner.getName(), Font.PLAIN, 18));

        heightSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                calculateBMI((int)(weightSpinner.getValue()), (int)(heightSpinner.getValue()), true);
            }
        });

        XPanel heightContainer = new XPanel();
        heightContainer.setLayout(new BoxLayout(heightContainer, BoxLayout.X_AXIS));
        heightContainer.setTransparent(true);

        heightContainer.add(heightLabel);
        heightContainer.add(heightSpinner);

        JLabel dataLabel = new JLabel("Enter Data");
        dataLabel.setFont(new Font(weightLabel.getName(), Font.PLAIN, 25));

        XPanel dataContainer = new XPanel();
        dataContainer.setLayout(new BoxLayout(dataContainer, BoxLayout.Y_AXIS));
        dataContainer.setTransparent(true);

        dataContainer.add(dataLabel);
        dataContainer.add(weightContainer);
        dataContainer.add(heightContainer);

        JLabel resultsLabel = new JLabel("Results");
        resultsLabel.setFont(new Font(weightLabel.getName(), Font.PLAIN, 25));

        bmiLabel = new JLabel("0");
        bmiLabel.setFont(new Font(weightLabel.getName(), Font.PLAIN, 30));
        bmiLabel.setAlignmentX(CENTER_ALIGNMENT);

        weightRangeLabel = new JLabel("Weight Range");
        weightRangeLabel.setFont(new Font(weightLabel.getName(), Font.PLAIN, 25));

        XPanel resultsContainer = new XPanel();
        resultsContainer.setLayout(new BoxLayout(resultsContainer, BoxLayout.Y_AXIS));
        resultsContainer.setTransparent(true);

        resultsContainer.add(resultsLabel);
        resultsContainer.add(bmiLabel);
        resultsContainer.add(weightRangeLabel);

        XPanel rootContainer = new XPanel();
        rootContainer.setLayout(new BoxLayout(rootContainer, BoxLayout.X_AXIS));
        rootContainer.setTransparent(true);

        rootContainer.add(dataContainer);
        rootContainer.add(resultsContainer);

        setLayout(new BorderLayout());

        add(rootContainer, BorderLayout.CENTER);

    }

    public double calculateBMI(int weight, int height, boolean updateUI){
        double bmi = 0;

        double weightDouble = (double) weight;
        double heightDouble = (double) height;

        if(height != 0 && weight != 0) bmi = (weightDouble/(heightDouble*heightDouble))*10000;

        if(updateUI == true) {
            bmiLabel.setText(df.format(bmi));
            determineWeightRange(bmi);
        }

        return Double.parseDouble(df.format(bmi));
    }

    private String determineWeightRange(double bmi){
        String weightRange = "You Are ";

        if(bmi <= 18.5){
            weightRange += "Underweight";
        }else if(bmi > 18.5 && bmi <= 24.9){
            weightRange += "Normal Weight";
        }else if(bmi > 24.9 && bmi <= 29.9){
            weightRange += "Overweight";
        }else if(bmi > 29.9){
            weightRange += "Obese";
        }

        weightRangeLabel.setText(weightRange);

        return weightRange;
    }
}
