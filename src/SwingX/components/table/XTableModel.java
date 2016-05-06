package SwingX.components.table;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Josh on 2016-02-25.
 */
public class XTableModel extends DefaultTableModel{


    public XTableModel(String[] rows, String[] columns) {
        super(null, columns);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
