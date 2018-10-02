package com.app_2sis.e_voluciona.servifumi_tecnicos.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatTanqueActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaPlataActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaPlataTanquesActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.MisPreferencias;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlata;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.rey.material.widget.CheckBox;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConstanciaPlataFormularioActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout tilFecha, tilCliente, tilContacto, tilHoraEntrada, tilHoraSalida,
            tilMatOtro, tilObservaciones, tilDineroRecibido;
    private EditText etFecha, etCliente, etContacto, etHoraEntrada, etHoraSalida, etMatOtro,
            etObservaciones, etDineroRecibido;
    private Button btnFecha, btnHoraEntrada, btnHoraSalida, btnTanques;
    private TextView tvUtiliza, tvAplica, tvMaterial, tvTanques, tvFirma;
    private CheckBox chkUtilPlataColoidal, chkUtilHipoclorito, chkUtilDesincrustante,
            chkApliAsperjado, chkApliRodillo, chkApliDirecto, chkMatPvc, chkMatFibrocemento,
            chkMatOtro, chkLiquidado;
    private ListView lvTanques;
    private ImageButton btnFirma;
    private ImageView ivFirma;
    private FloatingActionButton fabGuardar;

    private String programacionID_bd;
    private Programacion programacion;
    private ConstanciaPlata constanciaPlata;
    private MisPreferencias misPreferencias;
    private ProgramacionActiveRecord programacionActiveRecord;
    private ConstanciaPlataActiveRecord constanciaPlataActiveRecord;
    private CatTanqueActiveRecord catTanqueActiveRecord;
    private ConstanciaPlataTanquesActiveRecord constanciaPlataTanquesActiveRecord;

    public static final int COMPORTAMIENTO_ACTIVITY_NEW = 0;
    public static final int COMPORTAMIENTO_ACTIVITY_VIEW = 1;
    public static final int COMPORTAMIENTO_ACTIVITY_UPDATE = 2;

    //DateFormat para fechas y horas separadas
    private SimpleDateFormat dateFFecha = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFHora = new SimpleDateFormat("HH:mm");
    private DatePickerDialog dpdFecha;
    private TimePickerDialog tpdHoraEntrada, tpdHoraSalida;

    //Variables para la firma
    private Dialog dialogFirma;
    private Bitmap bitmapFirma;
    private String firmaPath;
    LinearLayout mContent;
    View view;
    Signature mSignature;
    Button mClear, mGetSign, mCancel;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constancia_plata_formulario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        programacionID_bd = getIntent().getStringExtra("programacionID_bd");
        programacionID_bd = programacionID_bd == null ? "" : programacionID_bd;

        findViewById();
        iniComponents();
    }

    private void iniComponents() {
        misPreferencias = new MisPreferencias(this);
        programacionActiveRecord = new ProgramacionActiveRecord(this);
        constanciaPlataActiveRecord = new ConstanciaPlataActiveRecord(this);
        catTanqueActiveRecord = new CatTanqueActiveRecord(this);
        constanciaPlataTanquesActiveRecord = new ConstanciaPlataTanquesActiveRecord(this);
        context = getApplicationContext();

        if (hayInfoCatalogos()) {
            setFechayHoras();
            tilMatOtro.setVisibility(View.GONE);
            chkMatOtro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        tilMatOtro.setVisibility(View.VISIBLE);
                    } else {
                        tilMatOtro.setVisibility(View.GONE);
                        etMatOtro.setText(null);
                    }
                }
            });

            switch (determinarNewUpdateView()) {
                case COMPORTAMIENTO_ACTIVITY_NEW:
                    break;
                case COMPORTAMIENTO_ACTIVITY_VIEW:
                    setTitle("Detalles Constancia PlataPlus");
                    loadInfo(true);
                    break;
                case COMPORTAMIENTO_ACTIVITY_UPDATE:
                    setTitle("Modificar Constancia PlataPlus");
                    loadInfo(false);
                    break;
            }
        } else {
            Toast.makeText(this, "Necesita descargar Catalogos", Toast.LENGTH_LONG).show();
            exit();
        }
    }

    private void findViewById() {
        fabGuardar = findViewById(R.id.fab_save);
        fabGuardar.setOnClickListener(this);

        tilFecha = findViewById(R.id.til_constancia_plata_form_fecha);
        tilCliente = findViewById(R.id.til_constancia_plata_form_cliente);
        tilContacto = findViewById(R.id.til_constancia_plata_form_contacto);
        tilHoraEntrada = findViewById(R.id.til_constancia_plata_form_hora_entrada);
        tilHoraSalida = findViewById(R.id.til_constancia_plata_form_hora_salida);
        tilMatOtro = findViewById(R.id.til_constancia_plata_form_material_otro);
        tilObservaciones = findViewById(R.id.til_constancia_plata_form_observaciones);
        tilDineroRecibido = findViewById(R.id.til_constancia_plata_form_dinero_recibido);

        etFecha = findViewById(R.id.et_constancia_plata_form_fecha);
        etCliente = findViewById(R.id.et_constancia_plata_form_cliente);
        etContacto = findViewById(R.id.et_constancia_plata_form_contacto);
        etHoraEntrada = findViewById(R.id.et_constancia_plata_form_hora_entrada);
        etHoraSalida = findViewById(R.id.et_constancia_plata_form_hora_salida);
        etMatOtro = findViewById(R.id.et_constancia_plata_form_material_otro);
        etObservaciones = findViewById(R.id.et_constancia_plata_form_observaciones);
        etDineroRecibido = findViewById(R.id.et_constancia_plata_form_dinero_recibido);

        btnFecha = findViewById(R.id.btn_constancia_plata_form_fecha);
        btnFecha.setOnClickListener(this);
        btnHoraEntrada = findViewById(R.id.btn_constancia_plata_form_hora_entrada);
        btnHoraEntrada.setOnClickListener(this);
        btnHoraSalida = findViewById(R.id.btn_constancia_plata_form_hora_salida);
        btnHoraSalida.setOnClickListener(this);
        btnTanques = findViewById(R.id.btn_constancia_plata_form_tanques);
        btnTanques.setOnClickListener(this);

        tvUtiliza = findViewById(R.id.tv_constancia_plata_form_utiliza);
        tvAplica = findViewById(R.id.tv_constancia_plata_form_aplica);
        tvMaterial = findViewById(R.id.tv_constancia_plata_form_material);
        tvTanques = findViewById(R.id.tv_constancia_plata_form_tanques);
        tvFirma = findViewById(R.id.tv_constancia_plata_form_firma);

        chkUtilPlataColoidal = findViewById(R.id.chk_constancia_plata_form_utiliza_plata_coloidal);
        chkUtilHipoclorito = findViewById(R.id.chk_constancia_plata_form_utiliza_hipoclorito);
        chkUtilDesincrustante = findViewById(R.id.chk_constancia_plata_form_utiliza_desincrustante);
        chkApliAsperjado = findViewById(R.id.chk_constancia_plata_form_aplica_asperjado);
        chkApliRodillo = findViewById(R.id.chk_constancia_plata_form_aplica_rodillo);
        chkApliDirecto = findViewById(R.id.chk_constancia_plata_form_aplica_directo);
        chkMatPvc = findViewById(R.id.chk_constancia_plata_form_material_pvc);
        chkMatFibrocemento = findViewById(R.id.chk_constancia_plata_form_material_fibrocemento);
        chkMatOtro = findViewById(R.id.chk_constancia_plata_form_material_otro);
        chkLiquidado = findViewById(R.id.chk_constancia_plata_form_liquidado);

        lvTanques = findViewById(R.id.lv_constancia_plata_form_tanques);
        ivFirma = findViewById(R.id.iv_constancia_plata_form_firma);
        btnFirma = findViewById(R.id.btn_constancia_plata_form_firma);
        btnFirma.setOnClickListener(this);
    }

    private boolean hayInfoCatalogos() {
        return !(programacionActiveRecord.isEmpty()
                || catTanqueActiveRecord.isEmpty());
    }

    /**
     * Carga el contenido de la programacion en la interfaz
     *
     * @param lock determina si bloquea y evita guardar o no la informacion
     */
    private void loadInfo(boolean lock) {
        // TODO: 02/10/2018 implementar mostar info de programacion en interfaz
        if (lock)
            lockInfo();
    }

    /**
     * Bloquea los componentes para que la info no se pueda modificar y evita guardar cambios
     */
    private void lockInfo() {
        // TODO: 02/10/2018 implementar que la info no se pueda modificar ni guardar cambios
    }

    /**
     * Decide que comportamiento tendrá el activity
     * 0 = new
     * 1 = view
     * 2 = update
     *
     * @return bandera de la accion
     */
    private int determinarNewUpdateView() {
        if (programacionID_bd.isEmpty())
            return COMPORTAMIENTO_ACTIVITY_NEW;   //No recibio como parametro el idProgramacion
        else {
            programacion = programacionActiveRecord.getProgramacion(Programacion.ID_WS, programacionID_bd);
            if (programacion != null) {
                if (programacion.getSincronizado().equals(Constant.SI))
                    return COMPORTAMIENTO_ACTIVITY_VIEW;   //Ya se sincronizó
                else
                    return COMPORTAMIENTO_ACTIVITY_UPDATE;   //No se ha sincronizado
            } else
                return COMPORTAMIENTO_ACTIVITY_NEW; //No existe el registro de programacion
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_advertencia)
                .setTitle("Abandonar Constancia")
                .setMessage("Al salir se perderán los datos sin guardar" +
                        "\n\n¿Salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exit();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void exit() {
        startActivity(new Intent(this, ConstanciaPlataActivity.class));
        finish();
    }

    /**
     * Crea los picker de fecha y hora, para que el evento clic solo haga un show()
     */
    private void setFechayHoras() {
        Calendar calendar = Calendar.getInstance();

        dpdFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etFecha.setText(dateFFecha.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        tpdHoraEntrada = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH),
                        selectedHour, selectedMinute);
                etHoraEntrada.setText(dateFHora.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

        tpdHoraSalida = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH),
                        selectedHour, selectedMinute);
                etHoraSalida.setText(dateFHora.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

        //set todos los EditText con la fecha y hora actual
        etFecha.setText(dateFFecha.format(calendar.getTime()));
        etHoraEntrada.setText(dateFHora.format(calendar.getTime()));
        etHoraSalida.setText(etHoraEntrada.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_save:
                break;
            case R.id.btn_constancia_plata_form_fecha:
                dpdFecha.show();
                break;
            case R.id.btn_constancia_plata_form_hora_entrada:
                tpdHoraEntrada.show();
                break;
            case R.id.btn_constancia_plata_form_hora_salida:
                tpdHoraSalida.show();
                break;
            case R.id.btn_constancia_plata_form_tanques:
                break;
            case R.id.btn_constancia_plata_form_firma:
                firmar();
                break;
        }
    }

    private void firmar() {
        dialogFirma = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        // Removing the features of Normal Dialogs
        dialogFirma.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogFirma.setContentView(R.layout.dialog_firma);
        dialogFirma.setCancelable(true);
        dialogAction();
    }

    private void dialogAction() {
        mContent = (LinearLayout) dialogFirma.findViewById(R.id.linearLayout_sf);
        mSignature = new Signature(getApplicationContext(), null);
        mSignature.setBackgroundColor(Color.WHITE);
        // Dynamically generating Layout through java code
        mContent.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mClear = (Button) dialogFirma.findViewById(R.id.clear);
        mGetSign = (Button) dialogFirma.findViewById(R.id.getsign);
        mGetSign.setEnabled(false);
        mCancel = (Button) dialogFirma.findViewById(R.id.cancel);
        view = mContent;

        mClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("tag", "Panel Cleared");
                mSignature.clear();
                mGetSign.setEnabled(false);
            }
        });
        mGetSign.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.v("tag", "Panel Saved");
                view.setDrawingCacheEnabled(true);
                mSignature.save(view);
                dialogFirma.dismiss();
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("tag", "Panel Cancelled");
                dialogFirma.dismiss();
            }
        });
        dialogFirma.show();


    }

    public class Signature extends View {
        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();

        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public Signature(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View v) {
            if (firmaPath == null) {
                ContextWrapper cw = new ContextWrapper(context);
                // path to /data/data/yourapp/app_data/imageDir
                File directory = cw.getDir("firmas", Context.MODE_PRIVATE);
                // Create imageDir
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_.jpg";
                File file = new File(directory, imageFileName);
                firmaPath = file.getAbsolutePath();
            }
            if (bitmapFirma == null) {
                bitmapFirma = Bitmap.createBitmap(mContent.getWidth(), mContent.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmapFirma);
            try {
                // Output the file
                FileOutputStream mFileOutStream = new FileOutputStream(firmaPath);
                v.draw(canvas);
                // Convert the output file to Image such as .png
                bitmapFirma.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);
                mFileOutStream.flush();
                mFileOutStream.close();
                showFirmaEnImageView(firmaPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void clear() {
            path.reset();
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            mGetSign.setEnabled(true);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:
                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;
                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string) {
            Log.v("log_tag", string);
        }

        private void expandDirtyRect(float historicalX, float historicalY) {
            if (historicalX < dirtyRect.left) {
                dirtyRect.left = historicalX;
            } else if (historicalX > dirtyRect.right) {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top) {
                dirtyRect.top = historicalY;
            } else if (historicalY > dirtyRect.bottom) {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }

    private void showFirmaEnImageView(String path) {
        if (path != null) {
            Bitmap b = BitmapFactory.decodeFile(path);
            ivFirma.setImageBitmap(b);
        } else {
            ivFirma.setImageBitmap(null);
        }
    }
}
