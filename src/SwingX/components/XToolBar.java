package SwingX.components;

import SwingX.XModel;

import javax.swing.*;

/**
 * Created by Josh on 2016-02-27.
 */
public class XToolBar extends JToolBar implements XModel {


    public XToolBar() {
    }

    public XToolBar(String name, int orientation) {
        super(name, orientation);
    }

    public XToolBar(int orientation) {
        super(orientation);
    }

    public XToolBar(String name) {
        super(name);
    }

    public void setTransparent(Boolean transparent){
        setOpaque(false);
        setBorderPainted(false);
    }

}
