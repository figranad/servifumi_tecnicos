package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProgramacionResponse {
    @SerializedName("programaciones")
    ProgramacionResponse.ProgramacionResult programacionResult;

    public ArrayList<Programacion> getProgramacions(){
        return programacionResult.programaciones;
    }

    private class ProgramacionResult {
        @SerializedName("programacion")
        ArrayList<Programacion> programaciones;
    }
}
