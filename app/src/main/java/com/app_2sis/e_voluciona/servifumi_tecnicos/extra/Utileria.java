package com.app_2sis.e_voluciona.servifumi_tecnicos.extra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
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

    public static ArrayAdapter<String> getAdapterSpinner(Context context, String data[]) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, data) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position % 2 == 1) {
                    // Set the item background color
                    //tv.setBackgroundColor(Color.parseColor("#408cff"));
                } else {
                    // Set the alternate item background color
                    //tv.setBackgroundColor(Color.parseColor("#08991c"));
                }
                return view;
            }
        };
        return spinnerArrayAdapter;
    }

    public static ArrayAdapter<String> getAdapterSpinnerVacio(Context context) {
        return getAdapterSpinner(context, new String[]{Constant.PROMPT});
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

    //TODO implementar con los Activity de la app
//    public static Intent getIntentNavigationDrawer(int id, Context context) {
//        Intent intent = null;
//        if (id == R.id.nav_home) {
//            intent = new Intent(context, MainActivity.class);
//
//        } else if (id == R.id.nav_descargar) {
//            intent = new Intent(context, SincronizarActivity.class);
//
//        } else if (id == R.id.nav_cargar) {
//            intent = new Intent(context, EnviarActivity.class);
//
//        } else if (id == R.id.nav_visitas) {
//            intent = new Intent(context, VisitasVerActivity.class);
//
//        } else if (id == R.id.nav_logout) {
//            intent = new Intent(context, LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        } else if (id == R.id.nav_inspecciones_sf) {
//            intent = new Intent(context, InspeccionesSFActivity.class);
//        } else if (id == R.id.nav_inspecciones_pp) {
//            intent = new Intent(context, InspeccionesPPActivity.class);
//        }
//        return intent;
//    }

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

    public static boolean deleteFirma(String path) {
        return new File(path).delete();
    }

    public static boolean isTelephonyEnabled(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return telephonyManager != null && telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY;
    }
}
