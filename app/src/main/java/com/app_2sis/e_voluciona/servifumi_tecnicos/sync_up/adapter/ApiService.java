package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.DatosAplicacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ConstanciaFumiWS;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ConstanciaPlataWS;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ProgramacionWS;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatPlagasResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatProductoResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatTanqueResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatTipoInstalacionResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.CatTurnoResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.IdsResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.MetodoPagoResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.ProgramacionProductosResponse;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response.ProgramacionResponse;
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

    @POST(ApiConstants.URL_GET_CAT_PLAGAS)
    Call<CatPlagasResponse> getCatPlagas(@Body String param);

    @POST(ApiConstants.URL_GET_PROGRAMACION)
    Call<ProgramacionResponse> getProgramacion(@Body String param);

    @POST(ApiConstants.URL_GET_PROGRAMACION_PRODUCTOS)
    Call<ProgramacionProductosResponse> getProgramacionProductos(@Body String param);

    @POST(ApiConstants.URL_GET_TANQUES)
    Call<CatTanqueResponse> getTanques(@Body String param);

    @POST(ApiConstants.URL_CREATE_CONSTANCIA_PLATA)
    Call<String> createConstanciaPlata(@Body ConstanciaPlataWS constanciaPlataWS);

    @POST(ApiConstants.URL_CREATE_CONSTANCIA_FUMI)
    Call<String> createConstanciaFumi(@Body ConstanciaFumiWS constanciaFumiWS);

    @POST(ApiConstants.URL_UPDATE_PROGRAMACIONES)
    Call<IdsResponse> updateProgramaciones(@Body ProgramacionWS programacionWS);
}
