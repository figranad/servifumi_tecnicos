package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatAccesorio;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatAccesorioActiveRecord extends MyActiveRecord {
    public CatAccesorioActiveRecord(Context context) {
        super(context);
    }

    public void save(CatAccesorio catAccesorios) {
        Dao<CatAccesorio, Integer> dao;
        try {
            dao = getHelper().getCatAccesorioDao();
            dao.create(catAccesorios);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<CatAccesorio> catAccesoriosList) {
        boolean exito;
        Dao<CatAccesorio, Integer> dao;
        try {
            dao = getHelper().getCatAccesorioDao();
            for (CatAccesorio catAccesorios : catAccesoriosList) {
                dao.create(catAccesorios);
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

    public void update(CatAccesorio catAccesorios) {
        Dao<CatAccesorio, Integer> dao;
        try {
            dao = getHelper().getCatAccesorioDao();
            dao.update(catAccesorios);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<CatAccesorio, Integer> dao;
        try {
            dao = getHelper().getCatAccesorioDao();
            DeleteBuilder<CatAccesorio, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(CatAccesorio.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public CatAccesorio getCatAccesorios(String col, String val) {
        CatAccesorio result = null;
        Dao<CatAccesorio, Integer> dao;
        try {
            dao = getHelper().getCatAccesorioDao();
            QueryBuilder<CatAccesorio, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<CatAccesorio> catAccesorioList = queryBuilder.query();
            for (CatAccesorio cat : catAccesorioList) {
                result = cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<CatAccesorio> getCatAccesorios() {
        Dao<CatAccesorio, Integer> dao;
        List<CatAccesorio> resultList = new ArrayList<>();
        try {
            dao = getHelper().getCatAccesorioDao();
            QueryBuilder<CatAccesorio, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(CatAccesorio.ID_WS, 0).and().eq(CatAccesorio.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getCatAccesorios().isEmpty();
    }

    public String[] getCatAccesoriosNombres() {
        List<CatAccesorio> catAccesoriosList = getCatAccesorios();

        if (catAccesoriosList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catAccesoriosList.size() + 1];
        results[i++] = Constant.PROMPT;

        for (CatAccesorio catAccesorios : catAccesoriosList) {
            results[i++] = catAccesorios.getNombre();
        }
        return results;
    }

    public String[] getCatAccesoriosIDs() {
        List<CatAccesorio> catAccesoriosList = getCatAccesorios();

        if (catAccesoriosList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catAccesoriosList.size()];

        for (CatAccesorio catAccesorios : catAccesoriosList) {
            results[i++] = catAccesorios.getCat_accesorio_id();
        }
        return results;
    }

    public String getIDInlista(String nombre) {
        List<CatAccesorio> list = getCatAccesorios();
        for (CatAccesorio item : list) {
            if (item.getNombre().equals(nombre))
                return item.getCat_accesorio_id();
        }
        return "0";
    }

    public int getPositionInlista(String id) {
        List<CatAccesorio> list = getCatAccesorios();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCat_accesorio_id().equals(id)) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Listado que requiere el recyclerview de las accesorios trabajadas, viene con informacion default para un nuevo registro
     * @return
     */
//    public List<AccesorioBeanAdapter> getAccesorioBeanAdapter() {
//        List<AccesorioBeanAdapter> accesorioBAList = new ArrayList<>();
//        List<CatAccesorio> catAccesorioList = getCatAccesorios();
//
//        for (CatAccesorio accesorio : catAccesorioList) {
//            accesorioBAList.add(new AccesorioBeanAdapter(
//                    accesorio.getNombre(),
//                    accesorio.getCat_accesorio_id()
//            ));
//        }
//        return accesorioBAList;
//    }
}
