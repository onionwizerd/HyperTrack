package com.company;

import com.company.database.DatabaseManager;
import com.company.tools.PlugInLoader;


/**
 *
 * @author Josh
 */
public class Main {
    
    private static MainFrame mainFrame = new MainFrame();

    public static void main(String[] args) {

        //Connect to database
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.connect();

        Configuration.init();
        mainFrame.init();
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    
}
