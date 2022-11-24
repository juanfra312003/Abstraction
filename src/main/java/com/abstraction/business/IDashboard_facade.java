package com.abstraction.business;

import com.abstraction.entities.Usuario;

import java.util.ArrayList;

public interface IDashboard_facade {
    float valorTransaccionPromedio();
    float verIngresos(String year, String periodo);
    float verCreditos();
    float verCrecimientoProducto(Long idProd, int periodo);
    float verCrecimientoVentas(int periodo);
    public Usuario getUsuario();
}
