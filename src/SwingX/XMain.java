package SwingX;

import SwingX.components.*;
import SwingX.components.XFrameBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class XMain {

    static BufferedImage bi;

    public static void main(String[] args) {
        initComponents();
    }

    public static  void initComponents(){

        //XFrame
        XFrame frame = new XFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.WHITE);
        try {
            bi = ImageIO.read(XMain.class.getResource("img/32x32/swingx_icon_black.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        frame.setIconImage(bi);
        frame.setUndecorated(true);
        frame.setTitle("SwingX");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setMinimumSize(new Dimension(500, 500));
        mainPanel.setBackground(Color.WHITE);

        XCalendar calendar = new XCalendar(300, 200);
        calendar.setBackground(Color.WHITE);
        calendar.setTransparent(true);
        calendar.setTransparentButtons(true);

        XButton button = new XButton("");
        button.setTransparent(true);
        button.setHoverEffect(Color.LIGHT_GRAY);
        button.setIcon(new ImageIcon(bi));


        XToolBar toolBar = new XToolBar("Navigation", XToolBar.VERTICAL);
        toolBar.setBackground(Color.WHITE);
        toolBar.setTransparent(false);
        toolBar.setMargin(new Insets(0,0,0,0));

        toolBar.add(button);

        XFrameBar frameBar  = new XFrameBar(frame);

        mainPanel.add(frameBar, BorderLayout.NORTH);
        mainPanel.add(calendar, BorderLayout.CENTER);
       // mainPanel.add(toolBar, BorderLayout.WEST);

        frame.setContentPane(mainPanel);

        frame.setVisible(true);
        frame.toFront();
    }
}

