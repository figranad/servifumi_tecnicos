package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ProgramacionProductos {
    public static final String ID_WS = "id";
    public static final String PROGRAMACION_ID_WS = "programacion_id";
    public static final String CAT_PRODUCTOS_ID_WS = "cat_productos_id";
    public static final String CANTIDAD_WS = "cantidad";
    public static final String STATUS_WS = "status";
    public static final String PROGRAMACION_PRODUCTOS_ID_WS = "programacion_productos_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(PROGRAMACION_ID_WS)
    @DatabaseField(columnName = PROGRAMACION_ID_WS)
    private String programacion_id;

    @SerializedName(CAT_PRODUCTOS_ID_WS)
    @DatabaseField(columnName = CAT_PRODUCTOS_ID_WS)
    private String cat_productos_id;

    @SerializedName(CANTIDAD_WS)
    @DatabaseField(columnName = CANTIDAD_WS)
    private String cantidad;

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status;

    @SerializedName(PROGRAMACION_PRODUCTOS_ID_WS)
    @DatabaseField(columnName = PROGRAMACION_PRODUCTOS_ID_WS)
    private String programacion_productos_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgramacion_id() {
        return programacion_id;
    }

    public void setProgramacion_id(String programacion_id) {
        this.programacion_id = programacion_id;
    }

    public String getCat_productos_id() {
        return cat_productos_id;
    }

    public void setCat_productos_id(String cat_productos_id) {
        this.cat_productos_id = cat_productos_id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProgramacion_productos_id() {
        return programacion_productos_id;
    }

    public void setProgramacion_productos_id(String programacion_productos_id) {
        this.programacion_productos_id = programacion_productos_id;
    }
}
