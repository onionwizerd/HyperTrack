package com.company.nutrition.recipe;

import SwingX.XPanel;
import SwingX.XScrollPanel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

/**
 * Created by Josh on 5/3/2016.
 */
public class FileTree extends XPanel {

    private String rootDirectoryName = null;

    public FileTree(File rootDirectory, String rootDirectoryName) {
        setLayout(new BorderLayout());

        this.rootDirectoryName = rootDirectoryName;

        JTree fileTree = new JTree(addNodes(null, rootDirectory));

        // Add a listener
        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                        .getPath().getLastPathComponent();
                System.out.println("You selected " + node);
            }
        });


        XScrollPanel scrollpane = new XScrollPanel();
        scrollpane.getViewport().add(fileTree);
        add(BorderLayout.CENTER, scrollpane);
    }

    private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File rootDirectory) {

        String curPath = rootDirectory.getPath();

        DefaultMutableTreeNode curDirectory = new DefaultMutableTreeNode(rootDirectoryName);

        Vector dirList = new Vector();

        String[] tmpDirectoryList = rootDirectory.list();

        for (int i = 0; i < tmpDirectoryList.length; i++){
            dirList.addElement(tmpDirectoryList[i]);
        }
        Collections.sort(dirList, String.CASE_INSENSITIVE_ORDER);

        File f;
        Vector files = new Vector();

        // Make two passes, one for Dirs and one for Files.

        // Pass for directories
        for (int i = 0; i < dirList.size(); i++) {
            String thisObject = (String) dirList.elementAt(i);
            String newPath;
            if (curPath.equals("."))
                newPath = thisObject;
            else
                newPath = curPath + File.separator + thisObject;
            if ((f = new File(newPath)).isDirectory())
                addNodes(curDirectory, f);
            else
                files.addElement(thisObject);
        }

        // Pass for files
        for (int fnum = 0; fnum < files.size(); fnum++)
            curDirectory.add(new DefaultMutableTreeNode(files.elementAt(fnum)));

        return curDirectory;
    }



}
