package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiVehiculos;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstanciaFumiVehiculosActiveRecord extends MyActiveRecord {
    public ConstanciaFumiVehiculosActiveRecord(Context context) {
        super(context);
    }

    public void save(ConstanciaFumiVehiculos constanciaFumiVehiculos) {
        Dao<ConstanciaFumiVehiculos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiVehiculosDao();
            dao.create(constanciaFumiVehiculos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ConstanciaFumiVehiculos> constanciaFumiVehiculosList) {
        boolean exito;
        Dao<ConstanciaFumiVehiculos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiVehiculosDao();
            for (ConstanciaFumiVehiculos constanciaFumiVehiculos : constanciaFumiVehiculosList) {
                dao.create(constanciaFumiVehiculos);
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

    public void update(ConstanciaFumiVehiculos constanciaFumiVehiculos) {
        Dao<ConstanciaFumiVehiculos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiVehiculosDao();
            dao.update(constanciaFumiVehiculos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(List<ConstanciaFumiVehiculos> constanciaFumiVehiculosList) {
        Dao<ConstanciaFumiVehiculos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiVehiculosDao();
            for (ConstanciaFumiVehiculos constanciaFumiVehiculos : constanciaFumiVehiculosList) {
                dao.update(constanciaFumiVehiculos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void delete(String col, String val) {
        Dao<ConstanciaFumiVehiculos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiVehiculosDao();
            DeleteBuilder<ConstanciaFumiVehiculos, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<ConstanciaFumiVehiculos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiVehiculosDao();
            DeleteBuilder<ConstanciaFumiVehiculos, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(ConstanciaFumiVehiculos.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public List<ConstanciaFumiVehiculos> getConstanciaFumiVehiculos(String col, String val) {
        List<ConstanciaFumiVehiculos> constanciaFumiVehiculosList = new ArrayList<>();
        Dao<ConstanciaFumiVehiculos, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiVehiculosDao();
            QueryBuilder<ConstanciaFumiVehiculos, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            constanciaFumiVehiculosList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return constanciaFumiVehiculosList;
    }
}
