package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ConstanciaPlataTanques {
    public static final String ID_WS = "id";
    public static final String CONSTANCIA_PLATA_ID_WS = "constancia_plata_id";
    public static final String CAT_TANQUE_ID_WS = "cat_tanque_id";
    public static final String CANTIDAD_WS = "cantidad";
    public static final String CONSTANCIA_PLATA_TANQUES_ID_WS = "constancia_plata_tanques_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(CONSTANCIA_PLATA_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_PLATA_ID_WS)
    private String constancia_plata_id;

    @SerializedName(CAT_TANQUE_ID_WS)
    @DatabaseField(columnName = CAT_TANQUE_ID_WS)
    private String cat_tanque_id;

    @SerializedName(CANTIDAD_WS)
    @DatabaseField(columnName = CANTIDAD_WS)
    private String cantidad;

    @SerializedName(CONSTANCIA_PLATA_TANQUES_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_PLATA_TANQUES_ID_WS)
    private String constancia_plata_tanques_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConstancia_plata_id() {
        return constancia_plata_id;
    }

    public void setConstancia_plata_id(String constancia_plata_id) {
        this.constancia_plata_id = constancia_plata_id;
    }

    public String getCat_tanque_id() {
        return cat_tanque_id;
    }

    public void setCat_tanque_id(String cat_tanque_id) {
        this.cat_tanque_id = cat_tanque_id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getConstancia_plata_tanques_id() {
        return constancia_plata_tanques_id;
    }

    public void setConstancia_plata_tanques_id(String constancia_plata_tanques_id) {
        this.constancia_plata_tanques_id = constancia_plata_tanques_id;
    }
}
