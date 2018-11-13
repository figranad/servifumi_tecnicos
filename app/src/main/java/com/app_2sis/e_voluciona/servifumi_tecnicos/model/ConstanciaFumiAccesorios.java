package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ConstanciaFumiAccesorios {
    public static final String ID_WS = "id";
    public static final String CONSTANCIA_FUMI_ID_WS = "constancia_serv_fumi_id";
    public static final String CLIENTE_ID_WS = "cliente_id";
    public static final String DOMICILIO_ID_WS = "domicilio_id";
    public static final String CAT_ACCESORIO_ID_WS = "cat_accesorios_id";
    public static final String CANTIDAD_WS = "cantidad";
    public static final String CONDICIONES_WS = "condiciones";
    public static final String CONSTANCIA_FUMI_ACCESORIOS_ID_WS = "constancia_fumi_accesorios_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(CONSTANCIA_FUMI_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_ID_WS)
    private String constancia_fumi_id;

    @SerializedName(CLIENTE_ID_WS)
    @DatabaseField(columnName = CLIENTE_ID_WS)
    private String cliente_id;

    @SerializedName(DOMICILIO_ID_WS)
    @DatabaseField(columnName = DOMICILIO_ID_WS)
    private String domicilio_id;

    @SerializedName(CAT_ACCESORIO_ID_WS)
    @DatabaseField(columnName = CAT_ACCESORIO_ID_WS)
    private String cat_accesorio_id;

    @SerializedName(CANTIDAD_WS)
    @DatabaseField(columnName = CANTIDAD_WS)
    private String cantidad;

    @SerializedName(CONDICIONES_WS)
    @DatabaseField(columnName = CONDICIONES_WS)
    private String condiciones;

    @SerializedName(CONSTANCIA_FUMI_ACCESORIOS_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_ACCESORIOS_ID_WS)
    private String constancia_fumi_accesorios_id;

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

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getDomicilio_id() {
        return domicilio_id;
    }

    public void setDomicilio_id(String domicilio_id) {
        this.domicilio_id = domicilio_id;
    }

    public String getCat_accesorio_id() {
        return cat_accesorio_id;
    }

    public void setCat_accesorio_id(String cat_accesorio_id) {
        this.cat_accesorio_id = cat_accesorio_id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public String getConstancia_fumi_accesorios_id() {
        return constancia_fumi_accesorios_id;
    }

    public void setConstancia_fumi_accesorios_id(String constancia_fumi_accesorios_id) {
        this.constancia_fumi_accesorios_id = constancia_fumi_accesorios_id;
    }
}
