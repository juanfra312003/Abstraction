package com.abstraction.entities;


import java.util.Date;

public class Factura {

    private Long numero;
    private Date fecha;
    private float valorTotal;
    private float abonoTotal;

    private Pedido pedidoFactura;

    public Factura() {

    }
    public Factura(Long numero, Date fecha, float valorTotal, float abonoTotal, Pedido pedidoFactura) {
        this.numero = numero;
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.abonoTotal = abonoTotal;
        this.pedidoFactura = pedidoFactura;
    }

    public Pedido getPedidoFactura() {
        return pedidoFactura;
    }

    public void setPedidoFactura(Pedido pedidoFactura) {
        this.pedidoFactura = pedidoFactura;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float getAbonoTotal() {
        return abonoTotal;
    }

    public void setAbonoTotal(float abonoTotal) {
        this.abonoTotal = abonoTotal;
    }
}
