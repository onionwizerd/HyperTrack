package com.company.nutrition.recipe;

import SwingX.components.XFrame;
import SwingX.components.XScrollPanel;
import com.company.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Josh on 2016-04-14.
 */
public class RecipeViewFrame extends XFrame {

    String title;
    String content;
    BufferedImage bufferedImageIcon;

    public RecipeViewFrame(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void init(){

        try {
            bufferedImageIcon = ImageIO.read(Main.class.getResource
                    ("img/icon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        // Set Attributes
        setSize(new Dimension(700, 750));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setIconImage(bufferedImageIcon);
        setTitle(title);

        // Custom close operation
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                dispose();
            }
        };

        JEditorPane recipeViewPane = new JEditorPane();
        recipeViewPane.setContentType("text/html");
        recipeViewPane.setText(content);

        XScrollPanel scrollPanel = new XScrollPanel();
        scrollPanel.setViewportView(recipeViewPane);

        setContentPane(scrollPanel);

        setVisible(true);
    }


}
