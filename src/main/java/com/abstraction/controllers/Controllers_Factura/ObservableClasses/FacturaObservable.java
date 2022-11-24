package com.abstraction.controllers.Controllers_Factura.ObservableClasses;

import javafx.scene.control.Button;

import java.util.Date;

public class FacturaObservable {
    Long numeroFactura;

    Long numeroPedido;

    String nombreCliente;

    Date fecha;

    private float precio;

    Button botonVer;

    Button botonActualizar;

    Button botonArchivar;

    public FacturaObservable(Long numeroFactura, Long numeroPedido, String nombreCliente, Date fecha, float precio, Button botonVer, Button botonActualizar, Button botonArchivar) {
        this.numeroFactura = numeroFactura;
        this.numeroPedido = numeroPedido;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.precio = precio;
        this.botonVer = botonVer;
        this.botonActualizar = botonActualizar;
        this.botonArchivar = botonArchivar;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Button getBotonActualizar() {
        return botonActualizar;
    }

    public Button getBotonArchivar() {
        return botonArchivar;
    }

    public Button getBotonVer() {
        return botonVer;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setBotonActualizar(Button botonActualizar) {
        this.botonActualizar = botonActualizar;
    }

    public void setBotonArchivar(Button botonArchivar) {
        this.botonArchivar = botonArchivar;
    }

    public void setBotonVer(Button botonVer) {
        this.botonVer = botonVer;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
}
