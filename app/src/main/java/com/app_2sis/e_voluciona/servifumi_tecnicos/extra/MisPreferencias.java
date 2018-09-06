package com.app_2sis.e_voluciona.servifumi_tecnicos.extra;

import android.content.Context;
import android.content.SharedPreferences;

public class MisPreferencias {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static final String NAME_FILE_PREFERENCIAS = "MisPreferencias";

    public static final String KEY_ID_USUARIO_LOGUEADO = "IdUsuarioLogueado";
    public static final String KEY_ID_TECNICO_LOGUEADO = "IdTecnicoLogueado";
    public static final String KEY_ID_TURNO_USUARIO_LOGUEADO = "IdTurnoUsuarioLogueado";

    public MisPreferencias(Context context) {
        prefs = context.getSharedPreferences(NAME_FILE_PREFERENCIAS, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setIdUsuarioLogueado(String idUsuario) {
        editor.putString(KEY_ID_USUARIO_LOGUEADO, idUsuario);
        editor.commit();
    }

    public String getIdTurnoUsuarioLogueado(){
        return prefs.getString(KEY_ID_TURNO_USUARIO_LOGUEADO, "");
    }

    public void setIdTurnoUsuarioLogueado(String idTurnoUsuario) {
        editor.putString(KEY_ID_TURNO_USUARIO_LOGUEADO, idTurnoUsuario);
        editor.commit();
    }

    public String getIdUsuarioLogueado(){
        return prefs.getString(KEY_ID_USUARIO_LOGUEADO, "");
    }

    public void setIdTecnicoLogueado(String idTecnico) {
        editor.putString(KEY_ID_TECNICO_LOGUEADO, idTecnico);
        editor.commit();
    }

    public String getIdTecnicoLogueado(){
        return prefs.getString(KEY_ID_TECNICO_LOGUEADO, "");
    }
}
