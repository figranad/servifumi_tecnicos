package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTipoInstalacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.MetodoPago;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Usuario;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DB extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "servifumi_tecnicos.db";

    private static final int DATABASE_VERSION = 1;
    // TODO: 06/07/2018 aumentar la version si hago cambios

    private Dao<Usuario, Integer> usuarioDao;
    private Dao<CatTipoInstalacion, Integer> catTipoInstalacionDao;
    private Dao<MetodoPago, Integer> metodoDao;


    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Usuario.class);
            TableUtils.createTableIfNotExists(connectionSource, CatTipoInstalacion.class);
            TableUtils.createTableIfNotExists(connectionSource, MetodoPago.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                TableUtils.dropTable(connectionSource, Usuario.class, true);
                TableUtils.dropTable(connectionSource, CatTipoInstalacion.class, true);
                TableUtils.dropTable(connectionSource, MetodoPago.class, true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        onCreate(db, connectionSource);
    }

    public Dao<Usuario, Integer> getUsuarioDao() throws SQLException {
        if (usuarioDao == null) {
            usuarioDao = getDao(Usuario.class);
        }
        return usuarioDao;
    }

    public Dao<CatTipoInstalacion, Integer> getCatTipoInstalacionDao() throws SQLException {
        if (catTipoInstalacionDao == null) {
            catTipoInstalacionDao = getDao(CatTipoInstalacion.class);
        }
        return catTipoInstalacionDao;
    }

    public Dao<MetodoPago, Integer> getMetodoPagoDao() throws SQLException {
        if (metodoDao == null) {
            metodoDao = getDao(MetodoPago.class);
        }
        return metodoDao;
    }

    @Override
    public void close(){
        super.close();
        usuarioDao = null;
        catTipoInstalacionDao = null;
        metodoDao = null;
    }
}
