package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.upload;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.EnviarActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumi;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ConstanciaFumiWS;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstanciaFumiEnviar implements Callback<String> {
    private EnviarActivity enviarActivity;
    private ConstanciaFumiActiveRecord constanciaFumiActiveRecord;

    public ConstanciaFumiEnviar(EnviarActivity enviarActivity) {
        this.enviarActivity = enviarActivity;
        constanciaFumiActiveRecord = new ConstanciaFumiActiveRecord(enviarActivity);
        List<ConstanciaFumiWS> constanciaFumiWSList = constanciaFumiActiveRecord.getConstanciasFumisWsSincronizar();
        ApiService apiService = ApiAdapter.getApiService_upload().create(ApiService.class);

        for (ConstanciaFumiWS constanciaFumiWS : constanciaFumiWSList){
            constanciaFumiWS.changePathsByFiles();
            enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_CARGANDO);
            apiService.createConstanciaFumi(constanciaFumiWS).enqueue(this);
        }
        if (constanciaFumiWSList.isEmpty()) {
            enviarActivity.showMensaje("Sin Constancias Fumi para Enviar");
            enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_OK);
        }
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.body() != null) {
            int id = Integer.parseInt(response.body());
            if (id > 0) {
                ConstanciaFumi constanciaFumi = constanciaFumiActiveRecord.getConstanciaFumi(ConstanciaFumi.ID_WS, id + "");
                constanciaFumi.setSincronizado(Constant.SI);
                constanciaFumiActiveRecord.update(constanciaFumi);
                enviarActivity.showMensaje("Constancia Fumi Enviada");
                enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_OK);
            } else {
                if (id == 0)
                    enviarActivity.showMensaje("Error en ConstanciaFumi (Rules BD)");
                if (id == -1)
                    enviarActivity.showMensaje("Error en ConstanciaFumi (Exception)");
                enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_ERROR);
            }
        } else {
            enviarActivity.showMensaje("Error enviando ConstanciaFumi (Respuesta vacia)");
            enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        enviarActivity.showMensaje("Error de Conexión enviando ConstanciasFumi , Reintentar");
        enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_ERROR);
    }
}