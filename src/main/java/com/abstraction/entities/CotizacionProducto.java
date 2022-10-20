package com.abstraction.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CotizacionProducto", schema = "abstraction")
public class CotizacionProducto{

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Producto_referencia")
    private Producto producto;
    private int cantidad;
    private float subtotal;


    public CotizacionProducto() {
    }

    public CotizacionProducto(Producto producto, int cantidad, float subtotal) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }



}
