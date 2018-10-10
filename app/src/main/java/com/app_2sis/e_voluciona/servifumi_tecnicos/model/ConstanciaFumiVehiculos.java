package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ConstanciaFumiVehiculos {
    public static final String ID_WS = "id";
    public static final String CONSTANCIA_FUMI_ID_WS = "constancia_fumi_id";
    public static final String MARCA_WS = "marca";
    public static final String MATRICULA_WS = "matricula";
    public static final String NUM_ECONOMICO_WS = "num_economico";
    public static final String CONSTANCIA_FUMI_VEHICULOS_ID_WS = "constancia_fumi_vehiculos_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(CONSTANCIA_FUMI_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_ID_WS)
    private String constancia_fumi_id;

    @SerializedName(MARCA_WS)
    @DatabaseField(columnName = MARCA_WS)
    private String marca;

    @SerializedName(MATRICULA_WS)
    @DatabaseField(columnName = MATRICULA_WS)
    private String matricula;

    @SerializedName(NUM_ECONOMICO_WS)
    @DatabaseField(columnName = NUM_ECONOMICO_WS)
    private String num_economico;

    @SerializedName(CONSTANCIA_FUMI_VEHICULOS_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_VEHICULOS_ID_WS)
    private String constancia_fumi_vehiculos_id;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNum_economico() {
        return num_economico;
    }

    public void setNum_economico(String num_economico) {
        this.num_economico = num_economico;
    }

    public String getConstancia_fumi_vehiculos_id() {
        return constancia_fumi_vehiculos_id;
    }

    public void setConstancia_fumi_vehiculos_id(String constancia_fumi_vehiculos_id) {
        this.constancia_fumi_vehiculos_id = constancia_fumi_vehiculos_id;
    }

    public void upperCase() {
        setMarca(getMarca().toUpperCase());
        setMatricula(getMatricula().toUpperCase());
        setNum_economico(getNum_economico().toUpperCase());
    }
}
