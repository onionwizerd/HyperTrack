package com.company.news;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Josh Beaver
 * @version 0.1
 * @since 2016-03-16
 *
 * <h1>News Item Factory</h1>
 * <p></p>
 *
 */

public class NewsItemFactory {

    private boolean hasNext = true;

    private int currentItem;
    private int totalItems;

    NodeList postList;

    public NewsItemFactory() {

        try {
            DocumentBuilderFactory docBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = (DocumentBuilderFactory.newInstance())
                    .newDocumentBuilder();

            // For online XML document
            //Document doc = docBuilder.parse(new URL("http://52.37.148.1/news.xml").openStream());

            //For local XML document
            Document doc = docBuilder.parse(new File("usr/news.xml"));
            doc.normalize();

            Node rootNode = doc.getDocumentElement();
            Element rootElement = (Element) rootNode;
            postList = doc.getElementsByTagName("post");

            Node verNode = rootElement.getElementsByTagName("version").item(0);
            Element verElement = (Element) verNode;
            String ver = verElement.getTextContent();
            System.out.println(ver);

            currentItem = 0;
            totalItems = postList.getLength();
            System.out.println("Total items: " + totalItems);


        } catch (ParserConfigurationException parserConfigExcep) {
            System.out.println("Parser Config Exception");
        } catch (IOException ioExcep) {
            System.out.println("IO Exception");
            ioExcep.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public NewsItem createNewsItem(){
        Node postNode = postList.item(currentItem);
        Element postElement = (Element)postNode;

        System.out.println("Currently at news item: " + currentItem);

        Node titleNode = postElement.getElementsByTagName("title").item(0);
        Element titleElement = (Element)titleNode;
        String title = titleElement.getTextContent();

        Node imageNode = postElement.getElementsByTagName("image").item(0);
        Element imageElement = (Element)imageNode;
        BufferedImage image = null;
        try{
            image = ImageIO.read((new URL(imageElement.getTextContent())));
        }catch(IOException ioExcep){
            ioExcep.printStackTrace();
        }

        Node descNode = postElement.getElementsByTagName("desc").item(0);
        Element descElement = (Element)descNode;
        String desc = descElement.getTextContent();

        Node urlNode = postElement.getElementsByTagName("url").item(0);
        Element urlElement = (Element)urlNode;
        String url = urlElement.getTextContent();

        NewsItem newsItem = new NewsItem(title, desc, url, image);

        currentItem++;

        return newsItem;
    }


    public boolean hasNext(){

        if(currentItem == totalItems){
            hasNext = false;
        }

        return hasNext;
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}

