package com.company.cloud;

import com.company.Main;
import com.company.MainFrame;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by josh on 2016/07/30.
 */
public class Login {

    public Login() {
    }

    public String login(String email, String password){

        String errorMessage = null;

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Connection connection = databaseManager.getConnection();

        String databasePassword = "";
        String verified = "FALSE";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM userinfo WHERE " +
                    "Email='"+ email +"';");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                databasePassword = resultSet.getString("password");
                verified = resultSet.getString("verified");
            }

            System.out.println(verified);

            System.out.println("Database password: " + databasePassword);
            System.out.println("User password: " + password);

            if(databasePassword.equals(password)){

                preparedStatement = connection.prepareStatement("SELECT picture FROM userinfo WHERE " +
                        "Email='"+ email +"';");

                resultSet = preparedStatement.executeQuery();

                File pictureFile = new File("pp.jpg");
                pictureFile.deleteOnExit();
                //pictureFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(pictureFile);

                while (resultSet.next()){


                    byte[] buffer = new byte[1];
                    InputStream inputStream = resultSet.getBinaryStream(1);

                    if(inputStream != null){

                        while (inputStream.read(buffer) > 0){
                            fileOutputStream.write(buffer);
                        }

                    }

                }

                fileOutputStream.close();

                AccountManager accountManager = AccountManager.getInstance();
                accountManager.setEmail(email);

                MainFrame mainFrame = Main.getMainFrame();
                CloudPanel cloudPanel = CloudPanel.getInstance();
                mainFrame.setContent(cloudPanel);

            }else{
                errorMessage = "Incorrect username or password";
            }


        }catch (SQLException sqlExcep){
            sqlExcep.printStackTrace();
        }catch (FileNotFoundException fnfExcep){
            fnfExcep.printStackTrace();
        }catch (IOException ioExcep){
            ioExcep.printStackTrace();
        }


        return errorMessage;
    }
}
