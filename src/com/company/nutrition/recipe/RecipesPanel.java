package com.company.nutrition.recipe;

import SwingX.components.tree.XFileTree;
import SwingX.components.XPanel;
import com.company.PanelModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;

/**
 * Created by Josh on 2016-04-14.
 */
public class RecipesPanel extends XPanel implements PanelModel{

    XPanel viewPortPanel;
    RecipeViewPanel recipeViewPanel;
    RecipePanelBuilder recipePanelBuilder;

    String rootDirectotyName = "";

    public RecipesPanel() {
        init();
    }

    @Override
    public void init() {
        setLayout(new BorderLayout());


        switch(System.getProperty("os.name")){
            case "Linux":
                rootDirectotyName = "usr/recipes/";
                break;
            case "Windows":
                rootDirectotyName = "usr\\recipes\\";
                break;
            default:
                rootDirectotyName = "usr\\recipes\\";
                break;

        }

        File rootDirectory = new File(rootDirectotyName);
        XFileTree recipeTree = new XFileTree(rootDirectory, "Recipes");
        recipeTree.getFileTree().addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                        .getPath().getLastPathComponent();

                File recipeFile = new File(rootDirectotyName + recipeTree.getFullNodeName(node.toString()));

                if(recipeFile.exists()){
                    recipePanelBuilder = new RecipePanelBuilder(recipeTree.getFullNodeName(node.toString()));
                    add(recipePanelBuilder.buildRecipePanel(),BorderLayout.CENTER);
                    refresh();
                }

            }
        });

        recipeViewPanel = new RecipeViewPanel();

        add(recipeTree, BorderLayout.WEST);

    }


}
