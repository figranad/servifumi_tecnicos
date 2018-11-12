package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionAccesorioActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionAccesorio;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.ProgramacionAccesorioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramacionAccesoriosSincronizar implements Callback<ProgramacionAccesorioResponse> {
    private DescargaActivity descargaActivity;
    private ProgramacionAccesorioActiveRecord programacionAccesorioActiveRecord;

    public ProgramacionAccesoriosSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        programacionAccesorioActiveRecord = new ProgramacionAccesorioActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload()
                .create(ApiService.class).
                getProgramacionAccesorio(new ProgramacionActiveRecord(descargaActivity).getDomiciliosIDs())
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<ProgramacionAccesorioResponse> call, Response<ProgramacionAccesorioResponse> response) {
        if (response.body() != null) {
            ArrayList<ProgramacionAccesorio> programacionAccesorioArrayList = response.body().getAccesorioContratos();
            if (!programacionAccesorioArrayList.isEmpty()) {
                programacionAccesorioActiveRecord.deleteAll();
                if (programacionAccesorioActiveRecord.save(programacionAccesorioArrayList)) {
                    descargaActivity.showMensaje("Accesorios contrato Descargados");
                    new ProgramacionProductoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
                } else {
                    descargaActivity.showMensaje("ERROR guardando accesorios contrato");
                    descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
                }
            } else {
                programacionAccesorioActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin accesorios contrato");
                new ProgramacionProductoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando accesorios contrato");
            descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<ProgramacionAccesorioResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar accesorios contrato");
        descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
    }
}
