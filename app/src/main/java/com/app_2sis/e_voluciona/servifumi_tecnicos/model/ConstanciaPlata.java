package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import android.content.Context;

import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatTanqueActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaPlataTanquesActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.TanqueBeanAdapter;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.util.List;

public class ConstanciaPlata {
    public static final String ID_WS = "id";
    public static final String ORDEN_ID_WS = "orden_id";
    public static final String INSPEC_PLATA_ID_WS = "inspec_plata_id";
    public static final String CLIENTE_ID_WS = "cliente_id";
    public static final String TECNICO_ID_WS = "tecnico_id";
    public static final String FECHA_WS = "fecha";
    public static final String DOMICILIO_ID_WS = "domicilio_id";
    public static final String HORA_ENTRADA_WS = "hora_entrada";
    public static final String HORA_SALIDA_WS = "hora_salida";
    public static final String CONTACTO_WS = "contacto";
    public static final String USA_PLATACOLOIDAL_WS = "usa_platacoloidal";
    public static final String USA_HIPOCLORITO_WS = "usa_hipoclorito";
    public static final String USA_DESINCRUSTANTE_WS = "usa_desincrustante";
    public static final String TIP_APLI_ASPERJADO_WS = "tip_apli_asperjado";
    public static final String TIP_APLI_RODILLO_WS = "tip_apli_rodillo";
    public static final String TIP_APLI_DIRECTO_WS = "tip_apli_directo";
    public static final String TIP_MATERIAL_PVC_WS = "tip_material_pvc";
    public static final String TIP_MATERIAL_FIBROCEMENTO_WS = "tip_material_fibrocemento";
    public static final String TIP_MATERIAL_OTRO_WS = "tip_material_otro";
    public static final String TIP_MATERIAL_OBSERVACION_WS = "tip_material_observacion";
    public static final String OBSERVACIONES_WS = "observaciones";
    public static final String RECIBE_DINERO_WS = "recibe_dinero";
    public static final String DINERO_RECIBIDO_WS = "dinero_recibido";
    public static final String FIRMA_WS = "firma";
    public static final String FOLIO_CERTIFICADO_WS = "folio_certificado";
    public static final String TITULO_PROGRAMACION_WS = "titulo_programacion";

    public static final String PROGRAMACION_ID_WS = "programacion_id";
    public static final String USUARIO_ID_WS = "usuario_id";
    public static final String CLAVE_ANDROID_WS = "clave_android";
    public static final String STATUS_WS = "status";
    public static final String SINCRONIZADO_WS = "sincronizado";
    public static final String CREATED_AT_WS = "created_at_ws";
    public static final String CRT_TIME_WS = "crt_time";
    public static final String CONSTANCIA_PLATA_ID_WS = "constancia_plata_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(ORDEN_ID_WS)
    @DatabaseField(columnName = ORDEN_ID_WS)
    private String orden_id;

    @SerializedName(INSPEC_PLATA_ID_WS)
    @DatabaseField(columnName = INSPEC_PLATA_ID_WS)
    private String inspec_plata_id;

    @SerializedName(CLIENTE_ID_WS)
    @DatabaseField(columnName = CLIENTE_ID_WS)
    private String cliente_id;

    @SerializedName(TECNICO_ID_WS)
    @DatabaseField(columnName = TECNICO_ID_WS)
    private String tecnico_id;

    @SerializedName(FECHA_WS)
    @DatabaseField(columnName = FECHA_WS)
    private String fecha;

    @SerializedName(DOMICILIO_ID_WS)
    @DatabaseField(columnName = DOMICILIO_ID_WS)
    private String domicilio_id;

    @SerializedName(HORA_ENTRADA_WS)
    @DatabaseField(columnName = HORA_ENTRADA_WS)
    private String hora_entrada;

    @SerializedName(HORA_SALIDA_WS)
    @DatabaseField(columnName = HORA_SALIDA_WS)
    private String hora_salida;

    @SerializedName(CONTACTO_WS)
    @DatabaseField(columnName = CONTACTO_WS)
    private String contacto;

    @SerializedName(USA_PLATACOLOIDAL_WS)
    @DatabaseField(columnName = USA_PLATACOLOIDAL_WS)
    private String usa_platacoloidal;

    @SerializedName(USA_HIPOCLORITO_WS)
    @DatabaseField(columnName = USA_HIPOCLORITO_WS)
    private String usa_hipoclorito;

    @SerializedName(USA_DESINCRUSTANTE_WS)
    @DatabaseField(columnName = USA_DESINCRUSTANTE_WS)
    private String usa_desincrustante;

    @SerializedName(TIP_APLI_ASPERJADO_WS)
    @DatabaseField(columnName = TIP_APLI_ASPERJADO_WS)
    private String tip_apli_asperjado;

