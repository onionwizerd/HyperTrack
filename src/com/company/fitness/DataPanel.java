package com.company.fitness;

import SwingX.XPanel;
import com.company.PanelModel;
import com.company.database.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Josh on 4/30/2016.
 */
public class DataPanel extends XPanel implements PanelModel{

    String tableName = "";
    DefaultTableModel dataModel;

    public DataPanel(String tableName) {
        this.tableName = tableName;
        init();
    }

    @Override
    public void init() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JTable dataTable = new JTable();

        dataModel = (DefaultTableModel)dataTable.getModel();

        String columnHeaders[] = {"Date", "Distance", "Time", "Speed"};
        dataModel.setColumnIdentifiers(columnHeaders);

        Object rowData[] = new Object[4];

        try {

            Connection connection = DatabaseManager.getInstance().getConnectionManager().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                rowData[0] = resultSet.getDate("date");
                rowData[1] = resultSet.getInt("distance");
                rowData[2] = resultSet.getTime("time");
                rowData[3] = resultSet.getInt("speed");

                dataModel.addRow(rowData);
            }

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }catch (Exception excep){
            excep.printStackTrace();
        }

        add(dataTable, BorderLayout.CENTER);

    }

    private int getRecordCount(){

        int recordCount = 0;

        try {

            Connection connection = DatabaseManager.getInstance().getConnectionManager().getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS rowCount FROM Running");

            ResultSet rs = ps.executeQuery();

            System.out.println("Recourd count = "+  rs.getInt(1));

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }catch (Exception excep){
            excep.printStackTrace();
        }

        return recordCount;
    }
}
