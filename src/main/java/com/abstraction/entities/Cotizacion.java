package com.abstraction.entities;

import java.util.ArrayList;
import java.util.Date;

public class Cotizacion {
    private Long numero;
    private String nombre;
    private Date fecha;
    private float precio;
    private String nombreCliente;
    private int archivado;

    private ArrayList<CotizacionProducto> productos;


    public Cotizacion(Long numero, String nombre, Date fecha, float precio, String nombreCliente, int archivado) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
        this.nombreCliente = nombreCliente;
        this.archivado = archivado;
    }

    public Cotizacion(String nombre, Date fecha, float precio, String nombreCliente, int archivado) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
        this.nombreCliente = nombreCliente;
        this.archivado = archivado;
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

    public int getArchivado() {
        return archivado;
    }

    public void setArchivado(int archivado) {
        this.archivado = archivado;
    }

    public ArrayList<CotizacionProducto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<CotizacionProducto> productos) {
        this.productos = productos;
    }
}
