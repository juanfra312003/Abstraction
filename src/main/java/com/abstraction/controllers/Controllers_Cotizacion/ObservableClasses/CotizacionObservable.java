package com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses;

import com.abstraction.entities.CotizacionProducto;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Date;

public class CotizacionObservable {
    private Long numero;
    private String nombre;
    private Date fecha;
    private float precio;

    Button botonVer;
    Button botonActualizar;
    Button botonGenerarPedido;
    Button botonArchivar;

    public CotizacionObservable(Long numero, String nombre, Date fecha, float precio, Button botonVer, Button botonActualizar, Button botonGenerarPedido, Button botonArchivar) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
        this.botonVer = botonVer;
        this.botonActualizar = botonActualizar;
        this.botonGenerarPedido = botonGenerarPedido;
        this.botonArchivar = botonArchivar;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Button getBotonVer() {
        return botonVer;
    }

    public void setBotonVer(Button botonVer) {
        this.botonVer = botonVer;
    }

    public Button getBotonActualizar() {
        return botonActualizar;
    }

    public void setBotonActualizar(Button botonActualizar) {
        this.botonActualizar = botonActualizar;
    }

    public Button getBotonGenerarPedido() {
        return botonGenerarPedido;
    }

    public void setBotonGenerarPedido(Button botonGenerarPedido) {
        this.botonGenerarPedido = botonGenerarPedido;
    }

    public Button getBotonArchivar() {
        return botonArchivar;
    }

    public void setBotonArchivar(Button botonArchivar) {
        this.botonArchivar = botonArchivar;
    }
}
