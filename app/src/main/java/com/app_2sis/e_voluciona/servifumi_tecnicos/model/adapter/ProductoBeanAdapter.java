package com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter;

public class ProductoBeanAdapter {
    private boolean isCheck;
    private String text;
    private String cantidad;
    private String productoID;

    public ProductoBeanAdapter() {
    }

    public ProductoBeanAdapter(String text, String productoID) {
        this.isCheck = false;
        this.text = text;
        this.productoID = productoID;
        this.cantidad = "";
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

    public String getProductoID() {
        return productoID;
    }

    public void setProductoID(String productoID) {
        this.productoID = productoID;
    }
}
