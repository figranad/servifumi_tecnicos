package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;

public class DatosAplicacion {
    public static final String CLAVE_WS = "clave";
    public static final String VALOR_WS = "valor";

    public DatosAplicacion(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    @SerializedName(CLAVE_WS)
    private String clave;

    @SerializedName(VALOR_WS)
    private String valor;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
