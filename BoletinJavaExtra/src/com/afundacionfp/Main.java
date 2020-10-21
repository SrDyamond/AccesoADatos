package com.afundacionfp;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        ArrayList<String> personas = new ArrayList();
        personas.add("Agustin Gutierrez Beira ");
        personas.add("Jaime Gutierrez Beira");
        personas.add("Laura Dominguez Caballero");
       // System.out.println(personas);

        System.out.println(personas.get(0));
        personas.remove(0);
        System.out.println(personas.get(0));
        /*
        Persona arrPersona[] = new Persona[3];

        arrPersona[0] = new Persona("Agustin","Gutierrez Beira");
        arrPersona[1] = new Persona("Jaime","Gutierrez Beira");
        arrPersona[2] = new Persona("Laura","Dominguez Caballero");
*/
        /*
        for (String p:personas)  {
            System.out.println(p); }
*/



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
