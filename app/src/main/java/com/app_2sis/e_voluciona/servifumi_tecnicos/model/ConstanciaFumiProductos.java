package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ConstanciaFumiProductos {
    public static final String ID_WS = "id";
    public static final String CONSTANCIA_FUMI_ID_WS = "constancia_serv_fumi_id";
    public static final String CAT_PRODUCTO_ID_WS = "cat_productos_id";
    public static final String CANTIDAD_WS = "cantidad";
    public static final String CONSTANCIA_FUMI_PRODUCTOS_ID_WS = "constancia_fumi_productos_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(CONSTANCIA_FUMI_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_ID_WS)
    private String constancia_fumi_id;

    @SerializedName(CAT_PRODUCTO_ID_WS)
    @DatabaseField(columnName = CAT_PRODUCTO_ID_WS)
    private String cat_producto_id;

    @SerializedName(CANTIDAD_WS)
    @DatabaseField(columnName = CANTIDAD_WS)
    private String cantidad;

    @SerializedName(CONSTANCIA_FUMI_PRODUCTOS_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_PRODUCTOS_ID_WS)
    private String constancia_fumi_productos_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConstancia_fumi_id() {
        return constancia_fumi_id;
    }

    public void setConstancia_fumi_id(String constancia_fumi_id) {
        this.constancia_fumi_id = constancia_fumi_id;
    }

    public String getCat_producto_id() {
        return cat_producto_id;
    }

    public void setCat_producto_id(String cat_producto_id) {
        this.cat_producto_id = cat_producto_id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getConstancia_fumi_productos_id() {
        return constancia_fumi_productos_id;
    }

    public void setConstancia_fumi_productos_id(String constancia_fumi_productos_id) {
        this.constancia_fumi_productos_id = constancia_fumi_productos_id;
    }
}
