package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IdsResponse {
    @SerializedName("ids")
    private ArrayList<String> ids;

    public ArrayList<String> getIds() {
        return ids;
    }
}
