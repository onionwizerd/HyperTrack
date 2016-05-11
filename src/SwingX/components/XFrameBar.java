package SwingX.components;

import SwingX.XMain;
import SwingX.components.XButton;
import SwingX.components.XPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Josh on 5/11/2016.
 */
public class XFrameBar extends XPanel {

    private JFrame rootFrame;

    public XFrameBar(JFrame rootFrame) {
        this.rootFrame =  rootFrame;
        init();
    }

    private void init(){

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);
        setSize(new Dimension(rootFrame.getWidth(), 25));
        setMinimumSize(new Dimension(rootFrame.getWidth(), 25));
        setMaximumSize(new Dimension(rootFrame.getWidth(), 25));

        BufferedImage exitIcon = null;
        try {
            exitIcon = ImageIO.read(XMain.class.getResource("img/16x16/exit.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton exitBtn = new XButton("");
        exitBtn.setSize(new Dimension(24, 24));
        exitBtn.setMinimumSize(new Dimension(26, 24));
        exitBtn.setMaximumSize(new Dimension(26, 24));
        exitBtn.setIcon(new ImageIcon(exitIcon));
        exitBtn.setTransparent(true);
        exitBtn.addActionListener(e -> {
            rootFrame.dispose();
            System.exit(0);
        });

        BufferedImage minimizeIcon = null;
        try {
            minimizeIcon = ImageIO.read(XMain.class.getResource("img/16x16/minimize.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton minimizeBtn = new XButton("");
        minimizeBtn.setSize(new Dimension(16, 16));
        minimizeBtn.setMinimumSize(new Dimension(26, 24));
        minimizeBtn.setMaximumSize(new Dimension(26, 24));
        minimizeBtn.setIcon(new ImageIcon(minimizeIcon));
        minimizeBtn.setTransparent(true);
        minimizeBtn.addActionListener(e -> {
            rootFrame.setState(Frame.ICONIFIED);
        });

        add(exitBtn);
        add(minimizeBtn);

    }

}
