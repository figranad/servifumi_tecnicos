package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.DatosAplicacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CheckUpdate implements Callback<String> {
    private Activity activity;

    public CheckUpdate(Activity activity) {
        this.activity = activity;
        Retrofit retrofit = ApiAdapter.getApiService_upload();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<String> call;

        PackageInfo pInfo;
        int version = 0;
        try {
            pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            version = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        call = apiService.checkUpdateVersion(new DatosAplicacion(Constant.CLAVE_CODE_VERSION_SERVIFUMI_TECNICOS, version + ""));
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.body() != null) {
            int id = Integer.parseInt(response.body());
            if (id == 1) { //necesita Update
                lanzarAlert();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
    }

    private void lanzarAlert() {
        new AlertDialog.Builder(activity)
                .setCancelable(false)
                .setIcon(R.drawable.ic_descarga)
                .setTitle("Nueva Version de la App")
                .setMessage("Es necesario descargar la nueva versión para continuar")
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.app_2sis.e_voluciona.servifumi_tecnicos")));
                        activity.finish();
                    }

                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .show();
    }
}
