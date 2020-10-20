package com.afundacionfp;

public class Main {

    public static void main(String[] args) {
        Persona persona1 = new Persona("Agustín", "Gutiérrez Beira");
        Persona persona2 = persona1;
        System.out.println(persona1);
        System.out.println(persona2);
        persona1.setNombre("Pepe");
        System.out.println(persona1);
        System.out.println(persona2);


    }
}
