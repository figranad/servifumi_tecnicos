package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Usuario {
    public static final String ID_WS = "id";
    public static final String USER_ID_WS = "usuario_id";
    public static final String NAME_WS = "name";
    public static final String USER_WS = "user";
    public static final String PASSWORD_WS = "password";
    public static final String STATUS_WS = "status";
    public static final String TECNICO_ID_WS = "tecnico_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(USER_ID_WS)
    @DatabaseField(columnName = USER_ID_WS)
    private String usuario_id = "0";

    @SerializedName(NAME_WS)
    @DatabaseField(columnName = NAME_WS)
    private String name = "";

    @SerializedName(USER_WS)
    @DatabaseField(columnName = USER_WS)
    private String user = "";

    @SerializedName(PASSWORD_WS)
    @DatabaseField(columnName = PASSWORD_WS)
    private String password = "";

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status = "";

    @SerializedName(TECNICO_ID_WS)
    @DatabaseField(columnName = TECNICO_ID_WS)
    private String tecnico_id = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsuarioId() {
        return usuario_id;
    }

    public String getTecnico_id() {
        return tecnico_id;
    }

    public void setTecnico_id(String tecnico_id) {
        this.tecnico_id = tecnico_id;
    }

    public void setUsuarioId(String usuario_id) {
        this.usuario_id = usuario_id;
    }
}
