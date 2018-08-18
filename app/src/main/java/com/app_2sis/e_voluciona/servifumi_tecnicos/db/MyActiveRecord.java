package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class MyActiveRecord {
    protected DB mDBHelper;
    protected Context context;

    public MyActiveRecord(Context context) {
        this.context = context;
    }

    protected DB getHelper() {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(context, DB.class);
        }
        return mDBHelper;
    }

    protected void cerrarConexion() {
        if (mDBHelper != null) {
            OpenHelperManager.releaseHelper();
            mDBHelper = null;
        }
    }
}
