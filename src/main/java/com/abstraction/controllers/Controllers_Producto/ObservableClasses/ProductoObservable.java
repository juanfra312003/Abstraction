package com.abstraction.controllers.Controllers_Producto.ObservableClasses;

import javafx.scene.control.Button;

public class ProductoObservable {
    private Long referencia;
    private String nombre;
    private Float precio;
    private int existencias;
    private Button botonVer;
    private Button botonActualizar;
    private Button botonBorrar;

    public ProductoObservable(Long referencia, String nombre, Float precio, int existencias, Button botonVer, Button botonActualizar, Button botonBorrar) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
        this.botonVer = botonVer;
        this.botonActualizar = botonActualizar;
        this.botonBorrar = botonBorrar;
    }

    public Long getReferencia() {
        return referencia;
    }

    public void setReferencia(Long referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
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

    public Button getBotonBorrar() {
        return botonBorrar;
    }

    public void setBotonBorrar(Button botonBorrar) {
        this.botonBorrar = botonBorrar;
    }
}
