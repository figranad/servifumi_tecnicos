package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class CatProducto {
    public static final String ID_WS = "id";
    public static final String NOMBRE_WS = "nombre";
    public static final String CATEGORIA_WS = "categoria";
    public static final String INGREDIENTE_ACT_WS = "ingrediente_act";
    public static final String REG_CICOPLAFEST_WS = "reg_cicoplafest";
    public static final String STATUS_WS = "status";
    public static final String CAT_PRODUCTOS_ID_WS = "cat_productos_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(NOMBRE_WS)
    @DatabaseField(columnName = NOMBRE_WS)
    private String nombre;

    @SerializedName(CATEGORIA_WS)
    @DatabaseField(columnName = CATEGORIA_WS)
    private String categoria;

    @SerializedName(INGREDIENTE_ACT_WS)
    @DatabaseField(columnName = INGREDIENTE_ACT_WS)
    private String ingrediente_act;

    @SerializedName(REG_CICOPLAFEST_WS)
    @DatabaseField(columnName = REG_CICOPLAFEST_WS)
    private String reg_cicoplafest;

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status;

    @SerializedName(CAT_PRODUCTOS_ID_WS)
    @DatabaseField(columnName = CAT_PRODUCTOS_ID_WS)
    private String cat_productos_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIngrediente_act() {
        return ingrediente_act;
    }

    public void setIngrediente_act(String ingrediente_act) {
        this.ingrediente_act = ingrediente_act;
    }

    public String getReg_cicoplafest() {
        return reg_cicoplafest;
    }

    public void setReg_cicoplafest(String reg_cicoplafest) {
        this.reg_cicoplafest = reg_cicoplafest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCat_productos_id() {
        return cat_productos_id;
    }

    public void setCat_productos_id(String cat_productos_id) {
        this.cat_productos_id = cat_productos_id;
    }
}
