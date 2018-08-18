package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.response;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Usuario;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UsuarioResponse {
    @SerializedName("usuarios")
    UsuarioResponse.UsuarioResult resultsUser;

    public ArrayList<Usuario> getUsuarios(){
        return resultsUser.usuarios;
    }

    private class UsuarioResult {
        @SerializedName("usuario")
        ArrayList<Usuario> usuarios;
    }
}
