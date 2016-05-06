package SwingX.components.tree;


import SwingX.components.XPanel;
import SwingX.components.XScrollPanel;

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
public class XFileTree extends XPanel {

    private String rootDirectoryName = null;
    private JTree fileTree;

    public XFileTree(File rootDirectory, String rootDirectoryName) {
        setLayout(new BorderLayout());

        this.rootDirectoryName = rootDirectoryName;

        fileTree = new JTree(addNodes(null, rootDirectory));

        // Add a listener
        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                        .getPath().getLastPathComponent();
            }
        });


        XScrollPanel scrollpane = new XScrollPanel();
        scrollpane.getViewport().add(fileTree);
        add(BorderLayout.CENTER, scrollpane);
    }

    private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File rootDirectory) {

        String rootPath = rootDirectory.getPath();

        DefaultMutableTreeNode rootDirectoryNode = new DefaultMutableTreeNode(rootDirectoryName);

        Vector dirList = new Vector();

        String[] tmpDirectoryList = rootDirectory.list();

        for (int i = 0; i < tmpDirectoryList.length; i++){
            dirList.addElement(tmpDirectoryList[i]);
        }
        Collections.sort(dirList, String.CASE_INSENSITIVE_ORDER);

        File f;
        Vector files = new Vector();


        // Pass for directories
        for (int i = 0; i < dirList.size(); i++) {
            String thisObject = (String) dirList.elementAt(i);
            String newPath;
            if (rootPath.equals("."))
                newPath = thisObject;
            else
                newPath = rootPath + File.separator + thisObject;
            if ((f = new File(newPath)).isDirectory())
                addNodes(rootDirectoryNode, f);
            else
                files.addElement(thisObject);
        }

        // Pass for files
        for (int fnum = 0; fnum < files.size(); fnum++)
            rootDirectoryNode.add(new DefaultMutableTreeNode(files.elementAt(fnum)));

        return rootDirectoryNode;
    }


    public JTree getFileTree() {
        return fileTree;
    }
}
