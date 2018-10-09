package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;
import android.widget.ListView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTanque;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.TanqueBeanAdapter;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatTanqueActiveRecord extends MyActiveRecord {
    public CatTanqueActiveRecord(Context context) {
        super(context);
    }

    public void save(CatTanque catTanques) {
        Dao<CatTanque, Integer> dao;
        try {
            dao = getHelper().getCatTanqueDao();
            dao.create(catTanques);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<CatTanque> catTanquesList) {
        boolean exito;
        Dao<CatTanque, Integer> dao;
        try {
            dao = getHelper().getCatTanqueDao();
            for (CatTanque catTanques : catTanquesList) {
                dao.create(catTanques);
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

    public void update(CatTanque catTanques) {
        Dao<CatTanque, Integer> dao;
        try {
            dao = getHelper().getCatTanqueDao();
            dao.update(catTanques);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<CatTanque, Integer> dao;
        try {
            dao = getHelper().getCatTanqueDao();
            DeleteBuilder<CatTanque, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(CatTanque.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public CatTanque getCatTanques(String col, String val) {
        CatTanque result = null;
        Dao<CatTanque, Integer> dao;
        try {
            dao = getHelper().getCatTanqueDao();
            QueryBuilder<CatTanque, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<CatTanque> catPlagaList = queryBuilder.query();
            for (CatTanque cat : catPlagaList) {
                result = cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<CatTanque> getCatTanques() {
        Dao<CatTanque, Integer> dao;
        List<CatTanque> resultList = new ArrayList<>();
        try {
            dao = getHelper().getCatTanqueDao();
            QueryBuilder<CatTanque, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(CatTanque.ID_WS, 0).and().eq(CatTanque.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getCatTanques().isEmpty();
    }

    public String[] getCatTanquesNombres() {
        List<CatTanque> catTanquesList = getCatTanques();

        if (catTanquesList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catTanquesList.size()];

        for (CatTanque catTanques : catTanquesList) {
            results[i++] = catTanques.getNombre();
        }
        return results;
    }

    public String[] getCatTanquesIDs() {
        List<CatTanque> catTanquesList = getCatTanques();

        if (catTanquesList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catTanquesList.size()];

        for (CatTanque catTanques : catTanquesList) {
            results[i++] = catTanques.getCat_tanque_id();
        }
        return results;
    }

    public String getIDInlista(String nombre) {
        List<CatTanque> list = getCatTanques();
        for (CatTanque item : list) {
            if (item.getNombre().equals(nombre))
                return item.getCat_tanque_id();
        }
        return "0";
    }

    public int getPositionInlista(String id) {
        List<CatTanque> list = getCatTanques();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCat_tanque_id().equals(id)) {
                return i + 1;
            }
        }
        return 0;
    }

    public List<TanqueBeanAdapter> getTanqueBeanAdapter() {
        List<TanqueBeanAdapter> tanqueBAList = new ArrayList<>();
        List<CatTanque> catTanqueList = getCatTanques();

        for (CatTanque tanque : catTanqueList) {
            tanqueBAList.add(new TanqueBeanAdapter(
                    tanque.getNombre(),
                    tanque.getCat_tanque_id()
            ));
        }
        return tanqueBAList;
    }
}
