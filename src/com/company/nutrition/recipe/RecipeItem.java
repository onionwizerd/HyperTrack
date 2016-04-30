package com.company.nutrition.recipe;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Josh on 2016-04-14.
 */
public class RecipeItem extends JXPanel{

    private String title;
    private String description;
    private String content;

    public RecipeItem(){
    }

    public RecipeItem(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;

        init();
    }

    private void init(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(800, 200));
        setMaximumSize(new Dimension(800, 200));
        setBackground(Color.WHITE);
        setAlignmentX(CENTER_ALIGNMENT);

        //Border
        DropShadowBorder dropShadow = new DropShadowBorder();
        dropShadow.setShadowColor(Color.BLACK);
        dropShadow.setShowLeftShadow(true);
        dropShadow.setShowRightShadow(true);
        dropShadow.setShowBottomShadow(true);
        dropShadow.setShowTopShadow(true);
        setBorder(dropShadow);

        Border border = this.getBorder();
        Border margin = new EmptyBorder(10,20,10,20);
        setBorder(new CompoundBorder(border, margin));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RecipeViewFrame recipeViewFrame = new RecipeViewFrame(title, content);
                recipeViewFrame.init();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 36));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);

        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        descArea.setAlignmentX(CENTER_ALIGNMENT);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setMaximumSize(new Dimension(500, 200));
        //descLabel.setMargin(new Insets(20,20,20,20));
        descArea.setBackground(Color.WHITE);

        JPanel divider = new JPanel();
        divider.setMaximumSize(new Dimension(0, 100));
        divider.setMinimumSize(new Dimension(0, 100));

        add(titleLabel);
        add(divider);
        add(descArea);

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
