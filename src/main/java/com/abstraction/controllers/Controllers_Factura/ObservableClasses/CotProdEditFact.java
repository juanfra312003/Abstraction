package com.abstraction.controllers.Controllers_Factura.ObservableClasses;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CotProdEditFact {
    Long referencia;
    String nombre;
    float precioUnitario;
    int existencias;
    float subTotal;

    public CotProdEditFact(Long referencia, String nombre, float precioUnitario, int existencias, float subTotal) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.existencias = existencias;
        this.subTotal = subTotal;
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

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getExistencias() {
        return existencias;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
}
