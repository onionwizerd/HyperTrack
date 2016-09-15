package com.company.fitness.settings;

import SwingX.components.XButton;
import SwingX.components.XDivider;
import SwingX.components.XPanel;
import SwingX.components.XTextField;
import com.company.PanelModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by josh on 2016/08/17.
 */
public class PresetComponent extends XPanel implements PanelModel {


    private String name;
    private String tableName;
    private Node node;


    public PresetComponent(String name, String tableName, Node node) {
        this.name = name;
        this.tableName = tableName;
        this.node = node;
        init();
    }

    @Override
    public void init() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        XTextField nameField = new XTextField(name);
        nameField.setMinimumSize(new Dimension(150, 27));
        nameField.setMaximumSize(new Dimension(150, 27));
        nameField.setEditable(false);

        XButton editBtn  = new XButton("Edit");
        editBtn.addActionListener(e -> {
            nameField.setEditable(true);
        });

        XButton deleteBtn = new XButton("Delete");
        deleteBtn.addActionListener(e -> {
            removeNode();
        });

        XButton saveBtn = new XButton("Save");
        saveBtn.addActionListener(e -> {
            nameField.setEditable(false);
        });

        add(nameField);
        add(new XDivider(5, 0));
        add(editBtn);
        add(new XDivider(5, 0));
        add(deleteBtn);
        add(new XDivider(5, 0));
        add(saveBtn);

    }

    protected void removeNode(){
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

            Element nodeElement = (Element) node;

            for(int i = 0; i < terrainsNodeList.getLength(); i++){

                Node terrainNode = terrainsNodeList.item(i);
                Element terrainElement = (Element)terrainNode;

                if(terrainElement.getAttribute("name").equals(nodeElement.getAttribute("name"))){
                    System.out.println("Node found");

                    terrainsNode.removeChild(terrainNode);

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    System.out.println("-----------Modified File-----------");
                    StreamResult consoleResult = new StreamResult(System.out);
                    transformer.transform(source, consoleResult);
                }else {
                    JOptionPane.showMessageDialog(null, "Could not remove element");
                }

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

    @Override
    public String getName() {
        return name;
    }

    public Node getNode() {
        return node;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
