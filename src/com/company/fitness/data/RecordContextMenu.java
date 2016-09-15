package com.company.fitness.data;

import javax.swing.*;

/**
 * Created by Josh on 5/1/2016.
 */
public class RecordContextMenu extends JPopupMenu{

    private JMenuItem editMenuItem;
    public RecordContextMenu() {
        editMenuItem = new JMenuItem("Edit");

        add(editMenuItem);
    }

    public JMenuItem getEditMenuItem() {
        return editMenuItem;
    }
}
