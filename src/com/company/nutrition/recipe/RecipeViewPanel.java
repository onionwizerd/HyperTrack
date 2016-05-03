package com.company.nutrition.recipe;

import SwingX.XPanel;
import SwingX.XScrollPanel;
import com.company.PanelModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Josh on 5/3/2016.
 */
public class RecipeViewPanel extends XPanel implements PanelModel{


    String pageUrl = "";
    JEditorPane recipeViewPane;

    public RecipeViewPanel(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public RecipeViewPanel() {
        init();
    }

    @Override
    public void init() {
        setLayout(new BorderLayout());

        recipeViewPane = new JEditorPane();
        recipeViewPane.setContentType("text/html");


        XScrollPanel scrollPanel = new XScrollPanel();
        scrollPanel.setViewportView(recipeViewPane);

        add(scrollPanel, BorderLayout.CENTER);
    }

    public void setPage(String url){
        try{
            recipeViewPane.setPage(url);
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not set page");
            e.printStackTrace();
        }
    }
}
