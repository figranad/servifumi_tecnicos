package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatProductoActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatProducto;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatProductoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatProductoSincronizar implements Callback<CatProductoResponse> {
    private DescargaActivity descargaActivity;
    private CatProductoActiveRecord catProductoActiveRecord;

    public CatProductoSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        catProductoActiveRecord = new CatProductoActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload().create(ApiService.class).
                getProductos("null").enqueue(this);
    }

    @Override
    public void onResponse(Call<CatProductoResponse> call, Response<CatProductoResponse> response) {
        if (response.body() != null) {
            ArrayList<CatProducto> catProductoArrayList = response.body().getProductos();
            if (!catProductoArrayList.isEmpty()) {
                catProductoActiveRecord.deleteAll();
                if (catProductoActiveRecord.save(catProductoArrayList)) {
                    descargaActivity.showMensaje("Productos Descargados");
                    new MetodoPagoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
                } else {
                    descargaActivity.showMensaje("ERROR guardando productos");
                    descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
                }

            } else {
                catProductoActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin productos");
                new MetodoPagoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando productos");
            descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<CatProductoResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar productos");
        descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
    }
}
