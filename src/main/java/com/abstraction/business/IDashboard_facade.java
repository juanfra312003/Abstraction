package com.abstraction.business;

public interface IDashboard_facade {
    float verIngresos(String year, String periodo);
    float verLeads();
    float valorTransaccionPromedio();
    float verCreditos();
    float verCrecimientoProducto(Long idProd, int periodo);
    float verCrecimientoVentas(int periodo);
}
