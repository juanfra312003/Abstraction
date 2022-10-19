package com.abstraction.business;

import com.abstraction.business.service.ProductoBO;
import com.abstraction.entities.*;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class FacadeGeneral implements IProducto_facade, ICotizacion_facade, IPedido_facade, IFactura_facade, IDashboard_facade{

    public ArrayList<Producto> inventarioProductos;
    public ArrayList<Cotizacion> listaCotizaciones;
    public ArrayList<Pedido> listaPedidos;
    public ArrayList<Factura> listaFacturas;
    public Dashboard dashboard1;

    @Autowired
    private ProductoBO producto;

    @Override
    public boolean crearProducto(Producto product) {
        producto.save(product);
        return false;
    }

    @Override
    public Producto verProducto(Long id) {
        return null;
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        return false;
    }

    @Override
    public boolean eliminarProducto(Long id) {
        return false;
    }

    @Override
    public ArrayList<Producto> listarProductos() {
        inventarioProductos = producto.listarProductos();
        return inventarioProductos;
    }

    @Override
    public ArrayList<Producto> findByPrecio(float precioMin, float precioMax) {
        return null;
    }

    @Override
    public boolean crearCotizacion(ArrayList<CotizacionProducto> prods) {
        return false;
    }

    @Override
    public Cotizacion verCotizacion(Long id) {
        return null;
    }

    @Override
    public ArrayList<Cotizacion> listarCotizaciones() {
        return null;
    }

    @Override
    public boolean actualizarCotizacion(Cotizacion cotizacion) {
        return false;
    }

    @Override
    public boolean archivarCotizacion(Long id) {
        return false;
    }

    @Override
    public boolean crearPedido(Cotizacion cotizacion) {
        return false;
    }

    @Override
    public float verLeads() {
        return 0;
    }

    @Override
    public float valorTransaccionPromedio() {
        return 0;
    }

    @Override
    public float verCreditos() {
        return 0;
    }

    @Override
    public float verCrecimientoProducto(Long idProd, int periodo) {
        return 0;
    }

    @Override
    public float verCrecimientoVentas(int periodo) {
        return 0;
    }

    @Override
    public Factura verFactura(Long id) {
        return null;
    }

    @Override
    public ArrayList<Factura> listarFacturas() {
        return null;
    }

    @Override
    public boolean actualizarFactura(Long id) {
        return false;
    }

    @Override
    public boolean archivarFactura(Long id) {
        return false;
    }

    @Override
    public ArrayList<Factura> filtrarFacturas(Date fecha1) {
        return null;
    }

    @Override
    public Pedido verPedido(Long id) {
        return null;
    }

    @Override
    public Pedido estadoPedido(Long id) {
        return null;
    }

    @Override
    public boolean actualizarPedido(Pedido pedido) {
        return false;
    }

    @Override
    public boolean eliminarPedido(Long id) {
        return false;
    }

    @Override
    public ArrayList<Pedido> filtrarPedido(String filtro) {
        return null;
    }

    @Override
    public ArrayList<Pedido> listarPedidos() {
        return null;
    }

    @Override
    public ArrayList<Pedido> historialPedidos(Date fecha1) {
        return null;
    }

    @Override
    public Alert alertaInventario(Producto producto) {
        return null;
    }

    @Override
    public boolean crearFactura(Pedido pedido) {
        return false;
    }
}
