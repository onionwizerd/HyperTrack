package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josh on 5/2/2016.
 */
public class Configuration {

    public static void init(){

        //TabbedPane Config
        UIManager.put("TabbedPane.selected", Color.white);
        UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
        UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);

    }

}
