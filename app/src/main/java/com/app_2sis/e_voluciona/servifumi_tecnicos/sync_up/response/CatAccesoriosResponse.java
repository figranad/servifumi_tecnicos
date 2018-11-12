package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatAccesorio;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatAccesoriosResponse {
    @SerializedName("catAccesorios")
    CatAccesoriosResponse.CatAccesorioResult resultCatAccesorios;

    public ArrayList<CatAccesorio> getCatAccesorios(){
        return resultCatAccesorios.accesorios;
    }

    private class CatAccesorioResult{
        @SerializedName("accesorio")
        ArrayList<CatAccesorio> accesorios;
    }
}
