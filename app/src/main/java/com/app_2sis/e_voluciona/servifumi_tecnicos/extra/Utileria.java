package com.app_2sis.e_voluciona.servifumi_tecnicos.extra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.ConstanciaFumiActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.ConstanciaPlataActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.DescargaActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.EnviarActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.LoginActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.MainActivity;
import com.app_2sis.e_voluciona.servifumi_tecnicos.activity.ProgramacionActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

public class Utileria {

    public static String getClave(String cadena) {
        String clave = "";
        String[] separated = cadena.split("-");
        clave = separated[0].trim();
        return clave;
    }

    public static String generarID() {
        Date horaActual = new Date();
        Random rand = new Random();
        return rand.nextInt(9999) + "" +
                horaActual.getHours() + "" +
                horaActual.getMinutes() + "" +
                horaActual.getSeconds();
    }

    /**
     * GENERA UNA CLAVE ÚNICA ENTRE TODOS LOS DISPOSITIVOS
     *
     * @return CLAVE CON PREFIJO "GEN"
     */
    public static String generarClaveAndroid() {
        return Constant.PREFIJO_CLAVE_ANDROID +
                generarID();
    }

    public static String md5(String clear) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());

        int size = b.length;
        StringBuffer h = new StringBuffer(size);
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0" + Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString();
    }

    public static int getPositionSpinner(String data[], String index) {
        int position = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(index)) {
                position = i;
                break;
            }
        }
        return position;
    }

    public static String getMinutos(int minutos) {
        String minTmp = String.valueOf(minutos);
        if (minTmp.length() < 2) {
            return "0" + minTmp;
        } else {
            return minTmp;
        }
    }

    public static String getHoras(int horas) {
        return Utileria.getMinutos(horas);
    }

    public static String getFecha() {
        return (DateFormat.format("yyyy-MM-dd", new java.util.Date()).toString());
    }

    public static String getHora() {
        return (DateFormat.format("HH:mm:ss", new java.util.Date()).toString());
    }

    public static String getFechaYHora() {
        return (DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date()).toString());
    }

    public static String[] getFecuencia() {
        return new String[]{
                Constant.PROMPT,
                Constant.FRECUENCIA_MENSUAL_VALUE,
                Constant.FRECUENCIA_BIMESTRAL_VALUE,
                Constant.FRECUENCIA_TRIMESTRAL_VALUE,
                Constant.FRECUENCIA_SEMESTRAL_VALUE,
                Constant.FRECUENCIA_ANUAL_VALUE
        };
    }

    public static String getFrecuenciaID(String value) {

        String id = "";
        if (value.equals(Constant.FRECUENCIA_MENSUAL_VALUE)) {
            id = Constant.FRECUENCIA_MENSUAL;
        }
        if (value.equals(Constant.FRECUENCIA_BIMESTRAL_VALUE)) {
            id = Constant.FRECUENCIA_BIMESTRAL;
        }
        if (value.equals(Constant.FRECUENCIA_TRIMESTRAL_VALUE)) {
            id = Constant.FRECUENCIA_TRIMESTRAL;
        }
        if (value.equals(Constant.FRECUENCIA_SEMESTRAL_VALUE)) {
            id = Constant.FRECUENCIA_SEMESTRAL;
        }
        if (value.equals(Constant.FRECUENCIA_ANUAL_VALUE)) {
            id = Constant.FRECUENCIA_ANUAL;
        }
        return id;
    }

    public static String getFrecuenciaNombre(String frecuenciaID) {
        String nombre = "";
        if (frecuenciaID.equals(Constant.FRECUENCIA_MENSUAL)) {
            nombre = Constant.FRECUENCIA_MENSUAL_VALUE;
        }
        if (frecuenciaID.equals(Constant.FRECUENCIA_BIMESTRAL)) {
            nombre = Constant.FRECUENCIA_BIMESTRAL_VALUE;
        }
        if (frecuenciaID.equals(Constant.FRECUENCIA_TRIMESTRAL)) {
            nombre = Constant.FRECUENCIA_TRIMESTRAL_VALUE;
        }
        if (frecuenciaID.equals(Constant.FRECUENCIA_SEMESTRAL)) {
            nombre = Constant.FRECUENCIA_SEMESTRAL_VALUE;
        }
        if (frecuenciaID.equals(Constant.FRECUENCIA_ANUAL)) {
            nombre = Constant.FRECUENCIA_ANUAL_VALUE;
        }
        return nombre;
    }

    public static String[] getAccesorioCondicion() {
        return new String[]{
                Constant.CONDICION_ACCESORIO_BUENA_VALUE,
                Constant.CONDICION_ACCESORIO_MALA_VALUE
        };
    }

    public static String[] getAccesorioCondicionID() {
        return new String[]{
                Constant.CONDICION_ACCESORIO_BUENA,
                Constant.CONDICION_ACCESORIO_MALA
        };
    }

    public static String getAccesorioCondicionID(String value) {
        switch (value) {
            case Constant.CONDICION_ACCESORIO_BUENA_VALUE:
                return Constant.CONDICION_ACCESORIO_BUENA;
            case Constant.CONDICION_ACCESORIO_MALA_VALUE:
                return Constant.CONDICION_ACCESORIO_MALA;
            default:
                return "";
        }
    }

    public static String getAccesorioCondicionNombre(String condicionID) {
        switch (condicionID) {
            case Constant.CONDICION_ACCESORIO_BUENA:
                return Constant.CONDICION_ACCESORIO_BUENA_VALUE;
            case Constant.CONDICION_ACCESORIO_MALA:
                return Constant.CONDICION_ACCESORIO_MALA_VALUE;
            default:
                return "";
        }
    }

    public static String[] getTipoServicio() {
        return new String[]{
                Constant.PROMPT,
                Constant.TIPO_SERVICIO_GENERAL_VALUE,
                Constant.TIPO_SERVICIO_CORRECTIVO_VALUE,
                Constant.TIPO_SERVICIO_PREVENTIVO_VALUE,
                Constant.TIPO_SERVICIO_SUPERVISION_VALUE,
                Constant.TIPO_SERVICIO_DES_PATOGENA_VALUE,
                Constant.TIPO_SERVICIO_OTRO_VALUE
        };
    }

    public static String[] getTipoServicioID() {
        return new String[]{
                Constant.PROMPT,
                Constant.TIPO_SERVICIO_GENERAL,
                Constant.TIPO_SERVICIO_CORRECTIVO,
                Constant.TIPO_SERVICIO_PREVENTIVO,
                Constant.TIPO_SERVICIO_SUPERVISION,
                Constant.TIPO_SERVICIO_DES_PATOGENA,
                Constant.TIPO_SERVICIO_OTRO
        };
    }

    public static String getTipoServicioID(String value) {
        switch (value) {
            case Constant.TIPO_SERVICIO_GENERAL_VALUE:
                return Constant.TIPO_SERVICIO_GENERAL;
            case Constant.TIPO_SERVICIO_CORRECTIVO_VALUE:
                return Constant.TIPO_SERVICIO_CORRECTIVO;
            case Constant.TIPO_SERVICIO_PREVENTIVO_VALUE:
                return Constant.TIPO_SERVICIO_PREVENTIVO;
            case Constant.TIPO_SERVICIO_SUPERVISION_VALUE:
                return Constant.TIPO_SERVICIO_SUPERVISION;
            case Constant.TIPO_SERVICIO_DES_PATOGENA_VALUE:
                return Constant.TIPO_SERVICIO_DES_PATOGENA;
            case Constant.TIPO_SERVICIO_OTRO_VALUE:
                return Constant.TIPO_SERVICIO_OTRO;
            default:
                return "";
        }
    }

    public static String getTipoServicioNombre(String tipoServicioID) {
        switch (tipoServicioID) {
            case Constant.TIPO_SERVICIO_GENERAL:
                return Constant.TIPO_SERVICIO_GENERAL_VALUE;
            case Constant.TIPO_SERVICIO_CORRECTIVO:
                return Constant.TIPO_SERVICIO_CORRECTIVO_VALUE;
            case Constant.TIPO_SERVICIO_PREVENTIVO:
                return Constant.TIPO_SERVICIO_PREVENTIVO_VALUE;
            case Constant.TIPO_SERVICIO_SUPERVISION:
                return Constant.TIPO_SERVICIO_SUPERVISION_VALUE;
            case Constant.TIPO_SERVICIO_DES_PATOGENA:
                return Constant.TIPO_SERVICIO_DES_PATOGENA_VALUE;
            case Constant.TIPO_SERVICIO_OTRO:
                return Constant.TIPO_SERVICIO_OTRO_VALUE;
            default:
                return "";
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static Intent getIntentNavigationDrawer(int id, Context context) {
        Intent intent = null;
        if (id == R.id.nav_home) {
            intent = new Intent(context, MainActivity.class);

        } else if (id == R.id.nav_descarga) {
            intent = new Intent(context, DescargaActivity.class);

        } else if (id == R.id.nav_programacion) {
            intent = new Intent(context, ProgramacionActivity.class);
        } else if (id == R.id.nav_constancia_sf) {
            intent = new Intent(context, ConstanciaFumiActivity.class);
        } else if (id == R.id.nav_constancia_pp) {
            intent = new Intent(context, ConstanciaPlataActivity.class);
        } else if (id == R.id.nav_enviar) {
            intent = new Intent(context, EnviarActivity.class);
        } else if (id == R.id.nav_acerca) {
            View view = ((Activity) context).getLayoutInflater().inflate(R.layout.acercade, null);
            try {
                TextView tv;
                tv = view.getRootView().findViewById(R.id.tv_acerca_version);
                tv.setText("Version: " + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
                tv.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }

            AlertDialog.Builder acercade = new AlertDialog.Builder(context);
            acercade.setView(view);
            acercade.setIcon(R.mipmap.ico_2sis);
            acercade.setTitle("Desarrollado por:");
            AlertDialog alert = acercade.create();
            alert.show();
        } else if (id == R.id.nav_salir) {
            intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }

    /**
     * Oculta el teclaodo si es que está mostrado en pantalla
     *
     * @param context Context de la aplicación donde va a funcionar
     */
    public static void hideTeclado(Context context) {
        InputMethodManager inputManager = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Convierte un Path de archivo en el File Base64
     *
     * @param path
     * @return EL string Base64 del archivo o cadena vacía si hay error
     */
    public static String changePathByFile(String path) {
        if (!path.isEmpty()) {
            Bitmap bm = BitmapFactory.decodeFile(path);
            if (bm != null) { //Si coincide el path con un archivo existente
                bm = reduceBitmapToMin(bm);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] byteArray = baos.toByteArray();
                String imageEncoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                return imageEncoded;
            }
        }
        //retorna cadena vacía si hay algun problema al convertir
        return "";
    }

    private static Bitmap reduceBitmapToMin(Bitmap bm) {
        double maxWidth = 600.0;
        double maxHeight = 600.0;
        int widht = bm.getWidth();
        int height = bm.getHeight();

        double widhtFinal;
        double heightFinal;

        //se calcula el ancho y alto de la imagen final
        double anchoRatio = maxWidth / widht;
        double altoRatio = maxHeight / height;

        //si el ancho y alto no son mayores que el máximo, se mantienen los originales
        if (widht <= maxWidth && height <= maxHeight) {
            widhtFinal = widht;
            heightFinal = height;
        } else if ((anchoRatio * height) < maxHeight) {
            //si la proporcion horizontal*alto es mayor que el alto maximo, el alto final
            //toma el valor de alto original por la proporcion horizontal, es decir,
            //le quitamos al ancho, la misma proporcion que le quitamos al alto
            heightFinal = Math.round(anchoRatio * height);
            widhtFinal = maxWidth;
        } else {
            //igual que el anterior pero con el ancho
            widhtFinal = Math.round(altoRatio * widht);
            heightFinal = maxHeight;
        }
        return Bitmap.createScaledBitmap(bm, (int) widhtFinal, (int) heightFinal, true);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String path,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static String calcularSaldo(String costoStr, String anticipoStr) {
        float costo = 0;
        float anticipo = 0;
        float result;
        try {
            costo = Float.parseFloat(costoStr);
            anticipo = Float.parseFloat(anticipoStr);

        } catch (Exception ex) {
            costo = 0;
            anticipo = 0;
        } finally {
            result = costo - anticipo;
            return result + "";
        }
    }

    public static boolean deleteFile(String path) {
        return new File(path).delete();
    }

    public static boolean deleteFirma(String path) {
        return new File(path).delete();
    }

    public static boolean isTelephonyEnabled(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return telephonyManager != null && telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY;
    }
}
