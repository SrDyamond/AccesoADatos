package com.afundacionfp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Lenovo\\Desktop\\ArchivoNombres.txt");
        FileReader reader = new FileReader(file);
        int i = reader.read();
        while (i != -1) {
            char character = (char) i;
            System.out.print(character);
            // Read the next character
            i = reader.read();
        }
        reader.close();
    }
}
