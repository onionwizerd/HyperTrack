package com.company.nutrition.recipe;

import SwingX.XPanel;
import SwingX.XScrollPanel;
import com.company.PanelModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josh on 2016-04-14.
 */
public class RecipesPanel extends XScrollPanel implements PanelModel{

    XPanel viewPortPanel;
    RecipeFactory recipeFactory;

    public RecipesPanel() {
        init();
    }

    @Override
    public void init() {
//        setLayout(new BorderLayout());

        recipeFactory = new RecipeFactory();

        viewPortPanel = new XPanel();
        viewPortPanel.setLayout(new BoxLayout(viewPortPanel, BoxLayout.Y_AXIS));

        System.out.println(recipeFactory.hasNext());
        recipeFactory.setCurrentItem(0);
        recipeFactory.setHasNext(true);
        while(recipeFactory.hasNext()){

            System.out.println("Creating item");
            RecipeItem recipeItem = recipeFactory.createRecipeItem();
            viewPortPanel.add(recipeItem);

            JPanel divider = new JPanel();
            divider.setMaximumSize(new Dimension(0, 20));
            divider.setMinimumSize(new Dimension(0, 20));
            viewPortPanel.add(divider);

        }

        setViewportView(viewPortPanel);

    }
}
