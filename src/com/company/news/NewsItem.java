package com.company.news;

import SwingX.components.XButton;
import SwingX.components.XImagePanel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Josh Beaver
 * @version 0.1
 * @since 2016-03-16
 * 
 * <h1>News Item </h1>
 * <p></p>
 * 
 * <h2>Notes</h2>
 * <p>Design Pattern: Factory</p>
 * 
 */

public class NewsItem extends JXPanel {
    
    private String title;
    private String description;
    private String url;
    private BufferedImage image;
    
    public NewsItem(){
    }

    public NewsItem(String title, String description, String url, 
            BufferedImage image) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.image = image;
        
        init();
    }
    
    private void init(){
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(200, 600));
        setMaximumSize(new Dimension(600, 600));
        setBackground(Color.WHITE);
        setAlignmentX(CENTER_ALIGNMENT);

        //Border
        DropShadowBorder dropShadow = new DropShadowBorder();
        dropShadow.setShadowColor(Color.BLACK);
        dropShadow.setShowLeftShadow(true);
        dropShadow.setShowRightShadow(true);
        dropShadow.setShowBottomShadow(true);
        dropShadow.setShowTopShadow(true);
        setBorder(dropShadow);

        Border border = this.getBorder();
        Border margin = new EmptyBorder(10,30,10,30);
        setBorder(new CompoundBorder(border, margin));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Desktop.isDesktopSupported()){
                    try{
                        if(e.getClickCount() == 1 || e.getClickCount() == 2){
                            Desktop.getDesktop().browse(new URI(url));
                        }
                    }catch(IOException ioExcep){
                        System.out.println("IOException opening URI");
                    }catch(URISyntaxException uriSyntaxExcep){
                        System.out.println("URI Syntax Exception");
                    }
                }else{
                    Runtime runtime = Runtime.getRuntime();
                    try{
                        runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                    }catch(IOException ioExcep){
                        System.out.println("IO Exception");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
         
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 36));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        descArea.setAlignmentX(CENTER_ALIGNMENT);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setMaximumSize(new Dimension(650, 300));
        //descLabel.setMargin(new Insets(20,20,20,20));
        descArea.setBackground(Color.WHITE);
       
        XImagePanel imagePanel =  new XImagePanel(image);
        
        JPanel divider1 = new JPanel();
        divider1.setMaximumSize(new Dimension(0, 200));
        divider1.setMinimumSize(new Dimension(0, 200));  
        
        JPanel divider2 = new JPanel();
        divider2.setMaximumSize(new Dimension(0, 200));
        divider2.setMinimumSize(new Dimension(0, 200));
        
        JPanel divider3 = new JPanel();
        divider3.setMaximumSize(new Dimension(0, 200));
        divider3.setMinimumSize(new Dimension(0, 200));
        
        XButton readMoreBtn = new XButton("Read More");
        readMoreBtn.addActionListener((ActionEvent evt) -> {
            if(Desktop.isDesktopSupported()){
                 try{
                    Desktop.getDesktop().browse(new URI(url));
                }catch(IOException ioExcep){
                    System.out.println("IOException opening URI");
                }catch(URISyntaxException uriSyntaxExcep){
                    System.out.println("URI Syntax Exception");
                }
            }else{
                Runtime runtime = Runtime.getRuntime();
                try{
                    runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                }catch(IOException ioExcep){
                    System.out.println("IO Exception");
                }
            }
        });

       
        add(titleLabel);
        add(divider1);
        add(imagePanel);
        add(divider2);
        add(descArea);
        add(divider3);
        
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    
    
    
    
    

    
    
    
    
    
    
}
