package com.afundacionfp;

import org.json.simple.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Parada {
    private String ciudad;
    private int tiempoDescanso;

    public Parada(Element paradaElement) {
        Node nodoCiudad = paradaElement.getElementsByTagName("Ciudad").item(0);
        this.ciudad = nodoCiudad.getTextContent();
        Node nodoCiudadDestino = paradaElement.getElementsByTagName("TiempoDescanso").item(0);
        this.tiempoDescanso = Integer.parseInt(nodoCiudadDestino.getTextContent());
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("ciudad", this.ciudad);
        json.put("tiempoDescanso", this.tiempoDescanso);
        return json;
    }
}