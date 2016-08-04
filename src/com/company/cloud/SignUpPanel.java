package com.company.cloud;

import SwingX.components.*;
import com.company.Main;
import com.company.PanelModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by josh on 2016/07/29.
 */
public class SignUpPanel extends XPanel implements PanelModel {

    private static SignUpPanel loginPanel = new SignUpPanel();
    private XTextArea errorOutput;

    public SignUpPanel() {
        init();
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

        XTextArea descriptionArea = new XTextArea();
        descriptionArea.setMaximumSize(new Dimension(200, 40));
        descriptionArea.setText("HyperTrack Cloud");
        descriptionArea.setFont(new Font(descriptionArea.getName(), Font.PLAIN, 22));
        descriptionArea.setEditable(false);

        XPanel descriptionPanel = new XPanel();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
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

        XLabel repeatPasswordLbl = new XLabel("Repeat Password:");

        XPanel repeatPasswordLblPanel = new XPanel();
        repeatPasswordLblPanel.setLayout(new BoxLayout(repeatPasswordLblPanel, BoxLayout.X_AXIS));

        repeatPasswordLblPanel.add(repeatPasswordLbl);
        repeatPasswordLblPanel.add(new XDivider(71, 0));

        XPasswordField repeatPasswordField = new XPasswordField();
        repeatPasswordField.setMaximumSize(new Dimension(200, 28));

        XPanel buttonsPanel = new XPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        XButton loginBtn = new XButton("Login");
        loginBtn.setMaximumSize(new Dimension(95, 28));

        XButton signupBtn = new XButton("Sign Up");
        signupBtn.setMaximumSize(new Dimension(95, 28));
        signupBtn.addActionListener(e -> {
            SignUp signUp = new SignUp();

            String password = new String(passwordField.getPassword());
            String repeatPassword = new String(repeatPasswordField.getPassword());

            if(!password.equals(repeatPassword)){
                appendError("Passwords do not match");
            }

            signUp.signUp(emailField.getText(), password);

        });

        errorOutput = new XTextArea();
        errorOutput.setMaximumSize(new Dimension(170, 200));
        errorOutput.setEditable(false);
        errorOutput.setLineWrap(true);
        errorOutput.setForeground(Color.RED);

        buttonsPanel.add(signupBtn);

        centrePanel.add(cloudIconPanel);
        centrePanel.add(descriptionPanel);
        centrePanel.add(emailLblPanel);
        centrePanel.add(emailField);
        centrePanel.add(new XDivider(0, 5));
        centrePanel.add(passwordLblPanel);
        centrePanel.add(passwordField);
        centrePanel.add(new XDivider(0, 5));
        centrePanel.add(repeatPasswordLblPanel);
        centrePanel.add(repeatPasswordField);
        centrePanel.add(new XDivider(0, 5));
        centrePanel.add(buttonsPanel);
        centrePanel.add(new XDivider(0,10));
        centrePanel.add(errorOutput);

        add(new XDivider(0, 50));
        add(centrePanel);

    }

    public static SignUpPanel getInstance(){ return loginPanel; }

    public void appendError(String error){
        errorOutput.append(error + "\n\n");
    }
}

