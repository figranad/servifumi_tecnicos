package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatAccesorioActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatAccesorio;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatAccesoriosResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatAccesorioSincronizar implements Callback<CatAccesoriosResponse> {
    private DescargaActivity descargaActivity;
    private CatAccesorioActiveRecord catAccesorioActiveRecord;

    public CatAccesorioSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        catAccesorioActiveRecord = new CatAccesorioActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload().create(ApiService.class).
                getCatAccesorios("null").enqueue(this);
    }

    @Override
    public void onResponse(Call<CatAccesoriosResponse> call, Response<CatAccesoriosResponse> response) {
        if (response.body() != null) {
            ArrayList<CatAccesorio> catAccesorioArrayList = response.body().getCatAccesorios();
            if (!catAccesorioArrayList.isEmpty()) {
                catAccesorioActiveRecord.deleteAll();
                if (catAccesorioActiveRecord.save(catAccesorioArrayList)) {
                    descargaActivity.showMensaje("Accesorios Descargadas");
                    new CatTurnoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
                } else {
                    descargaActivity.showMensaje("ERROR guardando accesorios");
                    descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
                }
            } else {
                catAccesorioActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin accesorios");
                new CatTurnoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando accesorios");
            descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<CatAccesoriosResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar accesorios");
        descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
    }
}
