package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiAccesorios;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstanciaFumiAccesoriosActiveRecord extends MyActiveRecord {
    public ConstanciaFumiAccesoriosActiveRecord(Context context) {
        super(context);
    }

    public void save(ConstanciaFumiAccesorios constanciaFumiAccesorios) {
        Dao<ConstanciaFumiAccesorios, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAccesoriosDao();
            dao.create(constanciaFumiAccesorios);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ConstanciaFumiAccesorios> constanciaFumiAccesoriosList) {
        boolean exito;
        Dao<ConstanciaFumiAccesorios, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAccesoriosDao();
            for (ConstanciaFumiAccesorios constanciaFumiAccesorios : constanciaFumiAccesoriosList) {
                dao.create(constanciaFumiAccesorios);
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

    public void update(ConstanciaFumiAccesorios constanciaFumiAccesorios) {
        Dao<ConstanciaFumiAccesorios, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAccesoriosDao();
            dao.update(constanciaFumiAccesorios);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(List<ConstanciaFumiAccesorios> constanciaFumiAccesoriosList) {
        Dao<ConstanciaFumiAccesorios, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAccesoriosDao();
            for (ConstanciaFumiAccesorios constanciaFumiAccesorios : constanciaFumiAccesoriosList) {
                dao.update(constanciaFumiAccesorios);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void delete(String col, String val) {
        Dao<ConstanciaFumiAccesorios, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAccesoriosDao();
            DeleteBuilder<ConstanciaFumiAccesorios, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<ConstanciaFumiAccesorios, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAccesoriosDao();
            DeleteBuilder<ConstanciaFumiAccesorios, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(ConstanciaFumiAccesorios.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public List<ConstanciaFumiAccesorios> getConstanciaFumiAccesorios(String col, String val) {
        List<ConstanciaFumiAccesorios> constanciaFumiAccesoriosList = new ArrayList<>();
        Dao<ConstanciaFumiAccesorios, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAccesoriosDao();
            QueryBuilder<ConstanciaFumiAccesorios, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            constanciaFumiAccesoriosList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return constanciaFumiAccesoriosList;
    }
}
