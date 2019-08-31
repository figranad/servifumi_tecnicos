package com.app_2sis.e_voluciona.servifumi_tecnicos.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaPlataActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download.CatTipoInstalacionSincronizar;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download.ProgramacionSincronizar;

public class DescargaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button btnCatalogos, btnProgramacion, btnDesTodo;
    private ProgressBar pbCatalogos, pbProgramacion;
    private ImageView ivCatalogos, ivProgramacion;
    private TextView tvIndicacion;
    public boolean catalogosOK = false,
            programacionOK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById();
        iniComponents();
    }

    private void iniComponents() {
        tvIndicacion.setVisibility(View.GONE);
        deshabiitarBotones();
    }

    /**
     * Si existen datos de constancia pendiente de enviar, bloquea la descarga de programación hasta que estos datos se envíen
     */
    private void deshabiitarBotones() {
        if (!new ConstanciaPlataActiveRecord(this).getConstanciasPlatasWsSincronizar().isEmpty()
                || !new ConstanciaFumiActiveRecord(this).getConstanciasFumisWsSincronizar().isEmpty()) {
            programacionOK = true;
            btnProgramacion.setEnabled(false);
            btnDesTodo.setEnabled(false);
            tvIndicacion.setVisibility(View.VISIBLE);
        }
    }

    private void findViewById() {
        btnCatalogos = findViewById(R.id.btn_descarga_catalogos);
        btnCatalogos.setOnClickListener(this);
        btnProgramacion = findViewById(R.id.btn_descarga_programacion);
        btnProgramacion.setOnClickListener(this);

        btnDesTodo = findViewById(R.id.btn_descarga_todo);
        btnDesTodo.setOnClickListener(this);

        pbCatalogos = findViewById(R.id.pb_descarga_catalogos);
        pbCatalogos.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorWarning),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        ivCatalogos = findViewById(R.id.iv_descarga_catalogos);

        pbProgramacion = findViewById(R.id.pb_descarga_programacion);
        pbProgramacion.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorWarning),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        ivProgramacion = findViewById(R.id.iv_descarga_programacion);

        tvIndicacion = findViewById(R.id.tv_descarga_indicacion);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = Utileria.getIntentNavigationDrawer(id, this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (intent != null) {
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_descarga_catalogos:
                descargarCatalogos();
                break;
            case R.id.btn_descarga_programacion:
                descargarProgramacion();
                break;
            case R.id.btn_descarga_todo:
                descargarTodo();
                break;
        }
    }

    private void descargarTodo() {
        descargarCatalogos();
        descargarProgramacion();
    }

    /**
     * Orden de la descarga encadenada de catalogos
     * 1-CatTipoInstalacion
     * 2-CatPlaga
     * 3-CatAccesorio
     * 4-CaTurno
     * 5-CatProductos
     * 6-CatTanques
     * 7-MetodoPago
     */
    private void descargarCatalogos() {
        refreshInterfazCatalogos(Constant.STATUS_DESCARGA_CARGANDO);
        new CatTipoInstalacionSincronizar(this);    //Inicia con el primer catalogo y prosigue en secuencia la descarga
    }

    /**
     * Orden de la descarga encadenada de programacion
     * 1-Programacion
     * 2-ProgramacionAccesorios
     * 3-ProgramacionProductos
     */
    private void descargarProgramacion() {
        refreshInterfazProgramacion(Constant.STATUS_DESCARGA_CARGANDO);
        new ProgramacionSincronizar(this);
    }

    private void refreshInterfaz(ProgressBar pb, ImageView iv, int status) {
        switch (status) {
            case Constant.STATUS_DESCARGA_CARGANDO:
                pb.setVisibility(View.VISIBLE);
                iv.setVisibility(View.GONE);
                lockButtons(true);
                break;

            case Constant.STATUS_DESCARGA_OK:
                pb.setVisibility(View.GONE);
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.check_icon);
                lockButtons(false);
                break;

            case Constant.STATUS_DESCARGA_ERROR:
                pb.setVisibility(View.GONE);
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.close_icon);
                lockButtons(false);
                break;

            default:
                pb.setVisibility(View.GONE);
                iv.setVisibility(View.GONE);
                lockButtons(false);
                break;
        }
    }

    public void refreshInterfazCatalogos(int status) {
        refreshInterfaz(pbCatalogos, ivCatalogos, status);
    }

    public void refreshInterfazProgramacion(int status) {
        refreshInterfaz(pbProgramacion, ivProgramacion, status);
    }

    public void showMensaje(String msj) {
        Snackbar.make(findViewById(android.R.id.content), msj, Snackbar.LENGTH_SHORT).show();
    }

    private void eliminarNoSincronizados() {
        new ConstanciaPlataActiveRecord(this).deleteAllNoSincronizados();
        new ConstanciaFumiActiveRecord(this).deleteAllNoSincronizados();
    }

    /**
     * Trata de bloquear o desbloquear todos los botones, si ya se descargo un apartado no se desbloquearan
     *
     * @param flag true trata de bloquear y false trata de desbloquear
     */
    private void lockButtons(boolean flag) {
        if (flag || catalogosOK)
            btnCatalogos.setEnabled(false);
        else
            btnCatalogos.setEnabled(true);

        if (flag || programacionOK)
            btnProgramacion.setEnabled(false);
        else
            btnProgramacion.setEnabled(true);
    }
}
