package com.afundacionfp;

import java.util.Objects;

public  class Persona  {
    public String nombre;
    public String apellidos;
    public Curso curso;

    public Persona(String nombre, String apellidos, String cursoString) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        if (cursoString.equals("OTC1")) {
            this.curso = Curso.OTC1;
        } else if (cursoString.equals("OTC2")) {
            this.curso = Curso.OTC2;
        } else if (cursoString.equals("AML1")) {
            this.curso = Curso.AML1;
        } else if (cursoString.equals("DAM2")) {
            this.curso = Curso.DAM2;
        } else {
            throw new CursoInvalidoException();
        }
    }


    @Override
    public boolean equals(Object obj) {
        return this.nombre.equals(((Persona) obj).getNombre());
    }

    public Persona(String nombre, String apellidos, Curso curso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
    }

    @Override
    public String toString() {

        return nombre + apellidos;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {

        this.apellidos = apellidos;
    }

    public String getApellidos() {

        return apellidos;
    }

    public String getNombre() {

        return nombre;
    }





}