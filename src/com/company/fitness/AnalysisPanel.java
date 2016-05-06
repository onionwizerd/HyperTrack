package com.company.fitness;

import SwingX.components.XDivider;
import SwingX.components.XPanel;
import SwingX.components.XScrollPanel;
import com.company.PanelModel;
import com.company.data.CategoryType;
import com.company.graph.GraphFactory;
import com.company.graph.GraphItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Josh on 4/30/2016.
 */
public class AnalysisPanel extends XScrollPanel implements PanelModel{


    private XPanel viewPortPanel;
    private String tableName;

    private GraphFactory graphFactory;

    private GraphItem distanceGraph;
    private GraphItem timeGraph;
    private GraphItem speedGraph;

    private XPanel statisticsPanel;

    private ArrayList<GraphItem> graphItems = new ArrayList<>();

    public AnalysisPanel(String tableName) {
        this.tableName = tableName;
        init();
    }

    @Override
    public void init() {

        setBackground(Color.WHITE);

        viewPortPanel = new XPanel();
        viewPortPanel.setLayout(new BoxLayout(viewPortPanel, BoxLayout.Y_AXIS));

        graphFactory = new GraphFactory(tableName);

        distanceGraph = graphFactory.createGraphItem("Distance", "distance", CategoryType.DOUBLE ,"Date (YYYY-MM-DD)",
                "Distance (Km)");
        graphItems.add(distanceGraph);
        timeGraph = graphFactory.createGraphItem("Time", "time", CategoryType.TIME, "Date (YYYY-MM-DD)",
                "Time (Hours)");
        graphItems.add(timeGraph);
        speedGraph = graphFactory.createGraphItem("Speed", "speed", CategoryType.DOUBLE, "Date (YYYY-MM-DD)",
                "Speed (Km/h)");
        graphItems.add(speedGraph);


        statisticsPanel = new XPanel();
        statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.Y_AXIS));
        statisticsPanel.setMaximumSize(new Dimension(830, 10000));

        populateStatisticsPanel();

        viewPortPanel.add(statisticsPanel);
        viewPortPanel.add(distanceGraph);
        viewPortPanel.add(timeGraph);
        viewPortPanel.add(speedGraph);

        setViewportView(viewPortPanel);

    }

    public void updateGraphs(){
        /*
        for(GraphItem graphItem : graphItems){
            graphFactory.updateGraphItem(graphItem, graphItem.getCategory(), graphItem.getCategoryType());
        }*/

        graphFactory.updateGraphItem(distanceGraph, "distance", CategoryType.INT);
        graphFactory.updateGraphItem(timeGraph, "time", CategoryType.TIME);
        graphFactory.updateGraphItem(speedGraph, "speed", CategoryType.INT);

        statisticsPanel.removeAll();
        populateStatisticsPanel();



    }

    private void populateStatisticsPanel(){

        statisticsPanel.add(new XDivider(0, 5));
        statisticsPanel.add(new StatisticPanel("Total Sessions", distanceGraph.getCount(), null));
        statisticsPanel.add(new XDivider(0, 5));
        statisticsPanel.add(new StatisticPanel("Total Distance", distanceGraph.getTotal(), "Km"));
        statisticsPanel.add(new XDivider(0, 5));
        statisticsPanel.add(new StatisticPanel("Average Distance Per Day", distanceGraph.getAverage(), "Km"));
        statisticsPanel.add(new XDivider(0, 5));
        statisticsPanel.add(new StatisticPanel("Average Distance Per Week", distanceGraph.getWeeklyAverage(), "Km"));
        statisticsPanel.add(new XDivider(0, 5));
        statisticsPanel.add(new StatisticPanel("Total Time", timeGraph.getTotal(), "Hours"));
        statisticsPanel.add(new XDivider(0, 5));
        statisticsPanel.add(new StatisticPanel("Average Time Per Day", timeGraph.getAverage(), "Hours"));
        statisticsPanel.add(new XDivider(0, 5));
        statisticsPanel.add(new StatisticPanel("Average Time Per Week", timeGraph.getWeeklyAverage(), "Hours"));
        statisticsPanel.add(new XDivider(0, 5));
        statisticsPanel.add(new StatisticPanel("Average Speed", speedGraph.getAverage(), "Km/h"));
        statisticsPanel.add(new XDivider(0, 5));

        statisticsPanel.refresh();
    }
}
