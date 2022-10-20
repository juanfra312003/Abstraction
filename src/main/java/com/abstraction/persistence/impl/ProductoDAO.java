package com.abstraction.persistence.impl;

import com.abstraction.entities.Producto;
import com.abstraction.persistence.IProductoDAO;

import java.util.ArrayList;

public class ProductoDAO implements IProductoDAO {
    @Override
    public boolean create(Producto producto) {
        return false;
    }

    @Override
    public boolean edit(Long referencia, Producto producto) {
        return false;
    }

    @Override
    public boolean delete(Long referencia) {
        return false;
    }

    @Override
    public Producto findById(Long referencia) {
        return null;
    }

    @Override
    public ArrayList<Producto> findAll() {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }
}
