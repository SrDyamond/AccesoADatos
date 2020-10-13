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

        int viajesTotales = 0;
        String tiempo = null, ciudad = null;

        try {
            // File archivo = new File("C:\\Users\\Lenovo\\Desktop\\ListaViajes.xml");
            File archivo = new File("C:\\Users\\dyang\\OneDrive\\Escritorio\\Viajes.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();
            System.out.println("Raiz: " + doc.getDocumentElement().getNodeName());
            NodeList raiz = doc.getElementsByTagName("Viaje");
            System.out.println("----------------------------");

            for (int temp = 0; temp < raiz.getLength(); temp++) {
                Node node = raiz.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    System.out.println("Ciudad de origen: " + element.getElementsByTagName("CiudadOrigen").item(0).getTextContent());

                    System.out.println("Ciudad de destino: " + element.getElementsByTagName("CiudadDestino").item(0).getTextContent());

                    try {

                        NodeList resultNodeList = element.getElementsByTagName("Parada");
                        int resultNodeListSize = resultNodeList.getLength();
                        for(int j = 0 ; j < resultNodeListSize ; j++ )
                        {
                            Node resultNode = resultNodeList.item(j);
                            if(resultNode.getNodeType() == Node.ELEMENT_NODE)
                            {
                                Element resultE = (Element) resultNode;
                                tiempo = resultE.getTextContent();
                                System.out.println(tiempo);
                            }
                        }
                    }
                    catch(NullPointerException e)
                    {
                        System.out.println("No existen paradas");
                    }

                    System.out.println("Tiempo del viaje: "
                            + element
                            .getElementsByTagName("TiempoViaje")
                            .item(0)
                            .getTextContent());

                    viajesTotales++;

                    System.out.print("\n");

                }
            }

            System.out.println("----------------------------");
            System.out.println("Viajes totales: " + viajesTotales);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}