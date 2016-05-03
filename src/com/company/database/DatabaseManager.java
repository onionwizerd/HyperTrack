package com.company.database;

import java.sql.*;
import java.sql.Connection;

/**
 *
 * @author Josh Beaver
 * @version 0.1
 * @since 2016-04-28
 *
 * <h1>Database Manager</h1>
 * <p></p>
 *
 * <h2>Notes</h2>
 * <p>Design Pattern: Singleton</p>
 *
 */

public class DatabaseManager implements Runnable{

    private static DatabaseManager databaseManager = new DatabaseManager();

    ConnectionManager connectionManager;
    private Connection connection;

    private String framework = "embedded";
    private String protocol = "jdbc:derby:";
    private String databaseName = "usr\\database";

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance(){
        return databaseManager;
    }

    public void connect(){
        connectionManager = new ConnectionManager();
        connectionManager.connect();
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }


    @Override
    public void run() {

    }
}
