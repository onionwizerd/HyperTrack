package SwingX.components;

import SwingX.XModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class XCalendar extends JPanel implements XModel {

    DefaultTableModel model;
    Calendar cal = new GregorianCalendar();
    JLabel label;
    JPanel panel;

    XButton nextBtn;
    XButton prevBtn;

    public XCalendar(int width, int height) {

        this.setMaximumSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.setVisible(true);


        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);

        prevBtn = new XButton("<");
        prevBtn.setBackground(Color.WHITE);
        prevBtn.setHoverEffect(Color.LIGHT_GRAY);
        prevBtn.refresh();
        prevBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, -1);
                updateMonth();
            }
        });

        nextBtn = new XButton(">");
        nextBtn.setBackground(Color.WHITE);
        nextBtn.setHoverEffect(Color.LIGHT_GRAY);
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cal.add(Calendar.MONTH, +1);
                updateMonth();
            }
        });

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(prevBtn,BorderLayout.WEST);
        panel.add(label,BorderLayout.CENTER);
        panel.add(nextBtn,BorderLayout.EAST);
        panel.setBackground(Color.WHITE);



        String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        model = new DefaultTableModel(null,columns);
        final JTable table = new JTable(model);
        table.setBackground(Color.WHITE);

        //table.setEnabled(false);
        JScrollPane pane = new JScrollPane(table);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();


                    System.out.println(model.getValueAt(row, column));
                }else if(e.getClickCount() > 1){

                }
            }
        });

        this.add(panel,BorderLayout.NORTH);
        this.add(pane,BorderLayout.CENTER);

        this.updateMonth();

    }

    void updateMonth() {
        cal.set(Calendar.DAY_OF_MONTH, 1);

        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int year = cal.get(Calendar.YEAR);
        label.setText(month + " " + year);

        int startDay = cal.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        model.setRowCount(0);
        model.setRowCount(weeks);

        int i = startDay-1;
        for(int day=1;day<=numberOfDays;day++){
            model.setValueAt(day, i/7 , i%7 );
            i = i + 1;
        }
    }

    public void setTransparent(Boolean isTransparent){
        setOpaque(false);
        panel.setOpaque(false);

    }

    public void setTransparentButtons(Boolean isTransparent){
        nextBtn.setTransparent(true);
        prevBtn.setTransparent(true);
    }





}
