package com.abstraction.persistence.impl;

import com.abstraction.entities.Pedido;
import com.abstraction.persistence.ICotizacionDAO;
import com.abstraction.persistence.IPedidoDAO;
import com.abstraction.persistence.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO implements IPedidoDAO {

    private final MySQL mysql;

    public PedidoDAO() {
        this.mysql = new MySQL();
    }

    @Override
    public boolean create(Pedido pedido) {
        try {
            this.mysql.conectar();

            String pattern = "dd/MM/YYYY";
            DateFormat df = new SimpleDateFormat(pattern);
            String dateToString = df.format(pedido.getFecha());

            String query = "INSERT INTO Pedido(numero, Cotizacion_numero, nombre, fecha, valor, estado, archivado) VALUES(" +
                    "'" + pedido.getNumero() + "'," +
                    "'" + pedido.getCotizacionPedido().getNumero() + "'," +
                    "'" + pedido.getNombre() + "'," +
                    "STR_TO_DATE('" + dateToString + "','%d/%m/%Y')," +
                    "'" + pedido.getValor() + "'," +
                    "'" + pedido.getEstado() + "'," +
                    "'0');";
            System.out.println(query);

            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean edit(Long numero, Pedido pedido) {
        try {

            String pattern = "dd/MM/YYYY";
            DateFormat df = new SimpleDateFormat(pattern);
            String dateToString = df.format(pedido.getFecha());

            this.mysql.conectar();
            String query = "UPDATE Pedido SET " +
                    "nombre = '" + pedido.getNombre() + "'," +
                    "fecha = TO_DATE('" + dateToString + "','" + pattern + "')," +
                    "valor = '" + pedido.getValor() + "'," +
                    "estado = " + pedido.getValor() + "'" +
                    " WHERE numero = '" + numero + "';";

            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Long numero) {
        try {
            String query = "DELETE FROM Pedido WHERE numero = '" + numero + "';";
            System.out.println(query);

            this.mysql.conectar();
            Statement stmt = this.mysql.getConnection().createStatement();
            int code = stmt.executeUpdate(query);
            stmt.close();
            this.mysql.desconectar();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Pedido findById(Long numero) {
        try {
            this.mysql.conectar();
            String query = "SELECT * FROM Pedido WHERE numero = '" + numero + "';";
            System.out.println(query);

            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            if (rs.first()) {
                ICotizacionDAO cotizacionDAO = new CotizacionDAO();
                Pedido pedido = new Pedido(rs.getLong("numero"),
                        rs.getString("nombre"),
                        rs.getDate("fecha"),
                        rs.getFloat("valor"),
                        rs.getString("estado"),
                        rs.getInt("archivado"),
                        cotizacionDAO.findById(rs.getLong("Cotizacion_numero")));
                pedido.setNombreCliente(pedido.getCotizacionPedido().getNombreCliente());

                rs.close();
                stmt.close();
                this.mysql.desconectar();

                return pedido;
            } else {
                rs.close();
                stmt.close();
                this.mysql.desconectar();

                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ArrayList<Pedido> findAll() {
        try{
            ArrayList<Pedido> pedidos = new ArrayList<>();

            this.mysql.conectar();
            String query = "SELECT * FROM Pedido;";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
            rs.previous();
            do
            {
                rs.next();
                ICotizacionDAO cotizacionDAO = new CotizacionDAO();
                Pedido pedido = new Pedido(rs.getLong("numero"),
                        rs.getString("nombre"),
                        rs.getDate("fecha"),
                        rs.getFloat("valor"),
                        rs.getString("estado"),
                        rs.getInt("archivado"),
                        cotizacionDAO.findById(rs.getLong("Cotizacion_numero")));
                pedido.setNombreCliente(pedido.getCotizacionPedido().getNombreCliente());

                pedidos.add(pedido);
            }
            while(!rs.isLast());

            rs.close();
            stmt.close();
            this.mysql.desconectar();

            return pedidos;
        }
        catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Integer count() {
        try{
            int n = 0;

            this.mysql.conectar();
            String query = "SELECT * FROM Pedido;";
            System.out.println(query);
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return null;
            rs.previous();
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
        }
        catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Long nextId() {
        try{
            this.mysql.conectar();
            Statement stmt = this.mysql.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM pedido;";
            ResultSet rs = stmt.executeQuery(query);
            if(!rs.next()) return 1L;
            while(rs.next());
            rs.previous();

            return rs.getLong("numero")+1;
        }catch (SQLException ex){
            Logger.getLogger(CotizacionDAO.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }
}
