package com.company.graph;

import com.company.PanelModel;
import com.company.fitness.data.CategoryType;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by Josh on 4/30/2016.
 */
public class GraphItem extends JXPanel implements PanelModel{

    private String chartTitle;
    private DefaultCategoryDataset dataset;
    private String xAxisLabel;
    private String yAxisLabel;

    private JFreeChart chart;
    private JPanel chartPanel;

    private String category;
    private CategoryType categoryType;

    private double total;
    private double count;


    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public GraphItem(String chartTitle, DefaultCategoryDataset dataset, String xAxisLabel, String yAxisLabel) {
        this.dataset = dataset;
        this.chartTitle = chartTitle;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        init();
    }

    @Override
    public void init() {

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(850, 400));
        setMaximumSize(new Dimension(850, 400));
        setBackground(Color.WHITE);
        setAlignmentX(CENTER_ALIGNMENT);

        //Border
        DropShadowBorder dropShadow = new DropShadowBorder();
        dropShadow.setShadowColor(Color.BLACK);
        dropShadow.setShowLeftShadow(true);
        dropShadow.setShowRightShadow(true);
        dropShadow.setShowBottomShadow(true);
        dropShadow.setShowTopShadow(true);
        setBorder(dropShadow);

        chart = ChartFactory.createLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, false, false, false);
        setChartUI();

        chartPanel = new ChartPanel(chart);

        chartPanel.setBackground(Color.WHITE);

        add(chartPanel, BorderLayout.CENTER);

    }

    public void update(DefaultCategoryDataset dataset){
        chart = ChartFactory.createLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, false, false, false);
        setChartUI();

        remove(chartPanel);
        chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.revalidate();
        chartPanel.repaint();
    }

    private void setChartUI(){
        chart.setBackgroundPaint(Color.WHITE);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.DARK_GRAY);

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
    }

    public double getCount() {
        return Double.parseDouble(decimalFormat.format(count));
    }

    public double getTotal() {
        return Double.parseDouble(decimalFormat.format(total));
    }

    public double getAverage(){

        double average = 0;
        if(count > 0){
            average = Double.parseDouble(decimalFormat.format(total/count));
        }

        return average;
    }

    public double getWeeklyAverage(){

        double weeklyAverage = 0;

        if(count > 0){
            weeklyAverage = Double.parseDouble(decimalFormat.format(total/((double)dataset.getColumnCount()/7)));
        }

        return weeklyAverage;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
