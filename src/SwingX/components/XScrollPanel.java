package SwingX.components;

import java.awt.Color;
import javax.swing.JScrollPane;

/**
 *
 * @author Josh
 */
public class XScrollPanel extends JScrollPane{
    
    private String title = "";

    public XScrollPanel() {
        init();
    }
    
    public XScrollPanel(String title) {
        init();
        this.title = title;
    }
    
    private void init(){
        setBackground(Color.WHITE);
        setBorder(null);
    }
    
    public void refresh(){
        repaint();
        revalidate();
        validate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
    
    
}
