package com.abstraction.persistence.impl;

import com.abstraction.entities.Producto;
import com.abstraction.persistence.IProductoDAO;
import com.abstraction.persistence.MySQL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductoDAO implements IProductoDAO {
    private final MySQL mysql;

    public ProductoDAO(MySQL mysql) {
        this.mysql = mysql;
    }

    @Override
    public boolean create(Producto producto) {
        try{
            this.mysql.conectar();
            String query="INSERT INTO producto(referencia, nombre, precio, existencias,descripcion,pathImage)VALUES("+
                    "'"+producto.getReferencia()+","+
                    "'"+producto.getPrecio()+","+
                    "'"+producto.getExistencias()+","+
                    "'"+producto.getDescripcion()+","+
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
            }}catch (SQLException ex){
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
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
