package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumi;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiPlagas;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiProductos;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiVehiculos;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ConstanciaFumiBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ConstanciaFumiWS;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstanciaFumiActiveRecord extends MyActiveRecord {
    public ConstanciaFumiActiveRecord(Context context) {
        super(context);
    }

    public boolean isEmpty() {
        return getConstanciasFumis().isEmpty();
    }

    public void save(ConstanciaFumi constanciaFumi) {
        Dao<ConstanciaFumi, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiDao();
            dao.create(constanciaFumi);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ConstanciaFumi> constanciaFumiList) {
        boolean exito;
        Dao<ConstanciaFumi, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiDao();
            for (ConstanciaFumi constanciaFumi : constanciaFumiList) {
                dao.create(constanciaFumi);
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

    public void update(ConstanciaFumi constanciaFumi) {
        Dao<ConstanciaFumi, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiDao();
            dao.update(constanciaFumi);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(List<ConstanciaFumi> constanciaFumiList) {
        Dao<ConstanciaFumi, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiDao();
            for (ConstanciaFumi constanciaFumi : constanciaFumiList) {
                dao.update(constanciaFumi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteConstanciaFumiFull(String constanciaFumiID) {
        if (constanciaFumiID != null) {
            delete(ConstanciaFumi.ID_WS, constanciaFumiID);
            deleteTablasDependientes(constanciaFumiID);
        }
    }

    private void delete(String col, String val) {
        Dao<ConstanciaFumi, Integer> dao;
        try {
            Utileria.deleteFile(getConstanciaFumi(col, val).getFirma());
            dao = getHelper().getConstanciaFumiDao();
            DeleteBuilder<ConstanciaFumi, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public ConstanciaFumi getConstanciaFumi(String col, String val) {
        ConstanciaFumi result = null;
        List<ConstanciaFumi> constanciaFumiList = getConstanciasFumis(col, val);
        if (constanciaFumiList.isEmpty() && col.equals(ConstanciaFumi.CONSTANCIA_FUMI_ID_WS)) {
            constanciaFumiList = getConstanciasFumis(ConstanciaFumi.CLAVE_ANDROID_WS, val);
        }
        for (ConstanciaFumi constanciaFumi : constanciaFumiList) {
            result = constanciaFumi;
        }
        return result;
    }

    public List<ConstanciaFumi> getConstanciasFumis(String col, String val) {
        List<ConstanciaFumi> constanciaFumiList = new ArrayList<>();
        Dao<ConstanciaFumi, Integer> dao;
        try {
            dao = getHelper().getConstanciaFumiDao();
            QueryBuilder<ConstanciaFumi, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            constanciaFumiList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return constanciaFumiList;
    }

    public List<ConstanciaFumi> getConstanciasFumis() {
        Dao<ConstanciaFumi, Integer> dao;
        List<ConstanciaFumi> resultList = new ArrayList<>();
        try {
            dao = getHelper().getConstanciaFumiDao();
            QueryBuilder<ConstanciaFumi, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(ConstanciaFumi.ID_WS, 0);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    private ArrayList<String> deleteNoSincronizados() {
        ArrayList<String> listConstanciaFumiID = new ArrayList<>();
        Dao<ConstanciaFumi, Integer> dao;
        try {
            List<ConstanciaFumi> list = getConstanciasFumis(ConstanciaFumi.SINCRONIZADO_WS, Constant.NO);
            for (ConstanciaFumi constanciaFumi : list) {
                listConstanciaFumiID.add(constanciaFumi.getId() + "");
                Utileria.deleteFile(constanciaFumi.getFirma());
            }
            dao = getHelper().getConstanciaFumiDao();
            DeleteBuilder<ConstanciaFumi, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(ConstanciaFumi.SINCRONIZADO_WS, Constant.NO);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return listConstanciaFumiID;
    }

    public void deleteAllNoSincronizados() {
        ArrayList<String> listConstanciaFumiID = deleteNoSincronizados();
        if (!listConstanciaFumiID.isEmpty()) {
            for (String constanciaFumiID : listConstanciaFumiID) {
                deleteTablasDependientes(constanciaFumiID);
            }
        }
    }

    public void deleteTablasDependientes(String constanciaFumiID) {
        new ConstanciaFumiPlagasActiveRecord(context).delete(
                ConstanciaFumiPlagas.CONSTANCIA_FUMI_ID_WS, constanciaFumiID);
        new ConstanciaFumiProductosActiveRecord(context).delete(
                ConstanciaFumiProductos.CONSTANCIA_FUMI_ID_WS, constanciaFumiID);
        new ConstanciaFumiVehiculosActiveRecord(context).delete(
                ConstanciaFumiVehiculos.CONSTANCIA_FUMI_ID_WS, constanciaFumiID);
    }

    public List<ConstanciaFumiBeanAdapter> getConstanciaFumiBeanAdapter(String usuarioID) {
        List<ConstanciaFumiBeanAdapter> constanciaFumiBAList = new ArrayList<>();
        List<ConstanciaFumi> constanciaFumiList = getConstanciasFumis(ConstanciaFumi.USUARIO_ID_WS, usuarioID);

        if (constanciaFumiList != null) {
            for (ConstanciaFumi constanciaFumi : constanciaFumiList) {
                constanciaFumiBAList.add(new ConstanciaFumiBeanAdapter(
                        constanciaFumi.getTitulo_programacion(),
                        constanciaFumi.getDinero_recibido(),
                        constanciaFumi.getObservaciones(),
                        constanciaFumi.getSincronizado(),
                        constanciaFumi.getId() + ""
                ));
            }
        }
        return constanciaFumiBAList;
    }

    //<editor-fold desc="IMPLEMENTACION DEL ENVIO EMPAQUETADO VIA WS">
    public List<ConstanciaFumiWS> getConstanciasFumisWsSincronizar() {
        List<ConstanciaFumiWS> results = new ArrayList<>();
        List<ConstanciaFumi> constanciaFumiList = getConstanciasFumis(ConstanciaFumi.SINCRONIZADO_WS, Constant.NO);
        for (ConstanciaFumi constanciaFumi : constanciaFumiList) {
            results.add(getConstanciaFumiWS(Integer.toString(constanciaFumi.getId())));
        }
        return results;
    }

    /**
     * @param constanciaFumiID ID real de la BD local
     * @return
     */
    private ConstanciaFumiWS getConstanciaFumiWS(String constanciaFumiID) {
        ConstanciaFumiWS constanciaFumiWS = new ConstanciaFumiWS();

        constanciaFumiWS.setConstanciaFumi(getConstanciaFumi(ConstanciaFumi.ID_WS, constanciaFumiID));

        constanciaFumiWS.setConstanciaFumiPlagasList(
                new ConstanciaFumiPlagasActiveRecord(context)
                        .getConstanciaFumiPlagas(ConstanciaFumiPlagas.CONSTANCIA_FUMI_ID_WS, constanciaFumiID)
        );

        constanciaFumiWS.setConstanciaFumiProductosList(
                new ConstanciaFumiProductosActiveRecord(context)
                        .getConstanciaFumiProductos(ConstanciaFumiProductos.CONSTANCIA_FUMI_ID_WS, constanciaFumiID)
        );

        constanciaFumiWS.setConstanciaFumiVehiculosList(
                new ConstanciaFumiVehiculosActiveRecord(context)
                        .getConstanciaFumiVehiculos(ConstanciaFumiVehiculos.CONSTANCIA_FUMI_ID_WS, constanciaFumiID)
        );

        return constanciaFumiWS;
    }
    //</editor-fold>
}
