package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.CatProducto;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ProductoBeanAdapter;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatProductoActiveRecord extends MyActiveRecord {
    
    public CatProductoActiveRecord(Context context) {
        super(context);
    }

    public void save(CatProducto catProductos) {
        Dao<CatProducto, Integer> dao;
        try {
            dao = getHelper().getCatProductoDao();
            dao.create(catProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean save(List<CatProducto> catProductosList) {
        boolean exito;
        Dao<CatProducto, Integer> dao;
        try {
            dao = getHelper().getCatProductoDao();
            for (CatProducto catProductos : catProductosList) {
                dao.create(catProductos);
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

    public void update(CatProducto catProductos) {
        Dao<CatProducto, Integer> dao;
        try {
            dao = getHelper().getCatProductoDao();
            dao.update(catProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<CatProducto, Integer> dao;
        try {
            dao = getHelper().getCatProductoDao();
            DeleteBuilder<CatProducto, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(CatProducto.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public CatProducto getCatProductos(String col, String val) {
        CatProducto result = null;
        Dao<CatProducto, Integer> dao;
        try {
            dao = getHelper().getCatProductoDao();
            QueryBuilder<CatProducto, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<CatProducto> catPlagaList = queryBuilder.query();
            for (CatProducto cat : catPlagaList) {
                result = cat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public List<CatProducto> getCatProductos() {
        Dao<CatProducto, Integer> dao;
        List<CatProducto> resultList = new ArrayList<>();
        try {
            dao = getHelper().getCatProductoDao();
            QueryBuilder<CatProducto, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().ge(CatProducto.ID_WS, 0).and().eq(CatProducto.STATUS_WS, Constant.SI);
            resultList = queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultList;
    }

    public boolean isEmpty() {
        return getCatProductos().isEmpty();
    }

    public String[] getCatProductosNombres() {
        List<CatProducto> catProductosList = getCatProductos();

        if (catProductosList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catProductosList.size() + 1];
        results[i++] = Constant.PROMPT;

        for (CatProducto catProductos : catProductosList) {
            results[i++] = catProductos.getNombre();
        }
        return results;
    }

    public String[] getCatProductosIDs() {
        List<CatProducto> catProductosList = getCatProductos();

        if (catProductosList.isEmpty())
            return null;

        int i = 0;
        String[] results = new String[catProductosList.size()];

        for (CatProducto catProductos : catProductosList) {
            results[i++] = catProductos.getCat_productos_id();
        }
        return results;
    }

    public String getIDInlista(String nombre) {
        List<CatProducto> list = getCatProductos();
        for (CatProducto item : list) {
            if (item.getNombre().equals(nombre))
                return item.getCat_productos_id();
        }
        return "0";
    }

    public int getPositionInlista(String id) {
        List<CatProducto> list = getCatProductos();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCat_productos_id().equals(id)) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Listado que requiere el recyclerview de las productos trabajadas
     * @return List viene con informacion default para un nuevo registro
     */
    public List<ProductoBeanAdapter> getProductoBeanAdapter() {
        List<ProductoBeanAdapter> productoBAList = new ArrayList<>();
        List<CatProducto> catProductoList = getCatProductos();

        for (CatProducto producto : catProductoList) {
            productoBAList.add(new ProductoBeanAdapter(
                    producto.getNombre() + " - " + producto.getIngrediente_act(),
                    producto.getCat_productos_id()
            ));
        }
        return productoBAList;
    }
}
