package com.afundacionfp;

import java.util.Objects;

public  class Persona  {
    public String nombre;
    public String apellidos;
    public Curso curso;



    @Override
    public boolean equals(Object obj) {
        return this.nombre.equals(((Persona) obj).getNombre());
    }

    public Persona(String nombre, String apellidos, Curso curso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
    }

    public boolean esInteligente() {
        return this.curso == Curso.DAM2;
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