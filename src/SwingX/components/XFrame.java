package SwingX.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Josh on 2016-02-27.
 */
public class XFrame extends JFrame {

    BufferedImage bufferedImageIcon;

    public XFrame() throws HeadlessException {
    }

    public XFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }

    public XFrame(String title) throws HeadlessException {
        super(title);
    }

    public XFrame(GraphicsConfiguration gc) {
        super(gc);
    }


}
