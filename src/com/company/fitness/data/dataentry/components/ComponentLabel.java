package com.company.fitness.data.dataentry.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josh on 5/6/2016.
 */
public class ComponentLabel extends JLabel{

    public ComponentLabel(String text) {
        super(text);

        setFont(new Font(this.getName(), Font.PLAIN, 16));
        setMinimumSize(new Dimension(0, 28));
        setMaximumSize(new Dimension(500, 28));

    }
}
