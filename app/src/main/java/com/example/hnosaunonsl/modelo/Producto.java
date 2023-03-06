package com.example.hnosaunonsl.modelo;

import java.util.Date;

public class Producto {
    String nombre,precio,caducidad;


    public Producto() {
    }

    public Producto(String nombre, String precio, String caducidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.caducidad = caducidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

}
