package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter;

import retrofit.RestAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    public static Retrofit getApiService_upload() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                //Convertidor para soportar gson
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
