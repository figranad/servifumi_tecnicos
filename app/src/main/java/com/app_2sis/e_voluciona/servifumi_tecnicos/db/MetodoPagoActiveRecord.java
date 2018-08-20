package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.MetodoPago;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoActiveRecord extends MyActiveRecord {

    public MetodoPagoActiveRecord(Context context) {
        super(context);
    }

    public void save(MetodoPago metodoPagos) {
        Dao<MetodoPago, Integer> dao;
        try {
            dao = getHelper().getMetodoPagoDao();
            dao.create(metodoPagos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<MetodoPago> metodoPagosList) {
        boolean exito;
        Dao<MetodoPago, Integer> dao;
        try {
            dao = getHelper().getMetodoPagoDao();
            for (MetodoPago metodoPagos : metodoPagosList) {
                dao.create(metodoPagos);
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

    public void update(MetodoPago metodoPagos) {
        Dao<MetodoPago, Integer> dao;
        try {
            dao = getHelper().getMetodoPagoDao();
            dao.update(metodoPagos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<MetodoPago, Integer> dao;
        try {
            dao = getHelper().getMetodoPagoDao();
            DeleteBuilder<MetodoPago, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(MetodoPago.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public MetodoPago getMetodoPagos(String col, String val) {
        MetodoPago result = null;
        Dao<MetodoPago, Integer> dao;
        try {
            dao = getHelper().getMetodoPagoDao();
            QueryBuilder<MetodoPago, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<MetodoPago> catPlagaList = queryBuilder.query();
            for (MetodoPago cat : catPlagaList) {
                result = cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<MetodoPago> getMetodoPagos() {
        Dao<MetodoPago, Integer> dao;
        List<MetodoPago> resultList = new ArrayList<>();
        try {
            dao = getHelper().getMetodoPagoDao();
            QueryBuilder<MetodoPago, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(MetodoPago.ID_WS, 0).and().eq(MetodoPago.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getMetodoPagos().isEmpty();
    }

    public String[] getMetodoPagosNombres() {
        List<MetodoPago> metodoPagosList = getMetodoPagos();

        if (metodoPagosList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[metodoPagosList.size() + 1];
        results[i++] = Constant.PROMPT;

        for (MetodoPago metodoPagos : metodoPagosList) {
            results[i++] = metodoPagos.getNombre();
        }
        return results;
    }

    public String[] getMetodoPagosIDs() {
        List<MetodoPago> metodoPagosList = getMetodoPagos();

        if (metodoPagosList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[metodoPagosList.size()];

        for (MetodoPago metodoPagos : metodoPagosList) {
            results[i++] = metodoPagos.getMetodo_pago_id();
        }
        return results;
    }

    public String getIDInlista(String nombre) {
        List<MetodoPago> tarjetaList = getMetodoPagos();
        for (MetodoPago tarjeta : tarjetaList) {
            if (tarjeta.getNombre().equals(nombre))
                return tarjeta.getMetodo_pago_id();
        }
        return "0";
    }

    public int getPositionInlista(String tarjetaID) {
        List<MetodoPago> tarjetaList = getMetodoPagos();
        for (int i = 0; i < tarjetaList.size(); i++) {
            if (tarjetaList.get(i).getMetodo_pago_id().equals(tarjetaID)) {
                return i + 1;
            }
        }
        return 0;
    }
}
