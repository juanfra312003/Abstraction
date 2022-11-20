package com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProductoObservable2 {
    Long referencia;
    String nombre;
    float precio;
    int cantidades;
    TextField agregarTexto;
    Button buttonConfirmarAgregar;

    public ProductoObservable2(Long referencia, String nombre, float precio, int cantidades, TextField agregarTexto, Button buttonConfirmarAgregar) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidades = cantidades;
        this.agregarTexto = agregarTexto;
        this.buttonConfirmarAgregar = buttonConfirmarAgregar;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidades() {
        return cantidades;
    }

    public void setCantidades(int cantidades) {
        this.cantidades = cantidades;
    }

    public TextField getAgregarTexto() {
        return agregarTexto;
    }

    public void setAgregarTexto(TextField agregarTexto) {
        this.agregarTexto = agregarTexto;
    }

    public Button getButtonConfirmarAgregar() {
        return buttonConfirmarAgregar;
    }

    public void setButtonConfirmarAgregar(Button buttonConfirmarAgregar) {
        this.buttonConfirmarAgregar = buttonConfirmarAgregar;
    }
}
