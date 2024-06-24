package com.example.gestiontienda.modelo;

public class Producto {
    String nombre,caducidad;
    Double precio;

    public Producto() {
    }

    public Producto(String nombre, Double precio, String caducidad) {
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(String caducidad) {
        this.caducidad = caducidad;
    }

}
