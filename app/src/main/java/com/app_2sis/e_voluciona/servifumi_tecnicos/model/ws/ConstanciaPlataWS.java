package com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlata;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlataTanques;

import java.util.ArrayList;
import java.util.List;

public class ConstanciaPlataWS {
    private ConstanciaPlata constanciaPlata;
    private List<ConstanciaPlataTanques> constanciaPlataTanquesList = new ArrayList<>();

    public ConstanciaPlataWS() {
    }

    public ConstanciaPlata getConstanciaPlata() {
        return constanciaPlata;
    }

    public void setConstanciaPlata(ConstanciaPlata constanciaPlata) {
        this.constanciaPlata = constanciaPlata;
    }

    public List<ConstanciaPlataTanques> getConstanciaPlataTanquesList() {
        return constanciaPlataTanquesList;
    }

    public void setConstanciaPlataTanquesList(List<ConstanciaPlataTanques> constanciaPlataTanquesList) {
        this.constanciaPlataTanquesList = constanciaPlataTanquesList;
    }

    public void changePathsByFiles() {
        constanciaPlata.setFirma(Utileria.changePathByFile(constanciaPlata.getFirma()));
    }
}
