package com.company.fitness.data.dataentry.components;

import com.company.fitness.data.DateLabelFormatter;
import com.company.fitness.data.dataentry.DataEntryComponent;
import com.company.fitness.data.dataentry.DataEntryComponentModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * Created by Josh on 5/6/2016.
 */
public class DateComponent extends DataEntryComponent implements DataEntryComponentModel {

    private  UtilDateModel dateModel;

    public DateComponent() {

        dateModel = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePickerPanel = new JDatePanelImpl(dateModel, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePickerPanel, new DateLabelFormatter());
        datePicker.setMaximumSize(new Dimension(150, 28));

        add(datePicker);

    }

    @Override
    public Object getValue() {
        return formatDate();
    }

    @Override
    public boolean hasDataIntegrity() {
        boolean dataIntegrity = true;

        if(dateModel.getValue() == null){
            dataIntegrity = false;
            System.out.println("Date Integrity = False");
            JOptionPane.showMessageDialog(null, "Pleas enter a valid date");
        }

        return dataIntegrity;
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

    public void setDate(int[] date){
        dateModel.setYear(date[0]);
        dateModel.setMonth(date[1]);
        dateModel.setDay(date[2]);
    }
}
