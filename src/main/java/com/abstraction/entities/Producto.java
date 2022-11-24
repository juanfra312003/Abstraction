package com.abstraction.entities;


public class Producto {
    private Long referencia;
    private String nombre;
    private Float precio;
    private int existencias;
    private String descripcion;
    private int archivado;
    private String pathImage;

    public Producto() {
    }

    public Producto(Long referencia, String nombre, Float precio, int existencias, String descripcion, int archivado) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
        this.descripcion = descripcion;
        this.archivado = archivado;
    }

    public Float getPrecio() {
        return precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public Long getReferencia() {
        return referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getArchivado(){return archivado;}

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

    public void setArchivado(int archivado){
        this.archivado=archivado;
    }

    public void setReferencia(Long referencia) {
        this.referencia = referencia;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
