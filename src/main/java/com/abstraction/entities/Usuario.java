package com.abstraction.entities;

public class Usuario {
    private String correo;
    private String password;
    private String rol;
    private int id;

    /*
    Constructores
     */
    public Usuario(){

    }

    public Usuario (int id, String correo, String password, String rol){
        this.id=id;
        this.correo=correo;
        this.password=password;
        this.rol=rol;
    }

    /*
    Getters and Setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
