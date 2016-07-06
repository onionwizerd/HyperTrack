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

    public RecipesPanel() {
        init();
    }

    @Override
    public void init() {
        setLayout(new BorderLayout());

        File rootDirectory = new File("usr\\recipes");
        XFileTree recipeTree = new XFileTree(rootDirectory, "Recipes");
        recipeTree.getFileTree().addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                        .getPath().getLastPathComponent();
                File recipeFile = new File("usr\\recipes\\" + node.toString());


                recipePanelBuilder = new RecipePanelBuilder(node.toString());
                add(recipePanelBuilder.buildRecipePanel(),BorderLayout.CENTER);
                refresh();
                //recipeViewPanel.setPage(recipeFile.toURI().toURL().toString());

            }
        });

        recipeViewPanel = new RecipeViewPanel();

        //add(recipeViewPanel, BorderLayout.CENTER);
        add(recipeTree, BorderLayout.WEST);

    }


}
