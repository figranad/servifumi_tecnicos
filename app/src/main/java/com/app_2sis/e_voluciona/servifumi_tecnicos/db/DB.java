package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatAccesorio;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiAccesorios;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionAccesorio;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatPlaga;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatProducto;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTanque;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTipoInstalacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTurno;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumi;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiPlagas;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiProductos;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiVehiculos;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlata;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlataTanques;
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

    //Version 2: Se agrega el campo de modo_pago_id a las constancias de plata
    private static final int DATABASE_VERSION = 2;
    // TODO: 06/07/2018 aumentar la version si hago cambios

    private Dao<Usuario, Integer> usuarioDao;
    private Dao<CatTipoInstalacion, Integer> catTipoInstalacionDao;
    private Dao<MetodoPago, Integer> metodoDao;
    private Dao<CatProducto, Integer> catProductoDao;
    private Dao<CatTurno, Integer> catTurnoDao;
    private Dao<CatTanque, Integer> catTanqueDao;
    private Dao<ProgramacionAccesorio, Integer> programacionAccesorioDao;
    private Dao<Programacion, Integer> programacionDao;
    private Dao<ProgramacionProductos, Integer> programacionProductosDao;
    private Dao<ConstanciaPlata, Integer> constanciaPlataDao;
    private Dao<ConstanciaPlataTanques, Integer> constanciaPlataTanquesDao;
    private Dao<ConstanciaFumi, Integer> constanciaFumiDao;
    private Dao<CatPlaga, Integer> catPlagaDao;
    private Dao<CatAccesorio, Integer> catAccesorioDao;
    private Dao<ConstanciaFumiPlagas, Integer> constanciaFumiPlagasDao;
    private Dao<ConstanciaFumiProductos, Integer> constanciaFumiProductosDao;
    private Dao<ConstanciaFumiAccesorios, Integer> constanciaFumiAccesoriosDao;
    private Dao<ConstanciaFumiVehiculos, Integer> constanciaFumiVehiculosDao;


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
            TableUtils.createTableIfNotExists(connectionSource, ProgramacionAccesorio.class);
            TableUtils.createTableIfNotExists(connectionSource, Programacion.class);
            TableUtils.createTableIfNotExists(connectionSource, ProgramacionProductos.class);
            TableUtils.createTableIfNotExists(connectionSource, CatTanque.class);
            TableUtils.createTableIfNotExists(connectionSource, ConstanciaPlata.class);
            TableUtils.createTableIfNotExists(connectionSource, ConstanciaPlataTanques.class);
            TableUtils.createTableIfNotExists(connectionSource, ConstanciaFumi.class);
            TableUtils.createTableIfNotExists(connectionSource, ConstanciaFumiPlagas.class);
            TableUtils.createTableIfNotExists(connectionSource, ConstanciaFumiProductos.class);
            TableUtils.createTableIfNotExists(connectionSource, ConstanciaFumiAccesorios.class);
            TableUtils.createTableIfNotExists(connectionSource, ConstanciaFumiVehiculos.class);
            TableUtils.createTableIfNotExists(connectionSource, CatPlaga.class);
            TableUtils.createTableIfNotExists(connectionSource, CatAccesorio.class);
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
                TableUtils.dropTable(connectionSource, ProgramacionAccesorio.class, true);
                TableUtils.dropTable(connectionSource, Programacion.class, true);
                TableUtils.dropTable(connectionSource, ProgramacionProductos.class, true);
                TableUtils.dropTable(connectionSource, CatTanque.class, true);
                TableUtils.dropTable(connectionSource, ConstanciaPlata.class, true);
                TableUtils.dropTable(connectionSource, ConstanciaPlataTanques.class, true);
                TableUtils.dropTable(connectionSource, ConstanciaFumi.class, true);
                TableUtils.dropTable(connectionSource, ConstanciaFumiPlagas.class, true);
                TableUtils.dropTable(connectionSource, ConstanciaFumiProductos.class, true);
                TableUtils.dropTable(connectionSource, ConstanciaFumiAccesorios.class, true);
                TableUtils.dropTable(connectionSource, ConstanciaFumiVehiculos.class, true);
                TableUtils.dropTable(connectionSource, CatPlaga.class, true);
                TableUtils.dropTable(connectionSource, CatAccesorio.class, true);
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

    public Dao<ProgramacionAccesorio, Integer> getProgramacionAccesorioDao() throws SQLException {
        if (programacionAccesorioDao == null) {
            programacionAccesorioDao = getDao(ProgramacionAccesorio.class);
        }
        return programacionAccesorioDao;
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

    public Dao<ConstanciaPlata, Integer> getConstanciaPlataDao() throws SQLException {
        if (constanciaPlataDao == null) {
            constanciaPlataDao = getDao(ConstanciaPlata.class);
        }
        return constanciaPlataDao;
    }

    public Dao<ConstanciaPlataTanques, Integer> getConstanciaPlataTanquesDao() throws SQLException {
        if (constanciaPlataTanquesDao == null) {
            constanciaPlataTanquesDao = getDao(ConstanciaPlataTanques.class);
        }
        return constanciaPlataTanquesDao;
    }

    public Dao<ConstanciaFumi, Integer> getConstanciaFumiDao() throws SQLException {
        if (constanciaFumiDao == null) {
            constanciaFumiDao = getDao(ConstanciaFumi.class);
        }
        return constanciaFumiDao;
    }

    public Dao<ConstanciaFumiPlagas, Integer> getConstanciaFumiPlagasDao() throws SQLException {
        if (constanciaFumiPlagasDao == null) {
            constanciaFumiPlagasDao = getDao(ConstanciaFumiPlagas.class);
        }
        return constanciaFumiPlagasDao;
    }

    public Dao<ConstanciaFumiProductos, Integer> getConstanciaFumiProductosDao() throws SQLException {
        if (constanciaFumiProductosDao == null) {
            constanciaFumiProductosDao = getDao(ConstanciaFumiProductos.class);
        }
        return constanciaFumiProductosDao;
    }

    public Dao<ConstanciaFumiAccesorios, Integer> getConstanciaFumiAccesoriosDao() throws SQLException {
        if (constanciaFumiAccesoriosDao == null) {
            constanciaFumiAccesoriosDao = getDao(ConstanciaFumiAccesorios.class);
        }
        return constanciaFumiAccesoriosDao;
    }

    public Dao<ConstanciaFumiVehiculos, Integer> getConstanciaFumiVehiculosDao() throws SQLException {
        if (constanciaFumiVehiculosDao == null) {
            constanciaFumiVehiculosDao = getDao(ConstanciaFumiVehiculos.class);
        }
        return constanciaFumiVehiculosDao;
    }

    public Dao<CatPlaga, Integer> getCatPlagaDao() throws SQLException {
        if (catPlagaDao == null) {
            catPlagaDao = getDao(CatPlaga.class);
        }
        return catPlagaDao;
    }

    public Dao<CatAccesorio, Integer> getCatAccesorioDao() throws SQLException {
        if (catAccesorioDao == null) {
            catAccesorioDao = getDao(CatAccesorio.class);
        }
        return catAccesorioDao;
    }

    @Override
    public void close() {
        super.close();
        usuarioDao = null;
        catTipoInstalacionDao = null;
        metodoDao = null;
        catProductoDao = null;
        catTurnoDao = null;
        programacionAccesorioDao = null;
        programacionDao = null;
        programacionProductosDao = null;
        catTanqueDao = null;
        constanciaPlataDao = null;
        constanciaPlataTanquesDao = null;
        constanciaFumiDao = null;
        constanciaFumiPlagasDao = null;
        constanciaFumiProductosDao = null;
        constanciaFumiAccesoriosDao = null;
        constanciaFumiVehiculosDao = null;
        catPlagaDao = null;
        catAccesorioDao = null;
    }
}
