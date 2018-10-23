package com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;

import java.util.ArrayList;
import java.util.List;

public class ProgramacionWS {
    List<BeanProgramacion> programacionList = new ArrayList<>();

    public List<BeanProgramacion> getProgramacionList() {
        return programacionList;
    }

    public void setProgramacionList(List<BeanProgramacion> programacionList) {
        this.programacionList = programacionList;
    }

    public boolean isEmpty(){
        return getProgramacionList().isEmpty();
    }
}
