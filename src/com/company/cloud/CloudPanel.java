package com.company.cloud;

import SwingX.components.*;
import com.company.Main;
import com.company.PanelModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by josh on 2016/07/30.
 */
public class CloudPanel extends XPanel implements PanelModel{

    private static CloudPanel cloudPanel = new CloudPanel();
    private XPanel loadingPanel;
    XPanel centrePanel;

    private CloudPanel() {
        init();
    }

    public static CloudPanel getInstance(){ return cloudPanel; }

    @Override
    public void init() {

        AccountManager accountManager = AccountManager.getInstance();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        centrePanel = new XPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
        centrePanel.setBackground(Color.WHITE);


        BufferedImage profilePicture = null;
        try {
            profilePicture = ImageIO.read(new File("pp1.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }


        BufferedImage cloudIcon = null;
        try {
            cloudIcon = ImageIO.read(Main.class.getResource
                    ("img/cloud_128.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton profilePictureBtn = new XButton();
        profilePictureBtn.setTransparent(true);
        profilePictureBtn.setIcon(new ImageIcon(profilePicture));
        profilePictureBtn.setHoverEffect(null);
        profilePictureBtn.setMaximumSize(new Dimension(1000, 200));
        profilePictureBtn.addActionListener(e -> {
            ProfilePictureUpdater profilePictureUpdater = new ProfilePictureUpdater();
            Thread profilePictureThread = new Thread(profilePictureUpdater);
            profilePictureThread.start();
        });

        XPanel profilePicturePanel = new XPanel(new FlowLayout());
        profilePicturePanel.setMaximumSize(new Dimension(1000, 200));
        profilePicturePanel.add(profilePictureBtn);

        XImagePanel cloudIconPanel = new XImagePanel(profilePicture);

        XButton restoreBtn = new XButton("Restore From Cloud");
        restoreBtn.addActionListener(e -> {

        });

        XButton backupBtn = new XButton("Backup Data");
        backupBtn.addActionListener(e -> {
            Backup backup = new Backup();
            backup.backup();
        });


        XPanel buttonsPanel = new XPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        buttonsPanel.add(backupBtn);
        buttonsPanel.add(new XDivider(5, 0));
        buttonsPanel.add(restoreBtn);

        XLabel usernameLbl = new XLabel("Logged in as " + accountManager.getEmail());

        BufferedImage logoutIcon = null;
        try {
            logoutIcon = ImageIO.read(Main.class.getResource
                    ("img/logout_32.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton logoutBtn = new XButton("");
        logoutBtn.setIcon(new ImageIcon(logoutIcon));
        logoutBtn.setTransparent(true);
        logoutBtn.setToolTipText("Logout");

        XPanel northPanel = new XPanel(new FlowLayout(FlowLayout.RIGHT));
        northPanel.setMaximumSize(new Dimension(10000, 35));
        northPanel.setMinimumSize(new Dimension(700, 35));

        northPanel.add(logoutBtn);

        centrePanel.add(new XDivider(0, 50));
        centrePanel.add(profilePicturePanel);
        //centrePanel.add(usernameLbl);
        centrePanel.add(new XDivider(0, 10));
        centrePanel.add(buttonsPanel);

        try{
            remove(loadingPanel);
        }catch (Exception e){}
        //add(new XDivider(0, 50));
        //add(northPanel);
        add(centrePanel);

        refresh();

    }

    public void setLoading(String loadingMessage){

        loadingPanel = new XPanel();
        loadingPanel.setLayout(new BoxLayout(loadingPanel, BoxLayout.Y_AXIS));

        URL url = Main.class.getResource("img/loadingJar.gif");
        ImageIcon imageIcon = new ImageIcon(url);
        JLabel loadingIcon = new JLabel(imageIcon);

        XLabel loadingLabel = new XLabel(loadingMessage);

        XPanel loadingMessagePanel = new XPanel();
        loadingMessagePanel.setLayout(new BoxLayout(loadingMessagePanel, BoxLayout.X_AXIS));

        loadingMessagePanel.add(new XDivider(200, 0));
        loadingMessagePanel.add(loadingLabel);

        loadingPanel.add(loadingIcon);
        //loadingPanel.add(loadingMessagePanel);

        remove(centrePanel);
        add(loadingPanel);
        refresh();


    }
}
