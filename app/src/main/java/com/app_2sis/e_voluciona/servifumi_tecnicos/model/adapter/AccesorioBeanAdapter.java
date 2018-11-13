package com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;

public class AccesorioBeanAdapter {
    private String text;
    private String condicionID;
    private String cantidad;
    private String accesorioID;

    public AccesorioBeanAdapter(String text, String cantidad, String accesorioID) {
        this.text = text;
        this.cantidad = cantidad;
        this.accesorioID = accesorioID;
        this.condicionID = Constant.CONDICION_ACCESORIO_BUENA;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCondicionID() {
        return condicionID;
    }

    public void setCondicionID(String condicionID) {
        this.condicionID = condicionID;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getAccesorioID() {
        return accesorioID;
    }

    public void setAccesorioID(String accesorioID) {
        this.accesorioID = accesorioID;
    }
}
