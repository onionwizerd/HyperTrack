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

package com.company.tools;

import SwingX.components.XPanel;
import SwingX.components.XScrollPanel;
import com.company.PanelModel;
import com.company.tools.calculators.BMICalculator;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class ToolPanel extends XScrollPanel implements PanelModel{

    private static ToolPanel toolPanel = new ToolPanel();

    XPanel rootPanel;
    XPanel centerPanel;

    JTree toolTree;
    DefaultMutableTreeNode calculatorsNode;

    ArrayList<File> plugins = null;

    public ToolPanel() {
        super("Calculator Panel");
        init();
    }
    
    public static ToolPanel getInstance(){
        return toolPanel;
    }

    @Override
    public void init() {

        PlugInLoader plugInLoader = new PlugInLoader("plugins/tools");
        plugins = plugInLoader.getPlugins();

        calculatorsNode = new DefaultMutableTreeNode("Calculators");
        //calculatorsNode.insert(bmiNode, calculatorsNode.getChildCount());

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Tools");
        rootNode.insert(calculatorsNode, 0);

        toolTree = new JTree(rootNode);
        toolTree.setBackground(Color.WHITE);
        toolTree.addTreeSelectionListener(tse -> toolTreeValueChanged(tse));

        centerPanel = new XPanel();
        centerPanel.setTransparent(true);

        rootPanel = new XPanel();
        rootPanel.setBackground(Color.WHITE);
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(centerPanel, BorderLayout.CENTER);
        rootPanel.add(toolTree, BorderLayout.WEST);

        populateTree();

        setViewportView(rootPanel);
    }

    private void populateTree(){

        for(int i = 0; i <= plugins.size()-1; i++){

            String name = "";
            String category = "";
            File pluginFile = null;

            try {

                URLClassLoader child = new URLClassLoader (new URL[]{plugins.get(i).toURI().toURL()},
                        ToolPanel.class.getClassLoader());
                Class classToLoad = Class.forName ("plugin.Tool", true, child);
                Method getName = classToLoad.getDeclaredMethod ("getName");
                Method getCategory = classToLoad.getDeclaredMethod ("getCategory");
                Object instance = classToLoad.newInstance ();
                name = (String) getName.invoke(instance);
                category = (String) getCategory.invoke(instance);

                pluginFile = plugins.get(i);

                System.out.println("Plug-in name: " + name);
                System.out.println("Plug-in category: " + category);
                System.out.println("\n");

            }catch(Exception e){
                e.printStackTrace();
            }

            ToolTreeNode pluginNode = new ToolTreeNode(name);
            pluginNode.setJarFile(pluginFile);

            switch (category){
                case "calculator":
                    calculatorsNode.add(pluginNode);
                    break;

            }

        }

    }

    private void toolTreeValueChanged(TreeSelectionEvent tse) {
        String node = tse.getNewLeadSelectionPath().getLastPathComponent().toString();

        if(toolTree.getLastSelectedPathComponent() instanceof ToolTreeNode){

            ToolTreeNode selectedNode = (ToolTreeNode) toolTree.getLastSelectedPathComponent();
            System.out.println(selectedNode.getJarFile());

            try {
                URLClassLoader child = new URLClassLoader (new URL[]{selectedNode.getJarFile().toURI().toURL()},
                        ToolPanel.class.getClassLoader());
                Class classToLoad = Class.forName ("plugin.Tool", true, child);
                Method getUI = classToLoad.getDeclaredMethod ("getUI");
                Object instance = classToLoad.newInstance ();

                centerPanel = (XPanel) getUI.invoke(instance);
                rootPanel.remove(centerPanel);
                rootPanel.add(centerPanel, BorderLayout.CENTER);
                rootPanel.refresh();

            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }
}
