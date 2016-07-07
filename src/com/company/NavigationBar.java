
package com.company;

import SwingX.XMain;
import SwingX.components.XButton;
import SwingX.components.XToolBar;
import com.company.tools.ToolPanel;
import com.company.fitness.FitnessPanel;
import com.company.nutrition.NutritionPanel;
import com.company.news.NewsFeedPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 
 * @author Josh Beaver
 * @version 0.1
 * @since 2016-03-16
 * 
 * <h1>Navigation Bar</h1>
 * <p></p>
 * 
 * <h2>Notes</h2>
 * <p>Design Pattern: Singleton</p>
 * 
 */

public class NavigationBar extends XToolBar{

    static NavigationBar navBar = new NavigationBar("Navigation", XToolBar.VERTICAL);

    private NavigationBar(String name, int orientation) {
        super(name, orientation);
        init();
    }
    
    public static NavigationBar getInstance(){
        return navBar;
    }
    
    public void init(){
        setBackground(Color.WHITE);
        setTransparent(false);
        setMargin(new Insets(0,0,0,0));

        BufferedImage newsIcon = null;
        try {
            newsIcon = ImageIO.read(XMain.class.getResource
            ("img/32x32/line_icon_black.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        
        XButton newsFeedBtn = new XButton("");
        newsFeedBtn.setTransparent(true);
        newsFeedBtn.setHoverEffect(Color.LIGHT_GRAY);
        newsFeedBtn.setIcon(new ImageIcon(newsIcon));
        newsFeedBtn.setToolTipText("News Feed");
        newsFeedBtn.addActionListener((ActionEvent evt) -> {
            MainFrame mainFrame = Main.getMainFrame();
            NewsFeedPanel newsFeedPanel = NewsFeedPanel.getInstance();
            mainFrame.setContent(newsFeedPanel);
            Thread newsThread = new Thread(newsFeedPanel);
            newsThread.start();
        });

        BufferedImage toolsIcon = null;
        try {
            toolsIcon = ImageIO.read(Main.class.getResource
                    ("img/tools.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton toolsBtn = new XButton("");
        toolsBtn.setTransparent(true);
        toolsBtn.setHoverEffect(Color.LIGHT_GRAY);
        toolsBtn.setIcon(new ImageIcon(toolsIcon));
        toolsBtn.setToolTipText("Tools");
        toolsBtn.addActionListener((ActionEvent evt) -> {
            MainFrame mainFrame = Main.getMainFrame();
            ToolPanel toolPanel = ToolPanel.getInstance();
            mainFrame.setContent(toolPanel);
        });

        BufferedImage nutritionIcon = null;
        try {
            nutritionIcon = ImageIO.read(Main.class.getResource
                    ("img/nutrition.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton nutritionBtn = new XButton("");
        nutritionBtn.setTransparent(true);
        nutritionBtn.setHoverEffect(Color.LIGHT_GRAY);
        nutritionBtn.setIcon(new ImageIcon(nutritionIcon));
        nutritionBtn.setToolTipText("Nutrition");
        nutritionBtn.addActionListener((ActionEvent evt) -> {
            MainFrame mainFrame = Main.getMainFrame();
            NutritionPanel nutritionPanel = NutritionPanel.getInstance();
            mainFrame.setContent(nutritionPanel);
        });


        BufferedImage runIcon = null;
        try {
            runIcon = ImageIO.read(Main.class.getResource
                    ("img/run.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton runBtn = new XButton("");
        runBtn.setTransparent(true);
        runBtn.setHoverEffect(Color.LIGHT_GRAY);
        runBtn.setIcon(new ImageIcon(runIcon));
        runBtn.setToolTipText("Run");
        runBtn.addActionListener((ActionEvent evt) -> {
            MainFrame mainFrame = Main.getMainFrame();
            FitnessPanel fitnessPanel = new FitnessPanel("Running");
            mainFrame.setContent(fitnessPanel);
        });

        BufferedImage swimIcon = null;
        try {
            swimIcon = ImageIO.read(Main.class.getResource
                    ("img/swim.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton swimBtn = new XButton("");
        swimBtn.setTransparent(true);
        swimBtn.setHoverEffect(Color.LIGHT_GRAY);
        swimBtn.setIcon(new ImageIcon(swimIcon));
        swimBtn.setToolTipText("Swim");
        swimBtn.addActionListener((ActionEvent evt) -> {
            MainFrame mainFrame = Main.getMainFrame();
            FitnessPanel fitnessPanel = new FitnessPanel("Swimming");
            mainFrame.setContent(fitnessPanel);
        });

        BufferedImage cycleIcon = null;
        try {
            cycleIcon = ImageIO.read(Main.class.getResource
                    ("img/cycle.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton cycleBtn = new XButton("");
        cycleBtn.setTransparent(true);
        cycleBtn.setHoverEffect(Color.LIGHT_GRAY);
        cycleBtn.setIcon(new ImageIcon(cycleIcon));
        cycleBtn.setToolTipText("Cycle");
        cycleBtn.addActionListener((ActionEvent evt) -> {
            MainFrame mainFrame = Main.getMainFrame();
            FitnessPanel fitnessPanel = new FitnessPanel("Cycling");
            mainFrame.setContent(fitnessPanel);
        });

        BufferedImage settingsIcon = null;
        try {
            settingsIcon = ImageIO.read(Main.class.getResource
                    ("img/settings.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton settingsBtn = new XButton("");
        settingsBtn.setTransparent(true);
        settingsBtn.setHoverEffect(Color.LIGHT_GRAY);
        settingsBtn.setIcon(new ImageIcon(settingsIcon));
        settingsBtn.setToolTipText("Settings");
        settingsBtn.addActionListener((ActionEvent evt) -> {
            MainFrame mainFrame = Main.getMainFrame();
            //ToolPanel calculatorPanel = ToolPanel.getInstance();
            //mainFrame.setContent(calculatorPanel);
        });

        /*
        XButton testBtn = new XButton("Test");
        testBtn.setHoverEffect(Color.LIGHT_GRAY);
        testBtn.addActionListener((ActionEvent evt) -> {

        });
        */

        add(newsFeedBtn);
        add(runBtn);
        add(swimBtn);
        add(cycleBtn);
        add(toolsBtn);
        add(nutritionBtn);
        add(settingsBtn);
        //add(testBtn);
    }

    

    
}
