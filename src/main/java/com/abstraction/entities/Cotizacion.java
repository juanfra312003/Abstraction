package com.abstraction.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "Cotizacion", schema = "abstraction")
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero;
    private String nombre;
    private Date fecha;
    private float precio;
    private String nombreCliente;

    @OneToMany
    private ArrayList<CotizacionProducto> productos;


    public Cotizacion() {
    }

    public Cotizacion(Long numero, String nombre, Date fecha, float precio, String nombreCliente, ArrayList<CotizacionProducto> productos) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
        this.nombreCliente = nombreCliente;
        this.productos = productos;
    }

    public Long getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public ArrayList<CotizacionProducto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<CotizacionProducto> productos) {
        this.productos = productos;
    }
}
