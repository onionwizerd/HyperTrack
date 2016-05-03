package com.company.data;

import SwingX.XButton;
import SwingX.XDivider;
import SwingX.XPanel;
import com.company.Main;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;

/**
 * Created by Josh on 5/1/2016.
 */
public class DataEntryFrame extends JDialog{

    private JSpinner kilometersSpinner;
    private JSpinner metersSpinner;
    private JSpinner hoursSpinner;
    private JSpinner minutesSpinner;
    private JSpinner secondsSpinner;
    private JTextField speedField;

    private Object[] rowData;
    private UtilDateModel dateModel;

    // Edit fields
    private int[] date = new int[3];
    private int kilometers;
    private int meters;
    private int[] time;

    public DataEntryFrame(){
        super(Main.getMainFrame(), "Enter", true);
    }

    public Object[] showAll(int id, boolean edit){
        rowData = new Object[5];

        BufferedImage bufferedImageIcon = null;
        try {
            bufferedImageIcon = ImageIO.read(Main.class.getResource
                    ("img/icon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(375, 200));
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setIconImage(bufferedImageIcon);
        setTitle("Enter New Record");

        // Date Entry
        JLabel dateLabel = new JLabel("Date:                  ");
        dateLabel.setFont(new Font(dateLabel.getName(), Font.PLAIN, 16));

        JLabel dateUnitLabel = new JLabel("                          ");
        dateUnitLabel.setFont(new Font(dateLabel.getName(), Font.PLAIN, 16));

        dateModel = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePickerPanel = new JDatePanelImpl(dateModel, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePickerPanel, new DateLabelFormatter());
        datePicker.setMaximumSize(new Dimension(150, 28));

        XPanel datePanel = new XPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        datePanel.add(dateLabel);
        datePanel.add(datePicker);
        datePanel.add(dateUnitLabel);

        // Distance Entry
        JLabel distanceLabel = new JLabel("Distance:           ");
        distanceLabel.setFont(new Font(distanceLabel.getName(), Font.PLAIN, 16));

        JLabel distanceUnitLabel = new JLabel("  (Km.M)                ");
        distanceUnitLabel.setFont(new Font(dateLabel.getName(), Font.PLAIN, 16));

        SpinnerNumberModel  kilometersSpinnerModel = new SpinnerNumberModel(0, 0,   9999,  1);
        kilometersSpinner = new JSpinner(kilometersSpinnerModel);
        kilometersSpinner.setBackground(Color.WHITE);
        kilometersSpinner.setMaximumSize(new Dimension(75, 28));
        kilometersSpinner.setFont(new Font(kilometersSpinner.getName(), Font.PLAIN, 16));
        kilometersSpinner.addChangeListener(e -> {calculateSpeed();});

        SpinnerNumberModel  metersSpinnerModel = new SpinnerNumberModel(0, 0,   1000,  1);
        metersSpinner = new JSpinner(metersSpinnerModel);
        metersSpinner.setBackground(Color.WHITE);
        metersSpinner.setMaximumSize(new Dimension(50, 28));
        metersSpinner.setFont(new Font(metersSpinner.getName(), Font.PLAIN, 16));
        metersSpinner.addChangeListener(e -> {
            if(Integer.parseInt(metersSpinner.getValue().toString()) == 1000){
                kilometersSpinner.setValue(Integer.parseInt(kilometersSpinner.getValue().toString()) + 1);
                metersSpinner.setValue(0);
            }
            calculateSpeed();
        });

        XPanel distancePanel = new XPanel();
        distancePanel.setLayout(new BoxLayout(distancePanel, BoxLayout.X_AXIS));
        distancePanel.add(distanceLabel);
        distancePanel.add(kilometersSpinner);
        distancePanel.add(new XDivider(2, 0));
        distancePanel.add(metersSpinner);
        distancePanel.add(distanceUnitLabel);


        // Time Entry
        SpinnerNumberModel hoursSpinnerModel = new SpinnerNumberModel(0, 0,   24,  1);
        hoursSpinner = new JSpinner(hoursSpinnerModel);
        hoursSpinner.setBackground(Color.WHITE);
        hoursSpinner.setMaximumSize(new Dimension(50, 28));
        hoursSpinner.setFont(new Font(hoursSpinner.getName(), Font.PLAIN, 16));
        hoursSpinner.addChangeListener(e -> {calculateSpeed();});

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
            calculateSpeed();
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
            calculateSpeed();
        });

        JLabel timeLabel = new JLabel("Time:                  ");
        timeLabel.setFont(new Font(timeLabel.getName(), Font.PLAIN, 16));

        JLabel timeUnitLabel = new JLabel("  (HH:MM:SS)   ");
        timeUnitLabel.setFont(new Font(timeUnitLabel.getName(), Font.PLAIN, 16));

        XPanel timePanel = new XPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        timePanel.add(timeLabel);
        timePanel.add(hoursSpinner);
        timePanel.add(new XDivider(2, 0));
        timePanel.add(minutesSpinner);
        timePanel.add(new XDivider(2, 0));
        timePanel.add(secondsSpinner);
        timePanel.add(timeUnitLabel);


        // Speed Entry
        JLabel speedLabel = new JLabel("Speed:               ");
        speedLabel.setFont(new Font(speedLabel.getName(), Font.PLAIN, 16));

        JLabel speedUnitLabel = new JLabel("  (Km/h)            ");
        speedUnitLabel.setFont(new Font(speedUnitLabel.getName(), Font.PLAIN, 16));

        speedField = new JTextField();
        speedField.setMaximumSize(new Dimension(150, 28));
        speedField.setEditable(false);

        XPanel speedPanel = new XPanel();
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.X_AXIS));
        speedPanel.add(speedLabel);
        speedPanel.add(speedField);
        speedPanel.add(speedUnitLabel);


