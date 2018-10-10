package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiProductos;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstanciaFumiProductosActiveRecord extends MyActiveRecord {
    public ConstanciaFumiProductosActiveRecord(Context context) {
        super(context);
    }

    public void save(ConstanciaFumiProductos constanciaFumiProductos) {
        Dao<ConstanciaFumiProductos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiProductosDao();
            dao.create(constanciaFumiProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ConstanciaFumiProductos> constanciaFumiProductosList) {
        boolean exito;
        Dao<ConstanciaFumiProductos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiProductosDao();
            for (ConstanciaFumiProductos constanciaFumiProductos : constanciaFumiProductosList) {
                dao.create(constanciaFumiProductos);
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

    public void update(ConstanciaFumiProductos constanciaFumiProductos) {
        Dao<ConstanciaFumiProductos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiProductosDao();
            dao.update(constanciaFumiProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(List<ConstanciaFumiProductos> constanciaFumiProductosList) {
        Dao<ConstanciaFumiProductos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiProductosDao();
            for (ConstanciaFumiProductos constanciaFumiProductos : constanciaFumiProductosList) {
                dao.update(constanciaFumiProductos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void delete(String col, String val) {
        Dao<ConstanciaFumiProductos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiProductosDao();
            DeleteBuilder<ConstanciaFumiProductos, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<ConstanciaFumiProductos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiProductosDao();
            DeleteBuilder<ConstanciaFumiProductos, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(ConstanciaFumiProductos.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public List<ConstanciaFumiProductos> getConstanciaFumiProductos(String col, String val) {
        List<ConstanciaFumiProductos> constanciaFumiProductosList = new ArrayList<>();
        Dao<ConstanciaFumiProductos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiProductosDao();
            QueryBuilder<ConstanciaFumiProductos, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            constanciaFumiProductosList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return constanciaFumiProductosList;
    }
}
