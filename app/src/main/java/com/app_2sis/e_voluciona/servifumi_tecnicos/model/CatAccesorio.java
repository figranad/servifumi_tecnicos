package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class CatAccesorio {
    public static final String ID_WS = "id";
    public static final String NOMBRE_WS = "nombre";
    public static final String STATUS_WS = "status";
    public static final String CAT_ACCESORIO_ID_WS = "cat_accesorio_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(NOMBRE_WS)
    @DatabaseField(columnName = NOMBRE_WS)
    private String nombre;

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status;

    @SerializedName(CAT_ACCESORIO_ID_WS)
    @DatabaseField(columnName = CAT_ACCESORIO_ID_WS)
    private String cat_accesorio_id;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCat_accesorio_id() {
        return cat_accesorio_id;
    }

    public void setCat_accesorio_id(String cat_accesorio_id) {
        this.cat_accesorio_id = cat_accesorio_id;
    }
}
