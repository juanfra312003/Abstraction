package com.abstraction.business;

import java.util.ArrayList;

public interface IDashboard_facade {
    ArrayList<ArrayList<String>> verLeads();
    float valorTransaccionPromedio();
    float verIngresos(String year, String periodo);
    float verCreditos();
    float verCrecimientoProducto(Long idProd, int periodo);
    float verCrecimientoVentas(int periodo);
}
