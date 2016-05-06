package com.company.fitness;

import SwingX.components.XButton;
import SwingX.components.XDivider;
import SwingX.components.XPanel;
import SwingX.components.XScrollPanel;
import SwingX.components.table.XTable;
import com.company.PanelModel;
import com.company.data.dataentry.DataEntryFrame;
import com.company.data.Format;
import com.company.data.RecordContextMenu;
import com.company.database.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

/**
 * Created by Josh on 4/30/2016.
 */
public class DataPanel extends XPanel implements PanelModel{

    private DefaultTableModel dataModel;
    private XTable dataTable;

    private String tableName = "";
    private int latestID = 0;
    private Connection connection;

    private Format formatter = new Format();

    public DataPanel(String tableName) {
        this.tableName = tableName;
        init();
    }

    @Override
    public void init() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        dataTable = new XTable();
        dataTable.setBackground(Color.WHITE);
        dataTable.getTableHeader().setBackground(Color.WHITE);
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getButton() == MouseEvent.BUTTON3){
                    System.out.println(dataTable.rowAtPoint(mouseEvent.getPoint()));
                    RecordContextMenu recordContextMenu = new RecordContextMenu();
                    recordContextMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                    recordContextMenu.getEditMenuItem().addActionListener(e1 -> {

                        int row = dataTable.rowAtPoint(mouseEvent.getPoint());

                        int[] date = formatter.formatDate(dataModel.getValueAt(row, 0).toString());
                        int[] distance = formatter.formatDistance(dataModel.getValueAt(row, 1).toString());
                        int[] time = formatter.formatTime(dataModel.getValueAt(row, 2).toString());
                        int id = Integer.parseInt(dataModel.getValueAt(row, 4).toString());
                        System.out.println("ID = " + id);

                        DataEntryFrame dataEntryFrame = new DataEntryFrame();
                        dataEntryFrame.setDate(date);
                        dataEntryFrame.setDistance(distance[0], distance[1]);
                        dataEntryFrame.setTime(time);


                        Object[] rowData = dataEntryFrame.showAll(id, true);
                        System.out.println("ID = " + rowData[0]);
                        // Data Integrity Check
                        boolean dataIntegrity = true;
                        for (int i = 0; i <= (rowData.length-1); i++){
                            if(rowData[i] == null) dataIntegrity = false;
                        }

                        if(dataIntegrity){
                            updateRecord(rowData);
                        }

                    });
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        dataModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dataTable.setModel(dataModel);

        Object columnHeaders[] = new Object[]{"Date (YYYY-MM-DD)", "Distance (KM)", "Time (HH:MM:SS)",
                "Speed (KM/H)", "ID"};
        dataModel.setColumnIdentifiers(columnHeaders);

        populateDataModel();

        // Make ID column invisible
        TableColumnModel columnModel = dataTable.getColumnModel();
        columnModel.removeColumn(columnModel.getColumn(4));

        XScrollPanel tablePanel = new XScrollPanel();
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setViewportView(dataTable);
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        XButton addRecordBtn = new XButton("Add");
        addRecordBtn.setMinimumSize(new Dimension(70, 50));
        addRecordBtn.setMaximumSize(new Dimension(70, 50));
        addRecordBtn.setMargin(new Insets(0,10,0,10));
        addRecordBtn.setBackground(Color.WHITE);
        addRecordBtn.setHoverEffect(Color.LIGHT_GRAY);
        addRecordBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DataEntryFrame dataEntryFrame = new DataEntryFrame();
                System.out.println("Latest ID = " + latestID);
                Object[] rowData = dataEntryFrame.showAll(latestID, false);

                // Data Integrity Check
                boolean dataIntegrity = true;
                for (int i = 0; i <= (rowData.length-1); i++){
                    if(rowData[i] == null) dataIntegrity = false;
                }

                if(dataIntegrity){
                    latestID = Integer.parseInt(rowData[0].toString());
                    insertRecord(rowData);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        XButton deleteRecordBtn = new XButton("Delete");
        deleteRecordBtn.setMinimumSize(new Dimension(70, 50));
        deleteRecordBtn.setMaximumSize(new Dimension(70, 50));
        deleteRecordBtn.setMargin(new Insets(0,10,0,10));
        deleteRecordBtn.setBackground(Color.WHITE);
        deleteRecordBtn.setHoverEffect(Color.LIGHT_GRAY);
        deleteRecordBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int[] rows = dataTable.getSelectedRows();

                if(rows.length == 0){
                    JOptionPane.showMessageDialog(null, "No records are selected");
                }else if(JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently delete " +
                            rows.length + " selected record(s)") == 0){

                        for(int i = 0; i < rows.length; i++){
                            deleteRecord(Integer.parseInt(dataModel.getValueAt(dataTable.getSelectedRow(), 4)
                                    .toString()));
                            System.out.println("removed row");
                            dataModel.removeRow(dataTable.getSelectedRow());

                        }

                        dataTable.revalidate();
                        dataTable.repaint();
                    }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        XPanel buttonPanel = new XPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(new XDivider(5, 0));
        buttonPanel.add(addRecordBtn);
        buttonPanel.add(new XDivider(5, 0));
        buttonPanel.add(deleteRecordBtn);

        XPanel northPanel = new XPanel();
        northPanel.setLayout(new BoxLayout( northPanel, BoxLayout.Y_AXIS));
        northPanel.add(new XDivider(0,1));
        northPanel.add(buttonPanel);
        northPanel.add(new XDivider(0,1));


        add(northPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

    }

    public DefaultTableModel getDataModel() {
        return dataModel;
    }

    private void populateDataModel(){

        dataModel.setRowCount(0);
        dataTable.revalidate();
        dataTable.repaint();

        Object rowData[] = new Object[5];
        try {

            connection = DatabaseManager.getInstance().getConnectionManager().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName
                    + " ORDER BY date DESC");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                rowData[0] = resultSet.getDate("date");
                rowData[1] = resultSet.getDouble("distance");
                rowData[2] = resultSet.getTime("time");
                rowData[3] = resultSet.getDouble("speed");
                rowData[4] = resultSet.getInt("id");

                if(resultSet.getInt("id") > latestID) latestID = resultSet.getInt("id");

                dataModel.addRow(rowData);
            }

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }
    }

    private void insertRecord(Object[] rowData){
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + tableName
                    +" VALUES ("
                    + rowData[0] + ", " // id
                    + "'" + rowData[1] + "'" + ", " // date
                    + rowData[2] + ", " // distance
                    + "'" + rowData[3] + "'" + ", " // time
                    + rowData[4] // speed
                    +")");

            preparedStatement.executeUpdate();

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
            displayErrorMessage("Unable to insert record \n SQLException");
        }catch (Exception excep){
            excep.printStackTrace();
            displayErrorMessage("Unable to insert record \n Exception");
        }

        populateDataModel();
        FitnessPanel fitnessPanel = (FitnessPanel) this.getParent();
        fitnessPanel.update();

    }

    private void updateRecord(Object[] rowData){
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + tableName
                    +" SET "
                    + "date=" + "'" + rowData[1] + "'" + ", " // date
                    + "distance=" + rowData[2] + ", " // distance
                    + "time=" + "'" + rowData[3] + "'" + ", " // time
                    + "speed=" + rowData[4] // speed
                    + " WHERE id = " + rowData[0]);

            preparedStatement.executeUpdate();

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
            displayErrorMessage("Unable to update record \n SQLException");
        }catch (Exception excep){
            excep.printStackTrace();
            displayErrorMessage("Unable to update record \n Exception");
        }

        populateDataModel();
        FitnessPanel fitnessPanel = (FitnessPanel) this.getParent();
        fitnessPanel.update();

    }

    private void deleteRecord(int id){
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + tableName
                    +" WHERE id=" + id);
            preparedStatement.execute();

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
            displayErrorMessage("Unable to delete record \n SQLException");
        }catch (Exception excep){
            excep.printStackTrace();
            displayErrorMessage("Unable to delete record \n Exception");
        }

        populateDataModel();
        FitnessPanel fitnessPanel = (FitnessPanel) this.getParent();
        fitnessPanel.update();

    }

    private void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(null, errorMessage);
    }

}
