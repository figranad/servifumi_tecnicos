package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionProductos;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProgramacionProductosResponse {
    @SerializedName("programacion_productos")
    ProgramacionProductosResponse.ProgramacionProductosResult programacionProductosResult;

    public ArrayList<ProgramacionProductos> getProgramacionProductos(){
        return programacionProductosResult.programacion_productos;
    }

    private class ProgramacionProductosResult {
        @SerializedName("programacion_producto")
        ArrayList<ProgramacionProductos> programacion_productos;
    }
}
