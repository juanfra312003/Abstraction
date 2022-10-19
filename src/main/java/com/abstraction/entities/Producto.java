package com.abstraction.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Producto {
    @Id
    private int referencia;
    private String nombre;
    private Float precio;
    private int existencias;
    private String descripcion;

    public Float getPrecio() {
        return precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public int getReferencia() {
        return referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }
}
