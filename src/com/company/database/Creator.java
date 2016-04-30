package com.company.database;

import javax.swing.*;
import java.sql.*;

/**
 * Created by Josh on 4/30/2016.
 */
public class Creator {

    private Connection connection;

    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private String protocol = "jdbc:derby:";
    private String databaseName = "usr\\database";

    public Creator() {

        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(protocol + databaseName + ";create=true");
            System.out.println("Established connection to " + databaseName);

            connection.setAutoCommit(false);
            System.out.println("Auto commit set to false");

            PreparedStatement ps1 = connection.prepareStatement("create table Running(id int, date date, distance int, time time, speed int)");
            ps1.execute();



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
