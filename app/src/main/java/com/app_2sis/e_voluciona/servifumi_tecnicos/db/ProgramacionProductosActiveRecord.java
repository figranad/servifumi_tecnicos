package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionProductos;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramacionProductosActiveRecord extends MyActiveRecord {
    public ProgramacionProductosActiveRecord(Context context) {
        super(context);
    }

    public void save(ProgramacionProductos programacionProductoss) {
        Dao<ProgramacionProductos, Integer> dao;
        try {
            dao = getHelper().getProgramacionProductosDao();
            dao.create(programacionProductoss);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ProgramacionProductos> programacionProductossList) {
        boolean exito;
        Dao<ProgramacionProductos, Integer> dao;
        try {
            dao = getHelper().getProgramacionProductosDao();
            for (ProgramacionProductos programacionProductoss : programacionProductossList) {
                dao.create(programacionProductoss);
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

    public void update(ProgramacionProductos programacionProductoss) {
        Dao<ProgramacionProductos, Integer> dao;
        try {
            dao = getHelper().getProgramacionProductosDao();
            dao.update(programacionProductoss);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<ProgramacionProductos, Integer> dao;
        try {
            dao = getHelper().getProgramacionProductosDao();
            DeleteBuilder<ProgramacionProductos, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(ProgramacionProductos.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public ProgramacionProductos getProgramacionProductoss(String col, String val) {
        ProgramacionProductos result = null;
        Dao<ProgramacionProductos, Integer> dao;
        try {
            dao = getHelper().getProgramacionProductosDao();
            QueryBuilder<ProgramacionProductos, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<ProgramacionProductos> catPlagaList = queryBuilder.query();
            for (ProgramacionProductos cat : catPlagaList) {
                result = cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<ProgramacionProductos> getProgramacionProductoss() {
        Dao<ProgramacionProductos, Integer> dao;
        List<ProgramacionProductos> resultList = new ArrayList<>();
        try {
            dao = getHelper().getProgramacionProductosDao();
            QueryBuilder<ProgramacionProductos, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(ProgramacionProductos.ID_WS, 0).and().eq(ProgramacionProductos.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getProgramacionProductoss().isEmpty();
    }
}
