package com.company.tools;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Josh on 2016-07-07.
 */
public class PlugInLoader {

    ArrayList<File> plugins = null;

    public PlugInLoader(String plugInDirectory) {

        System.out.println("Loading Plug-ins from " + plugInDirectory);

        File directory = new File(plugInDirectory);
        File[] files = directory.listFiles();

        plugins = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile() && (FilenameUtils.getExtension(files[i].toString()).equals("jar"))) {
                System.out.println(files[i]);
                plugins.add(files[i]);
            } else if (files[i].isDirectory()) {
                System.out.println("Directory " + files[i].getName());
            }
        }

    }

    public ArrayList<File> getPlugins() {
        return plugins;
    }
}
