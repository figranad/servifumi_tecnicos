package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTanque;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatTanqueResponse {
    @SerializedName("tanques")
    CatTanqueResponse.TanquesResult tanquesResult;

    public ArrayList<CatTanque> getTanques(){
        return tanquesResult.tanques;
    }

    private class TanquesResult {
        @SerializedName("tanque")
        ArrayList<CatTanque> tanques;
    }
}
