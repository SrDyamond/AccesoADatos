package com.afundacionfp;

import org.json.simple.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class ListaViajes {
    private List<Viaje> listaViajes;

    // Constructor, pero en vez de recibir las variables directamente,
    // recibe un documento XML.
    public ListaViajes(Document doc) throws ListaViajesParsingException {
        this.listaViajes = new ArrayList<>();

        Element root = doc.getDocumentElement();
        if (root.getNodeName().equals("ListaViajes")) {
            NodeList listaViajes = doc.getElementsByTagName("Viaje");
            for (int i = 0; i < listaViajes.getLength(); i++) {
                Node nodoViaje = listaViajes.item(i);
                if (nodoViaje.getNodeType() == Node.ELEMENT_NODE) {
                    Element viajeElement = (Element) nodoViaje;
                    this.listaViajes.add(new Viaje(viajeElement));
                }
            }
        } else {
            throw new ListaViajesParsingException();
        }
    }

    public JSONArray toJSONArray() {
        JSONArray json = new JSONArray();
        for (Viaje viaje: this.listaViajes) {
            // Cada viaje la almacenamos en el array JSON
            json.add(viaje.toJSONObject());
        }
        return json;
    }
}