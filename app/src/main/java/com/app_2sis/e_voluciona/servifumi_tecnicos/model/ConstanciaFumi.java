package com.app_2sis.e_voluciona.servifumi_tecnicos.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

public class ConstanciaFumi {
    public static final String ID_WS = "id";
    public static final String ORDEN_ID_WS = "orden_id";
    public static final String INSPEC_FUMI_ID_WS = "inspec_fumi_id";
    public static final String CLIENTE_ID_WS = "cliente_id";
    public static final String TECNICO_ID_WS = "tecnico_id";
    public static final String FECHA_WS = "fecha";
    public static final String DOMICILIO_ID_WS = "domicilio_id";
    public static final String HORA_ENTRADA_WS = "hora_entrada";
    public static final String HORA_SALIDA_WS = "hora_salida";
    public static final String CONTACTO_WS = "contacto";

    public static final String TIPO_INSTALACION_ID = "tipo_instalacion_id";
    public static final String AREAS_TRATADAS_INTERIOR = "areas_tratadas_interior";
    public static final String AREAS_TRATADAS_EXTERIOR = "areas_tratadas_exterior";
    public static final String AREAS_TRATADAS_VEHICULO = "areas_tratadas_vehiculo";
    public static final String TIPO_SERVICIO = "tipo_servicio";
    public static final String TIPO_SERVICIO_OTRO = "tipo_servicio_otro";
    public static final String TIP_APLICA_ASPERSION = "tip_aplica_aspersion";
    public static final String TIP_APLICA_MICRONIZACION = "tip_aplica_micronizacion";
    public static final String TIP_APLICA_CEBO_RODENT = "tip_aplica_cebo_rodent";
    public static final String TIP_APLICA_CEBO_GEL = "tip_aplica_cebo_gel";
    public static final String TIP_APLICA_TRAMPAS = "tip_aplica_trampas";
    public static final String TIP_APLICA_TERMONEB = "tip_aplica_termoneb";
    public static final String TIP_APLICA_INYECCION = "tip_aplica_inyeccion";

    public static final String OBSERVACIONES_WS = "observaciones";
    public static final String SERVICIO_LIQUIDADO_WS = "servicio_pagado";
    public static final String MODO_PAGO_ID = "modo_pago_id";
    public static final String DINERO_RECIBIDO_WS = "dinero_recibido";
    public static final String FIRMA_WS = "firma_pic";
    public static final String FOLIO_CERTIFICADO_WS = "folio_certificado";
    public static final String TITULO_PROGRAMACION_WS = "titulo_programacion";

    public static final String PROGRAMACION_ID_WS = "programacion_id";
    public static final String USUARIO_ID_WS = "usuario_id";
    public static final String CLAVE_ANDROID_WS = "clave_android";
    public static final String STATUS_WS = "status";
    public static final String SINCRONIZADO_WS = "sincronizado";
    public static final String CREATED_AT_WS = "created_at_ws";
    public static final String CRT_TIME_WS = "crt_time";
    public static final String CONSTANCIA_FUMI_ID_WS = "constancia_fumi_id";

    @SerializedName(ID_WS)
    @DatabaseField(generatedId = true, columnName = ID_WS)
    private int id;

    @SerializedName(ORDEN_ID_WS)
    @DatabaseField(columnName = ORDEN_ID_WS)
    private String orden_id;

    @SerializedName(INSPEC_FUMI_ID_WS)
    @DatabaseField(columnName = INSPEC_FUMI_ID_WS)
    private String inspec_fumi_id;

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

    @SerializedName(TIPO_INSTALACION_ID)
    @DatabaseField(columnName = TIPO_INSTALACION_ID)
    private String tipo_instalacion_id;

    @SerializedName(AREAS_TRATADAS_INTERIOR)
    @DatabaseField(columnName = AREAS_TRATADAS_INTERIOR)
    private String areas_tratadas_interior;

    @SerializedName(AREAS_TRATADAS_EXTERIOR)
    @DatabaseField(columnName = AREAS_TRATADAS_EXTERIOR)
    private String areas_tratadas_exterior;

    @SerializedName(AREAS_TRATADAS_VEHICULO)
    @DatabaseField(columnName = AREAS_TRATADAS_VEHICULO)
    private String areas_tratadas_vehiculo;

    @SerializedName(TIPO_SERVICIO)
    @DatabaseField(columnName = TIPO_SERVICIO)
    private String tipo_servicio;

    @SerializedName(TIPO_SERVICIO_OTRO)
    @DatabaseField(columnName = TIPO_SERVICIO_OTRO)
    private String tipo_servicio_otro;

    @SerializedName(TIP_APLICA_ASPERSION)
    @DatabaseField(columnName = TIP_APLICA_ASPERSION)
    private String tip_aplica_aspersion;

    @SerializedName(TIP_APLICA_MICRONIZACION)
    @DatabaseField(columnName = TIP_APLICA_MICRONIZACION)
    private String tip_aplica_micronizacion;

    @SerializedName(TIP_APLICA_CEBO_RODENT)
    @DatabaseField(columnName = TIP_APLICA_CEBO_RODENT)
    private String tip_aplica_cebo_rodent;

    @SerializedName(TIP_APLICA_CEBO_GEL)
    @DatabaseField(columnName = TIP_APLICA_CEBO_GEL)
    private String tip_aplica_cebo_gel;

    @SerializedName(TIP_APLICA_TRAMPAS)
    @DatabaseField(columnName = TIP_APLICA_TRAMPAS)
    private String tip_aplica_trampas;

