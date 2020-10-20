package com.afundacionfp;

public class Main {

    public static void main(String[] args) {
        Persona persona1 = new Persona("Agustín", "Gutiérrez Beira");
        Persona persona2 = new Persona("Jaime", "Gutiérrez Beira");

        if (persona1.equals(persona2)) {
            System.out.println("Tienen el mismo nombre y apellidos");
        } else {
            System.out.println("No tienen el mismo nombre y apellidos");
        }
        persona1.setNombre("Jaime");
        System.out.println("Y después del cambio de nombre...");
        if (persona1.equals(persona2)) {
            System.out.println("Tienen el mismo nombre y apellidos");
        } else {
            System.out.println("No tienen el mismo nombre y apellidos");
        }

    }
}
