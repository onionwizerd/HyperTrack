
package com.company;

import SwingX.components.XButton;
import SwingX.components.XFrame;
import SwingX.components.XPanel;
import com.company.news.NewsFeedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 
 * @author Josh Beaver
 * @version 1.0
 * @since 2016-03-16
 * 
 * <h1>Main Frame</h1>
 * <p></p>
 * 
 * <h2>Notes</h2>
 * <p></p>
 * 
 */

public class MainFrame extends XFrame{
    
    //private XFrame frame = new XFrame();
    private BufferedImage bufferedImageIcon;
    private XPanel mainPanel = new XPanel();
    private JComponent contentPanel;
    private NavigationBar navBar;

    public void init(){
        
        try {
            bufferedImageIcon = ImageIO.read(Main.class.getResource
            ("img/icon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(950, 750));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setIconImage(bufferedImageIcon);
        setTitle("HyperTrack");
        setResizable(false);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setMinimumSize(this.getSize());
        mainPanel.setBackground(Color.WHITE);

        navBar = NavigationBar.getInstance();
        
        XButton button = new XButton("Change");
        button.addActionListener((ActionEvent evt) -> {
            setContent(NewsFeedPanel.getInstance());
        });

        mainPanel.add(navBar, BorderLayout.WEST);
        //setContent(NewsFeedPanel.getInstance());

        setContentPane(mainPanel);
        setVisible(true);
        toFront();
    }
    
    public void setContent(JComponent contentPanel){
        if(this.contentPanel != null){
            mainPanel.remove(this.contentPanel);
            this.contentPanel = null;
        }

        this.contentPanel = contentPanel;
        
        mainPanel.add(this.contentPanel, BorderLayout.CENTER);
        mainPanel.refresh();
       
       // System.out.println("Content set to " + contentPanel.getTitle());
    }
    
    public XPanel getMainPanel(){
        return mainPanel;
    }


    
}
