package com.afundacionfp;

public class Main {

    public static void main(String[] args) {
        Persona instanciaPersona = new Persona("Agustín", "Gutiérrez Beira");
        System.out.println(instanciaPersona);



        int numero1 = 12;
        int numero2 = 13;
        if (numero1 == numero2) {
            System.out.println("Son el mismo número");
        } else {
            System.out.println("Son distintos números");
        }


    }
}
