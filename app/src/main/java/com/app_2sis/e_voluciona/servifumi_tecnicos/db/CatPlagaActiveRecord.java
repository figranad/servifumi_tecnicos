package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatPlaga;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatPlagaActiveRecord extends MyActiveRecord {
    public CatPlagaActiveRecord(Context context) {
        super(context);
    }

    public void save(CatPlaga catPlagas) {
        Dao<CatPlaga, Integer> dao;
        try {
            dao = getHelper().getCatPlagaDao();
            dao.create(catPlagas);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<CatPlaga> catPlagasList) {
        boolean exito;
        Dao<CatPlaga, Integer> dao;
        try {
            dao = getHelper().getCatPlagaDao();
            for (CatPlaga catPlagas : catPlagasList) {
                dao.create(catPlagas);
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

    public void update(CatPlaga catPlagas) {
        Dao<CatPlaga, Integer> dao;
        try {
            dao = getHelper().getCatPlagaDao();
            dao.update(catPlagas);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<CatPlaga, Integer> dao;
        try {
            dao = getHelper().getCatPlagaDao();
            DeleteBuilder<CatPlaga, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(CatPlaga.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public CatPlaga getCatPlagas(String col, String val) {
        CatPlaga result = null;
        Dao<CatPlaga, Integer> dao;
        try {
            dao = getHelper().getCatPlagaDao();
            QueryBuilder<CatPlaga, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<CatPlaga> catPlagaList = queryBuilder.query();
            for (CatPlaga cat : catPlagaList) {
                result = cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<CatPlaga> getCatPlagas() {
        Dao<CatPlaga, Integer> dao;
        List<CatPlaga> resultList = new ArrayList<>();
        try {
            dao = getHelper().getCatPlagaDao();
            QueryBuilder<CatPlaga, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(CatPlaga.ID_WS, 0).and().eq(CatPlaga.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getCatPlagas().isEmpty();
    }

    public String[] getCatPlagasNombres() {
        List<CatPlaga> catPlagasList = getCatPlagas();

        if (catPlagasList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catPlagasList.size() + 1];
        results[i++] = Constant.PROMPT;

        for (CatPlaga catPlagas : catPlagasList) {
            results[i++] = catPlagas.getNombre();
        }
        return results;
    }

    public String[] getCatPlagasIDs() {
        List<CatPlaga> catPlagasList = getCatPlagas();

        if (catPlagasList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catPlagasList.size()];

        for (CatPlaga catPlagas : catPlagasList) {
            results[i++] = catPlagas.getCat_plaga_id();
        }
        return results;
    }

    public String getIDInlista(String nombre) {
        List<CatPlaga> list = getCatPlagas();
        for (CatPlaga item : list) {
            if (item.getNombre().equals(nombre))
                return item.getCat_plaga_id();
        }
        return "0";
    }

    public int getPositionInlista(String id) {
        List<CatPlaga> list = getCatPlagas();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCat_plaga_id().equals(id)) {
                return i + 1;
            }
        }
        return 0;
    }
}