    @SerializedName(TIP_APLI_RODILLO_WS)
    @DatabaseField(columnName = TIP_APLI_RODILLO_WS)
    private String tip_apli_rodillo;

    @SerializedName(TIP_APLI_DIRECTO_WS)
    @DatabaseField(columnName = TIP_APLI_DIRECTO_WS)
    private String tip_apli_directo;

    @SerializedName(TIP_MATERIAL_PVC_WS)
    @DatabaseField(columnName = TIP_MATERIAL_PVC_WS)
    private String tip_material_pvc;

    @SerializedName(TIP_MATERIAL_FIBROCEMENTO_WS)
    @DatabaseField(columnName = TIP_MATERIAL_FIBROCEMENTO_WS)
    private String tip_material_fibrocemento;

    @SerializedName(TIP_MATERIAL_OTRO_WS)
    @DatabaseField(columnName = TIP_MATERIAL_OTRO_WS)
    private String tip_material_otro;

    @SerializedName(TIP_MATERIAL_OBSERVACION_WS)
    @DatabaseField(columnName = TIP_MATERIAL_OBSERVACION_WS)
    private String tip_material_observacion;

    @SerializedName(OBSERVACIONES_WS)
    @DatabaseField(columnName = OBSERVACIONES_WS)
    private String observaciones;

    @SerializedName(RECIBE_DINERO_WS)
    @DatabaseField(columnName = RECIBE_DINERO_WS)
    private String recibe_dinero;

    @SerializedName(DINERO_RECIBIDO_WS)
    @DatabaseField(columnName = DINERO_RECIBIDO_WS)
    private String dinero_recibido;

    @SerializedName(FIRMA_WS)
    @DatabaseField(columnName = FIRMA_WS)
    private String firma;

    @SerializedName(FOLIO_CERTIFICADO_WS)
    @DatabaseField(columnName = FOLIO_CERTIFICADO_WS)
    private String folio_certificado;

    @SerializedName(TITULO_PROGRAMACION_WS)
    @DatabaseField(columnName = TITULO_PROGRAMACION_WS)
    private String titulo_programacion;

    //*******************************************

    @SerializedName(USUARIO_ID_WS)
    @DatabaseField(columnName = USUARIO_ID_WS)
    private String usuario_id;

    @SerializedName(CLAVE_ANDROID_WS)
    @DatabaseField(columnName = CLAVE_ANDROID_WS)
    private String clave_android;

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status;

    @SerializedName(SINCRONIZADO_WS)
    @DatabaseField(columnName = SINCRONIZADO_WS)
    private String sincronizado;

    @SerializedName(CREATED_AT_WS)
    @DatabaseField(columnName = CREATED_AT_WS)
    private String created_at;

    @SerializedName(CRT_TIME_WS)
    @DatabaseField(columnName = CRT_TIME_WS)
    private String crt_time;

    @SerializedName(PROGRAMACION_ID_WS)
    @DatabaseField(columnName = PROGRAMACION_ID_WS)
    private String programacion_id;

    @SerializedName(CONSTANCIA_PLATA_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_PLATA_ID_WS)
    private String constancia_plata_id;

    //**********************************


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(String orden_id) {
        this.orden_id = orden_id;
    }

    public String getInspec_plata_id() {
        return inspec_plata_id;
    }

