package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionProductosActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionProductos;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.ProgramacionProductosResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramacionProductoSincronizar implements Callback<ProgramacionProductosResponse> {
    private DescargaActivity descargaActivity;
    private ProgramacionProductosActiveRecord programacionProductosActiveRecord;

    public ProgramacionProductoSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        programacionProductosActiveRecord = new ProgramacionProductosActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload()
                .create(ApiService.class)
                .getProgramacionProductos(new ProgramacionActiveRecord(descargaActivity).getProgramacionesIDs())
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<ProgramacionProductosResponse> call, Response<ProgramacionProductosResponse> response) {
        if (response.body() != null) {
            ArrayList<ProgramacionProductos> programacionProductosArrayList = response.body().getProgramacionProductos();
            if (!programacionProductosArrayList.isEmpty()) {
                programacionProductosActiveRecord.deleteAll();
                if (programacionProductosActiveRecord.save(programacionProductosArrayList)) {
                    descargaActivity.showMensaje("Productos de Programaciones Descargados");
                    descargaActivity.programacionOK = true;
                    descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_OK);
                } else {
                    descargaActivity.showMensaje("ERROR guardando Productos de Programaciones");
                    descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
                }
            } else {
                programacionProductosActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin Productos de Programaciones");
                descargaActivity.programacionOK = true;
                descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_OK);
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando Productos de Programaciones");
            descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<ProgramacionProductosResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar Productos de Programaciones");
        descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
    }
}
