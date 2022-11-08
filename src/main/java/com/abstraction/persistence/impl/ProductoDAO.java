package com.abstraction.persistence.impl;

import com.abstraction.entities.Producto;
import com.abstraction.persistence.IProductoDAO;
import com.abstraction.persistence.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductoDAO implements IProductoDAO {
    private final MySQL mysql;

    public ProductoDAO() {
        this.mysql = new MySQL();
    }

    @Override
    public boolean create(Producto producto) {
        try{
            this.mysql.conectar();
            String query="INSERT INTO producto(referencia, nombre, precio, existencias,descripcion,pathImagen)VALUES("+
                    "'"+producto.getReferencia()+"',"+
                    "'"+producto.getNombre()+"',"+
                    "'"+producto.getPrecio()+"',"+
                    "'"+producto.getExistencias()+"',"+
                    "'"+producto.getDescripcion()+"',"+
                    "'"+producto.getPathImage()+"'"+
                    ");";
            System.out.println(query);
            Statement stmt= this.mysql.getConnection().createStatement();
            int code=stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            switch (code){
                case 1:
                    System.out.println("Se creo el producto correctamente");
                    return true;
                default:
                    return false;
            }
        }
        catch (SQLException ex){
            Logger.getLogger(IProductoDAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }

    @Override
    public boolean edit(Long referencia, Producto producto) {
        try{
            this.mysql.conectar();
            String query="UPDATE producto SET"+
                    "nombre='"+producto.getNombre()+"',"+
                    "precio= '" +producto.getPrecio() + "'" +
                    "existencias= '" +producto.getExistencias() + "'" +
                    "descripcion= '" +producto.getDescripcion() + "'" +
                    "WHERE referencia='" + referencia + "';";

            System.out.println(query);
            Statement stmt= this.mysql.getConnection().createStatement();
            int code=stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            switch (code){
                case 1:
                    System.out.println("Se actualizo el producto correctamente");
                    return true;
                default:
                    return false;
            }

        }catch (SQLException ex){
            Logger.getLogger(IProductoDAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }

    }

    @Override
    public boolean delete(Long referencia) {
        try{
            String query = "DELETE FROM telefono WHERE referencia = '" + referencia + "';";
            System.out.println(query);

            Producto producto = findById(referencia);
            System.out.println("Eliminando: " + producto);

            this.mysql.conectar();
            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            switch (code) {
                case 1:
                    System.out.println("Se elimino el prodducto");
                    return true;
                default:
                    return false;
            }

        } catch (SQLException ex){
            Logger.getLogger(IProductoDAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }

    @Override
    public Producto findById(Long referencia) {
        try{
            this.mysql.conectar();
            String query = "SELECT * FROM producto WHERE referencia = '"+ referencia +"';";
            System.out.println(query);

            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            if(rs.first())
            {
                Producto producto = new Producto(rs.getLong("referencia"), rs.getString("nombre"),rs.getFloat("precio"),rs.getInt("existencias"), rs.getString("Descripcion"));
                rs.close();
                stmt.close();
                this.mysql.desconectar();

                return producto;
            }
            else
            {
                rs.close();
                stmt.close();
                this.mysql.desconectar();

                return null;
            }

        } catch (SQLException ex){
            Logger.getLogger(IProductoDAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }

    @Override
    public ArrayList<Producto> findAll() {
        try{

            ArrayList<Producto> productos = new ArrayList<>();

            this.mysql.conectar();
            String query = "SELECT * FROM producto;";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
            rs.previous();
            do
            {
                rs.next();

                Producto producto = new Producto(rs.getLong("referencia"), rs.getString("nombre"),rs.getFloat("precio"),rs.getInt("existencias"), rs.getString("Descripcion"));
                productos.add(producto);
            }
            while(!rs.isLast());

            rs.close();
            stmt.close();
            this.mysql.desconectar();

            return productos;

        } catch (SQLException ex){
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }

    @Override
    public Integer count() {
        try{

            Integer n = 0;

            this.mysql.conectar();
            String query = "SELECT * FROM producto;";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            do
            {
                rs.next();
                n++;
            }
            while(!rs.isLast());

            rs.close();
            stmt.close();
            this.mysql.desconectar();

            return n;

        } catch (SQLException ex){
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }
}
