package com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter;

public class ConstanciaPlataBeanAdapter {
    private String cliente;
    private String dineroRecibido;
    private String tanques;
    private String sincronizado;
    private String constanciaPlataID;

    public ConstanciaPlataBeanAdapter() {
    }

    public ConstanciaPlataBeanAdapter(String cliente, String dineroRecibido, String tanques,
                                      String sincronizado, String constanciaPlataID) {
        this.cliente = cliente;
        this.dineroRecibido = dineroRecibido;
        this.tanques = tanques;
        this.sincronizado = sincronizado;
        this.constanciaPlataID = constanciaPlataID;
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

    public String getTanques() {
        return tanques;
    }

    public void setTanques(String tanques) {
        this.tanques = tanques;
    }

    public String getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getConstanciaPlataID() {
        return constanciaPlataID;
    }

    public void setConstanciaPlataID(String constanciaPlataID) {
        this.constanciaPlataID = constanciaPlataID;
    }
}
