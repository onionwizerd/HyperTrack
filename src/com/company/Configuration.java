package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Josh on 5/2/2016.
 */
public class Configuration {

    public static void init(){

        //TabbedPane Config
        UIManager.put("TabbedPane.selected", Color.white);
        UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
        UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);

        BufferedImage folderIconImage = null;
        try {
            folderIconImage = ImageIO.read(Main.class.getResource
                    ("img/folder1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        Icon folderIcon = new ImageIcon(folderIconImage);

        BufferedImage fileIconImage = null;
        try {
            fileIconImage = ImageIO.read(Main.class.getResource
                    ("img/file.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        Icon fileIcon = new ImageIcon(fileIconImage);

        UIManager.put("Tree.closedIcon", folderIcon);
        UIManager.put("Tree.openIcon", folderIcon);
        UIManager.put("Tree.leafIcon", fileIcon);

    }

}
