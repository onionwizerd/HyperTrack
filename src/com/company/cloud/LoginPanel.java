package com.company.cloud;

import SwingX.borders.RoundedBorder;
import SwingX.components.*;
import com.company.Main;
import com.company.MainFrame;
import com.company.PanelModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by josh on 2016/07/29.
 */
public class LoginPanel extends XPanel implements PanelModel {

    private static LoginPanel loginPanel = new LoginPanel();
    private DatabaseManager databaseManager;
    private XPanel loadingPanel;

    public LoginPanel() {

        loadingPanel = new XPanel();
        loadingPanel.setLayout(new BoxLayout(loadingPanel, BoxLayout.Y_AXIS));

        URL url = Main.class.getResource("img/loadingJar.gif");
        ImageIcon imageIcon = new ImageIcon(url);
        JLabel loadingIcon = new JLabel(imageIcon);

        loadingPanel.add(loadingIcon);

        add(loadingPanel);


        databaseManager = DatabaseManager.getInstance();

        Thread databaseManagerThread = new Thread(databaseManager);
        databaseManagerThread.start();

    }

    @Override
    public void init() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        XPanel centrePanel = new XPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));

        BufferedImage cloudIcon = null;
        try {
            cloudIcon = ImageIO.read(Main.class.getResource
                    ("img/cloud_128.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        XImagePanel cloudIconPanel = new XImagePanel(cloudIcon);

        XTextArea nameArea = new XTextArea();
        nameArea.setMaximumSize(new Dimension(200, 40));
        nameArea.setText("HyperTrack Cloud");
        nameArea.setFont(new Font(nameArea.getName(), Font.PLAIN, 22));
        nameArea.setEditable(false);

        XTextArea descriptionArea = new XTextArea();
        descriptionArea.setMaximumSize(new Dimension(200, 40));
        descriptionArea.setText("Backup your local database");
        descriptionArea.setFont(new Font(nameArea.getName(), Font.PLAIN, 15));
        descriptionArea.setEditable(false);

        XPanel descriptionPanel = new XPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.add(nameArea);
        descriptionPanel.add(descriptionArea);

        XLabel emailLbl = new XLabel("Email:");

        XPanel emailLblPanel = new XPanel();
        emailLblPanel.setLayout(new BoxLayout(emailLblPanel, BoxLayout.X_AXIS));

        emailLblPanel.add(emailLbl);
        emailLblPanel.add(new XDivider(158, 0));

        XTextField emailField = new XTextField();
        emailField.setMaximumSize(new Dimension(200, 28));

        XLabel passwordLbl = new XLabel("Password:");

        XPanel passwordLblPanel = new XPanel();
        passwordLblPanel.setLayout(new BoxLayout(passwordLblPanel, BoxLayout.X_AXIS));

        passwordLblPanel.add(passwordLbl);
        passwordLblPanel.add(new XDivider(125, 0));

        XPasswordField passwordField = new XPasswordField();
        passwordField.setMaximumSize(new Dimension(200, 28));

        XPanel buttonsPanel = new XPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        XButton loginBtn = new XButton("Login");
        loginBtn.setMaximumSize(new Dimension(95, 28));
        loginBtn.setBorder(new RoundedBorder(15));
        loginBtn.addActionListener(e -> {
            Login login = new Login();
            String password = new String(passwordField.getPassword());
            String errorMessage =  login.login(emailField.getText(), password);
            if(errorMessage != null){
                JOptionPane.showMessageDialog(null, errorMessage);
            }
        });

        XButton signupBtn = new XButton("Sign Up");
        signupBtn.setMaximumSize(new Dimension(95, 28));
        signupBtn.setBorder(new RoundedBorder(15));
        signupBtn.addActionListener(e -> {
            MainFrame mainFrame = Main.getMainFrame();
            SignUpPanel signUpPanel = SignUpPanel.getInstance();
            mainFrame.setContent(signUpPanel);
        });

        buttonsPanel.add(loginBtn);
        buttonsPanel.add(new XDivider(5, 0));
        buttonsPanel.add(signupBtn);

        remove(loadingPanel);

        centrePanel.add(cloudIconPanel);
        centrePanel.add(descriptionPanel);
        centrePanel.add(emailLblPanel);
        centrePanel.add(emailField);
        centrePanel.add(new XDivider(0, 5));
        centrePanel.add(passwordLblPanel);
        centrePanel.add(passwordField);
        centrePanel.add(new XDivider(0, 5));
        centrePanel.add(buttonsPanel);

        add(new XDivider(0, 50));
        add(centrePanel);

        refresh();

    }

    public static LoginPanel getInstance(){ return loginPanel; }
}
