package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatPlagaActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatPlaga;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatPlagasResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatPlagaSincronizar implements Callback<CatPlagasResponse> {
    private DescargaActivity descargaActivity;
    private CatPlagaActiveRecord catPlagaActiveRecord;

    public CatPlagaSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        catPlagaActiveRecord = new CatPlagaActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload().create(ApiService.class).
                getCatPlagas("null").enqueue(this);
    }

    @Override
    public void onResponse(Call<CatPlagasResponse> call, Response<CatPlagasResponse> response) {
        if (response.body() != null) {
            ArrayList<CatPlaga> catPlagaArrayList = response.body().getCatPlagas();
            if (!catPlagaArrayList.isEmpty()) {
                catPlagaActiveRecord.deleteAll();
                if (catPlagaActiveRecord.save(catPlagaArrayList)) {
                    descargaActivity.showMensaje("Plagas Descargadas");
                    new CatAccesorioSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
                }
                else {
                    descargaActivity.showMensaje("ERROR guardando plagas");
                    descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
                }
            } else {
                catPlagaActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin plagas");
                new CatAccesorioSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando plagas");
            descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<CatPlagasResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar plagas");
        descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
    }
}
