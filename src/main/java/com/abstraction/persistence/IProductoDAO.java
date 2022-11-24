package com.abstraction.persistence;

import com.abstraction.entities.Producto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProductoDAO{
    public boolean create(Producto producto);
    public boolean edit(Long referencia, Producto producto);
    //public boolean delete(Long referencia);
    public boolean archivar(Producto producto);

    public Producto findById(Long referencia);
    public ArrayList<Producto> findAll();
    public Integer count();
}
