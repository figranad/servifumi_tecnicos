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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatAccesorioActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatPlagaActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatProductoActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.CatTipoInstalacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiAccesoriosActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiPlagasActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiProductosActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiVehiculosActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.MetodoPagoActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionAccesorioActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.UsuarioActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.MisPreferencias;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumi;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiAccesorios;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiPlagas;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiProductos;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ConstanciaFumiVehiculos;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.ProgramacionAccesorio;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Usuario;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.AccesorioBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.PlagaBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ProductoBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.VehiculoBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.AccesorioAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.PlagaAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.ProductoAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.VehiculoAdapter;
import com.rey.material.widget.CheckBox;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConstanciaFumiFormularioActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton fabGuardar;
    private TextInputLayout tilFecha, tilCliente, tilContacto, tilHoraEntrada, tilHoraSalida,
            tilTipoServOtro, tilObservaciones, tilDineroRecibido, tilVehiculoMarca,
            tilVehiculoMatricula, tilVehiculoNumEco;
    private EditText etFecha, etCliente, etContacto, etHoraEntrada, etHoraSalida, etTipoServOtro,
            etObservaciones, etDineroRecibido, etVehiculoMarca, etVehiculoMatricula,
            etVehiculoNumEco;
    private Button btnFecha, btnHoraEntrada, btnHoraSalida, btnVehiculoGuardar, btnVehiculoLimpiar;
    private TextView tvAreas, tvTipoServ, tvAplicacion, tvColocacion, tvPlagas, tvPlagasError,
            tvProductos, tvProductosError, tvModoPago, tvFirma, tvErrorSpTipoServ, tvVehiculos,
            tvVehiculosError, tvTipoInstalacion, tvErrorSpTipoInstalacion, tvAccesorios,
            tvAccesoriosError, tvSaldo;
    private CheckBox chkAreaInterior, chkAreaExterior, chkAreaVehiculo, chkAspersion, chkMicroniz,
            chkTermoneb, chkInyeccion, chkCeboRoden, chkCeboGel, chkTrampas, chkLiquidado;
    private Spinner spTipoServ, spModoPago, spTipoInstalacion;
    private ImageButton btnFirma;
    private ImageView ivFirma;
    private LinearLayout llVehiculo;

    private RecyclerView rvPlaga, rvProducto, rvVehiculo, rvAccesorio;
    private PlagaAdapter plagaAdapter;
    private List<PlagaBeanAdapter> plagaBeanAdapterList;
    private ProductoAdapter productoAdapter;
    private AccesorioAdapter accesorioAdapter;
    private List<ProductoBeanAdapter> productoBeanAdapterList;
    private List<AccesorioBeanAdapter> accesorioBeanAdapterList = new ArrayList<>();
    private VehiculoAdapter vehiculoAdapter;
    private List<VehiculoBeanAdapter> vehiculoBeanAdapterList;
    private List<String> vehiculosBean;

    private String programacionID_bd; //Si es view no lo envia
    private String constanciaFumiID_bd; //Si es new no lo envia

    private Programacion programacion;
    private List<ProgramacionAccesorio> programacionAccesorios = new ArrayList<>();
    private ConstanciaFumi constanciaFumi;
    private MisPreferencias misPreferencias;
    private UsuarioActiveRecord usuarioActiveRecord;
    private ProgramacionActiveRecord programacionActiveRecord;
    private ConstanciaFumiActiveRecord constanciaFumiActiveRecord;
    private CatPlagaActiveRecord catPlagaActiveRecord;
    private CatProductoActiveRecord catProductoActiveRecord;
    private CatAccesorioActiveRecord catAccesorioActiveRecord;
    private CatTipoInstalacionActiveRecord catTipoInstalacionActiveRecord;
    private MetodoPagoActiveRecord metodoPagoActiveRecord;
    private ConstanciaFumiPlagasActiveRecord constanciaFumiPlagasActiveRecord;
    private ConstanciaFumiProductosActiveRecord constanciaFumiProductosActiveRecord;
    private ConstanciaFumiAccesoriosActiveRecord constanciaFumiAccesoriosActiveRecord;
    private ProgramacionAccesorioActiveRecord programacionAccesorioActiveRecord;
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

    private String programacionSaldo;

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

        programacionSaldo = getIntent().getStringExtra("programacion_saldo");
        programacionSaldo = programacionSaldo == null ? "" : programacionSaldo;

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
        tilVehiculoMarca = findViewById(R.id.til_constancia_fumi_form_marca);
        tilVehiculoMatricula = findViewById(R.id.til_constancia_fumi_form_matricula);
        tilVehiculoNumEco = findViewById(R.id.til_constancia_fumi_form_num_economico);

        etFecha = findViewById(R.id.et_constancia_fumi_form_fecha);
        etCliente = findViewById(R.id.et_constancia_fumi_form_cliente);
        etContacto = findViewById(R.id.et_constancia_fumi_form_contacto);
        etHoraEntrada = findViewById(R.id.et_constancia_fumi_form_hora_entrada);
        etHoraSalida = findViewById(R.id.et_constancia_fumi_form_hora_salida);
        etTipoServOtro = findViewById(R.id.et_constancia_fumi_form_tipo_servicio_otro);
        etObservaciones = findViewById(R.id.et_constancia_fumi_form_observaciones);
        etDineroRecibido = findViewById(R.id.et_constancia_fumi_form_dinero_recibido);
        etVehiculoMarca = findViewById(R.id.et_constancia_fumi_form_marca);
        etVehiculoMatricula = findViewById(R.id.et_constancia_fumi_form_matricula);
        etVehiculoNumEco = findViewById(R.id.et_constancia_fumi_form_num_economico);

        btnFecha = findViewById(R.id.btn_constancia_fumi_form_fecha);
        btnFecha.setOnClickListener(this);
        btnHoraEntrada = findViewById(R.id.btn_constancia_fumi_form_hora_entrada);
        btnHoraEntrada.setOnClickListener(this);
        btnHoraSalida = findViewById(R.id.btn_constancia_fumi_form_hora_salida);
        btnHoraSalida.setOnClickListener(this);
        btnVehiculoGuardar = findViewById(R.id.btn_constancia_fumi_form_guardar_vehiculo);
        btnVehiculoGuardar.setOnClickListener(this);
        btnVehiculoLimpiar = findViewById(R.id.btn_constancia_fumi_form_limpiar_vehiculo);
        btnVehiculoLimpiar.setOnClickListener(this);

        tvAreas = findViewById(R.id.tv_constancia_fumi_form_areas);
        tvTipoServ = findViewById(R.id.tv_constancia_fumi_form_tipo_servicio);
        tvTipoInstalacion = findViewById(R.id.tv_constancia_fumi_form_tipo_instalacion);
        tvAplicacion = findViewById(R.id.tv_constancia_fumi_form_aplicacion);
        tvColocacion = findViewById(R.id.tv_constancia_fumi_form_colocacion);
        tvPlagas = findViewById(R.id.tv_constancia_fumi_form_plagas);
        tvPlagasError = findViewById(R.id.tv_constancia_fumi_form_plagas_error);
        tvProductos = findViewById(R.id.tv_constancia_fumi_form_productos);
        tvProductosError = findViewById(R.id.tv_constancia_fumi_form_productos_error);
        tvAccesorios = findViewById(R.id.tv_constancia_fumi_form_accesorios);
        tvAccesoriosError = findViewById(R.id.tv_constancia_fumi_form_accesorios_error);
        tvModoPago = findViewById(R.id.tv_constancia_fumi_form_modo_pago);
        tvFirma = findViewById(R.id.tv_constancia_fumi_form_firma);
        tvVehiculos = findViewById(R.id.tv_constancia_fumi_form_vehiculos);
        tvVehiculosError = findViewById(R.id.tv_constancia_fumi_form_vehiculos_error);
        tvSaldo = findViewById(R.id.tv_constancia_fumi_form_saldo);

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
        spTipoInstalacion = findViewById(R.id.sp_constancia_fumi_form_tipo_instalacion);

        btnFirma = findViewById(R.id.btn_constancia_fumi_form_firma);
        btnFirma.setOnClickListener(this);

        rvPlaga = findViewById(R.id.rv_constancia_fumi_form_plagas);
        rvProducto = findViewById(R.id.rv_constancia_fumi_form_productos);
        rvAccesorio = findViewById(R.id.rv_constancia_fumi_form_accesorios);
        rvVehiculo = findViewById(R.id.rv_constancia_fumi_form_list_vehiculos);

        ivFirma = findViewById(R.id.iv_constancia_fumi_form_firma);

        llVehiculo = findViewById(R.id.ll_constancia_fumi_form_vehiculos);
    }

    private void iniComponents() {
        plagaAdapter = new PlagaAdapter(this);
        productoAdapter = new ProductoAdapter(this);
        accesorioAdapter = new AccesorioAdapter(this);
        vehiculoAdapter = new VehiculoAdapter(this);
        vehiculoBeanAdapterList = new ArrayList<>();
        vehiculosBean = new ArrayList<>();
        misPreferencias = new MisPreferencias(this);
        usuarioActiveRecord = new UsuarioActiveRecord(this);
        programacionActiveRecord = new ProgramacionActiveRecord(this);
        programacionAccesorioActiveRecord = new ProgramacionAccesorioActiveRecord(this);
        constanciaFumiActiveRecord = new ConstanciaFumiActiveRecord(this);
        catTipoInstalacionActiveRecord = new CatTipoInstalacionActiveRecord(this);
        metodoPagoActiveRecord = new MetodoPagoActiveRecord(this);
        catPlagaActiveRecord = new CatPlagaActiveRecord(this);
        catProductoActiveRecord = new CatProductoActiveRecord(this);
        catAccesorioActiveRecord = new CatAccesorioActiveRecord(this);
        constanciaFumiPlagasActiveRecord = new ConstanciaFumiPlagasActiveRecord(this);
        constanciaFumiProductosActiveRecord = new ConstanciaFumiProductosActiveRecord(this);
        constanciaFumiAccesoriosActiveRecord = new ConstanciaFumiAccesoriosActiveRecord(this);
        constanciaFumiVehiculosActiveRecord = new ConstanciaFumiVehiculosActiveRecord(this);
        context = getApplicationContext();
        programacion = programacionActiveRecord.getProgramacion(Programacion.ID_WS, programacionID_bd);

        if (hayInfoCatalogos()) {
            setFechayHoras();
            iniSpinners();
            iniRecyclerViews();

            if (!catPlagaActiveRecord.isEmpty())
                cargarPlagas();
            if (!catProductoActiveRecord.isEmpty())
                cargarProductos();

            if (programacion != null) {
                programacionAccesorios = programacionAccesorioActiveRecord.getProgramacionAccesorios(ProgramacionAccesorio.DOMICILIO_ID_WS, programacion.getDomicilio_id());
                if (!programacionAccesorios.isEmpty()) {
                    tvAccesorios.setVisibility(View.VISIBLE);
                    tvAccesoriosError.setVisibility(View.VISIBLE);
                    rvAccesorio.setVisibility(View.VISIBLE);
                    cargarAccesorios();
                } else {
                    tvAccesorios.setVisibility(View.GONE);
                    tvAccesoriosError.setVisibility(View.GONE);
                    rvAccesorio.setVisibility(View.GONE);
                }
            }

            if (programacion != null)
                etCliente.setText(programacion.getTitulo());

            COMPORTAMIENTO_THIS_ACTIVITY = determinarNewUpdateView();

            llVehiculo.setVisibility(View.GONE);
            chkAreaVehiculo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked)
                        llVehiculo.setVisibility(View.VISIBLE);
                    else
                        llVehiculo.setVisibility(View.GONE);
                }
            });

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

            if (programacionSaldo.isEmpty())
                tvSaldo.setVisibility(View.GONE);
            else
                tvSaldo.setText("SALDO: " + programacionSaldo);

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
                || catAccesorioActiveRecord.isEmpty()
                || catTipoInstalacionActiveRecord.isEmpty()
                || metodoPagoActiveRecord.isEmpty());
    }

    private void iniSpinners() {
        spTipoServ.setAdapter(new ArrayAdapter<>(
                getApplicationContext(), R.layout.item_spinner, Utileria.getTipoServicio()));
        spTipoServ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (spTipoServ.getSelectedItem().toString().equals(Constant.TIPO_SERVICIO_OTRO_VALUE))
                    tilTipoServOtro.setVisibility(View.VISIBLE);
                else
                    tilTipoServOtro.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        spModoPago.setAdapter(new ArrayAdapter<>(
                getApplicationContext(), R.layout.item_spinner,
                metodoPagoActiveRecord.getMetodoPagosNombres()));

        spTipoInstalacion.setAdapter(new ArrayAdapter<>(
                getApplicationContext(), R.layout.item_spinner,
                catTipoInstalacionActiveRecord.getCatTipoInstalacionsNombres()));
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

        lm = new LinearLayoutManager(this);
        mDivider = new DividerItemDecoration(rvAccesorio.getContext(), LinearLayoutManager.VERTICAL);
        mDivider.setDrawable(getResources().getDrawable(R.drawable.line_divider_black));
        rvAccesorio.setLayoutManager(lm);
        rvAccesorio.addItemDecoration(mDivider);
        rvAccesorio.setAdapter(accesorioAdapter);

        lm = new LinearLayoutManager(this);
        mDivider = new DividerItemDecoration(rvVehiculo.getContext(), LinearLayoutManager.VERTICAL);
        mDivider.setDrawable(getResources().getDrawable(R.drawable.line_divider_gris));
        rvVehiculo.setLayoutManager(lm);
        rvVehiculo.addItemDecoration(mDivider);
        rvVehiculo.setAdapter(vehiculoAdapter);
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
        if (constanciaFumi != null) {
            etFecha.setText(constanciaFumi.getFecha());
            etCliente.setText(constanciaFumi.getTitulo_programacion());
            etContacto.setText(constanciaFumi.getContacto());
            etHoraEntrada.setText(constanciaFumi.getHora_entrada());
            etHoraSalida.setText(constanciaFumi.getHora_salida());

            chkAreaInterior.setCheckedImmediately(constanciaFumi.getAreas_tratadas_interior().equals(Constant.SI));
            chkAreaExterior.setCheckedImmediately(constanciaFumi.getAreas_tratadas_exterior().equals(Constant.SI));
            chkAreaVehiculo.setCheckedImmediately(constanciaFumi.getAreas_tratadas_vehiculo().equals(Constant.SI));

            spTipoServ.setSelection(Utileria.getPositionSpinner(
                    Utileria.getTipoServicioID(), constanciaFumi.getTipo_servicio()));
            etTipoServOtro.setText(constanciaFumi.getTipo_servicio_otro());
            spTipoInstalacion.setSelection(catTipoInstalacionActiveRecord.getPositionInlista(
                    constanciaFumi.getTipo_instalacion_id()));

            chkAspersion.setCheckedImmediately(constanciaFumi.getTip_aplica_aspersion().equals(Constant.SI));
            chkMicroniz.setCheckedImmediately(constanciaFumi.getTip_aplica_micronizacion().equals(Constant.SI));
            chkTermoneb.setCheckedImmediately(constanciaFumi.getTip_aplica_termoneb().equals(Constant.SI));
            chkInyeccion.setCheckedImmediately(constanciaFumi.getTip_aplica_inyeccion().equals(Constant.SI));
            chkCeboRoden.setCheckedImmediately(constanciaFumi.getTip_aplica_cebo_rodent().equals(Constant.SI));
            chkCeboGel.setCheckedImmediately(constanciaFumi.getTip_aplica_cebo_gel().equals(Constant.SI));
            chkTrampas.setCheckedImmediately(constanciaFumi.getTip_aplica_trampas().equals(Constant.SI));

            //<editor-fold desc="Cargar Vehiculos en UI">
            List<ConstanciaFumiVehiculos> constanciaFumiVehiculos = constanciaFumiVehiculosActiveRecord
                    .getConstanciaFumiVehiculos(ConstanciaFumiVehiculos.CONSTANCIA_FUMI_ID_WS, constanciaFumiID_bd);
            VehiculoBeanAdapter vehiculoBeanAdapter;

            for (ConstanciaFumiVehiculos vehiculo : constanciaFumiVehiculos) {
                vehiculoBeanAdapter = new VehiculoBeanAdapter(
                        vehiculo.getMarca(),
                        vehiculo.getMatricula(),
                        vehiculo.getNum_economico()
                );
                String text = vehiculo.getMarca() + ": " + vehiculo.getMatricula();
                vehiculoBeanAdapterList.add(vehiculoBeanAdapter);
                vehiculosBean.add(text);
                vehiculoAdapter.add(text);
            }
            //</editor-fold>

            //<editor-fold desc="Cargar Accesorios">
            programacionAccesorios = programacionAccesorioActiveRecord.getProgramacionAccesorios(ProgramacionAccesorio.DOMICILIO_ID_WS, constanciaFumi.getDomicilio_id());

            if (!programacionAccesorios.isEmpty()) {
                tvAccesorios.setVisibility(View.VISIBLE);
                tvAccesoriosError.setVisibility(View.VISIBLE);
                rvAccesorio.setVisibility(View.VISIBLE);
                cargarAccesorios();
            } else {
                tvAccesorios.setVisibility(View.GONE);
                tvAccesoriosError.setVisibility(View.GONE);
                rvAccesorio.setVisibility(View.GONE);
            }
            //</editor-fold>

            etObservaciones.setText(constanciaFumi.getObservaciones());
            chkLiquidado.setCheckedImmediately(constanciaFumi.getServicio_liquidado().equals(Constant.SI));
            spModoPago.setSelection(metodoPagoActiveRecord.getPositionInlista(
                    constanciaFumi.getModo_pago_id()));
            etDineroRecibido.setText(constanciaFumi.getDinero_recibido());
            firmaPath = constanciaFumi.getFirma();
            showFirmaEnImageView(firmaPath);
        }
        if (lock)
            lockInfo();
    }

    /**
     * Bloquea los componentes para que la info no se pueda modificar y evita guardar cambios
     */
    private void lockInfo() {
        fabGuardar.hide();
        btnFecha.setEnabled(false);
        btnHoraEntrada.setEnabled(false);
        btnHoraSalida.setEnabled(false);
        btnFirma.setEnabled(false);
        btnVehiculoGuardar.setEnabled(false);

        etContacto.setEnabled(false);
        chkAreaInterior.setEnabled(false);
        chkAreaExterior.setEnabled(false);
        chkAreaVehiculo.setEnabled(false);
        spTipoServ.setEnabled(false);
        spTipoInstalacion.setEnabled(false);
        etTipoServOtro.setEnabled(false);
        chkAspersion.setEnabled(false);
        chkMicroniz.setEnabled(false);
        chkTermoneb.setEnabled(false);
        chkInyeccion.setEnabled(false);
        chkCeboRoden.setEnabled(false);
        chkCeboGel.setEnabled(false);
        chkTrampas.setEnabled(false);

        etObservaciones.setEnabled(false);
        chkLiquidado.setEnabled(false);
        spModoPago.setEnabled(false);
        etDineroRecibido.setEnabled(false);

        vehiculoAdapter.hideDelete(true);
        etVehiculoMarca.setEnabled(false);
        etVehiculoMatricula.setEnabled(false);
        etVehiculoNumEco.setEnabled(false);
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
        if (COMPORTAMIENTO_THIS_ACTIVITY != Constant.COMPORTAMIENTO_ACTIVITY_VIEW) {
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
        } else
            exit();
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
        if (!programacionAccesorios.isEmpty()) {
            tvAccesorios.setVisibility(View.VISIBLE);
            tvAccesoriosError.setVisibility(View.VISIBLE);
            rvAccesorio.setVisibility(View.VISIBLE);
            cargarAccesorios();
        } else {
            tvAccesorios.setVisibility(View.GONE);
            tvAccesoriosError.setVisibility(View.GONE);
            rvAccesorio.setVisibility(View.GONE);
        }
    }

    /**
     * Crea la interfaz para las plagas y la inicializa con datos si ya existía la constancia
     */
    private void cargarPlagas() {
        //Si el comportamiento es de View; en el adapter se bloquean los componentes de plagas
        plagaAdapter.setComportamientoAdapter(COMPORTAMIENTO_THIS_ACTIVITY);


        if (COMPORTAMIENTO_THIS_ACTIVITY != Constant.COMPORTAMIENTO_ACTIVITY_NEW) {
            //Cargar la informacion de la constancia para que se muestre en los componentes
            plagaBeanAdapterList = constanciaFumi.getPlagaBeanAdapter(context);
        } else
            plagaBeanAdapterList = catPlagaActiveRecord.getPlagaBeanAdapter();

        plagaAdapter.deleteAll();
        if (plagaBeanAdapterList != null && !plagaBeanAdapterList.isEmpty()) {
            plagaAdapter.addAll(plagaBeanAdapterList);
        }
    }

    /**
     * Crea la interfaz para los productos y la inicializa con datos si ya existía la constancia
     */
    private void cargarProductos() {
        //Si el comportamiento es de View; en el adapter se bloquean los componentes de productos
        productoAdapter.setComportamientoAdapter(COMPORTAMIENTO_THIS_ACTIVITY);


        if (COMPORTAMIENTO_THIS_ACTIVITY != Constant.COMPORTAMIENTO_ACTIVITY_NEW) {
            //Cargar la informacion de la constancia para que se muestre en los componentes
            productoBeanAdapterList = constanciaFumi.getProductoBeanAdapter(context);
        } else
            productoBeanAdapterList = catProductoActiveRecord.getProductoBeanAdapter();

        productoAdapter.deleteAll();
        if (productoBeanAdapterList != null && !productoBeanAdapterList.isEmpty()) {
            productoAdapter.addAll(productoBeanAdapterList);
        }
    }

    /**
     * Crea la interfaz para los accesorios y la inicializa con datos si ya existía la constancia
     */
    private void cargarAccesorios() {
        //Si el comportamiento es de View; en el adapter se bloquean los componentes de accesorios
        accesorioAdapter.setComportamientoAdapter(COMPORTAMIENTO_THIS_ACTIVITY);

        if (COMPORTAMIENTO_THIS_ACTIVITY != Constant.COMPORTAMIENTO_ACTIVITY_NEW) {
            //Cargar la informacion de la constancia para que se muestre en los componentes
            accesorioBeanAdapterList = constanciaFumi.getAccesorioBeanAdapter(context);
        } else
            accesorioBeanAdapterList = programacionAccesorioActiveRecord.getAccesorioBeanAdapter();

        accesorioAdapter.deleteAll();
        if (accesorioBeanAdapterList != null && !accesorioBeanAdapterList.isEmpty()) {
            accesorioAdapter.addAll(accesorioBeanAdapterList);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_save:
                saveOrUpdate();
                break;
            case R.id.btn_constancia_fumi_form_fecha:
                dpdFecha.show();
                break;
            case R.id.btn_constancia_fumi_form_hora_entrada:
                tpdHoraEntrada.show();
                break;
            case R.id.btn_constancia_fumi_form_hora_salida:
                tpdHoraSalida.show();
                break;
            case R.id.btn_constancia_fumi_form_firma:
                firmar();
                break;
            case R.id.btn_constancia_fumi_form_guardar_vehiculo:
                addVehiculoList();
                break;
            case R.id.btn_constancia_fumi_form_limpiar_vehiculo:
                clearVehiculoUI();
                break;
        }
    }

    private void addVehiculoList() {
        if (validarVehiculo()) {
            VehiculoBeanAdapter vehiculoBeanAdapter = new VehiculoBeanAdapter(
                    etVehiculoMarca.getText().toString().trim(),
                    etVehiculoMatricula.getText().toString().trim(),
                    etVehiculoNumEco.getText().toString().trim()
            );
            vehiculoBeanAdapterList.add(vehiculoBeanAdapter);
            String text = vehiculoBeanAdapter.getMarca() + ": " + vehiculoBeanAdapter.getMatricula();
            vehiculosBean.add(text);
            vehiculoAdapter.add(text);
            clearVehiculoUI();
        }
    }

    private void clearVehiculoUI() {
        etVehiculoMarca.setText(null);
        etVehiculoMatricula.setText(null);
        etVehiculoNumEco.setText(null);
    }

    private void loadInfoVehiculosInUI(VehiculoBeanAdapter beanAdapter) {
        etVehiculoMarca.setText(beanAdapter.getMarca());
        etVehiculoMatricula.setText(beanAdapter.getMatricula());
        etVehiculoNumEco.setText(beanAdapter.getNumEconomico());
    }

    public void loadInfoVehiculosDesdeAdapter(int position) {
        if (vehiculoBeanAdapterList != null && !vehiculoBeanAdapterList.isEmpty()) {
            VehiculoBeanAdapter beanAdapter = vehiculoBeanAdapterList.get(position);
            loadInfoVehiculosInUI(beanAdapter);
        }
    }

    public void deleteInfoVehiculosDesdeAdapter(int position) {
        if (vehiculoBeanAdapterList != null && !vehiculoBeanAdapterList.isEmpty()) {
            clearVehiculoUI();
            vehiculoBeanAdapterList.remove(position);
            vehiculosBean.remove(position);
            vehiculoAdapter.remove(position);
        }
    }

    private void saveOrUpdate() {
        if (validar()) {
            Usuario usuario = usuarioActiveRecord.getUsuario(Usuario.USER_ID_WS, misPreferencias.getIdUsuarioLogueado());
            if (constanciaFumi == null)
                constanciaFumi = new ConstanciaFumi();

            constanciaFumi.setTecnico_id(usuario.getTecnico_id());
            constanciaFumi.setFecha(etFecha.getText().toString().trim());
            constanciaFumi.setHora_entrada(etHoraEntrada.getText().toString().trim());
            constanciaFumi.setHora_salida(etHoraSalida.getText().toString().trim());
            constanciaFumi.setContacto(etContacto.getText().toString().trim());

            constanciaFumi.setAreas_tratadas_interior(
                    chkAreaInterior.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setAreas_tratadas_exterior(
                    chkAreaExterior.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setAreas_tratadas_vehiculo(
                    chkAreaVehiculo.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setTip_aplica_aspersion(
                    chkAspersion.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setTip_aplica_micronizacion(
                    chkMicroniz.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setTip_aplica_termoneb(
                    chkTermoneb.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setTip_aplica_inyeccion(
                    chkInyeccion.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setTip_aplica_cebo_rodent(
                    chkCeboRoden.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setTip_aplica_cebo_gel(
                    chkCeboGel.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setTip_aplica_trampas(
                    chkTrampas.isChecked() ? Constant.SI : Constant.NO);

            constanciaFumi.setTipo_instalacion_id(catTipoInstalacionActiveRecord.getIDInlista(
                    spTipoInstalacion.getSelectedItem().toString()));
            constanciaFumi.setTipo_servicio(Utileria.getTipoServicioID(
                    spTipoServ.getSelectedItem().toString()));
            if (constanciaFumi.getTipo_servicio().equals(Constant.TIPO_SERVICIO_OTRO))
                constanciaFumi.setTipo_servicio_otro(etTipoServOtro.getText().toString().trim());
            else
                constanciaFumi.setTipo_servicio_otro("");

            constanciaFumi.setObservaciones(etObservaciones.getText().toString().trim());
            constanciaFumi.setServicio_liquidado(
                    chkLiquidado.isChecked() ? Constant.SI : Constant.NO);
            constanciaFumi.setModo_pago_id(metodoPagoActiveRecord.getIDInlista(
                    spModoPago.getSelectedItem().toString()));
            constanciaFumi.setDinero_recibido(etDineroRecibido.getText().toString().trim());
            constanciaFumi.setFirma(firmaPath);
            constanciaFumi.setSincronizado(Constant.NO);
            constanciaFumi.setUsuario_id(usuario.getUsuarioId());

            if (COMPORTAMIENTO_THIS_ACTIVITY == Constant.COMPORTAMIENTO_ACTIVITY_UPDATE) {   //Si es un update
                constanciaFumi.upperCase();
                constanciaFumiActiveRecord.update(constanciaFumi);
                constanciaFumiActiveRecord.deleteTablasDependientes(constanciaFumiID_bd);//se volverán a crear abajo
            } else {    //Si es nuevo
                constanciaFumi.setClave_android(Utileria.generarClaveAndroid());
                constanciaFumi.setCreated_at(Utileria.getFecha());
                constanciaFumi.setCrt_time(Utileria.getFechaYHora());
                constanciaFumi.setConstancia_fumi_id("");
                if (programacion != null) {
                    //Insertar campos de la programacion en la constancia
                    constanciaFumi.setOrden_id(programacion.getOrden_id());
                    constanciaFumi.setInspec_fumi_id(programacion.getInsp_fumi_id());
                    constanciaFumi.setCliente_id(programacion.getCliente_id());
                    constanciaFumi.setDomicilio_id(programacion.getDomicilio_id());
                    constanciaFumi.setProgramacion_id(programacion.getProgramacion_id());
                    constanciaFumi.setTitulo_programacion(programacion.getTitulo());
                }
                constanciaFumi.upperCase();
                constanciaFumiActiveRecord.save(constanciaFumi);

                if (programacion != null) {  //Actualiza que la programacion se realizó
                    programacion.setRealizado(Constant.SI);
                    programacionActiveRecord.update(programacion);
                }
            }
            String constanciaFumiID = constanciaFumi.getId() + "";

            //<editor-fold desc="ConstanciaFumiPlagas">
            ConstanciaFumiPlagas constanciaFumiPlagas;
            for (PlagaBeanAdapter plagaBeanAdapter : plagaBeanAdapterList) {
                if (plagaBeanAdapter.isCheck()) {
                    constanciaFumiPlagas = new ConstanciaFumiPlagas();
                    constanciaFumiPlagas.setConstancia_fumi_id(constanciaFumiID);
                    constanciaFumiPlagas.setCat_plaga_id(plagaBeanAdapter.getPlagaID());
                    constanciaFumiPlagasActiveRecord.save(constanciaFumiPlagas);
                }
            }
            //</editor-fold>

            //<editor-fold desc="ConstanciaFumiProductos">
            ConstanciaFumiProductos constanciaFumiProductos;
            for (ProductoBeanAdapter productoBeanAdapter : productoBeanAdapterList) {
                if (productoBeanAdapter.isCheck()) {
                    constanciaFumiProductos = new ConstanciaFumiProductos();
                    constanciaFumiProductos.setConstancia_fumi_id(constanciaFumiID);
                    constanciaFumiProductos.setCat_producto_id(productoBeanAdapter.getProductoID());
                    constanciaFumiProductos.setCantidad(productoBeanAdapter.getCantidad());
                    constanciaFumiProductosActiveRecord.save(constanciaFumiProductos);
                }
            }
            //</editor-fold>

            //<editor-fold desc="ConstanciaFumiAccesorios">
            ConstanciaFumiAccesorios constanciaFumiAccesorios;
            for (AccesorioBeanAdapter accesorioBeanAdapter : accesorioBeanAdapterList) {
                constanciaFumiAccesorios = new ConstanciaFumiAccesorios();
                constanciaFumiAccesorios.setConstancia_fumi_id(constanciaFumiID);
                constanciaFumiAccesorios.setCliente_id(constanciaFumi.getCliente_id());
                constanciaFumiAccesorios.setDomicilio_id(constanciaFumi.getDomicilio_id());
                constanciaFumiAccesorios.setCat_accesorio_id(accesorioBeanAdapter.getAccesorioID());
                constanciaFumiAccesorios.setCantidad(accesorioBeanAdapter.getCantidad());
                constanciaFumiAccesorios.setCondiciones(accesorioBeanAdapter.getCondicionID());
                constanciaFumiAccesoriosActiveRecord.save(constanciaFumiAccesorios);
            }
            //</editor-fold>

            //<editor-fold desc="ConstanciaFumiVehiculos">
            if (chkAreaVehiculo.isChecked()) {
                ConstanciaFumiVehiculos constanciaFumiVehiculos;
                for (VehiculoBeanAdapter vehiculoBeanAdapter : vehiculoBeanAdapterList) {
                    constanciaFumiVehiculos = new ConstanciaFumiVehiculos();
                    constanciaFumiVehiculos.setConstancia_fumi_id(constanciaFumiID);
                    constanciaFumiVehiculos.setMarca(vehiculoBeanAdapter.getMarca());
                    constanciaFumiVehiculos.setMatricula(vehiculoBeanAdapter.getMatricula());
                    constanciaFumiVehiculos.setNum_economico(vehiculoBeanAdapter.getNumEconomico());
                    constanciaFumiVehiculosActiveRecord.save(constanciaFumiVehiculos);
                }
            }
            //</editor-fold>

            Toast.makeText(this, "Guardado Exitosamente", Toast.LENGTH_LONG).show();
            exit();
        }
    }

    private boolean validarVehiculo() {
        clearErrorsVehiculo();
        boolean exito = true;
        if (etVehiculoMarca.getText().toString().trim().isEmpty()) {
            tilVehiculoMarca.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
            exito = false;
        }
        if (etVehiculoMatricula.getText().toString().trim().isEmpty()) {
            tilVehiculoMatricula.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
            exito = false;
        }
        if (!exito)
            Snackbar.make(findViewById(android.R.id.content), Constant.MSJ_VERIFICAR_ERRORES, Snackbar.LENGTH_SHORT).show();
        return exito;
    }

    private boolean validar() {
        clearErrors();
        boolean exito = true;

        if (etFecha.getText().toString().isEmpty()) {
            tilFecha.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
            exito = false;
        }

        if (etHoraEntrada.getText().toString().isEmpty()) {
            tilHoraEntrada.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
            exito = false;
        }
        if (etHoraSalida.getText().toString().isEmpty()) {
            tilHoraSalida.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
            exito = false;
        }
        if (spTipoInstalacion.getSelectedItem().toString().equals(Constant.PROMPT)) {
            exito = false;
            tvErrorSpTipoInstalacion = (TextView) spTipoInstalacion.getSelectedView();
            tvErrorSpTipoInstalacion.setError("anything here, just to add the icon");
            tvErrorSpTipoInstalacion.setTextColor(Color.RED);//just to highlight that this is an error
            tvErrorSpTipoInstalacion.setText(Constant.MSJ_CAMPO_OBLIGATORIO);
        }
        if (spTipoServ.getSelectedItem().toString().equals(Constant.PROMPT)) {
            exito = false;
            tvErrorSpTipoServ = (TextView) spTipoServ.getSelectedView();
            tvErrorSpTipoServ.setError("anything here, just to add the icon");
            tvErrorSpTipoServ.setTextColor(Color.RED);//just to highlight that this is an error
            tvErrorSpTipoServ.setText(Constant.MSJ_CAMPO_OBLIGATORIO);
        }
        if (spTipoServ.getSelectedItem().toString().equals(Constant.TIPO_SERVICIO_OTRO_VALUE)) {
            if (etTipoServOtro.getText().toString().trim().isEmpty()) {
                tilTipoServOtro.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
                exito = false;
            }
        }
        if (firmaPath == null) {
            tvFirma.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
            exito = false;
        }

        //<editor-fold desc="Validacion List Vehiculos">
        if (chkAreaVehiculo.isChecked() && vehiculoBeanAdapterList.size() < 1) {
            tvVehiculos.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
            tvVehiculosError.setText("Agregue al menos 1 vehículo");
            tvVehiculosError.setVisibility(View.VISIBLE);
            exito = false;
        }
        //</editor-fold>

        int cantidad;
        boolean haySeleccionado = false;
        //<editor-fold desc="Validacion RV de plagas">
        for (
                PlagaBeanAdapter plagaTrabajado : plagaBeanAdapterList)

        {
            if (plagaTrabajado.isCheck()) { //Si se marca como que se trabajo el plaga
                haySeleccionado = true;
                break;
            }
        }
        if (!haySeleccionado)

        {
            tvPlagas.setError(Constant.MSJ_MINIMO_1);
            tvPlagasError.setText(Constant.MSJ_MINIMO_1);
            tvPlagasError.setVisibility(View.VISIBLE);
            exito = false;
        }
        //</editor-fold>

        //<editor-fold desc="Validacion RV de productos">
        haySeleccionado = false;
        for (ProductoBeanAdapter productoTrabajado : productoBeanAdapterList) {
            if (productoTrabajado.isCheck()) { //Si se marca como que se trabajo el producto
                haySeleccionado = true;
                try {
                    cantidad = Integer.parseInt(productoTrabajado.getCantidad());
                    if (cantidad < 1) {  //Si se ingresó menos de 0 en un producto seleccionado
                        tvProductos.setError(Constant.MSJ_NO_MENOR_1);
                        tvProductosError.setText(Constant.MSJ_NO_MENOR_1);
                        tvProductosError.setVisibility(View.VISIBLE);
                        exito = false;
                        break;
                    }
                } catch (NumberFormatException ex) { //Si se ingresa texto en el campo de cantidad
                    tvProductos.setError(Constant.MSJ_CAMPO_NUMERICO);
                    tvProductosError.setText(Constant.MSJ_CAMPO_NUMERICO);
                    tvProductosError.setVisibility(View.VISIBLE);
                    exito = false;
                    break;
                }
            }
        }
        if (!haySeleccionado) {
            tvProductos.setError(Constant.MSJ_MINIMO_1);
            tvProductosError.setText(Constant.MSJ_MINIMO_1);
            tvProductosError.setVisibility(View.VISIBLE);
            exito = false;
        }
        //</editor-fold>

        //<editor-fold desc="Validacion RV de accesorios">
        for (AccesorioBeanAdapter accesorioRevisado : accesorioBeanAdapterList) {
            try {
                cantidad = Integer.parseInt(accesorioRevisado.getCantidad());
                if (cantidad < 0) {  //Si se ingresó menos de 0 en un accesorio seleccionado
                    tvAccesorios.setError(Constant.MSJ_NO_MENOR_0);
                    tvAccesoriosError.setText(Constant.MSJ_NO_MENOR_0);
                    tvAccesoriosError.setVisibility(View.VISIBLE);
                    exito = false;
                    break;
                }
            } catch (NumberFormatException ex) { //Si se ingresa texto en el campo de cantidad
                tvAccesorios.setError(Constant.MSJ_CAMPO_NUMERICO);
                tvAccesoriosError.setText(Constant.MSJ_CAMPO_NUMERICO);
                tvAccesoriosError.setVisibility(View.VISIBLE);
                exito = false;
                break;
            }

        }
        //</editor-fold>

        if (!exito)
            Snackbar.make(

                    findViewById(android.R.id.content), Constant.MSJ_VERIFICAR_ERRORES, Snackbar.LENGTH_SHORT).

                    show();
        return exito;
    }

    private void clearErrorsVehiculo() {
        tvVehiculos.setError(null);
        tvVehiculosError.setVisibility(View.GONE);
        tilVehiculoMarca.setError(null);
        tilVehiculoMatricula.setError(null);
    }

    private void clearErrors() {
        tilFecha.setError(null);
        tilHoraEntrada.setError(null);
        tilHoraSalida.setError(null);
        tilTipoServOtro.setError(null);
        tvPlagas.setError(null);
        tvPlagasError.setVisibility(View.GONE);
        tvProductos.setError(null);
        tvProductosError.setVisibility(View.GONE);
        tvAccesorios.setError(null);
        tvAccesoriosError.setVisibility(View.GONE);
        tvFirma.setError(null);
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
