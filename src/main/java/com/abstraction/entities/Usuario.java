package com.abstraction.entities;

public class Usuario {
    private String correo;
    private String password;
    private String rol;

    /*
    Constructores
     */
    public Usuario(){

    }

    public Usuario (String correo, String password, String rol){
        this.correo=correo;
        this.password=password;
        this.rol=rol;
    }

    /*
    Getters and Setters
     */

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
