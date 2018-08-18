package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.LoginActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.UsuarioActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Usuario;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsuarioSincronizar implements Callback<UsuarioResponse> {
    private LoginActivity loginActivity;
    private UsuarioActiveRecord usuarioActiveRecord;
    private boolean autoDescarga;

    public UsuarioSincronizar(LoginActivity loginActivity, boolean autoDescarga) {
        this.loginActivity = loginActivity;
        this.usuarioActiveRecord = new UsuarioActiveRecord(loginActivity);
        this.autoDescarga = autoDescarga;
        Retrofit retrofit = ApiAdapter.getApiService_upload();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<UsuarioResponse> call = apiService.getUsuarios("null");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
        if (response.body() != null) {
            ArrayList<Usuario> usuarioArrayList =  response.body().getUsuarios();
            if (!usuarioArrayList.isEmpty()){
                usuarioActiveRecord.deleteAll();
                usuarioActiveRecord.save(usuarioArrayList);
                if (!autoDescarga) {
                    loginActivity.showMensaje("Usuarios descargados", false);
                    loginActivity.loginLocal();
                }
            }
        } else {
            if (!autoDescarga) {
                loginActivity.showMensaje("ERROR descargando usuarios", true);
            }
        }
    }

    @Override
    public void onFailure(Call<UsuarioResponse> call, Throwable t) {
        if (!autoDescarga) {
            loginActivity.showMensaje("ERROR de red al descargar usuarios", true);
        }
    }
}
