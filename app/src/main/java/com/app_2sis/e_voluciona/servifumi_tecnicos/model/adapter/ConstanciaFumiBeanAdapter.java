package com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter;

public class ConstanciaFumiBeanAdapter {
    private String cliente;
    private String dineroRecibido;
    private String observaciones;
    private String sincronizado;
    private String constanciaFumiID;

    public ConstanciaFumiBeanAdapter() {
    }

    public ConstanciaFumiBeanAdapter(String cliente, String dineroRecibido, String observaciones,
                                      String sincronizado, String constanciaFumiID) {
        this.cliente = cliente;
        this.dineroRecibido = dineroRecibido;
        this.observaciones = observaciones;
        this.sincronizado = sincronizado;
        this.constanciaFumiID = constanciaFumiID;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDineroRecibido() {
        return dineroRecibido;
    }

    public void setDineroRecibido(String dineroRecibido) {
        this.dineroRecibido = dineroRecibido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getConstanciaFumiID() {
        return constanciaFumiID;
    }

    public void setConstanciaFumiID(String constanciaFumiID) {
        this.constanciaFumiID = constanciaFumiID;
    }
}
