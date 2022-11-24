package com.abstraction.persistence.impl;

import com.abstraction.entities.Usuario;
import com.abstraction.persistence.IProductoDAO;
import  com.abstraction.persistence.IUsuarioDAO;
import com.abstraction.persistence.MySQL;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements IUsuarioDAO {
    private final MySQL mysql;

    public UsuarioDAO() {
        this.mysql = new MySQL();
    }

    @Override
    public boolean create(Usuario usuario) {
        try{

            this.mysql.conectar();
            String query="INSERT INTO usuario(id, correo, password, rol) VALUES("+
                    "'"+usuario.getId()+"',"+
                    "'"+usuario.getCorreo()+"',"+
                    "'"+usuario.getPassword()+"',"+
                    "'"+usuario.getRol()+"');";


            System.out.println(query);
            Statement stmt= this.mysql.getConnection().createStatement();
            int code=stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            switch (code){
                case 1:
                    System.out.println("Se creo el usuario correctamente");
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
    public boolean validar(Usuario usuario) {
        return false;
    }
}
