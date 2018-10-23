package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.upload;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.EnviarActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ProgramacionWS;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.IdsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProgramacionEnviar implements Callback<IdsResponse> {
    private EnviarActivity enviarActivity;
    private ProgramacionActiveRecord activeRecordProgramacion;

    public ProgramacionEnviar(EnviarActivity enviarActivity) {
        this.enviarActivity = enviarActivity;
        activeRecordProgramacion = new ProgramacionActiveRecord(enviarActivity);
        ProgramacionWS programacionWS = activeRecordProgramacion.getProgramacionesWsSincronzar();

        if (!programacionWS.isEmpty()) {
            Retrofit retrofit = ApiAdapter.getApiService_upload();
            ApiService apiService = retrofit.create(ApiService.class);
            apiService.updateProgramaciones(programacionWS).enqueue(this);
        } else {
            enviarActivity.showMensaje("Programaciones Actualizadas");
            new ConstanciaPlataEnviar(enviarActivity);
        }
    }

    @Override
    public void onResponse(Call<IdsResponse> call, Response<IdsResponse> response) {
        if (response.body() != null) {
            Programacion programacion;
            for (String id : response.body().getIds()) {
                programacion = activeRecordProgramacion.getProgramacion(Programacion.ID_WS, id);
                programacion.setSincronizado(Constant.SI);
                activeRecordProgramacion.update(programacion);
            }
            enviarActivity.showMensaje("Programaciones Actualizadas");
            new ConstanciaPlataEnviar(enviarActivity);
        } else {
            enviarActivity.showMensaje("Error en Programaciones");
            enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<IdsResponse> call, Throwable t) {
        enviarActivity.showMensaje("Error de Conexión actualizando Programaciones");
        enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_ERROR);
    }
}