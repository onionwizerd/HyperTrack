package com.company.data.dataentry;

import com.company.data.dataentry.components.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Josh on 5/6/2016.
 */
public class DataEntryFrameFactory {

    private NodeList terrainsNodeList;

    public DataEntryFrameFactory(String tableName) {

        try {
            DocumentBuilderFactory docBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = (DocumentBuilderFactory.newInstance())
                    .newDocumentBuilder();

            Document doc = docBuilder.parse(new File("usr/dataconfig.xml"));
            doc.normalize();

            Node rootNode = doc.getDocumentElement();
            Element rootElement = (Element) rootNode;

            Node sportNode = doc.getElementsByTagName(tableName).item(0);
            Element sportElement = (Element)sportNode;

            Node terrainsNode = sportElement.getElementsByTagName("terrains").item(0);
            Element terrainsElement = (Element)terrainsNode;

            terrainsNodeList = terrainsElement.getElementsByTagName("terrain");


        } catch (ParserConfigurationException parserConfigExcep) {
            System.out.println("Parser Config Exception");
            parserConfigExcep.printStackTrace();
        } catch (IOException ioExcep) {
            System.out.println("IO Exception");
            ioExcep.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public DataEntryFrame createDataEntryFrame(){

        DataEntryFrame dataEntryFrame = new DataEntryFrame();

        DateComponent dateComponent = new DateComponent();
        dataEntryFrame.addLabel(new ComponentLabel("Date: (YYYY-MM-DD) "));
        dataEntryFrame.addComponent(dateComponent);
        ComponentManager.dateComponent = dateComponent;

        DistanceComponent distanceComponent = new DistanceComponent();
        dataEntryFrame.addLabel(new ComponentLabel("Distance: (Km.m)"));
        dataEntryFrame.addComponent(distanceComponent);
        ComponentManager.distanceComponent = distanceComponent;

        TimeComponent timeComponent = new TimeComponent();
        dataEntryFrame.addLabel(new ComponentLabel("Time: (HH:MM:SS)"));
        dataEntryFrame.addComponent(timeComponent);
        ComponentManager.timeComponent = timeComponent;

        SpeedComponent speedComponent = new SpeedComponent();
        dataEntryFrame.addLabel(new ComponentLabel("Speed: (Km/h)"));
        dataEntryFrame.addComponent(speedComponent);
        ComponentManager.speedComponent = speedComponent;

        if(terrainsNodeList.getLength() > 0){
            ArrayList<String> terrainsList = new ArrayList<>();

            for (int i = 0 ; i <= terrainsNodeList.getLength()-1; i++){
                Node terrainNode = terrainsNodeList.item(i);
                Element terrainElement = (Element)terrainNode;

                terrainsList.add(terrainElement.getAttribute("name"));

            }

            TerrainComponent terrainComponent = new TerrainComponent(terrainsList);

            dataEntryFrame.addLabel(new ComponentLabel("Terrain: "));
            dataEntryFrame.addComponent(new TerrainComponent(terrainsList));

        }

        return dataEntryFrame;
    }
}
