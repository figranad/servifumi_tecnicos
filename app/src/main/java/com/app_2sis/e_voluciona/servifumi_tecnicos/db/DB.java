package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatProducto;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTanque;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTipoInstalacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTurno;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.MetodoPago;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionProductos;
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
    private Dao<CatProducto, Integer> catProductoDao;
    private Dao<CatTurno, Integer> catTurnoDao;
    private Dao<CatTanque, Integer> catTanqueDao;
    private Dao<Programacion, Integer> programacionDao;
    private Dao<ProgramacionProductos, Integer> programacionProductosDao;


    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Usuario.class);
            TableUtils.createTableIfNotExists(connectionSource, CatTipoInstalacion.class);
            TableUtils.createTableIfNotExists(connectionSource, MetodoPago.class);
            TableUtils.createTableIfNotExists(connectionSource, CatProducto.class);
            TableUtils.createTableIfNotExists(connectionSource, CatTurno.class);
            TableUtils.createTableIfNotExists(connectionSource, Programacion.class);
            TableUtils.createTableIfNotExists(connectionSource, ProgramacionProductos.class);
            TableUtils.createTableIfNotExists(connectionSource, CatTanque.class);
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
                TableUtils.dropTable(connectionSource, CatProducto.class, true);
                TableUtils.dropTable(connectionSource, CatTurno.class, true);
                TableUtils.dropTable(connectionSource, Programacion.class, true);
                TableUtils.dropTable(connectionSource, ProgramacionProductos.class, true);
                TableUtils.dropTable(connectionSource, CatTanque.class, true);
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

    public Dao<CatProducto, Integer> getCatProductoDao() throws SQLException {
        if (catProductoDao == null) {
            catProductoDao = getDao(CatProducto.class);
        }
        return catProductoDao;
    }

    public Dao<CatTurno, Integer> getCatTurnoDao() throws SQLException {
        if (catTurnoDao == null) {
            catTurnoDao = getDao(CatTurno.class);
        }
        return catTurnoDao;
    }

    public Dao<Programacion, Integer> getProgramacionDao() throws SQLException {
        if (programacionDao == null) {
            programacionDao = getDao(Programacion.class);
        }
        return programacionDao;
    }

    public Dao<ProgramacionProductos, Integer> getProgramacionProductosDao() throws SQLException {
        if (programacionProductosDao == null) {
            programacionProductosDao = getDao(ProgramacionProductos.class);
        }
        return programacionProductosDao;
    }

    public Dao<CatTanque, Integer> getCatTanqueDao() throws SQLException {
        if (catTanqueDao == null) {
            catTanqueDao = getDao(CatTanque.class);
        }
        return catTanqueDao;
    }

    @Override
    public void close(){
        super.close();
        usuarioDao = null;
        catTipoInstalacionDao = null;
        metodoDao = null;
        catProductoDao = null;
        catTurnoDao = null;
        programacionDao = null;
        programacionProductosDao = null;
        catTanqueDao = null;
    }
}
