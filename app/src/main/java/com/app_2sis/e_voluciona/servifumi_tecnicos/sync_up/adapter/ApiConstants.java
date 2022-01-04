package com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter;

public class ApiConstants {

    // TODO: 01/02/2019 Colocar url servidor al poner en produccion
    public static final String BASE_URL = "https://aplicaciones2sis.com/";
//    public static final String BASE_URL = "http://192.168.1.86/";
//    public static final String BASE_URL = "http://192.168.0.4/";

    public static final String URL_PDF_INSP_FUMI = "/servifumi/web/index.php/ws-tecnicos/get-pdf-insp-fumi?id=";
    public static final String URL_PDF_INSP_PLATA = "/servifumi/web/index.php/ws-tecnicos/get-pdf-insp-plata?id=";
    public static final String URL_CROQUIS_INSP_FUMI = "/servifumi/web/archivos/";

    public static final String URL_GET_USUARIOS_TECNICOS = "/servifumi/web/index.php/ws-tecnicos/getusuarios";
    public static final String URL_CHECK_UPDATE_VERSION = "/servifumi/web/index.php/ws-tecnicos/need-update-servi-fumi-tecnicos";
    public static final String URL_GET_TIPO_INSTALACIONES = "/servifumi/web/index.php/ws-tecnicos/get-tipo-instalaciones";
    public static final String URL_GET_PRODUCTOS = "/servifumi/web/index.php/ws-tecnicos/get-productos";
    public static final String URL_GET_TURNOS = "/servifumi/web/index.php/ws-tecnicos/get-turnos-prog";
    public static final String URL_GET_PROGRAMACION = "/servifumi/web/index.php/ws-tecnicos/get-programacion";
    public static final String URL_GET_PROGRAMACION_PRODUCTOS = "/servifumi/web/index.php/ws-tecnicos/get-programacion-productos";
    public static final String URL_GET_PROGRAMACION_ACCESORIOS = "/servifumi/web/index.php/ws-tecnicos/get-programacion-accesorios";
    public static final String URL_GET_METODOS_PAGO = "/servifumi/web/index.php/ws/metodospago";
    public static final String URL_GET_CAT_PLAGAS = "/servifumi/web/index.php/ws/catplagas";
    public static final String URL_GET_CAT_ACCESORIOS = "/servifumi/web/index.php/ws-tecnicos/get-accesorios";
    public static final String URL_GET_TANQUES = "/servifumi/web/index.php/ws-tecnicos/get-tanques";
    public static final String URL_CREATE_CONSTANCIA_PLATA = "/servifumi/web/index.php/ws-tecnicos/create-constancia-plata";
    public static final String URL_CREATE_CONSTANCIA_FUMI = "/servifumi/web/index.php/ws-tecnicos/create-constancia-fumi";
    public static final String URL_UPDATE_PROGRAMACIONES = "/servifumi/web/index.php/ws-tecnicos/update-programaciones";
}
