package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class CatTanque {
    public static final String ID_WS = "id";
    public static final String NOMBRE_WS = "nombre";
    public static final String PRODUCTO_ID_WS = "producto_id";
    public static final String CANTIDAD_PRODUCTO_WS = "cantidad_producto";
    public static final String STATUS_WS = "status";
    public static final String CAT_TANQUE_ID_WS = "cat_tanque_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(NOMBRE_WS)
    @DatabaseField(columnName = NOMBRE_WS)
    private String nombre;

    @SerializedName(PRODUCTO_ID_WS)
    @DatabaseField(columnName = PRODUCTO_ID_WS)
    private String producto_id;

    @SerializedName(CANTIDAD_PRODUCTO_WS)
    @DatabaseField(columnName = CANTIDAD_PRODUCTO_WS)
    private String cantidad_producto;

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status;

    @SerializedName(CAT_TANQUE_ID_WS)
    @DatabaseField(columnName = CAT_TANQUE_ID_WS)
    private String cat_tanque_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(String producto_id) {
        this.producto_id = producto_id;
    }

    public String getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(String cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCat_tanque_id() {
        return cat_tanque_id;
    }

    public void setCat_tanque_id(String cat_tanque_id) {
        this.cat_tanque_id = cat_tanque_id;
    }
}
