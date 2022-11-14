package com.abstraction.controllers.Controllers_Cotizacion;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CotProdEditObs {
    Long referencia;
    String nombre;
    float precioUnitario;
    TextField existencias;
    Button botonEliminar;

    public CotProdEditObs(Long referencia, String nombre, float precioUnitario, TextField existencias, Button botonEliminar) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.existencias = existencias;
        this.botonEliminar = botonEliminar;
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

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public TextField getExistencias() {
        return existencias;
    }

    public void setExistencias(TextField existencias) {
        this.existencias = existencias;
    }

    public Button getBotonEliminar() {
        return botonEliminar;
    }

    public void setBotonEliminar(Button botonEliminar) {
        this.botonEliminar = botonEliminar;
    }
}
