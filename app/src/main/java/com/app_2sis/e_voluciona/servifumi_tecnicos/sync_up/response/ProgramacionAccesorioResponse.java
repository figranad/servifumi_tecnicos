package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionAccesorio;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProgramacionAccesorioResponse {
    @SerializedName("contratoAccesorios")
    ProgramacionAccesorioResponse.AccesorioContratosResult accesorioContratosResult;

    public ArrayList<ProgramacionAccesorio> getAccesorioContratos(){
        return accesorioContratosResult.accesorioContratos;
    }

    private class AccesorioContratosResult {
        @SerializedName("contratoAccesorio")
        ArrayList<ProgramacionAccesorio> accesorioContratos;
    }
}
