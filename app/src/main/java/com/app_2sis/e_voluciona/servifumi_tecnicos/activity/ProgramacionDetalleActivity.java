package com.app_2sis.e_voluciona.servifumi_tecnicos.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.adapter.ApiConstants;

import java.util.Objects;

public class ProgramacionDetalleActivity extends AppCompatActivity implements View.OnClickListener {
    private String programacionID_bd;
    private Programacion programacion;
    private ProgramacionActiveRecord programacionActiveRecord;

    private ScrollView scrollView;
    private FloatingActionButton fabConstancia;
    private CardView cvProgramacion, cvOrden, cvInspPlata, cvInspFumi;
    private TextView tvProgTitulo, tvProgCuando, tvProgUbicacion, tvProgReferencia, tvProgVendedor,
            tvProgProductos, tvProgDescripcion, tvOrdenFolio, tvOrdenTipoServ, tvOrdenDescripcion,
            tvOrdenFrecuencia, tvOrdenFormaPago, tvOrdenSaldo, tvOrdenObservaciones, tvInspPlataFolio,
            tvInspPlataTinacos, tvInspPlataCisternas, tvInspFumiFolio, tvInspFumiPlagas, tvInspFumiLugares;
    private Spinner spTelefonosCliente;
    private ImageButton btnLlamarCliente;
    private Button btnPdfInspPlata, btnPdfInspFumi, btnCroquisInspFumi, btnNoRealizarMotivo;
    private CheckBox chkNoRealizar;
    private LinearLayout llInspFumiBotones, llNoRealizar, llTelefonos;
    private EditText etNoRealizarMotivo;
    private TextInputLayout tilMotivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programacion_detalle);

        programacionID_bd = getIntent().getStringExtra("programacionID_bd");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        findViewById();
        iniComponents();
        loadData();
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

    private void exit() {
        startActivity(new Intent(this, ProgramacionActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void findViewById() {
        scrollView = findViewById(R.id.scroll_programacion_detalles);

        fabConstancia = findViewById(R.id.fab_constancia_nueva);

        cvProgramacion = findViewById(R.id.cv_programacion);
        cvOrden = findViewById(R.id.cv_programacion_orden);
        cvInspPlata = findViewById(R.id.cv_programacion_insp_plata);
        cvInspFumi = findViewById(R.id.cv_programacion_insp_fumi);

        tvProgTitulo = findViewById(R.id.tv_programacion_titulo);
        tvProgCuando = findViewById(R.id.tv_programacion_cuando);
        tvProgUbicacion = findViewById(R.id.tv_programacion_ubicacion);
        tvProgReferencia = findViewById(R.id.tv_programacion_referencia);
        tvProgVendedor = findViewById(R.id.tv_programacion_vendedor);
        tvProgProductos = findViewById(R.id.tv_programacion_productos);
        tvProgDescripcion = findViewById(R.id.tv_programacion_descripcion);
        tvOrdenFolio = findViewById(R.id.tv_programacion_orden_folio);
        tvOrdenTipoServ = findViewById(R.id.tv_programacion_orden_tipo_servicio);
        tvOrdenDescripcion = findViewById(R.id.tv_programacion_orden_descripcion);
        tvOrdenFrecuencia = findViewById(R.id.tv_programacion_orden_frecuencia);
        tvOrdenFormaPago = findViewById(R.id.tv_programacion_orden_forma_pago);
        tvOrdenSaldo = findViewById(R.id.tv_programacion_orden_saldo);
        tvOrdenObservaciones = findViewById(R.id.tv_programacion_orden_observaciones);
        tvInspPlataFolio = findViewById(R.id.tv_programacion_insp_plata_folio);
        tvInspPlataTinacos = findViewById(R.id.tv_programacion_insp_plata_tinacos);
        tvInspPlataCisternas = findViewById(R.id.tv_programacion_insp_plata_cisternas);
        tvInspFumiFolio = findViewById(R.id.tv_programacion_insp_fumi_folio);
        tvInspFumiPlagas = findViewById(R.id.tv_programacion_insp_fumi_plagas);
        tvInspFumiLugares = findViewById(R.id.tv_programacion_insp_fumi_lugares);

        spTelefonosCliente = findViewById(R.id.sp_programacion_telefonos);

        btnLlamarCliente = findViewById(R.id.btn_programacion_llamar);
        btnLlamarCliente.setOnClickListener(this);
        btnPdfInspPlata = findViewById(R.id.btn_programacion_pdf_plata);
        btnPdfInspPlata.setOnClickListener(this);
        btnPdfInspFumi = findViewById(R.id.btn_programacion_pdf_fumi);
        btnPdfInspFumi.setOnClickListener(this);
        btnCroquisInspFumi = findViewById(R.id.btn_programacion_croquis);
        btnCroquisInspFumi.setOnClickListener(this);
        btnNoRealizarMotivo = findViewById(R.id.btn_programacion_imposible_realizar);
        btnNoRealizarMotivo.setOnClickListener(this);

        chkNoRealizar = findViewById(R.id.chk_programacion_imposible_realizar);
        chkNoRealizar.setOnClickListener(this);

        llInspFumiBotones = findViewById(R.id.ll_programacion_insp_fumi_botones);
        llNoRealizar = findViewById(R.id.ll_programacion_imposible_realizar);
        llTelefonos = findViewById(R.id.ll_programacion_telefonos);

        etNoRealizarMotivo = findViewById(R.id.et_programacion_imposible_realizar_motivo);

        tilMotivo = findViewById(R.id.til_programacion_imposible_realizar);
    }

    private void iniComponents() {
        fabConstancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarConstancia();
            }
        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int dy = scrollView.getScrollY();
                if (dy > 0 && fabConstancia.getVisibility() == View.VISIBLE) {
                    fabConstancia.hide();
                } else if (dy < 10 && fabConstancia.getVisibility() != View.VISIBLE) {
                    fabConstancia.show();
                }
            }
        });

        chkNoRealizar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {    //Si es imposible realizar impide crear constancia y muestra campo de motivos
                    llNoRealizar.setVisibility(View.VISIBLE);
                    fabConstancia.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorverdeDesactivado)));
                } else {    //Si se puede realizar permite crear constancia y oculta los motivos
                    llNoRealizar.setVisibility(View.GONE);
                    fabConstancia.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorSuccess)));
                }
            }
        });

        programacionActiveRecord = new ProgramacionActiveRecord(this);
    }

    /**
     * Lanza la constancia correspodiente al tipo de orden o en su defecto avisa que no es posible crearla si se palomeo que es imposible
     */
    private void lanzarConstancia() {
        if (chkNoRealizar.isChecked()) {
            Snackbar.make(findViewById(android.R.id.content), "Está marcado que es imposible realizar", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent intent;
            if (programacion.getTipo_servicio_id().equals(Constant.PLATAPLUS_VALUE))
                intent = new Intent(getApplicationContext(), ConstanciaPlataFormularioActivity.class);
            else
                intent = new Intent(getApplicationContext(), ConstanciaFumiFormularioActivity.class);
            intent.putExtra("programacionID_bd", programacionID_bd);
            intent.putExtra("programacion_saldo", programacion.getSaldo());
            startActivity(intent);
            finish();
        }
    }

    private void loadData() {
        programacion = programacionActiveRecord.getProgramacion(Programacion.ID_WS, programacionID_bd);
        showHideComponents();
        loadTelefonos();
        String texto;

        //<editor-fold desc="Card Programacion">
        texto = programacion.getTitulo();
        if (texto == null || texto.isEmpty())
            tvProgTitulo.setVisibility(View.GONE);
        else
            tvProgTitulo.setText(texto);

        texto = programacion.getCuando();
        if (texto == null || texto.isEmpty())
            tvProgCuando.setVisibility(View.GONE);
        else
            tvProgCuando.setText("Cuando: " + texto);

        texto = programacion.getLugar();
        if (texto == null || texto.isEmpty())
            tvProgUbicacion.setVisibility(View.GONE);
        else
            tvProgUbicacion.setText("Ubicación: " + texto);

        texto = programacion.getReferencia();
        if (texto == null || texto.isEmpty())
            tvProgReferencia.setVisibility(View.GONE);
        else
            tvProgReferencia.setText("Referencia: " + texto);

        texto = programacion.getVendedor();
        if (texto == null || texto.isEmpty())
            tvProgVendedor.setVisibility(View.GONE);
        else
            tvProgVendedor.setText("Vendedor: " + texto);

        texto = programacion.getProductos();
        if (texto == null || texto.isEmpty())
            tvProgProductos.setVisibility(View.GONE);
        else
            tvProgProductos.setText("Productos: " + texto);

        texto = programacion.getDescripcion_visita();
        if (texto == null || texto.isEmpty())
            tvProgDescripcion.setVisibility(View.GONE);
        else
            tvProgDescripcion.setText("Desc Visita: " + texto);
        //</editor-fold>

        //<editor-fold desc="Card Orden">
        texto = programacion.getOrden_id();
        if (texto == null || texto.isEmpty())
            tvOrdenFolio.setVisibility(View.GONE);
        else
            tvOrdenFolio.setText("Folio Orden: " + texto);

        texto = programacion.getTipo_servicio();
        if (texto == null || texto.isEmpty())
            tvOrdenTipoServ.setVisibility(View.GONE);
        else
            tvOrdenTipoServ.setText("T. Servicio: " + texto);

        texto = programacion.getDescripcion_orden();
        if (texto == null || texto.isEmpty())
            tvOrdenDescripcion.setVisibility(View.GONE);
        else
            tvOrdenDescripcion.setText("Descripción: " + texto);

        texto = programacion.getFrecuencia();
        if (texto == null || texto.isEmpty())
            tvOrdenFrecuencia.setVisibility(View.GONE);
        else
            tvOrdenFrecuencia.setText("Frecuencia: " + texto);

        texto = programacion.getModo_pago();
        if (texto == null || texto.isEmpty())
            tvOrdenFormaPago.setVisibility(View.GONE);
        else
            tvOrdenFormaPago.setText("Forma Pago: " + texto);

        texto = programacion.getSaldo();
        if (texto == null || texto.isEmpty())
            tvOrdenSaldo.setVisibility(View.GONE);
        else
            tvOrdenSaldo.setText("Saldo: " + texto);

        texto = programacion.getObservacion_orden();
        if (texto == null || texto.isEmpty())
            tvOrdenObservaciones.setVisibility(View.GONE);
        else
            tvOrdenObservaciones.setText("Observaciones: " + texto);
        //</editor-fold>

        //<editor-fold desc="Card Insp Plata">
        texto = programacion.getInsp_plata_id();
        if (texto == null || texto.isEmpty())
            tvInspPlataFolio.setVisibility(View.GONE);
        else
            tvInspPlataFolio.setText("Folio Insp Plata: " + texto);

        texto = programacion.getInsp_plata_tinacos();
        if (texto == null || texto.isEmpty())
            tvInspPlataTinacos.setVisibility(View.GONE);
        else
            tvInspPlataTinacos.setText("Tinacos: " + texto);

        texto = programacion.getInsp_plata_cisternas();
        if (texto == null || texto.isEmpty())
            tvInspPlataCisternas.setVisibility(View.GONE);
        else
            tvInspPlataCisternas.setText("Cisternas: " + texto);
        //</editor-fold>

        //<editor-fold desc="Card Insp Fumi">
        texto = programacion.getInsp_fumi_id();
        if (texto == null || texto.isEmpty())
            tvInspFumiFolio.setVisibility(View.GONE);
        else
            tvInspFumiFolio.setText("Folio Insp Fumi: " + texto);

        texto = programacion.getInsp_fumi_plagas();
        if (texto == null || texto.isEmpty())
            tvInspFumiPlagas.setVisibility(View.GONE);
        else
            tvInspFumiPlagas.setText("Plagas: " + texto);

        texto = programacion.getInsp_fumi_lugares();
        if (texto == null || texto.isEmpty())
            tvInspFumiLugares.setVisibility(View.GONE);
        else
            tvInspFumiLugares.setText("Lugares: " + texto);
        //</editor-fold>
    }

    /**
     * Oculta de la interfaz componentes nulos o que no apliquen para la progamacion, tambien setea la info de ImposibleRealizar
     */
    private void showHideComponents() {
        String telefonos = programacion.getTelefonos();
        //Si no cuenta con telefonos o el dispositivo no es capaz de realizar llamadas oculta el spinner y el boton
        if (telefonos == null
                || telefonos.isEmpty()
                || !Utileria.isTelephonyEnabled(this)) {
            llTelefonos.setVisibility(View.GONE);
        }

        if (programacion.getOrden_id() == null || programacion.getOrden_id().isEmpty()) {   //Sin orden
            cvOrden.setVisibility(View.GONE);
        }

        if (programacion.getTipo_servicio_id() == null) {   //Si no tiene seleccionado tipo de servicio oculta ambas inspecciones
            cvInspPlata.setVisibility(View.GONE);
            btnPdfInspPlata.setVisibility(View.GONE);
            cvInspFumi.setVisibility(View.GONE);
            llInspFumiBotones.setVisibility(View.GONE);
            fabConstancia.setVisibility(View.GONE); //No se puede crear una constancia si la orden no dice el tipo de servicio
        } else {
            if (!programacion.getTipo_servicio_id().equals(Constant.PLATAPLUS_VALUE)) { //No es insp plata
                cvInspPlata.setVisibility(View.GONE);
                btnPdfInspPlata.setVisibility(View.GONE);
            }

            if (!programacion.getTipo_servicio_id().equals(Constant.SERVIFUMI_VALUE)) { //No es insp fumi
                cvInspFumi.setVisibility(View.GONE);
                llInspFumiBotones.setVisibility(View.GONE);
            }
        }

        if (programacion.getImposible_realizar() == null
                || programacion.getImposible_realizar().equals(Constant.NO)) {  //Si es posible realizar no pide motivos
            llNoRealizar.setVisibility(View.GONE);
        } else {
            chkNoRealizar.setChecked(true);
            etNoRealizarMotivo.setText(programacion.getImposible_realizar());
        }
    }

    /**
     * Carga el spinner con los telefonos que tiene la programacion
     */
    private void loadTelefonos() {
        String[] telefonosArray = getTelefonosArray();
        spTelefonosCliente.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, telefonosArray));
    }

    /**
     * Obtiene un array de los telefonos para alimentar el Spinner
     *
     * @return Arreglo con el PROMPT y los telefonos de la programacion
     */
    private String[] getTelefonosArray() {
        String telefonosString = Constant.PROMPT + Constant.SEPARADOR_TELEFONOS
                + programacion.getTelefonos();  //Para que se agregue el PROMPT a la lista
        return telefonosString.split(Constant.SEPARADOR_TELEFONOS);
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_programacion_llamar:
                if (spTelefonosCliente.getSelectedItem().toString().equals(Constant.PROMPT)) {
                    Snackbar.make(findViewById(android.R.id.content), "Seleccione un número de la lista", Snackbar.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + spTelefonosCliente.getSelectedItem().toString()));
                }
                break;
            case R.id.btn_programacion_pdf_plata:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ApiConstants.BASE_URL + ApiConstants.URL_PDF_INSP_PLATA + programacion.getInsp_plata_id()));
                break;
            case R.id.btn_programacion_pdf_fumi:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ApiConstants.BASE_URL + ApiConstants.URL_PDF_INSP_FUMI + programacion.getInsp_fumi_id()));
                break;
            case R.id.btn_programacion_croquis:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ApiConstants.BASE_URL + ApiConstants.URL_CROQUIS_INSP_FUMI + programacion.getInsp_fumi_croquis()));
                break;
            case R.id.btn_programacion_imposible_realizar:
                saveMotivos();
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

    /**
     * Guarda los motivos por el cual no es posible realizar la inspeccion y setea banderas
     */
    private void saveMotivos() {
        if (validarMotivos()) {
            programacion.setImposible_realizar(etNoRealizarMotivo.getText().toString().trim());
            programacion.setRealizado(Constant.SI);
            programacion.setImposible_realizar_chk(Constant.SI);
            programacionActiveRecord.update(programacion);
            Toast.makeText(this, Constant.MSJ_GUARDADO_EXITOSO, Toast.LENGTH_SHORT).show();
            exit();
        }
    }

    /**
     * Valida el motivo antes de guardar
     *
     * @return boleano que indica si se validó exitosamente
     */
    private boolean validarMotivos() {
        boolean exito = true;
        clearErrors();

        if (etNoRealizarMotivo.getText().toString().trim().isEmpty()) {
            tilMotivo.setError(Constant.MSJ_CAMPO_OBLIGATORIO);
            exito = false;
        }

        if (!exito) {
            Snackbar.make(findViewById(android.R.id.content), Constant.MSJ_VERIFICAR_ERRORES, Snackbar.LENGTH_SHORT).show();
        }
        return exito;
    }

    /**
     * Limpia msjs de error
     */
    private void clearErrors() {
        tilMotivo.setError(null);
    }
}
