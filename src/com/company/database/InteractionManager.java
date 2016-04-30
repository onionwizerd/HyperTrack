package com.company.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Josh on 4/30/2016.
 */
public class InteractionManager implements Runnable{

    private ConnectionManager connectionManager = DatabaseManager.getInstance().getConnectionManager();
    private Connection connection;

    private String statement;
    private InteractionType interactionType;

    private ResultSet resultSet;

    public InteractionManager() {
    }

    public InteractionManager(String statement, InteractionType interactionType) {
        this.statement = statement;
        this.interactionType = interactionType;
    }

    public ResultSet executeQuery(String statement){
        this.statement = statement;

        //InteractionManager interactionManager = new InteractionManager();
        Thread interactionThread = new Thread(this);
        interactionThread.start();

        return resultSet;
    }


    @Override
    public void run() {

        while (connectionManager.getConnection() == null){
            //Waiting for connection
        }
        if (connectionManager.getConnection() == null){
            System.out.println("Connection == null");
        }else if (connectionManager.getConnection() != null){
            System.out.println("Connection != null");
        }
        connection = connectionManager.getConnection();

        try {
            PreparedStatement ps1 = connection.prepareStatement("create table tabel1(num int, string varchar(40))");
            ps1.execute();

            PreparedStatement p2 = connection.prepareStatement("insert into tabel1 values (1956,'Webster St.')");
            p2.executeUpdate();


            PreparedStatement ps = connection.prepareStatement("select * from tabel1");

            /*
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("num"));
            }*/


            resultSet = ps.executeQuery();

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }

    }
}
