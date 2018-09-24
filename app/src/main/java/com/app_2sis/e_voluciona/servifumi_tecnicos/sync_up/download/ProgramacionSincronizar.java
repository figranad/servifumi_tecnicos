package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.MisPreferencias;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.ProgramacionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramacionSincronizar implements Callback<ProgramacionResponse> {
    private DescargaActivity descargaActivity;
    private ProgramacionActiveRecord programacionActiveRecord;

    public ProgramacionSincronizar(DescargaActivity descargaActivity) {
        this.descargaActivity = descargaActivity;
        programacionActiveRecord = new ProgramacionActiveRecord(descargaActivity);

        ApiAdapter.getApiService_upload()
                .create(ApiService.class)
                .getProgramacion(new MisPreferencias(descargaActivity).getIdTurnoUsuarioLogueado())
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<ProgramacionResponse> call, Response<ProgramacionResponse> response) {
        if (response.body() != null) {
            ArrayList<Programacion> programacionArrayList = response.body().getProgramacions();
            if (!programacionArrayList.isEmpty()) {
                programacionActiveRecord.deleteAll();
                if (programacionActiveRecord.save(programacionArrayList)) {
                    descargaActivity.showMensaje("Programaciones Descargadas");
                    new ProgramacionProductoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
                } else {
                    descargaActivity.showMensaje("ERROR guardando programaciones");
                    descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
                }
            } else {
                programacionActiveRecord.deleteAll();
                descargaActivity.showMensaje("Sin Programaciones");
                new MetodoPagoSincronizar(descargaActivity);    //Se lanza la descarga del siguiente catalogo
            }
        } else {
            descargaActivity.showMensaje("ERROR descargando programaciones");
            descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<ProgramacionResponse> call, Throwable t) {
        descargaActivity.showMensaje("ERROR de red al descargar programaciones");
        descargaActivity.refreshInterfazProgramacion(Constant.STATUS_DESCARGA_ERROR);
    }
}
