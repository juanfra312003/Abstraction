package com.abstraction.persistence.impl;

import com.abstraction.entities.Pedido;
import com.abstraction.persistence.IPedidoDAO;

import java.util.ArrayList;


public class PedidoDAO implements IPedidoDAO {
    @Override
    public boolean create(Pedido pedido) {
        return false;
    }

    @Override
    public boolean edit(Long numero, Pedido producto) {
        return false;
    }

    @Override
    public boolean delete(Long numero) {
        return false;
    }

    @Override
    public Pedido findById(Long numero) {
        return null;
    }

    @Override
    public ArrayList<Pedido> findAll() {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }
}
