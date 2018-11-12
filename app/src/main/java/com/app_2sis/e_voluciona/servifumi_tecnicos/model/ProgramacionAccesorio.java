package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ProgramacionAccesorio {
    public static final String ID_WS = "id";
    public static final String CLIENTE_ID_WS = "cliente_id";
    public static final String DOMICILIO_ID_WS = "domicilio_id";
    public static final String CAT_ACCESORIO_ID_WS = "cat_accesorio_id";
    public static final String CANTIDAD_WS = "cantidad";
    public static final String PRECIO_WS = "precio";
    public static final String STATUS_WS = "status";
    public static final String CONTRATO_ACC_COMODATO_ID_WS = "contrato_acc_comodato_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

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

    @SerializedName(PRECIO_WS)
    @DatabaseField(columnName = PRECIO_WS)
    private String precio;

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status;

    @SerializedName(CONTRATO_ACC_COMODATO_ID_WS)
    @DatabaseField(columnName = CONTRATO_ACC_COMODATO_ID_WS)
    private String contrato_acc_comodato_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContrato_acc_comodato_id() {
        return contrato_acc_comodato_id;
    }

    public void setContrato_acc_comodato_id(String contrato_acc_comodato_id) {
        this.contrato_acc_comodato_id = contrato_acc_comodato_id;
    }
}
