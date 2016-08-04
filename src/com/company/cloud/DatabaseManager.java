package com.company.cloud;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by josh on 2016/07/30.
 */

public class DatabaseManager implements Runnable{

    private Connection connection;
    private static DatabaseManager databaseManager = new DatabaseManager();

    private String databaseName = "hypertrack.cfn2sbobuv1c.us-west-2.rds.amazonaws.com:3306/hypertrack";

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance(){
        return databaseManager;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect(){

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://" + databaseName,
                    "user", "RvX,n?VEi2A799L");
            System.out.println("Established connection to " + databaseName);

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "SQL Exception");
        }catch (Exception excep){
            excep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "Exception");
        }
    }

    private void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(null, errorMessage);
    }

    @Override
    public void run() {
        try {

            System.out.println("Running");
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://" + databaseName,
                    "user", "RvX,n?VEi2A799L");
            System.out.println("Established connection to " + databaseName);

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "SQL Exception");
        }catch (Exception excep){
            excep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "Exception");
        }

        LoginPanel loginPanel = LoginPanel.getInstance();
        loginPanel.init();

    }

}
