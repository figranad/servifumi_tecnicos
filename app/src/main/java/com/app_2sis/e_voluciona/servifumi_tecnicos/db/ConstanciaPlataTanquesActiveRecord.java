package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlataTanques;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstanciaPlataTanquesActiveRecord extends MyActiveRecord {
    public ConstanciaPlataTanquesActiveRecord(Context context) {
        super(context);
    }

    public void save(ConstanciaPlataTanques constanciaPlataTanques) {
        Dao<ConstanciaPlataTanques, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataTanquesDao();
            dao.create(constanciaPlataTanques);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ConstanciaPlataTanques> constanciaPlataTanquesList) {
        boolean exito;
        Dao<ConstanciaPlataTanques, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataTanquesDao();
            for (ConstanciaPlataTanques constanciaPlataTanques : constanciaPlataTanquesList) {
                dao.create(constanciaPlataTanques);
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

    public void update(ConstanciaPlataTanques constanciaPlataTanques) {
        Dao<ConstanciaPlataTanques, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataTanquesDao();
            dao.update(constanciaPlataTanques);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(List<ConstanciaPlataTanques> constanciaPlataTanquesList) {
        Dao<ConstanciaPlataTanques, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataTanquesDao();
            for (ConstanciaPlataTanques constanciaPlataTanques : constanciaPlataTanquesList) {
                dao.update(constanciaPlataTanques);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void delete(String col, String val) {
        Dao<ConstanciaPlataTanques, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataTanquesDao();
            DeleteBuilder<ConstanciaPlataTanques, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<ConstanciaPlataTanques, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataTanquesDao();
            DeleteBuilder<ConstanciaPlataTanques, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(ConstanciaPlataTanques.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public List<ConstanciaPlataTanques> getConstanciaPlataTanques(String col, String val) {
        List<ConstanciaPlataTanques> constanciaPlataTanquesList = new ArrayList<>();
        Dao<ConstanciaPlataTanques, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataTanquesDao();
            QueryBuilder<ConstanciaPlataTanques, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            constanciaPlataTanquesList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return constanciaPlataTanquesList;
    }
}
