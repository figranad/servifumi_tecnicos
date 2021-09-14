package com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumi;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiAccesorios;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiAreas;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiPlagas;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiProductos;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiVehiculos;

import java.util.ArrayList;
import java.util.List;

public class ConstanciaFumiWS {
    private ConstanciaFumi constanciaFumi;
    private List<ConstanciaFumiPlagas> constanciaFumiPlagasList = new ArrayList<>();
    private List<ConstanciaFumiProductos> constanciaFumiProductosList = new ArrayList<>();
    private List<ConstanciaFumiAccesorios> constanciaFumiAccesoriosList = new ArrayList<>();
    private List<ConstanciaFumiVehiculos> constanciaFumiVehiculosList = new ArrayList<>();
    private List<ConstanciaFumiAreas> constanciaFumiAreasList = new ArrayList<>();

    public ConstanciaFumiWS() {
    }

    public ConstanciaFumi getConstanciaFumi() {
        return constanciaFumi;
    }

    public void setConstanciaFumi(ConstanciaFumi constanciaFumi) {
        this.constanciaFumi = constanciaFumi;
    }

    public List<ConstanciaFumiPlagas> getConstanciaFumiPlagasList() {
        return constanciaFumiPlagasList;
    }

    public void setConstanciaFumiPlagasList(List<ConstanciaFumiPlagas> constanciaFumiPlagasList) {
        this.constanciaFumiPlagasList = constanciaFumiPlagasList;
    }

    public List<ConstanciaFumiProductos> getConstanciaFumiProductosList() {
        return constanciaFumiProductosList;
    }

    public void setConstanciaFumiProductosList(List<ConstanciaFumiProductos> constanciaFumiProductosList) {
        this.constanciaFumiProductosList = constanciaFumiProductosList;
    }

    public List<ConstanciaFumiAccesorios> getConstanciaFumiAccesoriosList() {
        return constanciaFumiAccesoriosList;
    }

    public void setConstanciaFumiAccesoriosList(List<ConstanciaFumiAccesorios> constanciaFumiAccesoriosList) {
        this.constanciaFumiAccesoriosList = constanciaFumiAccesoriosList;
    }

    public List<ConstanciaFumiVehiculos> getConstanciaFumiVehiculosList() {
        return constanciaFumiVehiculosList;
    }

    public void setConstanciaFumiVehiculosList(List<ConstanciaFumiVehiculos> constanciaFumiVehiculosList) {
        this.constanciaFumiVehiculosList = constanciaFumiVehiculosList;
    }

    public List<ConstanciaFumiAreas> getConstanciaFumiAreasList() {
        return constanciaFumiAreasList;
    }

    public void setConstanciaFumiAreasList(List<ConstanciaFumiAreas> constanciaFumiAreasList) {
        this.constanciaFumiAreasList = constanciaFumiAreasList;
    }

    public void changePathsByFiles() {
        constanciaFumi.setFirma(Utileria.changePathByFile(constanciaFumi.getFirma()));
    }
}
