/**
 *
 * @author Josh Beaver
 * @version 0.1
 * @since 2016-04-05
 *
 * <h1>Calculator Panel</h1>
 * <p></p>
 *
 * <h2>Notes</h2>
 * <p>Design Pattern: Singleton</p>
 *
 */

package com.company.calculators;

import SwingX.components.XPanel;
import SwingX.components.XScrollPanel;
import com.company.PanelModel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class CalculatorPanel extends XScrollPanel implements PanelModel{

    private static CalculatorPanel calculatorPanel = new CalculatorPanel();

    XPanel rootPanel;
    XPanel centerPanel;
    
    public CalculatorPanel() {
        super("Calculator Panel");
        init();
    }
    
    public static CalculatorPanel getInstance(){
        return calculatorPanel;
    }

    public void calcTreeValueChanged(TreeSelectionEvent tse) {
        String node = tse.getNewLeadSelectionPath().getLastPathComponent().toString();

        switch (node){
            case "BMI":
                centerPanel = new BMICalculator();
                rootPanel.remove(centerPanel);
                rootPanel.add(centerPanel, BorderLayout.CENTER);
                rootPanel.refresh();
                break;
        }
    }


    @Override
    public void init() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Calculators");

        DefaultMutableTreeNode bmiNode = new DefaultMutableTreeNode("BMI");
        rootNode.insert(bmiNode, 0);

        JTree calcTree = new JTree(rootNode);
        calcTree.setBackground(Color.WHITE);
        calcTree.addTreeSelectionListener(tse -> calcTreeValueChanged(tse));

        centerPanel = new XPanel();
        centerPanel.setTransparent(true);

        rootPanel = new XPanel();
        rootPanel.setBackground(Color.WHITE);
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(centerPanel, BorderLayout.CENTER);
        rootPanel.add(calcTree, BorderLayout.WEST);

        setViewportView(rootPanel);
    }
}
