package com.company.database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Josh on 4/29/2016.
 */
public class ConnectionManager implements Runnable{

    private Connection connection;
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    private String databaseName = "usr\\database";

    public ConnectionManager() {
    }

    public ConnectionManager(String driver, String protocol, String databaseName) {
        this.driver = driver;
        this.protocol = protocol;
        this.databaseName = databaseName;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect(){
        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(protocol + databaseName);
            System.out.println("Established connection to " + databaseName);

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "SQL Exception");
        }catch (ClassNotFoundException cnfExcep){
            cnfExcep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "Class Not Found Exception");
        }catch (Exception excep){
            excep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "Exception");
        }
    }

    // Concurrent connection method
    @Override
    public void run() {
        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(protocol + databaseName);
            System.out.println("Established connection to " + databaseName);

            connection.setAutoCommit(false);
            System.out.println("Auto commit set to false");

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "SQL Exception");
        }catch (ClassNotFoundException cnfExcep){
            cnfExcep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "Class Not Found Exception");
        }catch (Exception excep){
            excep.printStackTrace();
            displayErrorMessage("Could not establish connection to database \n" +
                    "Exception");
        }
    }

    private void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(null, errorMessage);
    }
}
