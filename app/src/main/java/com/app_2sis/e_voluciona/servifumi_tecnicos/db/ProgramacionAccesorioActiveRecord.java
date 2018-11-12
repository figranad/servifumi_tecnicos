package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionAccesorio;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramacionAccesorioActiveRecord extends MyActiveRecord {
    
    public ProgramacionAccesorioActiveRecord(Context context) {
        super(context);
    }

    public void save(ProgramacionAccesorio programacionAccesorioContratos) {
        Dao<ProgramacionAccesorio, Integer> dao;
        try {
            dao = getHelper().getProgramacionAccesorioDao();
            dao.create(programacionAccesorioContratos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ProgramacionAccesorio> programacionAccesorioContratosList) {
        boolean exito;
        Dao<ProgramacionAccesorio, Integer> dao;
        try {
            dao = getHelper().getProgramacionAccesorioDao();
            for (ProgramacionAccesorio programacionAccesorioContratos : programacionAccesorioContratosList) {
                dao.create(programacionAccesorioContratos);
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

    public void update(ProgramacionAccesorio programacionAccesorioContratos) {
        Dao<ProgramacionAccesorio, Integer> dao;
        try {
            dao = getHelper().getProgramacionAccesorioDao();
            dao.update(programacionAccesorioContratos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<ProgramacionAccesorio, Integer> dao;
        try {
            dao = getHelper().getProgramacionAccesorioDao();
            DeleteBuilder<ProgramacionAccesorio, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(ProgramacionAccesorio.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public List<ProgramacionAccesorio> getProgramacionAccesorio() {
        Dao<ProgramacionAccesorio, Integer> dao;
        List<ProgramacionAccesorio> resultList = new ArrayList<>();
        try {
            dao = getHelper().getProgramacionAccesorioDao();
            QueryBuilder<ProgramacionAccesorio, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(ProgramacionAccesorio.ID_WS, 0).and().eq(ProgramacionAccesorio.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getProgramacionAccesorio().isEmpty();
    }

    /**
     * Listado que requiere el recyclerview de las accesorioContratos trabajadas
     * @return List viene con informacion default para un nuevo registro
     */
//    public List<AccesorioContratoBeanAdapter> getAccesorioContratoBeanAdapter() {
//        List<AccesorioContratoBeanAdapter> accesorioContratoBAList = new ArrayList<>();
//        List<ProgramacionAccesorio> programacionAccesorioList = getProgramacionAccesorio();
//
//        for (ProgramacionAccesorio accesorioContrato : programacionAccesorioList) {
//            accesorioContratoBAList.add(new AccesorioContratoBeanAdapter(
//                    accesorioContrato.getNombre(),
//                    accesorioContrato.getCat_accesorioContratos_id()
//            ));
//        }
//        return accesorioContratoBAList;
//    }
}
