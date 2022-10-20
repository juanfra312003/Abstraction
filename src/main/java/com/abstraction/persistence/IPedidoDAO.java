package com.abstraction.persistence;


import com.abstraction.entities.Pedido;

import java.util.ArrayList;

public interface IPedidoDAO {
    public boolean create(Pedido pedido);
    public boolean edit(Long numero, Pedido producto);
    public boolean delete(Long numero);
    public Pedido findById(Long numero);
    public ArrayList<Pedido> findAll();
    public Integer count();
}
