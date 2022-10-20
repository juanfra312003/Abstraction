package com.abstraction.persistence;

import com.abstraction.entities.Producto;

import java.util.ArrayList;

public interface IProductoDAO{
    public boolean create(Producto producto);
    public boolean edit(Long referencia, Producto producto);
    public boolean delete(Long referencia);
    public Producto findById(Long referencia);
    public ArrayList<Producto> findAll();
    public Integer count();
}
