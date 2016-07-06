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
public class RecipePanelBuilder {

    public RecipePanelBuilder(String recipeName) {

        try {
            DocumentBuilderFactory docBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = (DocumentBuilderFactory.newInstance())
                    .newDocumentBuilder();

            Document doc = docBuilder.parse(new File("usr\\recipes\\" + recipeName));
            doc.normalize();

            Node rootNode = doc.getDocumentElement();
            Element rootElement = (Element) rootNode;

            Node titleNode = rootElement.getElementsByTagName("title").item(0);
            Element titleElement = (Element)titleNode;

            //System.out.println(titleElement.getTextContent());


        } catch (ParserConfigurationException parserConfigExcep) {
            System.out.println("Parser Config Exception");
        } catch (IOException ioExcep) {
            System.out.println("IO Exception");
            ioExcep.printStackTrace();
        }catch(Exception e){}

    }

    public RecipePanel buildRecipePanel(){
        RecipePanel recipePanel = new RecipePanel();

        return recipePanel;
    }

}
