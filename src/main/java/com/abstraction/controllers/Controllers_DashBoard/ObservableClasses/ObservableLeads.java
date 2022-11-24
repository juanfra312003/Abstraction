package com.abstraction.controllers.Controllers_DashBoard.ObservableClasses;

public class ObservableLeads {
    Long numeroFactura;

    Long numeroCotizacion;

    Float total;

    public ObservableLeads (Long numeroFactura, Long  numeroCotizacion, Float total){
        this.numeroFactura = numeroFactura;
        this.numeroCotizacion = numeroCotizacion;
        this.total = total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroCotizacion(Long numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public Long getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public Float getTotal() {
        return total;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
}
