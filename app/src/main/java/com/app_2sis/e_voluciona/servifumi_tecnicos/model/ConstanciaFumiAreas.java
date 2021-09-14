package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ConstanciaFumiAreas {
    public static final String ID_WS = "id";
    public static final String CONSTANCIA_FUMI_ID_WS = "constancia_fumi_id";
    public static final String NOMBRE_WS = "nombre";
    public static final String CONSTANCIA_FUMI_AREAS_ID_WS = "constancia_fumi_areas_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(CONSTANCIA_FUMI_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_ID_WS)
    private String constancia_fumi_id;

    @SerializedName(NOMBRE_WS)
    @DatabaseField(columnName = NOMBRE_WS)
    private String nombre;

    @SerializedName(CONSTANCIA_FUMI_AREAS_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_AREAS_ID_WS)
    private String constancia_fumi_areas_id;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConstancia_fumi_areas_id() {
        return constancia_fumi_areas_id;
    }

    public void setConstancia_fumi_areas_id(String constancia_fumi_areas_id) {
        this.constancia_fumi_areas_id = constancia_fumi_areas_id;
    }

    public void upperCase() {
        setNombre(getNombre().toUpperCase());
    }
}
