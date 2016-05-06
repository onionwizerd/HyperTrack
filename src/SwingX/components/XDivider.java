package SwingX.components;

import java.awt.*;

/**
 * Created by Josh on 5/2/2016.
 */
public class XDivider extends XPanel{

    public XDivider(int width, int height) {
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setBackground(Color.GRAY);
        setForeground(Color.GRAY);
    }
}
