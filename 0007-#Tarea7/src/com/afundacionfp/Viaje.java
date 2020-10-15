package com.afundacionfp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Viaje {
    private String ciudadOrigen;
    private String ciudadDestino;
    private List<Parada> listaParadas;
    private int tiempoViaje;

    // Constructor, pero en vez de recibir las variables directamente, recibe
    // un elemento XML.
    public Viaje(Element viajeElement) {
        this.listaParadas = new ArrayList<>();

        Node nodoCiudadOrigen = viajeElement.getElementsByTagName("CiudadOrigen").item(0);
        this.ciudadOrigen = nodoCiudadOrigen.getTextContent();
        Node nodoCiudadDestino = viajeElement.getElementsByTagName("CiudadDestino").item(0);
        this.ciudadDestino = nodoCiudadDestino.getTextContent();
        // Extraemos la lista de nodos <Parada>
        NodeList listaParadas = viajeElement.getElementsByTagName("Parada");
        for (int i = 0; i < listaParadas.getLength(); i++) {
            Node nodoParada = listaParadas.item(i);
            if (nodoParada.getNodeType() == Node.ELEMENT_NODE) {
                Element paradaElement = (Element) nodoParada;
                this.listaParadas.add(new Parada(paradaElement));
            }
        }
        Node nodoTiempoViaje = viajeElement.getElementsByTagName("TiempoViaje").item(0);
        this.tiempoViaje = Integer.parseInt((nodoTiempoViaje.getTextContent()));
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("ciudadOrigen", this.ciudadOrigen);
        json.put("ciudadDestino", this.ciudadDestino);
        // Creamos una nueva variable, un array JSON
        JSONArray jsonParadas = new JSONArray();
        for (Parada parada: this.listaParadas) {
            // Cada parada la almacenamos en el array JSON
            jsonParadas.add(parada.toJSONObject());
        }
        json.put("listaParadas", jsonParadas);
        json.put("tiempoViaje", this.tiempoViaje);
        return json;
    }
}