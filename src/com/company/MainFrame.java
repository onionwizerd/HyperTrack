
package com.company;

import SwingX.XMain;
import SwingX.XButton;
import SwingX.XCalendar;
import SwingX.XFrame;
import SwingX.XPanel;
import SwingX.XScrollPanel;
import SwingX.XToolBar;
import com.company.calculators.CalculatorPanel;
import com.company.news.NewsFeedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
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

public class MainFrame {
    
    private XFrame frame = new XFrame();
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

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(950, 750));
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        frame.setIconImage(bufferedImageIcon);
        frame.setTitle("Fitness Tracker");

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setMinimumSize(frame.getSize());
        mainPanel.setBackground(Color.WHITE);

        navBar = NavigationBar.getInstance();
        
        XButton button = new XButton("Change");
        button.addActionListener((ActionEvent evt) -> {
            setContent(NewsFeedPanel.getInstance());
        });

        mainPanel.add(navBar, BorderLayout.WEST);
        //setContent(NewsFeedPanel.getInstance());

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.toFront();
    }
    
    public void setContent(JComponent contentPanel){
        if(this.contentPanel != null){
          mainPanel.remove(contentPanel);
        }
        mainPanel.refresh();

        this.contentPanel = contentPanel;
        
        mainPanel.add(this.contentPanel, BorderLayout.CENTER);
        mainPanel.refresh();
       
       // System.out.println("Content set to " + contentPanel.getTitle());
    }
    
    public XPanel getMainPanel(){
        return mainPanel;
    }


    
}
