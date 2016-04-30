/**
 *
 * @author Josh Beaver
 * @version 1.0
 * @since 2016-04-09
 *
 * <h1>Food Panel</h1>
 * <p></p>
 *
 * <h2>Notes</h2>
 * <p>Design Pattern: Singleton</p>
 *
 */
package com.company.nutrition;

import SwingX.XTabbedPane;
import com.company.PanelModel;
import com.company.nutrition.recipe.RecipesPanel;

import java.awt.*;

public class NutritionPanel extends XTabbedPane implements PanelModel{

    private static NutritionPanel nutritionPanel = new NutritionPanel();

    public NutritionPanel() {
        init();
    }

    @Override
    public void init() {

        setBackground(Color.WHITE);

        RecipesPanel recipesPanel = new RecipesPanel();
        addTab("Recipes", recipesPanel);


    }

    public static NutritionPanel getInstance(){
        return nutritionPanel;
    }
}
