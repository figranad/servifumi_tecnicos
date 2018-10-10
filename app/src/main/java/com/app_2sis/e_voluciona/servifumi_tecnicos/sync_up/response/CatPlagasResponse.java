package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatPlaga;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatPlagasResponse {
    @SerializedName("catPlagas")
    CatPlagasResponse.CatPlagaResult resultCatPLagas;

    public ArrayList<CatPlaga> getCatPlagas(){
        return resultCatPLagas.plagas;
    }

    private class CatPlagaResult{
        @SerializedName("plaga")
        ArrayList<CatPlaga> plagas;
    }
}
