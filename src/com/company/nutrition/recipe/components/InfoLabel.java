package com.company.nutrition.recipe.components;

import com.company.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Josh on 2016-07-06.
 */
public class InfoLabel extends JLabel {

    BufferedImage imageIcon;

    public InfoLabel() {
        init();
    }

    public InfoLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        init();
    }

    public InfoLabel(Icon image) {
        super(image);
        init();
    }

    public InfoLabel(String text) {
        super(text);
        init();
    }

    public InfoLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    public InfoLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        init();
    }

    public InfoLabel(String text, BufferedImage image){
        super(text);
        imageIcon = image;
        init();
    }

    private void init(){
        setFont(new Font(this.getName(), Font.PLAIN, 15));
        //setIcon(new ImageIcon(imageIcon));
    }
}
