package com.company.fitness;

import com.company.database.DatabaseManager;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.*;

/**
 * Created by Josh on 4/30/2016.
 */
public class GraphFactory {

    private String tableName;

    public GraphFactory(String tableName) {
        this.tableName = tableName;
    }

    public GraphItem createGraphItem(String chartTitle, String category, CategoryType categoryType,
                                     String xAxisLabel, String yAxisLabel){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {

            Connection connection = DatabaseManager.getInstance().getConnectionManager().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tableName
                    + " ORDER BY date");

            ResultSet resultSet = preparedStatement.executeQuery();

            switch (categoryType){
                case INT:
                    while (resultSet.next()){
                        dataset.addValue(resultSet.getInt(category), "Series1", resultSet.getDate("date"));
                        //System.out.println(resultSet.getInt(category) + " - " + resultSet.getDate("date"));
                    }
                    break;
                case TIME:
                    Time time;
                    double hours;
                    double minutes;
                    double formattedTime;
                    while (resultSet.next()){
                        time = resultSet.getTime(category);
                        hours = time.getHours();
                        minutes = time.getMinutes();
                        formattedTime = hours + (minutes/100);
                        dataset.addValue(formattedTime, "Series1", resultSet.getDate("date"));
                    }
                    break;
            }

        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }catch (Exception excep){
            excep.printStackTrace();
        }

        return new GraphItem(chartTitle, dataset, xAxisLabel, yAxisLabel);
    }
}
