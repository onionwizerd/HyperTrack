package com.company.cloud;

import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by josh on 2016/07/30.
 */
public class Backup implements Runnable{

    CloudPanel cloudPanel;

    public Backup() {
    }

    public void backup(){

        cloudPanel = CloudPanel.getInstance();
        cloudPanel.setLoading("Backing up data");

        Thread backupThread = new Thread(this);
        backupThread.start();


    }


    @Override
    public void run() {
        AccountManager accountManager = AccountManager.getInstance();
        String email = accountManager.getEmail();

        File srcDirectory = new File("usr/database");
        File dstDirectory = new File("usr/tmp/" + email + "/database");


        try {
            FileUtils.copyDirectory(srcDirectory, dstDirectory);
        }catch (IOException ioExcep){
            ioExcep.printStackTrace();
        }


        File uploadDirectory = new File("usr/tmp/" + email);

        Uploader uploader = new Uploader();
        uploader.upload(uploadDirectory);

        cloudPanel.init();
    }
}
