package com.abstraction.business;

import com.abstraction.entities.Pedido;
import com.abstraction.entities.Producto;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Date;

public interface IPedido_facade {
    Pedido verPedido(Long id);
    Pedido estadoPedido(Long id);
    boolean actualizarPedido(Pedido pedido);
    boolean eliminarPedido(Long id);
    ArrayList<Pedido> filtrarPedido(String filtro);
    ArrayList<Pedido> listarPedidos();
    ArrayList<Pedido> historialPedidos(Date fecha1);
    Alert alertaInventario(Producto producto);
    boolean crearFactura(Pedido pedido);
}
