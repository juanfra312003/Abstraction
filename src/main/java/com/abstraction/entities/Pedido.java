package com.abstraction.entities;


import java.util.Date;


public class Pedido {


    private Long numero;
    private String nombre;
    private Date fecha;
    private float valor;
    private String nombreCliente;
    private String estado;
    private Cotizacion cotizacionPedido;
    private int archivado;

    public Pedido() {
    }

    public Pedido(Long numero, String nombre, Date fecha, float valor, String estado, int archivado, Cotizacion cotizacionPedido) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.valor = valor;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
        this.archivado = archivado;
        this.cotizacionPedido = cotizacionPedido;
    }

    public Cotizacion getCotizacionPedido() {
        return cotizacionPedido;
    }

    public void setCotizacionPedido(Cotizacion cotizacionPedido) {
        this.cotizacionPedido = cotizacionPedido;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
