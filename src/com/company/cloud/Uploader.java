package com.company.cloud;

import com.jscape.inet.ftp.Ftp;
import com.jscape.inet.ftp.FtpAdapter;
import com.jscape.inet.ftp.FtpException;

import javax.swing.*;
import java.io.File;

/**
 * Created by josh on 2016/07/30.
 */
public class Uploader extends FtpAdapter {

    public void upload(File uploadDirectory){

        Ftp ftp = new Ftp("brinlee.ddns.net", "ftpuser", "topkek");
        ftp.setDebug(true);
        ftp.setTimeout(10000);

        ftp.addFtpListener(this);

        try{
            ftp.connect();
            ftp.setBinary();

            ftp.uploadDir(uploadDirectory);

            ftp.disconnect();

        }catch (FtpException ftpExcep){
            JOptionPane.showMessageDialog(null, "There was a problem connecting to the server");
            ftpExcep.printStackTrace();
        }

    }


}
