package com.afundacionfp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (input.equals("salir") == false) {
            System.out.println("Teclea un nombre o 'salir' para terminar");
            input = scanner.nextLine();
            System.out.println("Tecleaste: " + input);
        }

        System.out.println("Terminando el programa...");
    }
}
