package com.company.nutrition.recipe;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Josh on 2016-04-14.
 */
public class RecipeFactory {

    private boolean hasNext = true;

    private int currentItem;
    private int totalItems;

    NodeList recipesList;

    public RecipeFactory() {

        try {
            DocumentBuilderFactory docBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = (DocumentBuilderFactory.newInstance())
                    .newDocumentBuilder();

            Document doc = docBuilder.parse(new File("recipes.xml"));
            doc.normalize();

            Node rootNode = doc.getDocumentElement();
            Element rootElement = (Element) rootNode;
            recipesList = doc.getElementsByTagName("recipe");

            currentItem = 0;
            totalItems = recipesList.getLength();
            System.out.println("Total items: " + totalItems);


        } catch (ParserConfigurationException parserConfigExcep) {
            System.out.println("Parser Config Exception");
        } catch (IOException ioExcep) {
            System.out.println("IO Exception");
            ioExcep.printStackTrace();
        }catch(Exception e){}

    }


    public RecipeItem createRecipeItem(){
        Node recipeNode = recipesList.item(currentItem);
        Element recipeElement = (Element)recipeNode;

        System.out.println("Currently at recipe item: " + currentItem);

        Node titleNode = recipeElement.getElementsByTagName("title").item(0);
        Element titleElement = (Element)titleNode;
        String title = titleElement.getTextContent();

        // Keeping in case I add image to Recipe Item
        /*
        Node imageNode = postElement.getElementsByTagName("image").item(0);
        Element imageElement = (Element)imageNode;
        BufferedImage image = null;
        try{
            image = ImageIO.read((new URL(imageElement.getTextContent())));
        }catch(IOException ioExcep){
            ioExcep.printStackTrace();
        }
        */

        Node descNode = recipeElement.getElementsByTagName("description").item(0);
        Element descElement = (Element)descNode;
        String desc = descElement.getTextContent();

        Node contentNode = recipeElement.getElementsByTagName("content").item(0);
        Element contentElement = (Element)contentNode;
        String content = contentElement.getTextContent();

        RecipeItem recipeItem = new RecipeItem(title, desc, content);

        currentItem++;

        return recipeItem;
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
