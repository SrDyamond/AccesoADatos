package com.afundacionfp;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>(
                List.of(
                        new Persona("Agustín", "Gutiérrez Beira"),
                        new Persona("Jaime", "Gutiérrez Beira"),
                        new PersonaInteligente("Laura", "Domínguez Caballero")
                ));

        /*
        ArrayList<Persona> personas = new ArrayList<Persona>();
        Persona personas1 = new Persona("Agustin","Gutierrez Beira");
        Persona personas2 = new Persona("Jaime","Gutierrez Beira");
        Persona personas3 = new Persona("Laura","Dominguez Caballero");
        personas.add(personas1);
        personas.add(personas2);
        personas.add(personas3);
        */
       // System.out.println(personas);

        /*
        Persona arrPersona[] = new Persona[3]
*/

        for (Persona p:personas)  {
            if (p.esInteligente()) {
                System.out.println("La persona " + p.toString() + " es inteligente.");
            } else {
                System.out.println("La persona " + p.toString() + " no es inteligente.");
            }
        }

        /*
        if (arrPersona[0].equals(arrPersona[1])) {
            System.out.println("Tienen el mismo nombre y apellidos");
        } else {
            System.out.println("No tienen el mismo nombre y apellidos");
        }
        arrPersona[1].setNombre("Jaime");

        System.out.println("Y después del cambio de nombre...");
        if (arrPersona[1].equals(arrPersona[2])) {
            System.out.println("Tienen el mismo nombre y apellidos");
        } else {
            System.out.println("No tienen el mismo nombre y apellidos");
        }
*/
    }
}
