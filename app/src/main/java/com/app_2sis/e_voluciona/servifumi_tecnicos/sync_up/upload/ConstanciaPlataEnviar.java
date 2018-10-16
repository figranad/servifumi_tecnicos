package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.upload;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.EnviarActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaPlataActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlata;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ConstanciaPlataWS;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstanciaPlataEnviar implements Callback<String> {
    private EnviarActivity enviarActivity;
    private ConstanciaPlataActiveRecord constanciaPlataActiveRecord;
    
    public ConstanciaPlataEnviar(EnviarActivity enviarActivity) {
        this.enviarActivity = enviarActivity;
        constanciaPlataActiveRecord = new ConstanciaPlataActiveRecord(enviarActivity);
        List<ConstanciaPlataWS> constanciaPlataWSList = constanciaPlataActiveRecord.getConstanciasPlatasWsSincronizar();
        ApiService apiService = ApiAdapter.getApiService_upload().create(ApiService.class);
        
        for (ConstanciaPlataWS constanciaPlataWS : constanciaPlataWSList){
            constanciaPlataWS.changePathsByFiles();
            enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_CARGANDO);
            apiService.createConstanciaPlata(constanciaPlataWS).enqueue(this);
        }
        if (constanciaPlataWSList.isEmpty()) {
            enviarActivity.showMensaje("Sin Constancias Plata para Enviar");
        }
        new ConstanciaFumiEnviar(enviarActivity); //Se lanza el envío de las constancias fumi despues de lanzar las de plata
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.body() != null) {
            int id = Integer.parseInt(response.body());
            if (id > 0) {
                ConstanciaPlata constanciaPlata = constanciaPlataActiveRecord.getConstanciaPlata(ConstanciaPlata.ID_WS, id + "");
                constanciaPlata.setSincronizado(Constant.SI);
                constanciaPlataActiveRecord.update(constanciaPlata);
                enviarActivity.showMensaje("Constancia Plata Enviada");
                enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_OK);
            } else {
                if (id == 0)
                    enviarActivity.showMensaje("Error en ConstanciaPlata (Rules BD)");
                if (id == -1)
                    enviarActivity.showMensaje("Error en ConstanciaPlata (Exception)");
                enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_ERROR);
            }
        } else {
            enviarActivity.showMensaje("Error enviando ConstanciaPlata (Respuesta vacia)");
            enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_ERROR);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        enviarActivity.showMensaje("Error de Conexión enviando ConstanciasPlata, Reintentar");
        enviarActivity.refreshInterfazTodo(Constant.STATUS_DESCARGA_ERROR);
    }
}
