package com.company.data.dataentry;

import SwingX.components.XPanel;

import javax.swing.*;

/**
 * Created by Josh on 5/6/2016.
 */
public class DataEntryComponent extends XPanel{

    public DataEntryComponent() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public Object getValue() {
        return null;
    }

    public boolean hasDataIntegrity() {

        return false;
    }
}
