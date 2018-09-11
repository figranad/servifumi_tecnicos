package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class Programacion {
    public static final String ID_WS = "id";
    public static final String CLIENTE_ID_WS = "cliente_id";
    public static final String TITULO_WS = "titulo";
    public static final String CUANDO_WS = "cuando";
    public static final String FECHA_INICIO_WS = "fecha_inicio";
    public static final String FECHA_FIN_WS = "fecha_fin";

    public static final String LUGAR_WS = "lugar";
    public static final String REFERENCIA_WS = "referencia";
    public static final String VENDEDOR_ID_WS = "vendedor_id";
    public static final String VENDEDOR_WS = "vendedor";
    public static final String TELEFONOS_WS = "telefonos";
    public static final String DESCRIPCION_VISITA_WS = "descripcion_visita";
    public static final String PRODUCTOS_WS = "productos";
    public static final String MODO_PAGO_ID_WS = "modo_pago_id";
    public static final String IS_TRATAMIENTO_WS = "is_tratamiento";
    public static final String STATUS_VISITA_WS = "status_visita";
    public static final String OBSERVACION_VISITA_WS = "observacion_visita";
    public static final String LIQUIDADO_WS = "liquidado";
    public static final String DINERO_RECIBIDO_WS = "dinero_recibido";

    public static final String ORDEN_ID_WS = "orden_id";
    public static final String TIPO_SERVICIO_ID_WS = "tipo_servicio_id";
    public static final String TIPO_SERVICIO_WS = "tipo_servicio";
    public static final String DESCRIPCION_ORDEN_WS = "descripcion_orden";
    public static final String FRECUENCIA_WS = "frecuencia";
    public static final String MODO_PAGO_WS = "modo_pago";
    public static final String SALDO_WS = "saldo";
    public static final String OBSERVACION_ORDEN_WS = "observacion_orden";

    public static final String INSP_PLATA_ID_WS = "insp_plata_id";
    public static final String INSP_PLATA_TINACOS_WS = "insp_plata_tinacos";
    public static final String INSP_PLATA_CISTERNAS_WS = "insp_plata_cisternas";

    public static final String INSP_FUMI_ID_WS = "insp_fumi_id";
    public static final String INSP_FUMI_PLAGAS_WS = "insp_fumi_plagas";
    public static final String INSP_FUMI_LUGARES_WS = "insp_fumi_lugares";
    public static final String INSP_FUMI_CROQUIS_WS = "insp_fumi_croquis";


    public static final String STATUS_WS = "status";
    public static final String PROGRAMACION_ID_WS = "programacion_id";
    public static final String CLAVE_ANDROID_WS = "clave_android";
    public static final String SINCRONIZADO_WS = "sincronizado";
    public static final String REALIZADO_WS = "realizado";
    public static final String USUARIO_ID_WS = "usuario_id";
    public static final String CREATED_AT_WS = "created_at_ws";
    public static final String CRT_TIME_WS = "crt_time";
    public static final String IMPOSIBLE_REALIZAR_WS = "imposible_realizar";
    public static final String IMPOSIBLE_REALIZAR_CHK_WS = "imposible_realizar_chk";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(CLIENTE_ID_WS)
    @DatabaseField(columnName = CLIENTE_ID_WS)
    private String cliente_id;

    @SerializedName(TITULO_WS)
    @DatabaseField(columnName = TITULO_WS)
    private String titulo;

    @SerializedName(CUANDO_WS)
    @DatabaseField(columnName = CUANDO_WS)
    private String cuando;

    @SerializedName(FECHA_INICIO_WS)
    @DatabaseField(columnName = FECHA_INICIO_WS)
    private String fecha_inicio;

    @SerializedName(FECHA_FIN_WS)
    @DatabaseField(columnName = FECHA_FIN_WS)
    private String fecha_fin;

    @SerializedName(LUGAR_WS)
    @DatabaseField(columnName = LUGAR_WS)
    private String lugar;

    @SerializedName(REFERENCIA_WS)
    @DatabaseField(columnName = REFERENCIA_WS)
    private String referencia;

    @SerializedName(VENDEDOR_ID_WS)
    @DatabaseField(columnName = VENDEDOR_ID_WS)
    private String vendedor_id;

    @SerializedName(VENDEDOR_WS)
    @DatabaseField(columnName = VENDEDOR_WS)
    private String vendedor;

    @SerializedName(TELEFONOS_WS)
    @DatabaseField(columnName = TELEFONOS_WS)
    private String telefonos;

    @SerializedName(DESCRIPCION_VISITA_WS)
    @DatabaseField(columnName = DESCRIPCION_VISITA_WS)
    private String descripcion_visita;

    @SerializedName(PRODUCTOS_WS)
    @DatabaseField(columnName = PRODUCTOS_WS)
    private String productos;

    @SerializedName(MODO_PAGO_ID_WS)
    @DatabaseField(columnName = MODO_PAGO_ID_WS)
    private String modo_pago_id;

    @SerializedName(IS_TRATAMIENTO_WS)
    @DatabaseField(columnName = IS_TRATAMIENTO_WS)
    private String is_tratamiento;

    @SerializedName(STATUS_VISITA_WS)
    @DatabaseField(columnName = STATUS_VISITA_WS)
    private String status_visita;

    @SerializedName(OBSERVACION_VISITA_WS)
    @DatabaseField(columnName = OBSERVACION_VISITA_WS)
    private String observacion_visita;

    @SerializedName(LIQUIDADO_WS)
    @DatabaseField(columnName = LIQUIDADO_WS)
    private String liquidado;

    @SerializedName(DINERO_RECIBIDO_WS)
    @DatabaseField(columnName = DINERO_RECIBIDO_WS)
    private String dinero_recibido;

    @SerializedName(ORDEN_ID_WS)
    @DatabaseField(columnName = ORDEN_ID_WS)
    private String orden_id;

    @SerializedName(TIPO_SERVICIO_ID_WS)
    @DatabaseField(columnName = TIPO_SERVICIO_ID_WS)
    private String tipo_servicio_id;

    @SerializedName(TIPO_SERVICIO_WS)
    @DatabaseField(columnName = TIPO_SERVICIO_WS)
    private String tipo_servicio;

    @SerializedName(DESCRIPCION_ORDEN_WS)
    @DatabaseField(columnName = DESCRIPCION_ORDEN_WS)
    private String descripcion_orden;

    @SerializedName(FRECUENCIA_WS)
    @DatabaseField(columnName = FRECUENCIA_WS)
    private String frecuencia;

    @SerializedName(MODO_PAGO_WS)
    @DatabaseField(columnName = MODO_PAGO_WS)
    private String modo_pago;

    @SerializedName(SALDO_WS)
    @DatabaseField(columnName = SALDO_WS)
    private String saldo;

    @SerializedName(OBSERVACION_ORDEN_WS)
    @DatabaseField(columnName = OBSERVACION_ORDEN_WS)
    private String observacion_orden;

    @SerializedName(INSP_PLATA_ID_WS)
    @DatabaseField(columnName = INSP_PLATA_ID_WS)
    private String insp_plata_id;

    @SerializedName(INSP_PLATA_TINACOS_WS)
    @DatabaseField(columnName = INSP_PLATA_TINACOS_WS)
    private String insp_plata_tinacos;

    @SerializedName(INSP_PLATA_CISTERNAS_WS)
    @DatabaseField(columnName = INSP_PLATA_CISTERNAS_WS)
    private String insp_plata_cisternas;

    @SerializedName(INSP_FUMI_ID_WS)
    @DatabaseField(columnName = INSP_FUMI_ID_WS)
    private String insp_fumi_id;

    @SerializedName(INSP_FUMI_PLAGAS_WS)
    @DatabaseField(columnName = INSP_FUMI_PLAGAS_WS)
    private String insp_fumi_plagas;

    @SerializedName(INSP_FUMI_LUGARES_WS)
    @DatabaseField(columnName = INSP_FUMI_LUGARES_WS)
    private String insp_fumi_lugares;

    @SerializedName(INSP_FUMI_CROQUIS_WS)
    @DatabaseField(columnName = INSP_FUMI_CROQUIS_WS)
    private String insp_fumi_croquis;

    @SerializedName(STATUS_WS)
    @DatabaseField(columnName = STATUS_WS)
    private String status;

    @SerializedName(PROGRAMACION_ID_WS)
    @DatabaseField(columnName = PROGRAMACION_ID_WS)
    private String programacion_id;

    @SerializedName(SINCRONIZADO_WS)
    @DatabaseField(columnName = SINCRONIZADO_WS)
    private String sincronizado;

    @SerializedName(REALIZADO_WS)
    @DatabaseField(columnName = REALIZADO_WS)
    private String realizado;

    @SerializedName(USUARIO_ID_WS)
    @DatabaseField(columnName = USUARIO_ID_WS)
    private String usuario_id;

    @SerializedName(CREATED_AT_WS)
    @DatabaseField(columnName = CREATED_AT_WS)
    private String created_at;

    @SerializedName(CRT_TIME_WS)
    @DatabaseField(columnName = CRT_TIME_WS)
    private String crt_time;

    @SerializedName(CLAVE_ANDROID_WS)
    @DatabaseField(columnName = CLAVE_ANDROID_WS)
    private String clave_android;

    @SerializedName(IMPOSIBLE_REALIZAR_WS)
    @DatabaseField(columnName = IMPOSIBLE_REALIZAR_WS)
    private String imposible_realizar;

    @SerializedName(IMPOSIBLE_REALIZAR_CHK_WS)
    @DatabaseField(columnName = IMPOSIBLE_REALIZAR_CHK_WS)
    private String imposible_realizar_chk = "0";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuando() {
        return cuando;
    }

    public void setCuando(String cuando) {
        this.cuando = cuando;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(String vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getDescripcion_visita() {
        return descripcion_visita;
    }

    public void setDescripcion_visita(String descripcion_visita) {
        this.descripcion_visita = descripcion_visita;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getModo_pago_id() {
        return modo_pago_id;
    }

    public void setModo_pago_id(String modo_pago_id) {
        this.modo_pago_id = modo_pago_id;
    }

    public String getIs_tratamiento() {
        return is_tratamiento;
    }

    public void setIs_tratamiento(String is_tratamiento) {
        this.is_tratamiento = is_tratamiento;
    }

    public String getStatus_visita() {
        return status_visita;
    }

    public void setStatus_visita(String status_visita) {
        this.status_visita = status_visita;
    }

    public String getObservacion_visita() {
        return observacion_visita;
    }

    public void setObservacion_visita(String observacion_visita) {
        this.observacion_visita = observacion_visita;
    }

    public String getLiquidado() {
        return liquidado;
    }

    public void setLiquidado(String liquidado) {
        this.liquidado = liquidado;
    }

    public String getDinero_recibido() {
        return dinero_recibido;
    }

    public void setDinero_recibido(String dinero_recibido) {
        this.dinero_recibido = dinero_recibido;
    }

    public String getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(String orden_id) {
        this.orden_id = orden_id;
    }

    public String getTipo_servicio_id() {
        return tipo_servicio_id;
    }

    public void setTipo_servicio_id(String tipo_servicio_id) {
        this.tipo_servicio_id = tipo_servicio_id;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public String getDescripcion_orden() {
        return descripcion_orden;
    }

    public void setDescripcion_orden(String descripcion_orden) {
        this.descripcion_orden = descripcion_orden;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getModo_pago() {
        return modo_pago;
    }

    public void setModo_pago(String modo_pago) {
        this.modo_pago = modo_pago;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getObservacion_orden() {
        return observacion_orden;
    }

    public void setObservacion_orden(String observacion_orden) {
        this.observacion_orden = observacion_orden;
    }

    public String getInsp_plata_id() {
        return insp_plata_id;
    }

    public void setInsp_plata_id(String insp_plata_id) {
        this.insp_plata_id = insp_plata_id;
    }

    public String getInsp_plata_tinacos() {
        return insp_plata_tinacos;
    }

    public void setInsp_plata_tinacos(String insp_plata_tinacos) {
        this.insp_plata_tinacos = insp_plata_tinacos;
    }

    public String getInsp_plata_cisternas() {
        return insp_plata_cisternas;
    }

    public void setInsp_plata_cisternas(String insp_plata_cisternas) {
        this.insp_plata_cisternas = insp_plata_cisternas;
    }

    public String getInsp_fumi_id() {
        return insp_fumi_id;
    }

    public void setInsp_fumi_id(String insp_fumi_id) {
        this.insp_fumi_id = insp_fumi_id;
    }

    public String getInsp_fumi_plagas() {
        return insp_fumi_plagas;
    }

    public void setInsp_fumi_plagas(String insp_fumi_plagas) {
        this.insp_fumi_plagas = insp_fumi_plagas;
    }

    public String getInsp_fumi_lugares() {
        return insp_fumi_lugares;
    }

    public void setInsp_fumi_lugares(String insp_fumi_lugares) {
        this.insp_fumi_lugares = insp_fumi_lugares;
    }

    public String getInsp_fumi_croquis() {
        return insp_fumi_croquis;
    }

    public void setInsp_fumi_croquis(String insp_fumi_croquis) {
        this.insp_fumi_croquis = insp_fumi_croquis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProgramacion_id() {
        return programacion_id;
    }

    public void setProgramacion_id(String programacion_id) {
        this.programacion_id = programacion_id;
    }

    public String getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(String sincronizado) {
        this.sincronizado = sincronizado;
    }

    public String getRealizado() {
        return realizado == null ? Constant.NO : realizado;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
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

    public String getClave_android() {
        return clave_android;
    }

    public void setClave_android(String clave_android) {
        this.clave_android = clave_android;
    }

    public String getImposible_realizar() {
        return imposible_realizar;
    }

    public void setImposible_realizar(String imposible_realizar) {
        this.imposible_realizar = imposible_realizar;
    }

    public String getImposible_realizar_chk() {
        return imposible_realizar_chk == null ? "0" : imposible_realizar_chk;
    }

    public void setImposible_realizar_chk(String imposible_realizar_chk) {
        this.imposible_realizar_chk = imposible_realizar_chk;
    }
}
