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

    private String title = "";
    private String time = "";
    private String servings = "";
    private String calories = "";
    private String diet = "";

    private NodeList ingredientsList;
    private NodeList directionsList;

    public RecipePanelBuilder(String recipeName) {

        try {
            DocumentBuilderFactory docBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = (DocumentBuilderFactory.newInstance())
                    .newDocumentBuilder();

            String rootDirectotyName = "";

            switch(System.getProperty("os.name")){
                case "Linux":
                    rootDirectotyName = "usr/recipes/";
                    break;
                case "Windows":
                    rootDirectotyName = "usr\\recipes\\";
                    break;
                default:
                    rootDirectotyName = "usr\\recipes\\";
                    break;

            }

            Document doc = docBuilder.parse(new File(rootDirectotyName + recipeName));
            doc.normalize();

            Node rootNode = doc.getDocumentElement();
            Element rootElement = (Element) rootNode;

            Node titleNode = rootElement.getElementsByTagName("title").item(0);
            Element titleElement = (Element)titleNode;

            title = titleElement.getAttribute("text");

            Node timeNode = rootElement.getElementsByTagName("time").item(0);
            Element timeElement = (Element)timeNode;

            time = timeElement.getAttribute("text");

            Node servingsNode = rootElement.getElementsByTagName("servings").item(0);
            Element servingsElement = (Element)servingsNode;

            servings = servingsElement.getAttribute("text");

            Node caloriesNode = rootElement.getElementsByTagName("calories").item(0);
            Element caloriesElement = (Element)caloriesNode;

            calories = caloriesElement.getAttribute("text");


            Node dietNode = rootElement.getElementsByTagName("diet").item(0);
            Element dietElement = (Element)dietNode;

            diet = dietElement.getAttribute("text");

            Node ingredientsNode = rootElement.getElementsByTagName("ingredients").item(0);
            Element ingredientsElement = (Element)ingredientsNode;

            ingredientsList = ingredientsElement.getElementsByTagName("item");

            Node directionsNode = rootElement.getElementsByTagName("directions").item(0);
            Element directionsElement = (Element)directionsNode;

            directionsList = directionsElement.getElementsByTagName("item");




        } catch (ParserConfigurationException parserConfigExcep) {
            System.out.println("Parser Config Exception");
        } catch (IOException ioExcep) {
            System.out.println("IO Exception");
            ioExcep.printStackTrace();
        }catch(Exception e){}

    }

    public RecipePanel buildRecipePanel(){
        RecipePanel recipePanel = new RecipePanel();

        recipePanel.setTitle(title + "  ");
        recipePanel.setTime(time + "  ");
        recipePanel.setServings(servings + "  ");
        recipePanel.setCalories(calories + "  ");
        recipePanel.setDiet(diet + "  ");

        Node ingredientsNode;

        for(int i = 0; i < ingredientsList.getLength(); i++){
            ingredientsNode = ingredientsList.item(i);
            Element ingredientsElement = (Element)ingredientsNode;
            recipePanel.addIngredient(ingredientsElement.getAttribute("text"));
        }

        Node directionNode;

        for(int i = 0; i < directionsList.getLength(); i++){
            directionNode = directionsList.item(i);
            Element directionElement = (Element)directionNode;
            recipePanel.addDirection(directionElement.getAttribute("text"));
        }

        return recipePanel;
    }

}
