package com.company.fitness;

import com.company.PanelModel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Josh on 4/30/2016.
 */
public class GraphItem extends JXPanel implements PanelModel{

    private String chartTitle;
    private DefaultCategoryDataset dataset;
    private String xAxisLabel;
    private String yAxisLabel;

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

        Border border = this.getBorder();
        Border margin = new EmptyBorder(10,30,10,30);
        setBorder(new CompoundBorder(border, margin));

        JFreeChart chart = ChartFactory.createLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.VERTICAL, false, false, false);
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

        JPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.WHITE);

        add(chartPanel, BorderLayout.CENTER);


    }


}
