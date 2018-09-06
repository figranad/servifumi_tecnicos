package com.app_2sis.e_voluciona.servifumi_tecnicos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ProgramacionActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Programacion;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.ProgramacionCabeceraAdapter;

import java.util.List;

public class ProgramacionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
//    private FloatingActionButton fabBuscar;
    private RecyclerView rvProgramacion;

    private ProgramacionActiveRecord programacionActiveRecord;

    private ProgramacionCabeceraAdapter programacionCabeceraAdapter;

    private List<Programacion> programacionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById();

        programacionActiveRecord = new ProgramacionActiveRecord(this);

        iniComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!programacionActiveRecord.isEmpty()) {
            cargarProgramaciones();
        }
    }

    private void cargarProgramaciones() {
        programacionList = programacionActiveRecord.getProgramaciones();
        programacionCabeceraAdapter.deleteAll();
        if (programacionList != null && !programacionList.isEmpty()){
            programacionCabeceraAdapter.addAll(programacionList);
        }
    }

    private void iniComponents() {
        programacionCabeceraAdapter = new ProgramacionCabeceraAdapter(this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        DividerItemDecoration mDivider = new DividerItemDecoration(rvProgramacion.getContext(), LinearLayoutManager.VERTICAL);
        mDivider.setDrawable(getResources().getDrawable(R.drawable.line_divider));
        rvProgramacion.setLayoutManager(lm);
        rvProgramacion.addItemDecoration(mDivider);
        rvProgramacion.setAdapter(programacionCabeceraAdapter);
    }

    private void findViewById(){
        rvProgramacion = findViewById(R.id.rv_programacion_cabecera);
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
    public void onClick(View view) {

    }
}
