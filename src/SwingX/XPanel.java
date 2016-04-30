/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingX;

import org.jdesktop.swingx.JXPanel;

import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Josh
 */
public class XPanel extends JPanel{

    public XPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        init();
    }

    public XPanel(LayoutManager layout) {
        super(layout);
        init();
    }

    public XPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        init();
    }

    public XPanel() {
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
