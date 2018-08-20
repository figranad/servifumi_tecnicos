package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.MetodoPagoActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.MetodoPago;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.MetodoPagoResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MetodoPagoSincronizar implements Callback<MetodoPagoResponse> {
    private DescargaActivity descargaActivity;
    private MetodoPagoActiveRecord metodoPagoActiveRecord;

    public MetodoPagoSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        metodoPagoActiveRecord = new MetodoPagoActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload().create(ApiService.class).
                getMetodosPago("null").enqueue(this);
    }

    @Override
    public void onResponse(Call<MetodoPagoResponse> call, Response<MetodoPagoResponse> response) {
        if (response.body() != null) {
            ArrayList<MetodoPago> metodoPagoArrayList = response.body().getMetodosPago();
            if (!metodoPagoArrayList.isEmpty()) {
                metodoPagoActiveRecord.deleteAll();
                if (metodoPagoActiveRecord.save(metodoPagoArrayList)) {
                    descargaActivity.showMensaje("Metodos Pago Descargados");
                    descargaActivity.catalogosOK = true;
                    descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_OK);
                } else {
                    descargaActivity.showMensaje("ERROR guardando Metodos Pago");
                    descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
                }
            } else {
                metodoPagoActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin Metodos Pago");
                descargaActivity.catalogosOK = true;
                descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_OK);
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando Metodos Pago");
            descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<MetodoPagoResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar Metodos Pago");
        descargaActivity.refreshInterfazCatalogos(Constant.STATUS_DESCARGA_ERROR);
    }
}