        XButton acceptBtn = new XButton("Accept");
        acceptBtn.addActionListener(e -> {

            // Data Integrity Check
            boolean dataIntegrity = true;
            if(Integer.parseInt(kilometersSpinner.getValue().toString()) == 0
                    && Integer.parseInt(metersSpinner.getValue().toString()) == 0){
                dataIntegrity = false;
                JOptionPane.showMessageDialog(null, "Please enter valid distance");
            }
            if(Integer.parseInt(hoursSpinner.getValue().toString()) == 0
                    && Integer.parseInt(minutesSpinner.getValue().toString()) == 0
                    && Integer.parseInt(secondsSpinner.getValue().toString()) == 0){
                dataIntegrity = false;
                JOptionPane.showMessageDialog(null, "Please enter valid time");
            }

            if(dataIntegrity == true){
                // Returns same ID if record is being edited
                if(edit == true){
                    rowData[0] = (id);
                }else {
                    rowData[0] = (id + 1);
                }
                rowData[1]= formatDate();
                rowData[2] = formatDistance();
                rowData[3] = formatTime();
                rowData[4] = Double.parseDouble(speedField.getText());

                dispose();
                return;
            }
        });

        XButton discardBtn = new XButton("Discard");
        discardBtn.addActionListener(e -> {
            dispose();
        });

        XPanel finaliseButtonsPanel = new XPanel();
        finaliseButtonsPanel.setLayout(new BoxLayout(finaliseButtonsPanel, BoxLayout.X_AXIS));

        finaliseButtonsPanel.add(acceptBtn);
        finaliseButtonsPanel.add(new XDivider(5, 0));
        finaliseButtonsPanel.add(discardBtn);

        XPanel contentPanel = new XPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentY(Component.LEFT_ALIGNMENT);

        contentPanel.add(new XDivider(0, 5));
        contentPanel.add(datePanel);
        contentPanel.add(new XDivider(0, 5));
        contentPanel.add(distancePanel);
        contentPanel.add(new XDivider(0, 5));
        contentPanel.add(timePanel);
        contentPanel.add(new XDivider(0, 5));
        contentPanel.add(speedPanel);
        contentPanel.add(new XDivider(0, 5));
        contentPanel.add(finaliseButtonsPanel);

        if(edit == true){
            setTitle("Edit Record");
            dateModel.setDate(date[0], date[1], date[2]);
            kilometersSpinnerModel.setValue(kilometers);
            metersSpinnerModel.setValue(meters);
            hoursSpinnerModel.setValue(time[0]);
            minutesSpinnerModel.setValue(time[1]);
            secondsSpinnerModel.setValue(time[2]);
            calculateSpeed();
        }

        setContentPane(contentPanel);
        setResizable(false);
        setVisible(true);
        toFront();

        return rowData;
    }

    private double calculateSpeed(){
        double speed = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        double hours = Double.parseDouble(hoursSpinner.getValue().toString());
        double minutes = Double.parseDouble(minutesSpinner.getValue().toString());
        double seconds = Double.parseDouble(secondsSpinner.getValue().toString());
        double kilometers = Double.parseDouble(kilometersSpinner.getValue().toString());
        double meters = Double.parseDouble(metersSpinner.getValue().toString());
        double distance = kilometers + (meters/1000);
        speed = distance/(hours + (minutes/60) +  ((seconds/60)/60));

        if(speed < 1){
            speedField.setText("0" + decimalFormat.format(speed));
        }else {
            speedField.setText(decimalFormat.format(speed));
        }

        return speed;
    }

    private String formatDate(){
        String date = "";

        date += dateModel.getYear();
        date += "-";
        int month = dateModel.getMonth()+1; // Month index is given by default (i.e one too low)
        if(month < 10){
            date += ("0" + month);
        }else{
            date += month;
        }
        date += "-";
        if(dateModel.getDay() < 10){
            date += ("0" + dateModel.getDay());
        }else{
            date += dateModel.getDay();
        }

        return date;
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

    private double formatDistance(){
        double distance = 0;

        distance += Double.parseDouble(kilometersSpinner.getValue().toString());
        distance += (Double.parseDouble(metersSpinner.getValue().toString())/1000);

        return distance;
    }

    public void setTime(int hours, int minutes, int seconds) {
        time[0] = hours;
        time[1] = minutes;
        time[2] = seconds;
    }

    public void setTime(int[] time) {
        this.time = time;
    }

    public void setDistance(int kilometers, int meters) {
        this.kilometers = kilometers;
        this.meters = meters;
    }

    public void setDate(int year, int month, int day) {
        date[0] = year;
        date[1] = (month+1);
        date[2] = day;
    }

    public void setDate(int[] date) {
        this.date[0] = date[0];
        this.date[1] = (date[1]-1);
        this.date[2] = date[2];
    }
}
