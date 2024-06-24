package com.example.gestiontienda.modelo;

public class Pedido {
    String fecha, productoagregado, proveedoragregado;
    Integer npedido, cantidad;

    public Pedido() {
    }

    public Pedido(Integer npedido, Integer cantidad, String fecha, String productoagregado, String proveedoragregado) {
        this.npedido = npedido;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.productoagregado = productoagregado;
        this.proveedoragregado = proveedoragregado;
    }

    public String getProveedoragregado() {
        return proveedoragregado;
    }

    public void setProveedoragregado(String proveedoragregado) {
        this.proveedoragregado = proveedoragregado;
    }

    public Integer getNpedido() {
        return npedido;
    }

    public void setNpedido(Integer npedido) {
        this.npedido = npedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getProductoagregado() {
        return productoagregado;
    }

    public void setProductoagregado(String productoagregado) {
        this.productoagregado = productoagregado;
    }
}
