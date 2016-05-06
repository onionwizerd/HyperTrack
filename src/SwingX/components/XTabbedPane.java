
package SwingX.components;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

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

    public void setUI(){
        setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                highlight = Color.LIGHT_GRAY;
                lightHighlight = Color.LIGHT_GRAY;
                shadow = Color.LIGHT_GRAY;
                darkShadow = Color.WHITE;
                focus = Color.WHITE;
            }
        });
    }
    
}
