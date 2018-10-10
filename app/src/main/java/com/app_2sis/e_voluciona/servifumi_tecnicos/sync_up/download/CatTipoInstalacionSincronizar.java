package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatTipoInstalacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTipoInstalacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatTipoInstalacionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Primera Clase que se descarga de los catalogos
 */
public class CatTipoInstalacionSincronizar implements Callback<CatTipoInstalacionResponse> {
    private DescargaActivity descargaActivity;
    private CatTipoInstalacionActiveRecord catTipoInstalacionActiveRecord;

    public CatTipoInstalacionSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        catTipoInstalacionActiveRecord = new CatTipoInstalacionActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload().create(ApiService.class).
                getTipoInstalaciones("null").enqueue(this);
    }

    @Override
    public void onResponse(Call<CatTipoInstalacionResponse> call, Response<CatTipoInstalacionResponse> response) {
        if (response.body() != null) {
            ArrayList<CatTipoInstalacion> catTipoInstalacionArrayList = response.body().getTipoInstalaciones();
            if (!catTipoInstalacionArrayList.isEmpty()) {
                catTipoInstalacionActiveRecord.deleteAll();
                if (catTipoInstalacionActiveRecord.save(catTipoInstalacionArrayList)) {
                    descargaActivity.showMensaje("Tipo Instalaciones Descargados");
                    new CatPlagaSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
                }
                else {
                    descargaActivity.showMensaje("ERROR guardando tipo instalaciones");
                    descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
                }

            } else {
                catTipoInstalacionActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin tipos de instalaciones");
                new CatPlagaSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando tipo instalaciones");
            descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<CatTipoInstalacionResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar tipo instalaciones");
        descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
    }
}
