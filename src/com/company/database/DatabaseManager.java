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

    public void test(){

        while (connectionManager.getConnection() == null){
            //Waiting for connection
        }
        connection = connectionManager.getConnection();


        try {
            PreparedStatement ps1 = connection.prepareStatement("create table tabel1(num int, string varchar(40))");
            ps1.execute();

            PreparedStatement p2 = connection.prepareStatement("insert into tabel1 values (1956,'Webster St.')");
            p2.executeUpdate();


            PreparedStatement ps = connection.prepareStatement("select * from tabel1");

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("num"));
            }


        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }

    }

    @Override
    public void run() {

    }
}
