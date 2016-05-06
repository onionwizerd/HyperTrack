package SwingX.components.table;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Created by Josh on 5/2/2016.
 */
public class XTable extends JTable {

    public XTable() {
        init();
    }

    public XTable(TableModel dm) {
        super(dm);
        init();
    }

    public XTable(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
        init();
    }

    public XTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);
        init();
    }

    public XTable(int numRows, int numColumns) {
        super(numRows, numColumns);
        init();
    }

    public XTable(Vector rowData, Vector columnNames) {
        super(rowData, columnNames);
        init();
    }

    public XTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        init();
    }

    private void init(){
        setBackground(Color.WHITE);
        setBorder(null);
    }
}
