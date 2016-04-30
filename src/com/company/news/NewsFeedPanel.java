
package com.company.news;

import SwingX.XPanel;
import SwingX.XScrollPanel;
import com.company.PanelModel;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Josh Beaver
 * @version 0.1
 * @since 2016-03-16
 * 
 * <h1>News Feed Panel</h1>
 * <p></p>
 * 
 * <h2>Notes</h2>
 * <p>Design Pattern: Singleton</p>
 * 
 */

public class NewsFeedPanel extends XScrollPanel implements PanelModel, Runnable{

    private static NewsFeedPanel newsFeedPanel = new NewsFeedPanel();
    private XPanel viewPortPanel;
    private NewsItemFactory newsItemFactory;

    JProgressBar progressBar;

    int height;
    int width;
    
    private NewsFeedPanel() {
        super("News Feed Panel");
        init();
    }

    @Override
    public void init(){
        XPanel loadingPanel = new XPanel();
        loadingPanel.setLayout(new BorderLayout());
        loadingPanel.setBackground(Color.WHITE);
        loadingPanel.setTransparent(true);

        progressBar = new JProgressBar();
        progressBar.setBackground(Color.WHITE);
        progressBar.setStringPainted(true);

        loadingPanel.add(progressBar, BorderLayout.CENTER);

        setBackground(new Color(242, 242, 242));
        setViewportView(loadingPanel);
    }
    
    /**
     *
     * @return
     */
    public static NewsFeedPanel getInstance(){
        return newsFeedPanel;
    }


    @Override
    public void run() {

        newsItemFactory = new NewsItemFactory();

        progressBar.setMinimum(0);
        progressBar.setMaximum(newsItemFactory.getTotalItems());

        viewPortPanel = new XPanel();
        viewPortPanel.setLayout(new BoxLayout(viewPortPanel, BoxLayout.Y_AXIS));

        System.out.println("News item factory has next: " + newsItemFactory.hasNext());
        newsItemFactory.setCurrentItem(0);
        newsItemFactory.setHasNext(true);
        while(newsItemFactory.hasNext()){

            System.out.println("Creating item");
            NewsItem newsItem = newsItemFactory.createNewsItem();
            progressBar.setValue(newsItemFactory.getCurrentItem());
            viewPortPanel.add(newsItem);

            JPanel divider = new JPanel();
            divider.setMaximumSize(new Dimension(0, 90));
            divider.setMinimumSize(new Dimension(0, 90));
            viewPortPanel.add(divider);

        }

        setViewportView(viewPortPanel);

    }
}
