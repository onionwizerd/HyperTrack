package com.company.tools;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

/**
 * Created by Josh on 2016-07-07.
 */
public class ToolTreeNode extends DefaultMutableTreeNode {

    File jarFile = null;

    public ToolTreeNode() {
    }

    public ToolTreeNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    public ToolTreeNode(Object userObject) {
        super(userObject);
    }

    public File getJarFile() {
        return jarFile;
    }

    public void setJarFile(File jarFile) {
        this.jarFile = jarFile;
    }
}
