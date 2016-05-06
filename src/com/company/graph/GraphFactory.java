package com.company.graph;

import com.company.database.DatabaseManager;
import com.company.data.CategoryType;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.*;
import java.text.DecimalFormat;

/**
 * Created by Josh on 4/30/2016.
 */
public class GraphFactory {

    private String tableName;
    private DefaultCategoryDataset dataset;

    private double total = 0;
    private double count = 0;

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public GraphFactory(String tableName) {
        this.tableName = tableName;

        try {
            connection = DatabaseManager.getInstance().getConnectionManager().getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName
                    + " ORDER BY date");

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }catch (Exception excep){
            excep.printStackTrace();
        }
    }

    public GraphItem createGraphItem(String chartTitle, String category, CategoryType categoryType,
                                     String xAxisLabel, String yAxisLabel){

        dataset = new DefaultCategoryDataset();

        total = 0;
        count = 0;

        try {

            resultSet = preparedStatement.executeQuery();

            switch (categoryType){
                case INT:
                    while (resultSet.next()){
                        addValue(resultSet.getInt(category), "Series1", resultSet.getDate("date"));
                    }
                    break;
                case DOUBLE:
                    while (resultSet.next()){
                        addValue(resultSet.getDouble(category), "Series1", resultSet.getDate("date"));
                    }
                    break;
                case TIME:
                    while (resultSet.next()){
                        addValue(formatTime(resultSet.getTime(category)), "Series1",
                                resultSet.getDate("date").toString());
                    }
                    break;
            }

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }catch (Exception excep){
            excep.printStackTrace();
        }

        GraphItem graphItem = new GraphItem(chartTitle, dataset, xAxisLabel, yAxisLabel);
        graphItem.setTotal(total);
        graphItem.setCount(count);
        graphItem.setCategoryType(categoryType);

        return graphItem;
    }

    public void updateGraphItem(GraphItem graphItem, String category, CategoryType categoryType){

        dataset = new DefaultCategoryDataset();

        total = 0;
        count = 0;

        try {

            resultSet = preparedStatement.executeQuery();

            switch (categoryType){
                case INT:
                    while (resultSet.next()){
                        addValue(resultSet.getInt(category), "Series1", (resultSet.getDate("date")));
                    }
                    break;
                case DOUBLE:
                    while (resultSet.next()){
                        addValue(resultSet.getDouble(category), "Series1", resultSet.getDate("date"));
                    }
                    break;
                case TIME:
                    while (resultSet.next()){
                        addValue(formatTime(resultSet.getTime(category)), "Series1",
                                resultSet.getDate("date").toString());
                    }
                    break;
            }

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }catch (Exception excep){
            excep.printStackTrace();
        }

        graphItem.update(dataset);


    }

    private void addValue(double value, Comparable rowKey, Comparable columnKey){
        dataset.addValue(value, rowKey, columnKey);
        total += value;
        count++;
    }

    private double formatTime(Time time){
        double formattedTime = 0;
        String timeString = time.toString();
        DecimalFormat df = new DecimalFormat("#.0000");

        double hours = Double.parseDouble(timeString.substring(0, timeString.indexOf(":")));
        double minutes = Double.parseDouble(timeString.substring((timeString.indexOf(":")+1),
                timeString.lastIndexOf(":")));
        double seconds = Double.parseDouble(timeString.substring((timeString.lastIndexOf(":")+1),
                timeString.length()));
        formattedTime = (hours + (minutes/60) + ((seconds/60)/60));

        formattedTime = Double.parseDouble(df.format(formattedTime));

        return formattedTime;
    }

}
