package com.company.nutrition.recipe;

import SwingX.XFileTree;
import SwingX.XPanel;
import com.company.PanelModel;

import java.awt.*;
import java.io.File;

/**
 * Created by Josh on 2016-04-14.
 */
public class RecipesPanel extends XPanel implements PanelModel{

    XPanel viewPortPanel;
    RecipeFactory recipeFactory;

    public RecipesPanel() {
        init();
    }

    @Override
    public void init() {
        setLayout(new BorderLayout());


        File rootDirectory = new File("usr\\recipes");
        System.out.println(rootDirectory.exists());
        XFileTree recipeTree = new XFileTree(rootDirectory, "Recipes");

        add(recipeTree, BorderLayout.WEST);

        /*
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
        */

    }
}
