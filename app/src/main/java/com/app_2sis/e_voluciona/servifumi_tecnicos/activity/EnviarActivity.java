package com.app_2sis.e_voluciona.servifumi_tecnicos.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Constant;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download.CheckUpdate;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.upload.ProgramacionEnviar;

public class EnviarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Button btnEnviar;
    private ProgressBar pbEnviar;
    private ImageView ivEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);
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
        new CheckUpdate(this);
    }

    private void findViewById() {
        btnEnviar = findViewById(R.id.btn_enviar_todo);
        btnEnviar.setOnClickListener(this);
        pbEnviar = findViewById(R.id.pb_enviar_todo);
        ivEnviar = findViewById(R.id.iv_enviar_todo);
    }

    private void iniComponents() {
        pbEnviar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorWarning),
                android.graphics.PorterDuff.Mode.MULTIPLY);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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
            case R.id.btn_enviar_todo:
                enviarTodo();
                break;
        }
    }


    private void enviarTodo() {
        refreshInterfazTodo(Constant.STATUS_DESCARGA_CARGANDO);
        new ProgramacionEnviar(this);
    }

    private void refreshInterfaz(ProgressBar pb, ImageView iv, int status) {
        switch (status) {
            case Constant.STATUS_DESCARGA_CARGANDO:
                pb.setVisibility(View.VISIBLE);
                iv.setVisibility(View.GONE);
                btnEnviar.setEnabled(false);
                break;

            case Constant.STATUS_DESCARGA_OK:
                pb.setVisibility(View.GONE);
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.check_icon);
                btnEnviar.setEnabled(false);
                break;

            case Constant.STATUS_DESCARGA_ERROR:
                pb.setVisibility(View.GONE);
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.close_icon);
                btnEnviar.setEnabled(true);
                break;

            default:
                pb.setVisibility(View.GONE);
                iv.setVisibility(View.GONE);
                btnEnviar.setEnabled(true);
                break;
        }
    }

    public void refreshInterfazTodo(int status) {
        refreshInterfaz(pbEnviar, ivEnviar, status);
    }

    public void showMensaje(String msj) {
        Snackbar.make(findViewById(android.R.id.content), msj, Snackbar.LENGTH_SHORT).show();
    }
}
