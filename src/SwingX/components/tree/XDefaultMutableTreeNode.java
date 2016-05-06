package SwingX.components.tree;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Josh on 5/5/2016.
 */
public class XDefaultMutableTreeNode extends DefaultMutableTreeNode {

    private String detailedName;


    public XDefaultMutableTreeNode(Object userObject, String detailedName) {
        super(userObject);
        this.detailedName = detailedName;
    }

    public String getDetailedName() {
        return detailedName;
    }
}
