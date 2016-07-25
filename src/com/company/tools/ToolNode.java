package com.company.tools;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

/**
 * Created by Josh on 2016-07-07.
 */
public class ToolNode extends DefaultMutableTreeNode {

    File jarFile = null;

    public ToolNode() {
    }

    public ToolNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    public ToolNode(Object userObject) {
        super(userObject);
    }

    public File getJarFile() {
        return jarFile;
    }

    public void setJarFile(File jarFile) {
        this.jarFile = jarFile;
    }
}