    @SerializedName(TIP_APLICA_TERMONEB)
    @DatabaseField(columnName = TIP_APLICA_TERMONEB)
    private String tip_aplica_termoneb;

    @SerializedName(TIP_APLICA_INYECCION)
    @DatabaseField(columnName = TIP_APLICA_INYECCION)
    private String tip_aplica_inyeccion;

    @SerializedName(OBSERVACIONES_WS)
    @DatabaseField(columnName = OBSERVACIONES_WS)
    private String observaciones;

    @SerializedName(SERVICIO_LIQUIDADO_WS)
    @DatabaseField(columnName = SERVICIO_LIQUIDADO_WS)
    private String servicio_liquidado;

    @SerializedName(MODO_PAGO_ID)
    @DatabaseField(columnName = MODO_PAGO_ID)
    private String modo_pago_id;

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

    @SerializedName(CONSTANCIA_FUMI_ID_WS)
    @DatabaseField(columnName = CONSTANCIA_FUMI_ID_WS)
    private String constancia_fumi_id;

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

    public String getInspec_fumi_id() {
        return inspec_fumi_id;
    }

    public void setInspec_fumi_id(String inspec_fumi_id) {
        this.inspec_fumi_id = inspec_fumi_id;
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

    public String getTipo_instalacion_id() {
        return tipo_instalacion_id;
    }

    public void setTipo_instalacion_id(String tipo_instalacion_id) {
        this.tipo_instalacion_id = tipo_instalacion_id;
    }

    public String getAreas_tratadas_interior() {
        return areas_tratadas_interior;
    }

    public void setAreas_tratadas_interior(String areas_tratadas_interior) {
        this.areas_tratadas_interior = areas_tratadas_interior;
    }

    public String getAreas_tratadas_exterior() {
        return areas_tratadas_exterior;
    }

    public void setAreas_tratadas_exterior(String areas_tratadas_exterior) {
        this.areas_tratadas_exterior = areas_tratadas_exterior;
    }

    public String getAreas_tratadas_vehiculo() {
        return areas_tratadas_vehiculo;
    }

    public void setAreas_tratadas_vehiculo(String areas_tratadas_vehiculo) {
        this.areas_tratadas_vehiculo = areas_tratadas_vehiculo;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public String getTipo_servicio_otro() {
        return tipo_servicio_otro;
    }

    public void setTipo_servicio_otro(String tipo_servicio_otro) {
        this.tipo_servicio_otro = tipo_servicio_otro;
    }

    public String getTip_aplica_aspersion() {
        return tip_aplica_aspersion;
    }

    public void setTip_aplica_aspersion(String tip_aplica_aspersion) {
        this.tip_aplica_aspersion = tip_aplica_aspersion;
    }

    public String getTip_aplica_micronizacion() {
        return tip_aplica_micronizacion;
    }

    public void setTip_aplica_micronizacion(String tip_aplica_micronizacion) {
        this.tip_aplica_micronizacion = tip_aplica_micronizacion;
    }

    public String getTip_aplica_cebo_rodent() {
        return tip_aplica_cebo_rodent;
    }

    public void setTip_aplica_cebo_rodent(String tip_aplica_cebo_rodent) {
        this.tip_aplica_cebo_rodent = tip_aplica_cebo_rodent;
    }

    public String getTip_aplica_cebo_gel() {
        return tip_aplica_cebo_gel;
    }

    public void setTip_aplica_cebo_gel(String tip_aplica_cebo_gel) {
        this.tip_aplica_cebo_gel = tip_aplica_cebo_gel;
    }

    public String getTip_aplica_trampas() {
        return tip_aplica_trampas;
    }

    public void setTip_aplica_trampas(String tip_aplica_trampas) {
        this.tip_aplica_trampas = tip_aplica_trampas;
    }

    public String getTip_aplica_termoneb() {
        return tip_aplica_termoneb;
    }

    public void setTip_aplica_termoneb(String tip_aplica_termoneb) {
        this.tip_aplica_termoneb = tip_aplica_termoneb;
    }

    public String getTip_aplica_inyeccion() {
        return tip_aplica_inyeccion;
    }

    public void setTip_aplica_inyeccion(String tip_aplica_inyeccion) {
        this.tip_aplica_inyeccion = tip_aplica_inyeccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getServicio_liquidado() {
        return servicio_liquidado;
    }

    public void setServicio_liquidado(String servicio_liquidado) {
        this.servicio_liquidado = servicio_liquidado;
    }

    public String getModo_pago_id() {
        return modo_pago_id;
    }

    public void setModo_pago_id(String modo_pago_id) {
        this.modo_pago_id = modo_pago_id;
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

    public String getTitulo_programacion() {
        return titulo_programacion;
    }

    public void setTitulo_programacion(String titulo_programacion) {
        this.titulo_programacion = titulo_programacion;
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

    public String getProgramacion_id() {
        return programacion_id;
    }

    public void setProgramacion_id(String programacion_id) {
        this.programacion_id = programacion_id;
    }

    public String getConstancia_fumi_id() {
        return constancia_fumi_id;
    }

    public void setConstancia_fumi_id(String constancia_fumi_id) {
        this.constancia_fumi_id = constancia_fumi_id;
    }

    public void upperCase(){
        setContacto(getContacto().toUpperCase());
        setTipo_servicio_otro(getTipo_servicio_otro().toUpperCase());
        setObservaciones(getObservaciones().toUpperCase());
    }
}
