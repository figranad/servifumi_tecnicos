package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiPlagas;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstanciaFumiPlagasActiveRecord extends MyActiveRecord {
    public ConstanciaFumiPlagasActiveRecord(Context context) {
        super(context);
    }

    public void save(ConstanciaFumiPlagas constanciaFumiPlagas) {
        Dao<ConstanciaFumiPlagas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiPlagasDao();
            dao.create(constanciaFumiPlagas);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ConstanciaFumiPlagas> constanciaFumiPlagasList) {
        boolean exito;
        Dao<ConstanciaFumiPlagas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiPlagasDao();
            for (ConstanciaFumiPlagas constanciaFumiPlagas : constanciaFumiPlagasList) {
                dao.create(constanciaFumiPlagas);
            }
            exito = true;
        } catch (SQLException e) {
            exito = false;
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return exito;
    }

    public void update(ConstanciaFumiPlagas constanciaFumiPlagas) {
        Dao<ConstanciaFumiPlagas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiPlagasDao();
            dao.update(constanciaFumiPlagas);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(List<ConstanciaFumiPlagas> constanciaFumiPlagasList) {
        Dao<ConstanciaFumiPlagas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiPlagasDao();
            for (ConstanciaFumiPlagas constanciaFumiPlagas : constanciaFumiPlagasList) {
                dao.update(constanciaFumiPlagas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void delete(String col, String val) {
        Dao<ConstanciaFumiPlagas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiPlagasDao();
            DeleteBuilder<ConstanciaFumiPlagas, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<ConstanciaFumiPlagas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiPlagasDao();
            DeleteBuilder<ConstanciaFumiPlagas, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(ConstanciaFumiPlagas.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public List<ConstanciaFumiPlagas> getConstanciaFumiPlagas(String col, String val) {
        List<ConstanciaFumiPlagas> constanciaFumiPlagasList = new ArrayList<>();
        Dao<ConstanciaFumiPlagas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiPlagasDao();
            QueryBuilder<ConstanciaFumiPlagas, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            constanciaFumiPlagasList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return constanciaFumiPlagasList;
    }
}
