package com.abstraction.business;

import com.abstraction.entities.Factura;
import com.abstraction.entities.Usuario;

import java.util.ArrayList;
import java.util.Date;

public interface IFactura_facade {
    Factura verFactura(Long id);
    ArrayList<Factura> listarFacturas();
    boolean actualizarFactura(Long id, Factura factura);
    boolean archivarFactura(Long id);
    ArrayList<Factura> filtrarFacturas(Date fecha1);
    public Usuario getUsuario();
}
