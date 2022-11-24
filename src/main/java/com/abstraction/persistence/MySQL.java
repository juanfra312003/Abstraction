package com.abstraction.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private final String host;
    private final String port;
    private final String user;
    private final String pass;
    private final String database;
    private final String parameters;
    private Connection connection;

    public MySQL() {

        this.host = "localhost";
        this.port = "3306";
        this.user = "abstraction_SU";
        this.pass = "Abstraction";
        this.database = "Abstraction";
        this.parameters = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        this.connection = null;
    }

    public void conectar(){
        try {
            String path = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + this.parameters;
            //System.out.println(path);
            this.connection = DriverManager.getConnection(path,user,pass);
            //System.out.println("Se conecto");
        } catch (SQLException ex) {
            System.out.println("No se conecto");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            this.connection=null;
        }catch(Exception ex){
            System.out.println("No se conecto");
            System.out.println("Exception: " + ex.getMessage());
            this.connection=null;
        }
    }

    public void desconectar() {
        try {
            this.connection.close();
            //System.out.println("Se desconecto");
        } catch (SQLException ex) {
            System.out.println("No se desconecto");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        this.connection = null;
    }

    public Connection getConnection() {
        return connection;
    }
}