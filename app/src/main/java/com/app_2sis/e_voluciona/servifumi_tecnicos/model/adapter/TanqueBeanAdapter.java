package com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter;

/**
 * Se usa en la ConstanciaPlata para seleccionar los tanques trabajados y la cantidad de cada uno
 */
public class TanqueBeanAdapter {
    private boolean isCheck;
    private String text;
    private String cantidad;
    private String tanqueID;

    public TanqueBeanAdapter() {
    }

    public TanqueBeanAdapter(String text, String tanqueID) {
        this.isCheck = false;
        this.text = text;
        this.tanqueID = tanqueID;
    }

    public TanqueBeanAdapter(boolean isCheck, String text, String cantidad, String tanqueID) {
        this.isCheck = isCheck;
        this.text = text;
        this.cantidad = cantidad;
        this.tanqueID = tanqueID;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     *  Puede ser null
     * @return
     */
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTanqueID() {
        return tanqueID;
    }

    public void setTanqueID(String tanqueID) {
        this.tanqueID = tanqueID;
    }
}
