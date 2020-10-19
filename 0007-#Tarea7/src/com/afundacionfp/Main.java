package com.afundacionfp;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Vamos a parsear lista_viajes.xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();


        File inputFile = new File("C:\\Users\\Lenovo\\Desktop\\lista_viajes.xml");
        //File inputFile = new File("C:\\Users\\dyang\\OneDrive\\Escritorio\\lista_viajes.xml");
        Document doc = builder.parse(new FileInputStream(inputFile));

        ListaViajes listaViajes = null;
        try {
            listaViajes = new ListaViajes(doc);
        } catch (ListaViajesParsingException e) {
            e.printStackTrace();
        }

        File outputFile = new File("C:\\Users\\Lenovo\\Desktop\\lista_viajes.json");
        //File outputFile = new File("C:\\Users\\dyang\\OneDrive\\Escritorio\\lista_viajes.json");
        FileWriter fileWriter = new FileWriter(outputFile);
        // La siguiente línea guarda el JSON en el archivo.
        // No importa que esté todo en la misma línea, sigue siendo un JSON válido.
        listaViajes.toJSONArray().writeJSONString(fileWriter);
        fileWriter.close();
        System.out.println("Archivo JSON escrito correctamente");
    }
}