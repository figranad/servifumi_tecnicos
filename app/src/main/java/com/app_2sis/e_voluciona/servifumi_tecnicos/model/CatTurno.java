package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class CatTurno {
    public static final String ID_WS = "id";
    public static final String NOMBRE_WS = "nombre";
    public static final String DESCRIPCION_WS = "descripcion";
    public static final String COLOR_WS = "color";
    public static final String STATUS_WS = "status";
    public static final String CAT_TURNOS_ID_WS = "cat_turnos_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(NOMBRE_WS)
    @DatabaseField(columnName = NOMBRE_WS)
    private String nombre;

    @SerializedName(DESCRIPCION_WS)
    @DatabaseField(columnName = DESCRIPCION_WS)
    private String descripcion;

    @SerializedName(COLOR_WS)
    @DatabaseField(columnName = COLOR_WS)
    private String color;

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status;

    @SerializedName(CAT_TURNOS_ID_WS)
    @DatabaseField(columnName = CAT_TURNOS_ID_WS)
    private String cat_turnos_id;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCat_turnos_id() {
        return cat_turnos_id;
    }

    public void setCat_turnos_id(String cat_turnos_id) {
        this.cat_turnos_id = cat_turnos_id;
    }
}
