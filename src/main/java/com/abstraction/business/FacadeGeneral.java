package com.abstraction.business;

import com.abstraction.entities.*;
import com.abstraction.persistence.ICotizacionDAO;
import com.abstraction.persistence.IProductoDAO;
import com.abstraction.persistence.impl.CotizacionDAO;
import com.abstraction.persistence.impl.ProductoDAO;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Date;

public class FacadeGeneral implements IProducto_facade, ICotizacion_facade, IPedido_facade, IFactura_facade, IDashboard_facade{

    public ArrayList<Producto> inventarioProductos;
    public ArrayList<Cotizacion> listaCotizaciones;
    public ArrayList<Pedido> listaPedidos;
    public ArrayList<Factura> listaFacturas;
    public Dashboard dashboard1;

    @Override
    public boolean crearProducto(Producto product) {
        IProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.create(product);
    }

    @Override
    public Producto verProducto(Long id) {
        IProductoDAO productoDAO =  new ProductoDAO();
        return productoDAO.findById(id);
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        IProductoDAO productoDAO =  new ProductoDAO();
        return productoDAO.edit(producto.getReferencia(), producto);
    }

    @Override
    public boolean eliminarProducto(Long id) {
        IProductoDAO productoDAO =  new ProductoDAO();
        return productoDAO.delete(id);
    }

    @Override
    public ArrayList<Producto> listarProductos() {
        IProductoDAO productoDAO = new ProductoDAO();
        inventarioProductos = productoDAO.findAll();
        return inventarioProductos;
    }

    @Override
    public ArrayList<Producto> findByPrecio(float precioMin, float precioMax) {
        ArrayList<Producto> productos = listarProductos();
        ArrayList<Producto> filtrados = new ArrayList<>();
        for(Producto p : productos)
            if(p.getPrecio() >= precioMin && p.getPrecio() <= precioMax) filtrados.add(p);
        return filtrados;
    }

    @Override
    public boolean crearCotizacion(Cotizacion cotizacion) {
        ICotizacionDAO cotizacionDAO = new CotizacionDAO();
        return cotizacionDAO.create(cotizacion);
    }

    @Override
    public Cotizacion verCotizacion(Long id) {
        ICotizacionDAO cotizacionDAO = new CotizacionDAO();
        return cotizacionDAO.findById(id);
    }

    @Override
    public ArrayList<Cotizacion> listarCotizaciones() {
        ICotizacionDAO cotizacionDAO = new CotizacionDAO();
         return  cotizacionDAO.findAll();
    }

    @Override
    public boolean actualizarCotizacion(Cotizacion cotizacion) {
        ICotizacionDAO cotizacionDAO = new CotizacionDAO();
        return cotizacionDAO.edit(cotizacion.getNumero(), cotizacion);
    }

    @Override
    public boolean archivarCotizacion(Long id) {
        ICotizacionDAO cotizacionDAO = new CotizacionDAO();
        Cotizacion cotizacion = cotizacionDAO.findById(id);
        cotizacion.setArchivado(1);
        return cotizacionDAO.edit(cotizacion.getNumero(),cotizacion);
    }

    @Override
    public boolean crearPedido(Cotizacion cotizacion) {
        return false;
    }

    @Override
    public Long nextCotId() {
        ICotizacionDAO cotizacionDAO = new CotizacionDAO();
        return cotizacionDAO.nextId();
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
