package com.afundacionfp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, CursoInvalidoException {
        final int NUMERO_ALUMNOS = 10;
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Persona> personas = new ArrayList<>();

        for (int i = 0; i<NUMERO_ALUMNOS; i++) {
            System.out.println("Introduce el nombre del alumno " + i);
            String nombre = reader.readLine();
            System.out.println("Introduce los apellidos del alumno " + i);
            String apellidos = reader.readLine();
            System.out.println("Introduce el curso del alumno " + i);
            String cursoString = reader.readLine();
            personas.add(new Persona(nombre, apellidos, cursoString));
        }
 }
}
