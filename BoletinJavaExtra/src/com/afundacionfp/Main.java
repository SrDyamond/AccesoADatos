package com.afundacionfp;

public class Main {

    public static void main(String[] args) {
        Persona arrPersona[] = new Persona[3];

        arrPersona[0] = new Persona("Agustin","Gutierrez Beira");
        arrPersona[1] = new Persona("Jaime","Gutierrez Beira");
        arrPersona[2] = new Persona("Laura","Dominguez Caballero");


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

    }
}
