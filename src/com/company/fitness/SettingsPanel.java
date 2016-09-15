package com.company.fitness;

import SwingX.components.*;
import com.company.PanelModel;
import com.company.fitness.settings.TerrainComponent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by josh on 2016/08/15.
 */
public class SettingsPanel extends XScrollPanel implements PanelModel, FitnessPanelModel{

    private String tableName = "";

    private FitnessPanel parentPanel;

    public SettingsPanel(String tableName) {
        this.tableName = tableName;
        init();
    }

    @Override
    public void init() {

        XPanel rootPanel = new XPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));

        XPanel terrainPanel = new XPanel();
        terrainPanel.setLayout(new BoxLayout(terrainPanel, BoxLayout.Y_AXIS));

        XLabel terrainsLbl = new XLabel("Terrians");
        terrainsLbl.setFont(new Font(terrainsLbl.getName(), Font.PLAIN, 24));

        terrainPanel.add(new XDivider(0, 10));
        terrainPanel.add(terrainsLbl);

        XPanel terrainComponentsPanel = new XPanel();
        terrainComponentsPanel.setLayout(new BoxLayout(terrainComponentsPanel, BoxLayout.Y_AXIS));

        addTerrains(terrainComponentsPanel);

        XButton addTerrainBtn = new XButton("Add");
        addTerrainBtn.addActionListener(e -> {
            terrainComponentsPanel.add(new TerrainComponent("", tableName, null));
            terrainComponentsPanel.add(new XDivider(0, 5));
            terrainComponentsPanel.refresh();
        });

        terrainPanel.add(terrainComponentsPanel);
        terrainPanel.add(addTerrainBtn);

        XPanel presetsPanel = new XPanel();
        presetsPanel.setLayout(new BoxLayout(presetsPanel, BoxLayout.Y_AXIS));

        XLabel presetsLbl = new XLabel("Presets");
        presetsLbl.setFont(new Font(terrainsLbl.getName(), Font.PLAIN, 24));

        presetsPanel.add(new XDivider(0, 10));
        presetsPanel.add(presetsLbl);       

        XPanel presetComponentsPanel = new XPanel();
        presetComponentsPanel.setLayout(new BoxLayout(presetComponentsPanel, BoxLayout.Y_AXIS));

        addPresets(presetComponentsPanel);

        XButton addPresetBtn = new XButton("Add");

        presetsPanel.add(presetComponentsPanel);
        presetsPanel.add(addPresetBtn);

        rootPanel.add(terrainPanel);
        rootPanel.add(new XDivider(0, 15));
        rootPanel.add(presetsPanel);

        setViewportView(rootPanel);


        refresh();
    }

    @Override
    public void setParentPanel(FitnessPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    private void addTerrains(XPanel terrainPanel){
        try {
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

            NodeList terrainsNodeList = terrainsElement.getElementsByTagName("terrain");

            for(int i = 0; i < terrainsNodeList.getLength(); i++){

                Node terrainNode = terrainsNodeList.item(i);
                Element terrainElement = (Element)terrainNode;

                terrainPanel.add(new TerrainComponent(terrainElement.getAttribute("name"), tableName, terrainNode));
                terrainPanel.add(new XDivider(0, 5));
            }


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

    private void addPresets(XPanel presetsPanel){
        try {
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

            NodeList terrainsNodeList = terrainsElement.getElementsByTagName("terrain");

            for(int i = 0; i < terrainsNodeList.getLength(); i++){

                Node terrainNode = terrainsNodeList.item(i);
                Element terrainElement = (Element)terrainNode;

                presetsPanel.add(new TerrainComponent(terrainElement.getAttribute("name"), tableName, terrainNode));
                presetsPanel.add(new XDivider(0, 5));
            }


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
}
