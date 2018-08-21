package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.DatosAplicacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatProductoResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatTipoInstalacionResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatTurnoResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.MetodoPagoResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST(ApiConstants.URL_GET_USUARIOS_TECNICOS)
    Call<UsuarioResponse> getUsuarios(@Body String param);

    @POST(ApiConstants.URL_CHECK_UPDATE_VERSION)
    Call<String> checkUpdateVersion(@Body DatosAplicacion datosAplicacion);

    @POST(ApiConstants.URL_GET_TIPO_INSTALACIONES)
    Call<CatTipoInstalacionResponse> getTipoInstalaciones(@Body String param);

    @POST(ApiConstants.URL_GET_PRODUCTOS)
    Call<CatProductoResponse> getProductos(@Body String param);

    @POST(ApiConstants.URL_GET_METODOS_PAGO)
    Call<MetodoPagoResponse> getMetodosPago(@Body String param);

    @POST(ApiConstants.URL_GET_TURNOS)
    Call<CatTurnoResponse> getTurnos(@Body String param);
}
