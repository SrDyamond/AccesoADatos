package com.afundacionfp;

import java.util.Objects;

public abstract class Persona  {
    public String nombre;
    public String apellidos;

    public abstract boolean esInteligente() {

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return this.nombre.equals(((Persona) obj).getNombre());
    }

    public Persona(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
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