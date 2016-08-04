package com.company.cloud;

import SwingX.components.XFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Created by josh on 2016/07/31.
 */
public class FileChooser extends XFrame {


    public FileChooser(String title) throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(550, 400));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setResizable(false);
        setTitle(title);
    }

    public File showFileChooser(){

        File selectedFile = null;

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG Image", "jpg");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {

            selectedFile = fileChooser.getSelectedFile();

            dispose();

        }
        return selectedFile;
    }


}
