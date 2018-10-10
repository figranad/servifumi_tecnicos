package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ConstanciaFumiPlagas {
    public static final String ID_WS = "id";
    public static final String CONSTANCIA_FUMI_ID_WS = "constancia_servicio_fumi_id";
    public static final String CAT_PLAGA_ID_WS = "cat_plaga_id";
    public static final String CONSTANCIA_FUMI_PLAGAS_ID_WS = "constancia_fumi_plagas_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(CONSTANCIA_FUMI_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_ID_WS)
    private String constancia_fumi_id;

    @SerializedName(CAT_PLAGA_ID_WS)
    @DatabaseField(columnName = CAT_PLAGA_ID_WS)
    private String cat_plaga_id;

    @SerializedName(CONSTANCIA_FUMI_PLAGAS_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_PLAGAS_ID_WS)
    private String constancia_fumi_plagas_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConstancia_fumi_id() {
        return constancia_fumi_id;
    }

    public void setConstancia_fumi_id(String constancia_fumi_id) {
        this.constancia_fumi_id = constancia_fumi_id;
    }

    public String getCat_plaga_id() {
        return cat_plaga_id;
    }

    public void setCat_plaga_id(String cat_plaga_id) {
        this.cat_plaga_id = cat_plaga_id;
    }

    public String getConstancia_fumi_plagas_id() {
        return constancia_fumi_plagas_id;
    }

    public void setConstancia_fumi_plagas_id(String constancia_fumi_plagas_id) {
        this.constancia_fumi_plagas_id = constancia_fumi_plagas_id;
    }
}
