package com.afundacionfp;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>(
                List.of(
                        new Persona("Agustín", "Gutiérrez Beira", Curso.OTC1),
                        new Persona("Jaime", "Gutiérrez Beira", Curso.DAM2),
                        new Persona("Laura", "Domínguez Caballero", Curso.DAM2)
                ));


        for (Persona p:personas)  {
            if (p.esInteligente()) {
                System.out.println("La persona " + p.toString() + " es inteligente.");
            } else {
                System.out.println("La persona " + p.toString() + " no es inteligente.");
            }
        }
    }
}
