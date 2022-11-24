package com.abstraction.persistence.impl;

import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.CotizacionProducto;
import com.abstraction.persistence.ICotizacionDAO;
import com.abstraction.persistence.IProductoDAO;
import com.abstraction.persistence.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CotizacionDAO implements ICotizacionDAO {
    private final MySQL mysql;

    public CotizacionDAO() {
        this.mysql = new MySQL();
    }


    @Override
    public boolean create(Cotizacion cotizacion){
        this.mysql.conectar();
        try{
            String pattern = "dd/MM/YYYY";
            DateFormat df = new SimpleDateFormat(pattern);
            String dateToString = df.format(cotizacion.getFecha());

            String query = "INSERT INTO cotizacion(nombre, fecha, precioTotal, nombreCliente, archivado) VALUES(" +
                    "'" + cotizacion.getNombre() + "'," +
                    "STR_TO_DATE('" + dateToString + "','%d/%m/%Y')," +
                    "'" + cotizacion.getPrecio() + "'," +
                    "'" + cotizacion.getNombreCliente() + "'," +
                    "'0');";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();

            ArrayList<CotizacionProducto> cotProds = cotizacion.getProductos();
            for (CotizacionProducto cotProd : cotProds) {
                String query2 = "INSERT INTO cotizacionProducto(Cotizacion_numero, Producto_referencia, cantidad) VALUES(" +
                        "'" + cotizacion.getNumero() + "'," +
                        "'" + cotProd.getProducto().getReferencia() + "'," +
                        "'" + cotProd.getCantidad() + "');";

                Statement stmt2 = this.mysql.getConnection().createStatement();
                int code2 = stmt2.executeUpdate(query2);
                stmt2.close();
            }

            this.mysql.desconectar();

            if (code == 1) {
                System.out.println("Se creo la cotizacion!");
                return true;
            }
            return false;
        }
        catch (SQLException ex){
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }

    @Override
    public boolean edit(Long numero, Cotizacion cotizacion) {
        this.mysql.conectar();
        try{
            String query = "UPDATE  cotizacion SET " +
                    "nombre = '" + cotizacion.getNombre() + "', " +
                    "precioTotal = '" + cotizacion.getPrecio() + "'," +
                    "nombreCliente = '" + cotizacion.getNombreCliente() + "' " +
                    "WHERE numero = " + numero + ";";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();

            String queryDelete = "DELETE FROM cotizacionProducto WHERE Cotizacion_numero = " + numero + ";";
            Statement stmtDelete = this.mysql.getConnection().createStatement();
            int codeDelete = stmtDelete.executeUpdate(queryDelete);
            stmtDelete.close();

            ArrayList<CotizacionProducto> cotProds = cotizacion.getProductos();
            for (CotizacionProducto cotProd : cotProds) {
                String query2 = "INSERT INTO cotizacionProducto(Cotizacion_numero, Producto_referencia, cantidad) VALUES(" +
                        "'" + numero + "'," +
                        "'" + cotProd.getProducto().getReferencia() + "'," +
                        "'" + cotProd.getCantidad() + "');";

                Statement stmt2 = this.mysql.getConnection().createStatement();
                int code2 = stmt2.executeUpdate(query2);
                stmt2.close();
            }

            this.mysql.desconectar();

            if (code == 1) {
                System.out.println("Se creo la cotizacion!");
                return true;
            }
            return false;
        }
        catch (SQLException ex){
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }

    @Override
    public boolean archivar(Cotizacion cotizacion) {
        this.mysql.conectar();
        try{
            String query = "UPDATE  cotizacion SET " +
                    "archivado = '" + cotizacion.getArchivado() + "' " +
                    "WHERE numero = " + cotizacion.getNumero() + ";";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            if (code == 1) {
                System.out.println("Se archivo la cotizacion!");
                return true;
            }
            return false;
        }
        catch (SQLException ex){
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }


    @Override
    public Cotizacion findById(Long numero) {
        try{
            this.mysql.conectar();
            String query = "SELECT * FROM cotizacion WHERE numero = '" + numero + "';";
            System.out.println(query);

            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);

            if(rs.first())
            {
                Cotizacion cotizacion = new Cotizacion(rs.getLong("numero"),
                        rs.getString("nombre"),
                        rs.getDate("fecha"),
                        rs.getLong("precioTotal"),
                        rs.getString("nombreCliente"),
                        rs.getInt("archivado"));

                cotizacion.setProductos(getCotiProductos(cotizacion.getNumero()));

                rs.close();
                stmt.close();
                this.mysql.desconectar();

                return cotizacion;
            }
            else
            {
                rs.close();
                stmt.close();
                this.mysql.desconectar();

                return null;
            }

        }catch (SQLException ex){
            Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }

    @Override
    public ArrayList<Cotizacion> findAll() {
        try{
            ArrayList<Cotizacion> cotizaciones = new ArrayList<>();
            this.mysql.conectar();
            String query = "SELECT * FROM cotizacion;";
            System.out.println(query);

            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);



            if(!rs.next()) return null;
            rs.previous();
            do
            {
                rs.next();
                Cotizacion cotizacion = new Cotizacion(rs.getLong("numero"),
                        rs.getString("nombre"),
                        rs.getDate("fecha"),
                        rs.getLong("precioTotal"),
                        rs.getString("nombreCliente"),
                        rs.getInt("archivado"));

                cotizacion.setProductos(getCotiProductos(cotizacion.getNumero()));
                cotizaciones.add(cotizacion);

            }
            while(!rs.isLast());

            rs.close();
            stmt.close();
            this.mysql.desconectar();

            return cotizaciones;

        }catch (SQLException ex){
            Logger.getLogger(CotizacionDAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }

    ArrayList<CotizacionProducto> getCotiProductos(Long num){
        try{
            ArrayList<CotizacionProducto> cotizacionesProd = new ArrayList<>();
            this.mysql.conectar();
            String query = "SELECT * FROM CotizacionProducto WHERE Cotizacion_numero = '" + num + "';";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);

            IProductoDAO productoDAO = new ProductoDAO();

            if(!rs.next()) return null;
            rs.previous();
            do
            {
                rs.next();
                CotizacionProducto cotizacionProd = new CotizacionProducto(productoDAO.findById(rs.getLong("Producto_referencia")),
                        rs.getInt("cantidad"));
                cotizacionesProd.add(cotizacionProd);
            }
            while(!rs.isLast());
            return cotizacionesProd;
        }catch (SQLException ex){
            Logger.getLogger(CotizacionDAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Long nextId() {
        try{
            this.mysql.conectar();
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM cotizacion;";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next());
            rs.previous();

            return rs.getLong("numero")+1;
        }catch (SQLException ex){
            Logger.getLogger(CotizacionDAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }
}
