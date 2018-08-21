package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatTurnoActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTurno;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatTurnoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatTurnoSincronizar implements Callback<CatTurnoResponse> {
    private DescargaActivity descargaActivity;
    private CatTurnoActiveRecord catTurnoActiveRecord;

    public CatTurnoSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        catTurnoActiveRecord = new CatTurnoActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload().create(ApiService.class).
                getTurnos("null").enqueue(this);
    }

    @Override
    public void onResponse(Call<CatTurnoResponse> call, Response<CatTurnoResponse> response) {
        if (response.body() != null) {
            ArrayList<CatTurno> catTurnoArrayList = response.body().getTurnos();
            if (!catTurnoArrayList.isEmpty()) {
                catTurnoActiveRecord.deleteAll();
                if (catTurnoActiveRecord.save(catTurnoArrayList)) {
                    descargaActivity.showMensaje("Turnos Descargados");
                    new CatProductoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
                }
                else {
                    descargaActivity.showMensaje("ERROR guardando turnos");
                    descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
                }

            } else {
                catTurnoActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin turnos");
                new CatProductoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando turnos");
            descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<CatTurnoResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar turnos");
        descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
    }
}
