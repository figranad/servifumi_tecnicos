package com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;

import java.util.ArrayList;
import java.util.List;

public class ProgramacionWS {
    List<Programacion> programacionList = new ArrayList<>();

    public List<Programacion> getProgramacionList() {
        return programacionList;
    }

    public void setProgramacionList(List<Programacion> programacionList) {
        this.programacionList = programacionList;
    }

    public boolean isEmpty(){
        return getProgramacionList().isEmpty();
    }
}
