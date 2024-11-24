package com.jah.lista_contactos_crud;

import java.util.ArrayList;
import java.util.List;

public class GestionContactos {

    private static List<Contacto> arrContactos;

    public GestionContactos(){}

    public static List<Contacto> getArrContactos() {
        return arrContactos;
    }

    public static void setArrContactos(List<Contacto> arrContactos) {
        GestionContactos.arrContactos = arrContactos;
    }

    public static void anhadirContacto(Contacto contacto){
        arrContactos.add(contacto);
        GestionContactos.setArrContactos(arrContactos);
    }

    public static void editarContacto(int position, Contacto contacto){
        arrContactos.set(position, contacto);
        GestionContactos.setArrContactos(arrContactos);
    }

    public static void borrarContacto(int position){
        arrContactos.remove(position);
        GestionContactos.setArrContactos(arrContactos);
    }

    public static void ordenarPorNombre(){
        arrContactos.sort((c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()));
        GestionContactos.setArrContactos(arrContactos);
    }

    public static void cargarContactos(){
        GestionContactos.arrContactos = new ArrayList<>();
        int telefono = 600123456;
        Contacto c;
        c = new Contacto("Jaime Amuedo", telefono, R.drawable.imagen5);
        arrContactos.add(c);
        telefono = 600123453;
        c = new Contacto("Paco chocolatero", telefono, R.drawable.imagen1);
        arrContactos.add(c);
        telefono = 600123459;
        c = new Contacto("Paca Pacheco", telefono, R.drawable.imagen2);
        arrContactos.add(c);
        telefono = 600123450;
        c = new Contacto("Josefina López", telefono, R.drawable.imagen3);
        arrContactos.add(c);
        telefono = 600123452;
        c = new Contacto("Churumbe Heredia", telefono, R.drawable.imagen4);
        arrContactos.add(c);
        telefono = 600123457;
        c = new Contacto("Isabel Pantoja", telefono, R.drawable.imagen6);
        arrContactos.add(c);

        GestionContactos.setArrContactos(arrContactos);
    }
}
