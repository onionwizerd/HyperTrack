package com.company.nutrition.recipe;

import SwingX.components.XDivider;
import SwingX.components.XPanel;
import SwingX.components.XScrollPanel;
import SwingX.components.XTextArea;
import com.company.Main;
import com.company.nutrition.recipe.components.InfoLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Josh on 2016-07-06.
 */
public class RecipePanel extends XPanel {

    XPanel titlePanel = new XPanel();
    JLabel titleLabel = new JLabel("Title");

    XPanel infoPanel = new XPanel();
    InfoLabel timeLabel = new InfoLabel("Time: ");
    InfoLabel servingsLabel = new InfoLabel("Servings: ");
    InfoLabel caloriesLabel = new InfoLabel("Calories: ");
    InfoLabel dietLabel = new InfoLabel("Diet: ");

    XPanel ingredientsPanel = new XPanel();
    XPanel ingredientsTitlePanel = new XPanel();
    XScrollPanel ingredientsScrollPanel = new XScrollPanel();
    XPanel ingredientsContentPanel = new XPanel();
    XTextArea ingredientsTextArea = new XTextArea();
    JLabel ingredientsLabel = new JLabel("  Ingredients");

    XPanel directionsPanel = new XPanel();
    XPanel directionsTitlePanel = new XPanel();
    XScrollPanel directionsScrollPanel = new XScrollPanel();
    XPanel directionsContentPanel = new XPanel();
    XTextArea directionsTextArea = new XTextArea();
    JLabel directionsLabel = new JLabel("  Directions");


    public RecipePanel() {
        init();
    }

    public void init(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel.setFont(new Font(titleLabel.getName(), Font.PLAIN, 45));
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);

        BufferedImage timeIcon = null;
        try {
            timeIcon = ImageIO.read(Main.class.getResource
                    ("img/time_16.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        timeLabel.setIcon(new ImageIcon(timeIcon));

        BufferedImage servingsIcon = null;
        try {
            servingsIcon = ImageIO.read(Main.class.getResource
                    ("img/servings_16.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        servingsLabel.setIcon(new ImageIcon(servingsIcon));

        BufferedImage caloriesIcon = null;
        try {
            caloriesIcon = ImageIO.read(Main.class.getResource
                    ("img/calories_16.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        caloriesLabel.setIcon(new ImageIcon(caloriesIcon));

        BufferedImage dietIcon = null;
        try {
            dietIcon = ImageIO.read(Main.class.getResource
                    ("img/diet_16.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        dietLabel.setIcon(new ImageIcon(dietIcon));

        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(titleLabel);
        titlePanel.add(new XDivider(650, 0));

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        infoPanel.add(timeLabel);
        infoPanel.add(servingsLabel);
        infoPanel.add(caloriesLabel);
        infoPanel.add(dietLabel);

        ingredientsLabel.setFont(new Font(ingredientsLabel.getName(), Font.PLAIN, 35));

        ingredientsTitlePanel.setLayout(new BoxLayout(ingredientsTitlePanel, BoxLayout.X_AXIS));
        ingredientsTitlePanel.add(ingredientsLabel);
        ingredientsTitlePanel.add(new XDivider(580, 0));

        //ingredientsTextArea.setBackground(Color.GRAY);
        ingredientsTextArea.setMaximumSize(new Dimension(1000, 150));
        ingredientsTextArea.setEditable(false);

        ingredientsContentPanel.setLayout(new BoxLayout(ingredientsContentPanel, BoxLayout.X_AXIS));
        ingredientsContentPanel.add(new XDivider(50, 0));
        ingredientsContentPanel.add(ingredientsTextArea);

        ingredientsScrollPanel.setViewportView(ingredientsContentPanel);
        ingredientsScrollPanel.setMaximumSize(new Dimension(750, 150));

        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        ingredientsPanel.add(ingredientsTitlePanel);
        ingredientsPanel.add(new XDivider(0, 5));
        ingredientsPanel.add(ingredientsScrollPanel);

        directionsLabel.setFont(new Font(titleLabel.getName(), Font.PLAIN, 35));

        directionsTitlePanel.setLayout(new BoxLayout(directionsTitlePanel, BoxLayout.X_AXIS));
        directionsTitlePanel.add(directionsLabel);
        directionsTitlePanel.add(new XDivider(600, 0));

        directionsTextArea.setMaximumSize(new Dimension(600, 150));
        directionsTextArea.setEditable(false);
        directionsTextArea.setLineWrap(true);

        directionsContentPanel.setLayout(new BoxLayout(directionsContentPanel, BoxLayout.X_AXIS));
        directionsContentPanel.add(new XDivider(10, 0));
        directionsContentPanel.add(directionsTextArea);
        directionsContentPanel.add(new XDivider(150, 0));

        directionsScrollPanel.setViewportView(directionsContentPanel);
        directionsScrollPanel.setMaximumSize(new Dimension(750, 250));

        directionsPanel.setLayout(new BoxLayout(directionsPanel, BoxLayout.Y_AXIS));
        directionsPanel.add(directionsTitlePanel);
        directionsPanel.add(new XDivider(0, 5));
        directionsPanel.add(directionsScrollPanel);

        add(titlePanel);
        add(new XDivider(0, 10));
        add(infoPanel);
        add(new XDivider(0, 10));
        add(ingredientsPanel);
        add(new XDivider(0, 10));
        add(directionsPanel);
        add(new XDivider(0, 10));

    }

    public void setTitle(String title){
        titleLabel.setText("  " + title);
    }

    public void setTime(String time){
        timeLabel.setText("Time: " + time);
        refresh();
    }

    public void setServings(String servings){
        servingsLabel.setText("Servings: " + servings);
    }

    public void setCalories(String calories){
        caloriesLabel.setText("Calories: " + calories);
    }

    public void setDiet(String diet){
        dietLabel.setText("Diet: " + diet);
    }

    public void addIngredient(String ingredientText){
        ingredientsTextArea.append(ingredientText + "\n");
    }

    public void addDirection(String directionText){
        directionsTextArea.append(directionText + "\n \n");
    }




}