    public void setInspec_plata_id(String inspec_plata_id) {
        this.inspec_plata_id = inspec_plata_id;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getTecnico_id() {
        return tecnico_id;
    }

    public void setTecnico_id(String tecnico_id) {
        this.tecnico_id = tecnico_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDomicilio_id() {
        return domicilio_id;
    }

    public void setDomicilio_id(String domicilio_id) {
        this.domicilio_id = domicilio_id;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getUsa_platacoloidal() {
        return usa_platacoloidal;
    }

    public void setUsa_platacoloidal(String usa_platacoloidal) {
        this.usa_platacoloidal = usa_platacoloidal;
    }

    public String getUsa_hipoclorito() {
        return usa_hipoclorito;
    }

    public void setUsa_hipoclorito(String usa_hipoclorito) {
        this.usa_hipoclorito = usa_hipoclorito;
    }

    public String getUsa_desincrustante() {
        return usa_desincrustante;
    }

    public void setUsa_desincrustante(String usa_desincrustante) {
        this.usa_desincrustante = usa_desincrustante;
    }

    public String getTip_apli_asperjado() {
        return tip_apli_asperjado;
    }

    public void setTip_apli_asperjado(String tip_apli_asperjado) {
        this.tip_apli_asperjado = tip_apli_asperjado;
    }

    public String getTip_apli_rodillo() {
        return tip_apli_rodillo;
    }

    public void setTip_apli_rodillo(String tip_apli_rodillo) {
        this.tip_apli_rodillo = tip_apli_rodillo;
    }

    public String getTip_apli_directo() {
        return tip_apli_directo;
    }

    public void setTip_apli_directo(String tip_apli_directo) {
        this.tip_apli_directo = tip_apli_directo;
    }

    public String getTip_material_pvc() {
        return tip_material_pvc;
    }

    public void setTip_material_pvc(String tip_material_pvc) {
        this.tip_material_pvc = tip_material_pvc;
    }

    public String getTip_material_fibrocemento() {
        return tip_material_fibrocemento;
    }

    public void setTip_material_fibrocemento(String tip_material_fibrocemento) {
        this.tip_material_fibrocemento = tip_material_fibrocemento;
    }

    public String getTip_material_otro() {
        return tip_material_otro;
    }

    public void setTip_material_otro(String tip_material_otro) {
        this.tip_material_otro = tip_material_otro;
    }

    public String getTip_material_observacion() {
        return tip_material_observacion;
    }

    public void setTip_material_observacion(String tip_material_observacion) {
        this.tip_material_observacion = tip_material_observacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRecibe_dinero() {  //Servicio Liaquidado
        return recibe_dinero;
    }

    public void setRecibe_dinero(String recibe_dinero) {
        this.recibe_dinero = recibe_dinero;
    }

    public String getDinero_recibido() {
        return dinero_recibido;
    }

    public void setDinero_recibido(String dinero_recibido) {
        this.dinero_recibido = dinero_recibido;
    }

    public String getFirma() {
        if (firma == null) {
            firma = "";
        }
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getFolio_certificado() {
        return folio_certificado;
    }

    public void setFolio_certificado(String folio_certificado) {
        this.folio_certificado = folio_certificado;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getClave_android() {
        return clave_android;
    }

    public void setClave_android(String clave_android) {
        this.clave_android = clave_android;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCrt_time() {
        return crt_time;
    }

    public void setCrt_time(String crt_time) {
        this.crt_time = crt_time;
    }

    public String getConstancia_plata_id() {
        return constancia_plata_id;
    }

    public void setConstancia_plata_id(String constancia_plata_id) {
        this.constancia_plata_id = constancia_plata_id;
    }

    public String getProgramacion_id() {
        return programacion_id;
    }

    public void setProgramacion_id(String programacion_id) {
        this.programacion_id = programacion_id;
    }

    public String getTitulo_programacion() {
        return titulo_programacion;
    }

    public void setTitulo_programacion(String titulo_programacion) {
        this.titulo_programacion = titulo_programacion;
    }

    public String getTanquesString(Context context) {
        ConstanciaPlataTanquesActiveRecord constanciaPlataTanquesActiveRecord = new ConstanciaPlataTanquesActiveRecord(context);
        CatTanqueActiveRecord tanqueActiveRecord = new CatTanqueActiveRecord(context);
        String result = "";

        List<ConstanciaPlataTanques> constanciaPlataTanquesList = constanciaPlataTanquesActiveRecord
                .getConstanciaPlataTanques(ConstanciaPlataTanques.CONSTANCIA_PLATA_ID_WS, id + "");

        for (ConstanciaPlataTanques constanciaPlataTanque : constanciaPlataTanquesList) {
            result += constanciaPlataTanque.getCantidad() + ": " + tanqueActiveRecord.getCatTanques(
                    CatTanque.CAT_TANQUE_ID_WS, constanciaPlataTanque.getCat_tanque_id()).getNombre() + ". ";
        }

        return result;
    }

    public List<TanqueBeanAdapter> getTanqueBeanAdapter(Context context) {
        ConstanciaPlataTanquesActiveRecord constanciaPlataTanquesActiveRecord = new ConstanciaPlataTanquesActiveRecord(context);
        CatTanqueActiveRecord tanqueActiveRecord = new CatTanqueActiveRecord(context);

        List<TanqueBeanAdapter> tanqueBeanAdapterList = tanqueActiveRecord.getTanqueBeanAdapter();
        List<ConstanciaPlataTanques> constanciaPlataTanquesList = constanciaPlataTanquesActiveRecord
                .getConstanciaPlataTanques(ConstanciaPlataTanques.CONSTANCIA_PLATA_ID_WS, id + "");

        for (ConstanciaPlataTanques constanciaPlataTanque : constanciaPlataTanquesList) {
            for (TanqueBeanAdapter tanqueBeanAdapter : tanqueBeanAdapterList) {
                if (constanciaPlataTanque.getCat_tanque_id().equals(tanqueBeanAdapter.getTanqueID())) {
                    tanqueBeanAdapter.setCheck(true);
                    tanqueBeanAdapter.setCantidad(constanciaPlataTanque.getCantidad());
                    break;
                }
            }
        }
        return tanqueBeanAdapterList;
    }

    public void upperCase() {
        setContacto(getContacto().toUpperCase());
        setTip_material_observacion(getTip_material_observacion().toUpperCase());
        setObservaciones(getObservaciones().toUpperCase());
    }


}
