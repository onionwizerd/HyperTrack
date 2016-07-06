package com.company.nutrition.recipe;

import SwingX.components.XDivider;
import SwingX.components.XPanel;
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
    JLabel ingredientsLabel = new JLabel("Ingredients");

    XPanel directionsPanel = new XPanel();
    XPanel directionsTitlePanel = new XPanel();
    JLabel directionsLabel = new JLabel("Directions");


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

        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        ingredientsPanel.add(ingredientsTitlePanel);

        directionsLabel.setFont(new Font(titleLabel.getName(), Font.PLAIN, 35));

        directionsTitlePanel.setLayout(new BoxLayout(directionsTitlePanel, BoxLayout.X_AXIS));
        directionsTitlePanel.add(directionsLabel);
        directionsTitlePanel.add(new XDivider(600, 0));

        directionsPanel.setLayout(new BoxLayout(directionsPanel, BoxLayout.Y_AXIS));
        directionsPanel.add(directionsTitlePanel);

        add(titlePanel);
        add(infoPanel);
        add(ingredientsPanel);
        add(directionsPanel);


    }

    public void setTitle(String title){
        titleLabel.setText(title);
    }

    public void setTime(String time){
        timeLabel.setText("Time: " + time);
    }

    public void setServings(String servings){
        servingsLabel.setText("Servings: " + servings);
    }

    public void setCalories(String calories){
        caloriesLabel.setText("Calories: " + calories);
    }

    private void setDiet(String diet){
        dietLabel.setText("Diet: " + diet);
    }




}
