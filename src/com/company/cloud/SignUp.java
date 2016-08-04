package com.company.cloud;

import com.company.Main;
import com.company.MainFrame;
import com.company.email.Email;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by josh on 2016/07/31.
 */
public class SignUp {

    private String errorMessage;
    private SignUpPanel signUpPanel = SignUpPanel.getInstance();

    public SignUp() {
    }

    public String signUp(String email, String password){

        errorMessage = null;

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Connection connection = databaseManager.getConnection();

        try {

            PreparedStatement preparedStatement
                    = connection.prepareStatement("SELECT Email FROM userinfo WHERE Email = \'" + email + "\';");

            ResultSet resultSet = preparedStatement.executeQuery();


            boolean emailValid = true;

            if(resultSet.next() == false) {
                System.out.println("Email Valid");
            }else {
                errorMessage = "Email is already taken";
                emailValid = false;
                signUpPanel.appendError(errorMessage);
            }

            if (! new Email().isValidAddress(email)){
                emailValid = false;
                errorMessage = "Email is not valid";
                signUpPanel.appendError(errorMessage);
            }

            if(!isPasswordValid(password)){
                signUpPanel.appendError(errorMessage);
            }

            if(emailValid){

                System.out.println("Inserting into database");

                preparedStatement = connection.prepareStatement("INSERT INTO userinfo (`Email`, `password`) VALUES (\'"
                        + email +"\', \'" + password + "\');");

                preparedStatement.executeUpdate();

                AccountManager accountManager = AccountManager.getInstance();
                accountManager.setEmail(email);

                Email emailutils = new Email();
                emailutils.sendEmail(email, "Thank you for registering", "Registration Complete");

                MainFrame mainFrame = Main.getMainFrame();
                CloudPanel cloudPanel = CloudPanel.getInstance();
                mainFrame.setContent(cloudPanel);
            }


        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }

        return errorMessage;
    }

    private boolean isPasswordValid(String password){
        boolean result = true;

        if(password.length() < 6){
            result = false;
            errorMessage = "Password must be at least 6 characters";
        }

        return result;
    }

}
