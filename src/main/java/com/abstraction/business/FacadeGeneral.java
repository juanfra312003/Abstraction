package com.abstraction.business;

import com.abstraction.entities.*;
import com.abstraction.persistence.ICotizacionDAO;
import com.abstraction.persistence.IFacturaDAO;
import com.abstraction.persistence.IProductoDAO;
import com.abstraction.persistence.impl.CotizacionDAO;
import com.abstraction.persistence.impl.FacturaDAO;
import com.abstraction.persistence.impl.ProductoDAO;
import javafx.scene.control.Alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public boolean archivarProducto(Long id) {
        IProductoDAO productoDAO =  new ProductoDAO();
        Producto producto = productoDAO.findById(id);
        producto.setArchivado(1);
        return productoDAO.archivar(producto);
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
        return cotizacionDAO.archivar(cotizacion);
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
    public float verIngresos(String year, String periodo) {
        IFacturaDAO facturaDAO = new FacturaDAO();
        listaFacturas = facturaDAO.findAll();
        //if(listaFacturas == null) return 0;
        if(year == null) year = "00";
        if(periodo == null) periodo = "00";
        float ingresos = 0;
        String mesInicio, mesFin;
        if(periodo == "Periodo 1"){
            mesInicio = "01";
            mesFin = "06";
        }
        else {
            mesInicio = "07";
            mesFin = "12";
        }
        String sInicio = "01/" + mesInicio + "/" + year, sFin = "30/" + mesFin + "/" + year;
        try {
            Date inicio = new SimpleDateFormat("dd/MM/yyyy").parse(sInicio), fin = new SimpleDateFormat("dd/MM/yyyy").parse(sFin);
            Date fechaFactura;
            System.out.println(inicio);
            System.out.println(fin);
            if(listaFacturas == null) return 0;
            for(Factura factura : listaFacturas){
                fechaFactura = factura.getFecha();
                if(year == "00" && periodo == "00"){
                    ingresos += factura.getAbonoTotal();
                }
                else if(fechaFactura.equals(inicio) || fechaFactura.equals(fin) || (fechaFactura.after(inicio) && fechaFactura.before(fin))){
                    ingresos += factura.getAbonoTotal();
                }
            }
            return ingresos;
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<ArrayList<String>> verLeads() {
        ArrayList<ArrayList<String>> listaRetorno = new ArrayList<>();

        //Recuperar las facturas desde la base de datos.
        IFacturaDAO facturaDAO = new FacturaDAO();
        ArrayList<Factura> facturas = facturaDAO.findAll();

        if (facturas == null) return listaRetorno;

        //Mirar las facturas, si tienen relacionadas un pedido y por consiguiente cotizacion, ingresar el valor a la lista.
        for (int i = 0; i < facturas.size(); i++){
            ArrayList<String> valorIngresarIndividual = new ArrayList<>();
            valorIngresarIndividual.add(String.valueOf(facturas.get(i).getNumero())); //Ingresar numero de factura.
            valorIngresarIndividual.add(String.valueOf(facturas.get(i).getPedidoFactura().getCotizacionPedido().getNumero())); //Ingresar numero de cotizacion.
            valorIngresarIndividual.add(String.valueOf(facturas.get(i).getValorTotal())); //Ingresar total facturado.
            listaRetorno.add(valorIngresarIndividual);
        }
        return listaRetorno;
    }

    @Override
    public float valorTransaccionPromedio() {
        return 0;
    }

    @Override
    public float verCreditos() {
        float creditos = 0F;
        IFacturaDAO facturaDAO = new FacturaDAO();
        ArrayList<Factura> facturas = facturaDAO.findAll();
        if (facturas == null){
            return creditos;
        }
        for (int i = 0; i < facturas.size(); i++){
            float creditoFactura = facturas.get(i).getValorTotal() - facturas.get(i).getAbonoTotal();
            creditos += creditoFactura;
        }
        return creditos;
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
        IFacturaDAO facturaDAO = new FacturaDAO();
        Factura factura = facturaDAO.findById(id);
        return facturaDAO.edit(id, factura);
    }

    @Override
    public boolean archivarFactura(Long id) {
        IFacturaDAO facturaDAO = new FacturaDAO();
        Factura factura = facturaDAO.findById(id);
        factura.setArchivado(1);
        return facturaDAO.archivar(factura);
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
    public boolean crearFactura(Factura factura) {
        IFacturaDAO facturaDAO = new FacturaDAO();
        return facturaDAO.create(factura);
    }
}
