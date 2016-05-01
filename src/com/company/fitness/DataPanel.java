package com.company.fitness;

import SwingX.XPanel;
import SwingX.XScrollPanel;
import com.company.PanelModel;
import com.company.database.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Josh on 4/30/2016.
 */
public class DataPanel extends XScrollPanel implements PanelModel{

    String tableName = "";
    DefaultTableModel dataModel;

    public DataPanel(String tableName) {
        this.tableName = tableName;
        init();
    }

    @Override
    public void init() {
        setBackground(Color.WHITE);

        JTable dataTable = new JTable();
        dataTable.setBackground(Color.WHITE);

        dataTable.getTableHeader().setBackground(Color.WHITE);


        dataModel = (DefaultTableModel)dataTable.getModel();

        Object columnHeaders[] = new Object[]{"Date", "Distance", "Time", "Speed"};
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

        setViewportView(dataTable);

    }

    public DefaultTableModel getDataModel() {
        return dataModel;
    }
}
