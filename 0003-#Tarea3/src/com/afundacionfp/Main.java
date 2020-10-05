package com.afundacionfp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File file = new File("C:\\Users\\Lenovo\\Desktop\\ArchivoNombres.txt");
        String input = "";

        FileWriter fileWriter = new FileWriter(file);

        while (input.equals("salir") == false) {
            System.out.println("Teclea un nombre o 'salir' para terminar");
            input = scanner.nextLine();
            System.out.println("Tecleaste: " + input);
            System.out.println("Ahora se va a guardar al fichero...");
            if (input.equals(("salir")) == false) {
                fileWriter.write("\n");
                fileWriter.write(input);
                System.out.println("Se ha escrito al fichero.");
            }
        }

        fileWriter.close();
        System.out.println("Terminando el programa...");
    }
}