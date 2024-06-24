package com.example.gestiontienda.modelo;

public class Proveedor {
    String  cif, correo, nombre;

    public Proveedor(){
    }

    public Proveedor(String cif, String correo, String nombre) {
        this.cif = cif;
        this.correo = correo;
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
