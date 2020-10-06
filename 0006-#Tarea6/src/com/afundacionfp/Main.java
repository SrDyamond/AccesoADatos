package com.afundacionfp;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String args[]) {
        try {
             File archivo = new File("C:\\Users\\Lenovo\\Desktop\\ListaViajes.xml");
           // File archivo = new File("C:\\Users\\dyang\\OneDrive\\Escritorio\\Viajes.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();
            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
            NodeList Viajes = document.getElementsByTagName("Viaje");
            for (int temp = 0; temp < Viajes.getLength(); temp++) {
                Node nodo = Viajes.item(temp);
                System.out.println("Elemento:" + nodo.getNodeName());
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    System.out.println("CiudadOrigen: " + element.getElementsByTagName("CiudadOrigen").item(0).getTextContent());
                    System.out.println("CiudadDestino: " + element.getElementsByTagName("CiudadDestino").item(0).getTextContent());
                    if ((element.getElementsByTagName("ListaParadas").item(0).getTextContent()) != null) {
                        System.out.println("ListaParadas: " + element.getElementsByTagName("ListaParadas").item(0).getTextContent());
                        if ((element.getElementsByTagName("Parada").item(0).getTextContent()) != null) {
                            System.out.println("Parada: " + element.getElementsByTagName("Parada").item(0).getTextContent());
                            System.out.println("Ciudad: " + element.getElementsByTagName("Ciudad").item(0).getTextContent());
                        }
                    }
                    System.out.println("TiempoDescanso: " + element.getElementsByTagName("TiempoDescanso").item(0).getTextContent());
                    System.out.println("TiempoViaje: " + element.getElementsByTagName("TiempoViaje").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}