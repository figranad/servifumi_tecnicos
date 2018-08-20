package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTipoInstalacion;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatTipoInstalacionResponse {
    @SerializedName("tipoInstalaciones")
    CatTipoInstalacionResponse.TipoInstalacionResult tipoInstalacionResult;

    public ArrayList<CatTipoInstalacion> getTipoInstalaciones(){
        return tipoInstalacionResult.tipoInstalaciones;
    }

    private class TipoInstalacionResult {
        @SerializedName("tipoInstalacion")
        ArrayList<CatTipoInstalacion> tipoInstalaciones;
    }
}

