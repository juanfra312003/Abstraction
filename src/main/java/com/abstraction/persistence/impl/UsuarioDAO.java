package com.abstraction.persistence.impl;

import com.abstraction.entities.Usuario;
import com.abstraction.persistence.IProductoDAO;
import  com.abstraction.persistence.IUsuarioDAO;
import com.abstraction.persistence.MySQL;

import java.sql.ResultSet;
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
            String query="INSERT INTO usuario(correo, password, rol) VALUES("+
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
    public Usuario validar(Usuario usuario) {
        try{

            this.mysql.conectar();
            String query="SELECT * FROM usuario WHERE "+
                    "correo = '"+ usuario.getCorreo()+"' AND "+
                    "pass = '"+usuario.getPassword()+"';";

            System.out.println(query);
            Statement stmt= this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
            Usuario usuario1 = new Usuario(rs.getString("correo"), rs.getString("pass"), rs.getString("rol"));
            rs.close();
            stmt.close();
            this.mysql.desconectar();
            return usuario1;
        }
        catch (SQLException ex){
            Logger.getLogger(IProductoDAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }
}
