package com.abstraction.persistence;

import com.abstraction.entities.Factura;

import java.util.ArrayList;

public interface IFacturaDAO{
    public boolean create(Factura factura);

    public boolean edit(Long numero, Factura factura);
    public boolean delete(Long numero);
    public Factura findById(Long numero);
    public ArrayList<Factura> findAll();
    public Integer count();
}
