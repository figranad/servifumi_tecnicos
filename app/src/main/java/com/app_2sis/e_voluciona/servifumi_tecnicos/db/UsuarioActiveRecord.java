package com.app_2sis.e_voluciona.servifumi_tecnicos.db;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Usuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class UsuarioActiveRecord extends MyActiveRecord {
    public UsuarioActiveRecord(Context context) {
        super(context);
    }

    public void save(Usuario usuario) {
        Dao<Usuario, Integer> dao;
        try {
            dao = getHelper().getUsuarioDao();
            dao.create(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void save(List<Usuario> usuarioList) {
        Dao<Usuario, Integer> dao;
        try {
            dao = getHelper().getUsuarioDao();
            for (Usuario usuario : usuarioList) {
                dao.create(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void update(Usuario usuario) {
        Dao<Usuario, Integer> dao;
        try {
            dao = getHelper().getUsuarioDao();
            dao.update(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public void deleteAll() {
        Dao<Usuario, Integer> dao;
        try {
            dao = getHelper().getUsuarioDao();
            DeleteBuilder<Usuario, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().ge(Usuario.ID_WS, 0);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public Usuario getUsuario(String col, String val) {
        Usuario result = null;
        Dao<Usuario, Integer> dao;
        try {
            dao = getHelper().getUsuarioDao();
            QueryBuilder<Usuario, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(col, val);
            List<Usuario> usuarioList = queryBuilder.query();
            for (Usuario usuario : usuarioList) {
                result = usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }

    public Usuario getUsuarioLogin(String user, String password) {
        Usuario result = null;
        Dao<Usuario, Integer> dao;
        try {
            dao = getHelper().getUsuarioDao();
            QueryBuilder<Usuario, Integer> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(Usuario.USER_WS, user.toLowerCase()).and().eq(Usuario.PASSWORD_WS, password);
            List<Usuario> usuarioList = queryBuilder.query();
            for (Usuario usuario : usuarioList) {
                result = usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return result;
    }
}
