package com.app_2sis.e_voluciona.servifumi_tecnicos.extra;

import android.content.Context;
import android.content.SharedPreferences;

public class MisPreferencias {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static final String NAME_FILE_PREFERENCIAS = "MisPreferencias";

    public MisPreferencias(Context context) {
        prefs = context.getSharedPreferences(NAME_FILE_PREFERENCIAS, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

//    public void setTelefonoDirectorioLogistica1(String telefono){
//        editor.putString(KEY_CLAVE_DIRECTORIO_LOGISTICA1, telefono);
//        editor.commit();
//    }
//    /**
//     *
//     * @return Cadena con el telefono o vacía si no hay numero guardado
//     */
//    public String getTelefonoDirectorioLogistica1(){
//        return prefs.getString(KEY_CLAVE_DIRECTORIO_LOGISTICA1, "");
//    }
}
