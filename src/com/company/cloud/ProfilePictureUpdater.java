package com.company.cloud;

import SwingX.components.XButton;
import SwingX.components.XDivider;
import SwingX.components.XFrame;
import SwingX.components.XPanel;
import com.company.Main;
import com.company.NavigationBar;
import com.company.news.NewsFeedPanel;
import com.company.utilities.ImageUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by josh on 2016/07/31.
 */
public class ProfilePictureUpdater extends XFrame implements Runnable{

    public ProfilePictureUpdater() {
    }

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

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(180, 200));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setIconImage(bufferedImageIcon);
        setTitle("Profile Picture");
        setResizable(false);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setMinimumSize(this.getSize());
        mainPanel.setBackground(Color.WHITE);


        BufferedImage webcamIcon = null;
        try {
            webcamIcon = ImageIO.read(Main.class.getResource
                    ("img/webcam_32.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton webcamBtn = new XButton("Use Webcam");
        webcamBtn.setIcon(new ImageIcon(webcamIcon));
        webcamBtn.setMinimumSize(new Dimension(125, 30));
        webcamBtn.addActionListener((ActionEvent evt) -> {
            SwingUtilities.invokeLater(new WebCamPanel());
        });

        BufferedImage uploadIcon = null;
        try {
            uploadIcon = ImageIO.read(Main.class.getResource
                    ("img/upload_32.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XButton uploadBtn = new XButton("Upload");
        uploadBtn.setIcon(new ImageIcon(uploadIcon));
        uploadBtn.setMinimumSize(new Dimension(161, 42));
        uploadBtn.setMaximumSize(new Dimension(161, 42));
        uploadBtn.setSize(new Dimension(161, 42));
        uploadBtn.addActionListener(e -> {
            FileChooser fileChooser = new FileChooser("Profile Picture");
            File imageFile = fileChooser.showFileChooser();
            updateProfilePicture(imageFile);
        });


        XPanel buttonsPanel = new XPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(webcamBtn);
        buttonsPanel.add(new XDivider(0, 10));
        buttonsPanel.add(uploadBtn);

        XDivider northDivider = new XDivider(0, 20);
        northDivider.setBackground(Color.WHITE);

        XDivider westDivider = new XDivider(100, 0);
        westDivider.setBackground(Color.WHITE);

        mainPanel.add(northDivider, BorderLayout.NORTH);
        mainPanel.add(westDivider, BorderLayout.WEST);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
        toFront();

    }

    @Override
    public void run() {
        init();

    }

    public void updateProfilePicture(File imageFile){

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Connection connection = databaseManager.getConnection();
        AccountManager accountManager = AccountManager.getInstance();

        if(imageFile != null){
            if(FilenameUtils.getExtension(imageFile.getName()).equals("jpg")){
                try{
                    System.out.println("Updating profile picture for " + accountManager.getEmail());
                    File resizedImage = new ImageUtil().resizeImage(imageFile, 190, 190);
                    FileInputStream fileInputStream = null;
                    fileInputStream = new FileInputStream(resizedImage);

                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE userinfo SET picture = ? "
                                + "WHERE Email = \'" + accountManager.getEmail() + "\';");
                    preparedStatement.setBinaryStream(1, fileInputStream, (int)imageFile.length());
                    preparedStatement.executeUpdate();
                    //croppedImage.deleteOnExit();
                }catch (FileNotFoundException fnfExcep){
                    fnfExcep.printStackTrace();
                }catch (SQLException sqlExcep){
                    sqlExcep.printStackTrace();
                }
            }else{
                System.out.println("Image file is not jpg");
                JOptionPane.showMessageDialog(null, "File is not JPG");
            }
        }else {
            System.out.println("Image file is null");
        }

    }
}
