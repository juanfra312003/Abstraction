package com.abstraction.business;

import com.abstraction.entities.Usuario;

import java.util.ArrayList;

public interface IDashboard_facade {
    float valorTransaccionPromedio(String year, String periodo);
    float verIngresos(String year, String periodo);
    float verCreditos();
    public Usuario getUsuario();
}
