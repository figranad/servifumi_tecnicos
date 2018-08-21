package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTurno;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CatTurnoResponse {
    @SerializedName("turnos")
    CatTurnoResponse.TurnosResult turnosResult;

    public ArrayList<CatTurno> getTurnos(){
        return turnosResult.turnos;
    }

    private class TurnosResult {
        @SerializedName("turno")
        ArrayList<CatTurno> turnos;
    }
}
