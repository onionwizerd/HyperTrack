
package SwingX;

import java.awt.Color;
import javax.swing.JTabbedPane;

/**
 *
 * @author Josh
 */
public class XTabbedPane extends JTabbedPane {

    public XTabbedPane() {
        init();
    }

    public XTabbedPane(int tabPlacement) {
        super(tabPlacement);
        init();
    }

    public XTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
        init();
    }
    
     private void init(){
        setBackground(Color.WHITE);
    }
    
    public void setTransparent(Boolean transparent){
        setOpaque(false);
    }
    
    public void refresh(){
        repaint();
        revalidate();
        validate();
    }
    
}
