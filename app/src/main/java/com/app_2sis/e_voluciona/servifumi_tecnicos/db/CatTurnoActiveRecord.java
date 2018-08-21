package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatTurno;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatTurnoActiveRecord extends MyActiveRecord {
    public CatTurnoActiveRecord(Context context) {
        super(context);
    }

    public void save(CatTurno catTurnos) {
        Dao<CatTurno, Integer> dao;
        try {
            dao = getHelper().getCatTurnoDao();
            dao.create(catTurnos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<CatTurno> catTurnosList) {
        boolean exito;
        Dao<CatTurno, Integer> dao;
        try {
            dao = getHelper().getCatTurnoDao();
            for (CatTurno catTurnos : catTurnosList) {
                dao.create(catTurnos);
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

    public void update(CatTurno catTurnos) {
        Dao<CatTurno, Integer> dao;
        try {
            dao = getHelper().getCatTurnoDao();
            dao.update(catTurnos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<CatTurno, Integer> dao;
        try {
            dao = getHelper().getCatTurnoDao();
            DeleteBuilder<CatTurno, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(CatTurno.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public CatTurno getCatTurnos(String col, String val) {
        CatTurno result = null;
        Dao<CatTurno, Integer> dao;
        try {
            dao = getHelper().getCatTurnoDao();
            QueryBuilder<CatTurno, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<CatTurno> catPlagaList = queryBuilder.query();
            for (CatTurno cat : catPlagaList) {
                result = cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<CatTurno> getCatTurnos() {
        Dao<CatTurno, Integer> dao;
        List<CatTurno> resultList = new ArrayList<>();
        try {
            dao = getHelper().getCatTurnoDao();
            QueryBuilder<CatTurno, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(CatTurno.ID_WS, 0).and().eq(CatTurno.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getCatTurnos().isEmpty();
    }

    public String[] getCatTurnosNombres() {
        List<CatTurno> catTurnosList = getCatTurnos();

        if (catTurnosList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catTurnosList.size() + 1];
        results[i++] = Constant.PROMPT;

        for (CatTurno catTurnos : catTurnosList) {
            results[i++] = catTurnos.getNombre();
        }
        return results;
    }

    public String[] getCatTurnosIDs() {
        List<CatTurno> catTurnosList = getCatTurnos();

        if (catTurnosList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catTurnosList.size()];

        for (CatTurno catTurnos : catTurnosList) {
            results[i++] = catTurnos.getCat_turnos_id();
        }
        return results;
    }

    public String getIDInlista(String nombre) {
        List<CatTurno> list = getCatTurnos();
        for (CatTurno item : list) {
            if (item.getNombre().equals(nombre))
                return item.getCat_turnos_id();
        }
        return "0";
    }

    public int getPositionInlista(String id) {
        List<CatTurno> list = getCatTurnos();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCat_turnos_id().equals(id)) {
                return i + 1;
            }
        }
        return 0;
    }
}
