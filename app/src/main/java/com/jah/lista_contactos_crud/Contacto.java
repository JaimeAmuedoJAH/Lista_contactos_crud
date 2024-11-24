package com.jah.lista_contactos_crud;

import java.io.Serializable;

public class Contacto implements Serializable {

    private String nombre;
    private int telefono;
    private int fotoContacto;

    public Contacto(){}

    public Contacto(String nombre, int telefono, int fotoContacto) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fotoContacto = fotoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getFotoContacto() {
        return fotoContacto;
    }

    public void setFotoContacto(int fotoContacto) {
        this.fotoContacto = fotoContacto;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", fotoContacto=" + fotoContacto +
                '}';
    }
}
