package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlata;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlataTanques;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ConstanciaPlataBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ws.ConstanciaPlataWS;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConstanciaPlataActiveRecord extends MyActiveRecord {
    public ConstanciaPlataActiveRecord(Context context) {
        super(context);
    }

    public boolean isEmpty() {
        return getConstanciasPlatas().isEmpty();
    }

    public void save(ConstanciaPlata constanciaPlata) {
        Dao<ConstanciaPlata, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataDao();
            dao.create(constanciaPlata);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<ConstanciaPlata> constanciaPlataList) {
        boolean exito;
        Dao<ConstanciaPlata, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataDao();
            for (ConstanciaPlata constanciaPlata : constanciaPlataList) {
                dao.create(constanciaPlata);
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

    public void update(ConstanciaPlata constanciaPlata) {
        Dao<ConstanciaPlata, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataDao();
            dao.update(constanciaPlata);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(List<ConstanciaPlata> constanciaPlataList) {
        Dao<ConstanciaPlata, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataDao();
            for (ConstanciaPlata constanciaPlata : constanciaPlataList) {
                dao.update(constanciaPlata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteConstanciaPlataFull(String constanciaPlataID) {
        if (constanciaPlataID != null) {
            delete(ConstanciaPlata.ID_WS, constanciaPlataID);
            deleteTablasDependientes(constanciaPlataID);
        }
    }

    private void delete(String col, String val) {
        Dao<ConstanciaPlata, Integer> dao;
        try {
            Utileria.deleteFile(getConstanciaPlata(col, val).getFirma());
            dao = getHelper().getConstanciaPlataDao();
            DeleteBuilder<ConstanciaPlata, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(col, val);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public ConstanciaPlata getConstanciaPlata(String col, String val) {
        ConstanciaPlata result = null;
        List<ConstanciaPlata> constanciaPlataList = getConstanciasPlatas(col, val);
        if (constanciaPlataList.isEmpty() && col.equals(ConstanciaPlata.CONSTANCIA_PLATA_ID_WS)) {
            constanciaPlataList = getConstanciasPlatas(ConstanciaPlata.CLAVE_ANDROID_WS, val);
        }
        for (ConstanciaPlata constanciaPlata : constanciaPlataList) {
            result = constanciaPlata;
        }
        return result;
    }

    public List<ConstanciaPlata> getConstanciasPlatas(String col, String val) {
        List<ConstanciaPlata> constanciaPlataList = new ArrayList<>();
        Dao<ConstanciaPlata, Integer> dao;
        try {
            dao = getHelper().getConstanciaPlataDao();
            QueryBuilder<ConstanciaPlata, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            constanciaPlataList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return constanciaPlataList;
    }

    public List<ConstanciaPlata> getConstanciasPlatas() {
        Dao<ConstanciaPlata, Integer> dao;
        List<ConstanciaPlata> resultList = new ArrayList<>();
        try {
            dao = getHelper().getConstanciaPlataDao();
            QueryBuilder<ConstanciaPlata, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(ConstanciaPlata.ID_WS, 0);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    private ArrayList<String> deleteNoSincronizados() {
        ArrayList<String> listConstanciaPlataID = new ArrayList<>();
        Dao<ConstanciaPlata, Integer> dao;
        try {
            List<ConstanciaPlata> list = getConstanciasPlatas(ConstanciaPlata.SINCRONIZADO_WS, Constant.NO);
            for (ConstanciaPlata constanciaPlata : list) {
                listConstanciaPlataID.add(constanciaPlata.getId() + "");
                Utileria.deleteFile(constanciaPlata.getFirma());
            }
            dao = getHelper().getConstanciaPlataDao();
            DeleteBuilder<ConstanciaPlata, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(ConstanciaPlata.SINCRONIZADO_WS, Constant.NO);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return listConstanciaPlataID;
    }

    public void deleteAllNoSincronizados() {
        ArrayList<String> listConstanciaPlataID = deleteNoSincronizados();
        if (!listConstanciaPlataID.isEmpty()) {
            for (String constanciaPlataID : listConstanciaPlataID) {
                deleteTablasDependientes(constanciaPlataID);
            }
        }
    }

    public void deleteTablasDependientes(String constanciaPlataID) {
        new ConstanciaPlataTanquesActiveRecord(context).delete(
                ConstanciaPlataTanques.CONSTANCIA_PLATA_ID_WS, constanciaPlataID);
    }

    public List<ConstanciaPlataBeanAdapter> getConstanciaPlataBeanAdapter(String usuarioID) {
        List<ConstanciaPlataBeanAdapter> constanciaPlataBAList = new ArrayList<>();
        List<ConstanciaPlata> constanciaPlataList = getConstanciasPlatas(ConstanciaPlata.USUARIO_ID_WS, usuarioID);

        if (constanciaPlataList != null) {
            for (ConstanciaPlata constanciaPlata : constanciaPlataList) {
                constanciaPlataBAList.add(new ConstanciaPlataBeanAdapter(
                        constanciaPlata.getTitulo_programacion(),
                        constanciaPlata.getDinero_recibido(),
                        constanciaPlata.getTanquesString(context),
                        constanciaPlata.getSincronizado(),
                        constanciaPlata.getId() + ""
                ));
            }
        }
        return constanciaPlataBAList;
    }

//    <editor-fold desc="IMPLEMENTACION DEL ENVIO EMPAQUETADO VIA WS">
    public List<ConstanciaPlataWS> getConstanciasPlatasWsSincronizar() {
        List<ConstanciaPlataWS> results = new ArrayList<>();
        List<ConstanciaPlata> constanciaPlataList = getConstanciasPlatas(ConstanciaPlata.SINCRONIZADO_WS, Constant.NO);
        for (ConstanciaPlata constanciaPlata : constanciaPlataList) {
            results.add(getConstanciaPlataWS(Integer.toString(constanciaPlata.getId())));
        }
        return results;
    }

    /**
     * @param constanciaPlataID ID real de la BD local
     * @return
     */
    private ConstanciaPlataWS getConstanciaPlataWS(String constanciaPlataID) {
        ConstanciaPlataWS constanciaPlataWS = new ConstanciaPlataWS();

        constanciaPlataWS.setConstanciaPlata(getConstanciaPlata(ConstanciaPlata.ID_WS, constanciaPlataID));

        constanciaPlataWS.setConstanciaPlataTanquesList(
                new ConstanciaPlataTanquesActiveRecord(context)
                        .getConstanciaPlataTanques(ConstanciaPlataTanques.CONSTANCIA_PLATA_ID_WS, constanciaPlataID)
        );

        return constanciaPlataWS;
    }
//    </editor-fold>
}
