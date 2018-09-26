package com.app_2sis.e_voluciona.servifumi_tecnicos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.ConstanciaPlataActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.MisPreferencias;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.adapter.ConstanciaPlataBeanAdapter;
import com.app_2sis.e_voluciona.servifumi_tecnicos.ui.adapter.ConstanciaPlataAdapter;

import java.util.List;

public class ConstanciaPlataActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fabNuevo;
    private RecyclerView rvConstanciaPlata;
    private ConstanciaPlataActiveRecord constanciaPlataActiveRecord;
    private ConstanciaPlataAdapter constanciaPlataAdapter;
    private List<ConstanciaPlataBeanAdapter> constanciaPlataList;

    private MisPreferencias misPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constancia_plata);
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

        constanciaPlataActiveRecord = new ConstanciaPlataActiveRecord(this);

        iniComponents();
    }

    private void iniComponents() {
        constanciaPlataAdapter = new ConstanciaPlataAdapter(this);
        misPreferencias = new MisPreferencias(this);

        rvConstanciaPlata.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabNuevo.getVisibility() == View.VISIBLE) {
                    fabNuevo.hide();
                } else if (dy < 0 && fabNuevo.getVisibility() != View.VISIBLE) {
                    fabNuevo.show();
                }
            }
        });
        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearNuevaConstancia();
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(this);
        DividerItemDecoration mDivider = new DividerItemDecoration(rvConstanciaPlata.getContext(), LinearLayoutManager.VERTICAL);
        mDivider.setDrawable(getResources().getDrawable(R.drawable.line_divider));
        rvConstanciaPlata.setLayoutManager(lm);
        rvConstanciaPlata.addItemDecoration(mDivider);
        rvConstanciaPlata.setAdapter(constanciaPlataAdapter);

        if (!constanciaPlataActiveRecord.isEmpty()) {
            cargarConstanciasPlatas();
        }
    }

    private void findViewById() {
        fabNuevo = findViewById(R.id.fab_constancia_plata_nuevo);
        rvConstanciaPlata = findViewById(R.id.rv_constancia_plata);
    }

    private void crearNuevaConstancia() {
        // TODO: 25/09/2018 Implementar activity
//        Intent i = new Intent(this, ConstanciaPlataFormularioActivity.class);
//        startActivity(i);
//        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!constanciaPlataActiveRecord.isEmpty()) {
            cargarConstanciasPlatas();
        }
    }

    private void cargarConstanciasPlatas() { //Muestra todos los constanciasPlatas
        constanciaPlataList = constanciaPlataActiveRecord.getConstanciaPlataBeanAdapter(misPreferencias.getIdUsuarioLogueado());
        constanciaPlataAdapter.deleteAll();
        if (constanciaPlataList != null && !constanciaPlataList.isEmpty()) {
            constanciaPlataAdapter.addAll(constanciaPlataList);
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
