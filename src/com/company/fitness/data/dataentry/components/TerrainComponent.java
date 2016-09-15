package com.company.fitness.data.dataentry.components;

import com.company.fitness.data.dataentry.DataEntryComponent;
import com.company.fitness.data.dataentry.DataEntryComponentModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Josh on 5/6/2016.
 */
public class TerrainComponent extends DataEntryComponent implements DataEntryComponentModel {

    private JComboBox<String> terrainComboBox;

    public TerrainComponent(ArrayList<String> terrainList) {

        String[] terrainArray = terrainList.toArray(new String[0]);

        terrainComboBox = new JComboBox<>(terrainArray);
        terrainComboBox.setMinimumSize(new Dimension(150, 28));
        terrainComboBox.setMaximumSize(new Dimension(150, 28));

        add(terrainComboBox);
    }

    @Override
    public Object getValue() {
        return terrainComboBox.getSelectedItem();
    }

    @Override
    public boolean hasDataIntegrity() {
        boolean dataIntegrity = true;

        if(terrainComboBox.getSelectedItem() == null){
            dataIntegrity = false;
            System.out.println("Terrain Integrity = False");
            JOptionPane.showMessageDialog(null, "Pleas enter a valid terrain");
        }

        return dataIntegrity;
    }
}
