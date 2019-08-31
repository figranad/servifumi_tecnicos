package com.app_2sis.e_voluciona.servifumi_tecnicos.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaFumiActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.MisPreferencias;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ConstanciaFumiBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.ConstanciaFumiAdapter;

import java.util.List;

public class ConstanciaFumiActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvConstanciaFumi;
    private ConstanciaFumiActiveRecord constanciaFumiActiveRecord;
    private ConstanciaFumiAdapter constanciaFumiAdapter;
    private List<ConstanciaFumiBeanAdapter> constanciaFumiList;

    private MisPreferencias misPreferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constancia_fumi);
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

        constanciaFumiActiveRecord = new ConstanciaFumiActiveRecord(this);

        iniComponents();
    }

    private void iniComponents() {
        constanciaFumiAdapter = new ConstanciaFumiAdapter(this);
        misPreferencias = new MisPreferencias(this);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        DividerItemDecoration mDivider = new DividerItemDecoration(rvConstanciaFumi.getContext(), LinearLayoutManager.VERTICAL);
        mDivider.setDrawable(getResources().getDrawable(R.drawable.line_divider));
        rvConstanciaFumi.setLayoutManager(lm);
        rvConstanciaFumi.addItemDecoration(mDivider);
        rvConstanciaFumi.setAdapter(constanciaFumiAdapter);

        if (!constanciaFumiActiveRecord.isEmpty()) {
            cargarConstanciasFumis();
        }
    }

    private void findViewById() {
        rvConstanciaFumi = findViewById(R.id.rv_constancia_fumi);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!constanciaFumiActiveRecord.isEmpty()) {
            cargarConstanciasFumis();
        }
    }

    private void cargarConstanciasFumis() { //Muestra todos los constanciasFumis
        constanciaFumiList = constanciaFumiActiveRecord.getConstanciaFumiBeanAdapter(misPreferencias.getIdUsuarioLogueado());
        constanciaFumiAdapter.deleteAll();
        if (constanciaFumiList != null && !constanciaFumiList.isEmpty()) {
            constanciaFumiAdapter.addAll(constanciaFumiList);
        }
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
}
