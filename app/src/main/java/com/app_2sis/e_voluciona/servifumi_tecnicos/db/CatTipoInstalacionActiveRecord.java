package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTipoInstalacion;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatTipoInstalacionActiveRecord extends MyActiveRecord {

    public CatTipoInstalacionActiveRecord(Context context) {
        super(context);
    }

    public void save(CatTipoInstalacion catTipoInstalacions) {
        Dao<CatTipoInstalacion, Integer> dao;
        try {
            dao = getHelper().getCatTipoInstalacionDao();
            dao.create(catTipoInstalacions);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<CatTipoInstalacion> catTipoInstalacionsList) {
        boolean exito;
        Dao<CatTipoInstalacion, Integer> dao;
        try {
            dao = getHelper().getCatTipoInstalacionDao();
            for (CatTipoInstalacion catTipoInstalacions : catTipoInstalacionsList) {
                dao.create(catTipoInstalacions);
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

    public void update(CatTipoInstalacion catTipoInstalacions) {
        Dao<CatTipoInstalacion, Integer> dao;
        try {
            dao = getHelper().getCatTipoInstalacionDao();
            dao.update(catTipoInstalacions);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<CatTipoInstalacion, Integer> dao;
        try {
            dao = getHelper().getCatTipoInstalacionDao();
            DeleteBuilder<CatTipoInstalacion, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(CatTipoInstalacion.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public CatTipoInstalacion getCatTipoInstalacions(String col, String val) {
        CatTipoInstalacion result = null;
        Dao<CatTipoInstalacion, Integer> dao;
        try {
            dao = getHelper().getCatTipoInstalacionDao();
            QueryBuilder<CatTipoInstalacion, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<CatTipoInstalacion> catPlagaList = queryBuilder.query();
            for (CatTipoInstalacion cat : catPlagaList) {
                result = cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<CatTipoInstalacion> getCatTipoInstalacions() {
        Dao<CatTipoInstalacion, Integer> dao;
        List<CatTipoInstalacion> resultList = new ArrayList<>();
        try {
            dao = getHelper().getCatTipoInstalacionDao();
            QueryBuilder<CatTipoInstalacion, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(CatTipoInstalacion.ID_WS, 0).and().eq(CatTipoInstalacion.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getCatTipoInstalacions().isEmpty();
    }

    public String[] getCatTipoInstalacionsNombres() {
        List<CatTipoInstalacion> catTipoInstalacionsList = getCatTipoInstalacions();

        if (catTipoInstalacionsList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catTipoInstalacionsList.size() + 1];
        results[i++] = Constant.PROMPT;

        for (CatTipoInstalacion catTipoInstalacions : catTipoInstalacionsList) {
            results[i++] = catTipoInstalacions.getNombre();
        }
        return results;
    }

    public String[] getCatTipoInstalacionsIDs() {
        List<CatTipoInstalacion> catTipoInstalacionsList = getCatTipoInstalacions();

        if (catTipoInstalacionsList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catTipoInstalacionsList.size()];

        for (CatTipoInstalacion catTipoInstalacions : catTipoInstalacionsList) {
            results[i++] = catTipoInstalacions.getCat_tipo_instalacion_id();
        }
        return results;
    }

    public String getIDInlista(String nombre) {
        List<CatTipoInstalacion> tarjetaList = getCatTipoInstalacions();
        for (CatTipoInstalacion tarjeta : tarjetaList) {
            if (tarjeta.getNombre().equals(nombre))
                return tarjeta.getCat_tipo_instalacion_id();
        }
        return "0";
    }

    public int getPositionInlista(String tarjetaID) {
        List<CatTipoInstalacion> tarjetaList = getCatTipoInstalacions();
        for (int i = 0; i < tarjetaList.size(); i++) {
            if (tarjetaList.get(i).getCat_tipo_instalacion_id().equals(tarjetaID)) {
                return i + 1;
            }
        }
        return 0;
    }
}
