package SwingX.components;

import SwingX.XModel;

import javax.swing.*;
import java.awt.*;

public class XButton extends JButton implements XModel {

    // Shape Attributes
    private int shape= 0;
    private int shapeX;
    private int shapeY;
    private int shapeWidth;
    private int shapeHeight;
    private Color shapeColor;
    private Color backgroundColor = UIManager.getColor("control");

    public XButton() {
        init();
    }

    public XButton(Icon icon) {
        super(icon);
        init();
    }

    public XButton(String text) {
        super(text);
        init();
    }

    public XButton(Action a) {
        super(a);
        init();
    }

    public XButton(String text, Icon icon) {
        super(text, icon);
        init();
    }
    
    private void init(){
        setBackground(Color.WHITE);   
        setFocusPainted(false);
        setHoverEffect(Color.LIGHT_GRAY);
    }
    
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        backgroundColor = bg;
    }

    public void setTransparent(Boolean transparent){
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    public void setHoverEffect(final Color color){
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setOpaque(true);
                setBackground(color);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(backgroundColor);
                setOpaque(false);
            }
        });
    }

    /*
    public void setShape(Shape shape, int x, int y, int width, int height, Color color){
        switch (shape){
            case OVAL:
                this.shape = 1;
                break;
            case RECTANGLE:
                this.shape = 2;
                break;
            case TRIANGLE:
                this.shape = 3;
                break;
        }
    }
    */

    
   
    /*
    @Override
    public void paint(Graphics g){

        if (shape != 0){

        }else {
            switch (shape){
                case 1:
                    g.drawOval(shapeX, shapeY, shapeWidth, shapeHeight);
                    g.setColor(shapeColor);
                    break;

            }
        }



    }
    */

    public void refresh(){
        repaint();
        revalidate();
    }

}
