package com.company;

import com.company.database.Creator;
import com.company.database.DatabaseManager;

/**
 *
 * @author Josh
 */
public class Main {
    
    static MainFrame mainFrame = new MainFrame();

    public static void main(String[] args) {

        //Connect to database
        //DatabaseManager databaseManager = DatabaseManager.getInstance();
        //databaseManager.connect();

        Creator creator = new Creator();

        mainFrame.init();
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    
}
