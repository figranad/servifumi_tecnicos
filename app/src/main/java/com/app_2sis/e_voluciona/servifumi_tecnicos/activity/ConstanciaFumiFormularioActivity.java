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
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatPlagaActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatProductoActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatTipoInstalacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiPlagasActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiProductosActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiVehiculosActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.UsuarioActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.MisPreferencias;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumi;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaPlata;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.PlagaBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ProductoBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.PlagaAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.ProductoAdapter;
import com.rey.material.widget.CheckBox;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConstanciaFumiFormularioActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton fabGuardar;
    private TextInputLayout tilFecha, tilCliente, tilContacto, tilHoraEntrada, tilHoraSalida,
    tilTipoServOtro, tilObservaciones, tilDineroRecibido;
    private EditText etFecha, etCliente, etContacto, etHoraEntrada, etHoraSalida, etTipoServOtro,
    etObservaciones, etDineroRecibido;
    private Button btnFecha, btnHoraEntrada, btnHoraSalida;
    private TextView tvAreas, tvTipoServ, tvAplicacion, tvColocacion, tvPlagas, tvPlagasError,
    tvProductos, tvProductosError, tvModoPago, tvFirma;
    private CheckBox chkAreaInterior, chkAreaExterior, chkAreaVehiculo, chkAspersion, chkMicroniz,
    chkTermoneb, chkInyeccion, chkCeboRoden, chkCeboGel, chkTrampas, chkLiquidado;
    private Spinner spTipoServ, spModoPago;
    private ImageButton btnFirma;
    private ImageView ivFirma;
    // TODO: 11/10/2018 implementar vehiculos

    private RecyclerView rvPlaga, rvProducto;
    private PlagaAdapter plagaAdapter;
    private List<PlagaBeanAdapter> plagaBeanAdapterList;
    private ProductoAdapter productoAdapter;
    private List<ProductoBeanAdapter> productoBeanAdapterList;
    
    private String programacionID_bd; //Si es view no lo envia
    private String constanciaFumiID_bd; //Si es new no lo envia

    private Programacion programacion;
    private ConstanciaFumi constanciaFumi;
    private MisPreferencias misPreferencias;
    private UsuarioActiveRecord usuarioActiveRecord;
    private ProgramacionActiveRecord programacionActiveRecord;
    private ConstanciaFumiActiveRecord constanciaFumiActiveRecord;
    private CatPlagaActiveRecord catPlagaActiveRecord;
    private CatProductoActiveRecord catProductoActiveRecord;
    private CatTipoInstalacionActiveRecord catTipoInstalacionActiveRecord;
    private ConstanciaFumiPlagasActiveRecord constanciaFumiPlagasActiveRecord;
    private ConstanciaFumiProductosActiveRecord constanciaFumiProductosActiveRecord;
    private ConstanciaFumiVehiculosActiveRecord constanciaFumiVehiculosActiveRecord;

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
    private int COMPORTAMIENTO_THIS_ACTIVITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constancia_fumi_formulario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        programacionID_bd = getIntent().getStringExtra("programacionID_bd");
        programacionID_bd = programacionID_bd == null ? "" : programacionID_bd;

        constanciaFumiID_bd = getIntent().getStringExtra("constanciaFumiID_bd");
        constanciaFumiID_bd = constanciaFumiID_bd == null ? "" : constanciaFumiID_bd;

        findViewById();
        iniComponents();
    }

    private void findViewById() {
        fabGuardar = findViewById(R.id.fab_save);
        fabGuardar.setOnClickListener(this);

        tilFecha = findViewById(R.id.til_constancia_fumi_form_fecha);
        tilCliente = findViewById(R.id.til_constancia_fumi_form_cliente);
        tilContacto = findViewById(R.id.til_constancia_fumi_form_contacto);
        tilHoraEntrada = findViewById(R.id.til_constancia_fumi_form_hora_entrada);
        tilHoraSalida = findViewById(R.id.til_constancia_fumi_form_hora_salida);
        tilTipoServOtro = findViewById(R.id.til_constancia_fumi_form_tipo_servicio_otro);
        tilObservaciones = findViewById(R.id.til_constancia_fumi_form_observaciones);
        tilDineroRecibido = findViewById(R.id.til_constancia_fumi_form_dinero_recibido);

        etFecha = findViewById(R.id.et_constancia_fumi_form_fecha);
        etCliente = findViewById(R.id.et_constancia_fumi_form_cliente);
        etContacto = findViewById(R.id.et_constancia_fumi_form_contacto);
        etHoraEntrada = findViewById(R.id.et_constancia_fumi_form_hora_entrada);
        etHoraSalida = findViewById(R.id.et_constancia_fumi_form_hora_salida);
        etTipoServOtro = findViewById(R.id.et_constancia_fumi_form_tipo_servicio_otro);
        etObservaciones = findViewById(R.id.et_constancia_fumi_form_observaciones);
        etDineroRecibido = findViewById(R.id.et_constancia_fumi_form_dinero_recibido);

        btnFecha = findViewById(R.id.btn_constancia_fumi_form_fecha);
        btnFecha.setOnClickListener(this);
        btnHoraEntrada = findViewById(R.id.btn_constancia_fumi_form_hora_entrada);
        btnHoraEntrada.setOnClickListener(this);
        btnHoraSalida = findViewById(R.id.btn_constancia_fumi_form_hora_salida);
        btnHoraSalida.setOnClickListener(this);

        tvAreas = findViewById(R.id.tv_constancia_fumi_form_areas);
        tvTipoServ = findViewById(R.id.tv_constancia_fumi_form_tipo_servicio);
        tvAplicacion = findViewById(R.id.tv_constancia_fumi_form_aplicacion);
        tvColocacion = findViewById(R.id.tv_constancia_fumi_form_colocacion);
        tvPlagas = findViewById(R.id.tv_constancia_fumi_form_plagas);
        tvPlagasError = findViewById(R.id.tv_constancia_fumi_form_plagas_error);
        tvProductos = findViewById(R.id.tv_constancia_fumi_form_productos);
        tvProductosError = findViewById(R.id.tv_constancia_fumi_form_productos_error);
        tvModoPago = findViewById(R.id.tv_constancia_fumi_form_modo_pago);
        tvFirma = findViewById(R.id.tv_constancia_fumi_form_firma);

        chkAreaInterior = findViewById(R.id.chk_constancia_fumi_form_area_interior);
        chkAreaExterior = findViewById(R.id.chk_constancia_fumi_form_area_exterior);
        chkAreaVehiculo = findViewById(R.id.chk_constancia_fumi_form_area_vehiculo);
        chkAspersion = findViewById(R.id.chk_constancia_fumi_form_aspersion);
        chkMicroniz = findViewById(R.id.chk_constancia_fumi_form_micronizacion);
        chkTermoneb = findViewById(R.id.chk_constancia_fumi_form_termoneb);
        chkInyeccion = findViewById(R.id.chk_constancia_fumi_form_inyeccion);
        chkCeboRoden = findViewById(R.id.chk_constancia_fumi_form_cebo_roden);
        chkCeboGel = findViewById(R.id.chk_constancia_fumi_form_cebo_gel);
        chkTrampas = findViewById(R.id.chk_constancia_fumi_form_trampas);
        chkLiquidado = findViewById(R.id.chk_constancia_fumi_form_liquidado);

        spTipoServ = findViewById(R.id.sp_constancia_fumi_form_tipo_servicio);
        spModoPago = findViewById(R.id.sp_constancia_fumi_form_modo_pago);

        btnFirma = findViewById(R.id.btn_constancia_fumi_form_firma);
        btnFirma.setOnClickListener(this);

        rvPlaga = findViewById(R.id.rv_constancia_fumi_form_plagas);
        rvProducto = findViewById(R.id.rv_constancia_fumi_form_productos);

        ivFirma = findViewById(R.id.iv_constancia_fumi_form_firma);
    }

    private void iniComponents() {
        plagaAdapter = new PlagaAdapter(this);
        productoAdapter = new ProductoAdapter(this);
        misPreferencias = new MisPreferencias(this);
        usuarioActiveRecord = new UsuarioActiveRecord(this);
        programacionActiveRecord = new ProgramacionActiveRecord(this);
        constanciaFumiActiveRecord = new ConstanciaFumiActiveRecord(this);
        catTipoInstalacionActiveRecord = new CatTipoInstalacionActiveRecord(this);
        catPlagaActiveRecord = new CatPlagaActiveRecord(this);
        catProductoActiveRecord = new CatProductoActiveRecord(this);
        constanciaFumiPlagasActiveRecord = new ConstanciaFumiPlagasActiveRecord(this);
        constanciaFumiProductosActiveRecord = new ConstanciaFumiProductosActiveRecord(this);
        context = getApplicationContext();
        programacion = programacionActiveRecord.getProgramacion(Programacion.ID_WS, programacionID_bd);
        if (hayInfoCatalogos()) {
            setFechayHoras();
            iniRecyclerViews();
            if (!catPlagaActiveRecord.isEmpty())
                cargarPlagas();
            if (!catProductoActiveRecord.isEmpty())
                cargarProductos();

            if (programacion != null)
                etCliente.setText(programacion.getTitulo());

            COMPORTAMIENTO_THIS_ACTIVITY = determinarNewUpdateView();
            tilTipoServOtro.setVisibility(View.GONE);
            // TODO: 11/10/2018 implementar spinner que muestre el tilTipoServOtro solo cuando la seleccion es "otro" 
//            spTipoServ

            switch (COMPORTAMIENTO_THIS_ACTIVITY) {
                case Constant.COMPORTAMIENTO_ACTIVITY_NEW:
                    break;
                case Constant.COMPORTAMIENTO_ACTIVITY_VIEW:
                    setTitle("Detalles Constancia Fumi");
                    loadInfo(true);
                    break;
                case Constant.COMPORTAMIENTO_ACTIVITY_UPDATE:
                    setTitle("Modificar Constancia Fumi");
                    loadInfo(false);
                    break;
            }
        } else {
            Toast.makeText(this, "Necesita descargar Catalogos", Toast.LENGTH_LONG).show();
            exit();
        }
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

    private boolean hayInfoCatalogos() {
        return !(programacionActiveRecord.isEmpty()
                || catPlagaActiveRecord.isEmpty()
                || catProductoActiveRecord.isEmpty()
                || catTipoInstalacionActiveRecord.isEmpty());
    }

    private void iniRecyclerViews() {
        LinearLayoutManager lm;
        DividerItemDecoration mDivider;

        lm = new LinearLayoutManager(this);
        mDivider = new DividerItemDecoration(rvPlaga.getContext(), LinearLayoutManager.VERTICAL);
        mDivider.setDrawable(getResources().getDrawable(R.drawable.line_divider_black));
        rvPlaga.setLayoutManager(lm);
        rvPlaga.addItemDecoration(mDivider);
        rvPlaga.setAdapter(plagaAdapter);

        lm = new LinearLayoutManager(this);
        mDivider = new DividerItemDecoration(rvProducto.getContext(), LinearLayoutManager.VERTICAL);
        mDivider.setDrawable(getResources().getDrawable(R.drawable.line_divider_black));
        rvProducto.setLayoutManager(lm);
        rvProducto.addItemDecoration(mDivider);
        rvProducto.setAdapter(productoAdapter);
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
        if (constanciaFumiID_bd.isEmpty())
            return Constant.COMPORTAMIENTO_ACTIVITY_NEW;   //No recibio como parametro el idConstancia
        else {
            constanciaFumi = constanciaFumiActiveRecord.getConstanciaFumi(ConstanciaFumi.ID_WS, constanciaFumiID_bd);
            if (constanciaFumi != null) {
                if (constanciaFumi.getSincronizado().equals(Constant.SI))
                    return Constant.COMPORTAMIENTO_ACTIVITY_VIEW;   //Ya se sincronizó
                else
                    return Constant.COMPORTAMIENTO_ACTIVITY_UPDATE;   //No se ha sincronizado
            } else
                return Constant.COMPORTAMIENTO_ACTIVITY_NEW; //No existe el registro de constanciaFumi
        }
    }

    /**
     * Carga el contenido de la programacion en la interfaz
     *
     * @param lock determina si bloquea y evita guardar o no la informacion
     */
    private void loadInfo(boolean lock) {
        // TODO: 11/10/2018 implementar
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
        startActivity(new Intent(this, ConstanciaFumiActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!catPlagaActiveRecord.isEmpty())
            cargarPlagas();
        if (!catProductoActiveRecord.isEmpty())
            cargarProductos();
    }

    private void cargarPlagas() {
        // TODO: 11/10/2018 implementar
    }

    private void cargarProductos() {
        // TODO: 11/10/2018 implementar
    }

    @Override
    public void onClick(View view) {
        // TODO: 11/10/2018 implementar
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
