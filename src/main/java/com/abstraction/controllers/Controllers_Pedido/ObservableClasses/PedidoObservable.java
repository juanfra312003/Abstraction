package com.abstraction.controllers.Controllers_Pedido.ObservableClasses;

import java.util.Date;

import javafx.scene.control.Button;

public class PedidoObservable {
    private long numero;
    private String nombre;
    private Date fecha;
    private float valor;

    Button botonVer;
    Button botonActualizar;
    Button botonArchivar;

    public PedidoObservable(long numero, String nombre, Date fecha, float valor, Button botonVer, Button botonActualizar, Button botonArchivar) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.valor = valor;
        this.botonVer = botonVer;
        this.botonActualizar = botonActualizar;
        this.botonArchivar = botonArchivar;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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

    public Button getBotonArchivar() {
        return botonArchivar;
    }

    public void setBotonArchivar(Button botonArchivar) {
        this.botonArchivar = botonArchivar;
    }
}
