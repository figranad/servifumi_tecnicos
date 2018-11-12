package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.BeanProgramacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ProgramacionWS;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramacionActiveRecord extends MyActiveRecord {
    public ProgramacionActiveRecord(Context context) {
        super(context);
    }

    public void save(Programacion programaciones) {
        Dao<Programacion, Integer> dao;
        try {
            dao = getHelper().getProgramacionDao();
            dao.create(programaciones);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<Programacion> programacionesList) {
        boolean exito;
        Dao<Programacion, Integer> dao;
        try {
            dao = getHelper().getProgramacionDao();
            for (Programacion programaciones : programacionesList) {
                dao.create(programaciones);
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

    public void update(Programacion programaciones) {
        Dao<Programacion, Integer> dao;
        try {
            dao = getHelper().getProgramacionDao();
            dao.update(programaciones);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void delete(String col, String val) {
        Dao<Programacion, Integer> dao;
        try {
            dao = getHelper().getProgramacionDao();
            DeleteBuilder<Programacion, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<Programacion, Integer> dao;
        try {
            dao = getHelper().getProgramacionDao();
            DeleteBuilder<Programacion, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(Programacion.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public Programacion getProgramacion(String col, String val) {
        Programacion result = null;
        Dao<Programacion, Integer> dao;
        try {
            dao = getHelper().getProgramacionDao();
            QueryBuilder<Programacion, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<Programacion> programacionList = queryBuilder.query();
            if (programacionList.isEmpty() && col.equals(Programacion.PROGRAMACION_ID_WS)) {
                programacionList = getProgramaciones(Programacion.CLAVE_ANDROID_WS, val);
            }
            for (Programacion programacion : programacionList) {
                result = programacion;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<Programacion> getProgramaciones(String col, String val) {
        Dao<Programacion, Integer> dao;
        List<Programacion> resultList = new ArrayList<>();
        try {
            dao = getHelper().getProgramacionDao();
            QueryBuilder<Programacion, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    /**
     * Obtiene Completo el listado de programaciones Activas ordenadas por fechaInicio
     *
     * @return Ya ordenado
     */
    public List<Programacion> getProgramaciones() {
        Dao<Programacion, Integer> dao;
        List<Programacion> resultList = new ArrayList<>();
        try {
            dao = getHelper().getProgramacionDao();
            QueryBuilder<Programacion, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(Programacion.ID_WS, 0).and().eq(Programacion.STATUS_WS, Constant.SI);
            queryBuilder.orderBy(Programacion.FECHA_INICIO_WS, true);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    private List<BeanProgramacion> getProgramacionesSincronizar() {
        Dao<Programacion, Integer> dao;
        List<Programacion> programacionList = new ArrayList<>();
        List<BeanProgramacion> beanProgramacionList = new ArrayList<>();
        try {
            dao = getHelper().getProgramacionDao();
            QueryBuilder<Programacion, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where()
                    .eq(Programacion.IMPOSIBLE_REALIZAR_CHK_WS, Constant.SI)
                    .and().eq(Programacion.SINCRONIZADO_WS, Constant.NO);
            programacionList = queryBuilder.query();

            for (Programacion programacion : programacionList) {
                beanProgramacionList.add(new BeanProgramacion(
                        programacion.getId() + "",
                        programacion.getProgramacion_id(),
                        programacion.getImposible_realizar(),
                        programacion.getTitulo()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return beanProgramacionList;
    }

    public ProgramacionWS getProgramacionesWsSincronzar() {
        ProgramacionWS programacionWS = new ProgramacionWS();
        programacionWS.setProgramacionList(getProgramacionesSincronizar());
        return programacionWS;
    }

    public boolean isEmpty() {
        return getProgramaciones().isEmpty();
    }

    public String getProgramacionesIDs() {
        String result = "";
        Dao<Programacion, Integer> dao;
        List<Programacion> resultList;
        try {
            dao = getHelper().getProgramacionDao();
            QueryBuilder<Programacion, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.distinct()
                    .selectColumns(Programacion.PROGRAMACION_ID_WS)
                    .where().ge(Programacion.ID_WS, 0).and().eq(Programacion.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
            for (Programacion programacion : resultList) {
                result += programacion.getProgramacion_id() + ",";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public String getDomiciliosIDs() {
        String result = "";
        Dao<Programacion, Integer> dao;
        List<Programacion> resultList;
        try {
            dao = getHelper().getProgramacionDao();
            QueryBuilder<Programacion, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.distinct()
                    .selectColumns(Programacion.DOMICILIO_ID_WS)
                    .where().ge(Programacion.ID_WS, 0).and().eq(Programacion.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
            for (Programacion programacion : resultList) {
                result += programacion.getDomicilio_id() + ",";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }
}
