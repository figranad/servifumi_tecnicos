package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatTanqueActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTanque;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatTanqueResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatTanqueSincronizar implements Callback<CatTanqueResponse> {
    private DescargaActivity descargaActivity;
    private CatTanqueActiveRecord catTanqueActiveRecord;

    public CatTanqueSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        catTanqueActiveRecord = new CatTanqueActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload().create(ApiService.class).
                getTanques("null").enqueue(this);
    }

    @Override
    public void onResponse(Call<CatTanqueResponse> call, Response<CatTanqueResponse> response) {
        if (response.body() != null) {
            ArrayList<CatTanque> catTanqueArrayList = response.body().getTanques();
            if (!catTanqueArrayList.isEmpty()) {
                catTanqueActiveRecord.deleteAll();
                if (catTanqueActiveRecord.save(catTanqueArrayList)) {
                    descargaActivity.showMensaje("Tanques Descargados");
                    new MetodoPagoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
                } else {
                    descargaActivity.showMensaje("ERROR guardando tanques");
                    descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
                }

            } else {
                catTanqueActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin tanques");
                new MetodoPagoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando tanques");
            descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<CatTanqueResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar tanques");
        descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
    }
}
