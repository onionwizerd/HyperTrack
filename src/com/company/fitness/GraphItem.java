package com.company.fitness;

import com.company.PanelModel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
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
    private  XYDataset dataset;
    private String xAxisLabel;
    private String yAxisLabel;

    public GraphItem(String chartTitle, XYDataset dataset, String xAxisLabel, String yAxisLabel) {
        this.dataset = dataset;
        this.chartTitle = chartTitle;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
    }

    @Override
    public void init() {

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(200, 600));
        setMaximumSize(new Dimension(600, 600));
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

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset,
                PlotOrientation.HORIZONTAL, true, false, false);


        JPanel chartPanel = new ChartPanel(chart);

        add(chartPanel, BorderLayout.CENTER);


    }
}
