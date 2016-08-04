
package com.company.nutrition;

import SwingX.components.XTabbedPane;
import com.company.PanelModel;
import com.company.nutrition.recipe.RecipesPanel;

import java.awt.*;

/**
 *
 * @author Josh Beaver
 * @version 1.0
 * @since 2016-04-09
 *
 */

public class NutritionPanel extends XTabbedPane implements PanelModel{

    private static NutritionPanel nutritionPanel = new NutritionPanel();

    public NutritionPanel() {
        init();
    }

    /**
     * Initialises the Nutrition Panel UI
     */
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
