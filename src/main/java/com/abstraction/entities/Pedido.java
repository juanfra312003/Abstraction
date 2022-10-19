package com.abstraction.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pedido", schema = "abstraction")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero;
    private String nombre;
    private Date fecha;
    private float valor;
    private String nombreCliente;
    private String estado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cotizacion_numero", unique = false)
    private Cotizacion cotizacionPedido;

    public Pedido() {
    }

    public Pedido(Long numero, String nombre, Date fecha, float valor, String nombreCliente, String estado, Cotizacion cotizacionPedido) {
        this.numero = numero;
        this.nombre = nombre;
        this.fecha = fecha;
        this.valor = valor;
        this.nombreCliente = nombreCliente;
        this.estado = estado;
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
