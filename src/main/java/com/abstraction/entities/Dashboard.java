package com.abstraction.entities;

import java.util.ArrayList;

public class Dashboard {
    private ArrayList<Factura> listaFacturas;
    private ArrayList<Cotizacion> listaCotizaciones;
    private int anio;
    private int periodo;

    public Dashboard() {
    }

    public Dashboard(ArrayList<Factura> listaFacturas, ArrayList<Cotizacion> listaCotizaciones, int anio, int periodo) {
        this.listaFacturas = listaFacturas;
        this.listaCotizaciones = listaCotizaciones;
        this.anio = anio;
        this.periodo = periodo;
    }

    public ArrayList<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(ArrayList<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public ArrayList<Cotizacion> getListaCotizaciones() {
        return listaCotizaciones;
    }

    public void setListaCotizaciones(ArrayList<Cotizacion> listaCotizaciones) {
        this.listaCotizaciones = listaCotizaciones;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
}
