package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.MetodoPago;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MetodoPagoResponse {
    @SerializedName("metodos")
    MetodoPagoResponse.MetodosResult results;

    public ArrayList<MetodoPago> getMetodosPago(){
        return results.metodos;
    }

    private class MetodosResult {
        @SerializedName("metodo")
        ArrayList<MetodoPago> metodos;
    }
}
