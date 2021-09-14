package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiAreas;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstanciaFumiAreasActiveRecord extends MyActiveRecord {
    public ConstanciaFumiAreasActiveRecord(Context context) {
        super(context);
    }

    public void save(ConstanciaFumiAreas constanciaFumiAreas) {
        Dao<ConstanciaFumiAreas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAreasDao();
            dao.create(constanciaFumiAreas);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ConstanciaFumiAreas> constanciaFumiAreasList) {
        boolean exito;
        Dao<ConstanciaFumiAreas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAreasDao();
            for (ConstanciaFumiAreas constanciaFumiAreas : constanciaFumiAreasList) {
                dao.create(constanciaFumiAreas);
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

    public void update(ConstanciaFumiAreas constanciaFumiAreas) {
        Dao<ConstanciaFumiAreas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAreasDao();
            dao.update(constanciaFumiAreas);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(List<ConstanciaFumiAreas> constanciaFumiAreasList) {
        Dao<ConstanciaFumiAreas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAreasDao();
            for (ConstanciaFumiAreas constanciaFumiAreas : constanciaFumiAreasList) {
                dao.update(constanciaFumiAreas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void delete(String col, String val) {
        Dao<ConstanciaFumiAreas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAreasDao();
            DeleteBuilder<ConstanciaFumiAreas, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<ConstanciaFumiAreas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAreasDao();
            DeleteBuilder<ConstanciaFumiAreas, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(ConstanciaFumiAreas.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public List<ConstanciaFumiAreas> getConstanciaFumiAreas(String col, String val) {
        List<ConstanciaFumiAreas> constanciaFumiAreasList = new ArrayList<>();
        Dao<ConstanciaFumiAreas, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiAreasDao();
            QueryBuilder<ConstanciaFumiAreas, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            constanciaFumiAreasList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return constanciaFumiAreasList;
    }
}
