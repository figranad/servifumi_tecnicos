package com.app_2sis.e_voluciona.servifumi_tecnicos.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app_2sis.e_voluciona.servifumi_tecnicos.R;
import com.app_2sis.e_voluciona.servifumi_tecnicos.db.UsuarioActiveRecord;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.MisPreferencias;
import com.app_2sis.e_voluciona.servifumi_tecnicos.extra.Utileria;
import com.app_2sis.e_voluciona.servifumi_tecnicos.model.Usuario;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download.CheckUpdate;
import com.app_2sis.e_voluciona.servifumi_tecnicos.sync_up.download.UsuarioSincronizar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogin;
    private EditText etUsuario, etPassword;
    private TextInputLayout tilUsuario, tilPassword;
    private static final String CAMPO_OBLIGATORIO = "Campo Obligatorio";
    private static final String VERIFICAR_CAMPO = "Verificar";
    private UsuarioActiveRecord usuarioActiveRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        tilUsuario = findViewById(R.id.tilUsuario);
        tilPassword = findViewById(R.id.tilPassword);
        usuarioActiveRecord = new UsuarioActiveRecord(this);
        new UsuarioSincronizar(this, true);
        new CheckUpdate(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login(v);
                break;
        }
    }

    private void login(View v) {
        btnLogin.setEnabled(false);
        Utileria.hideTeclado(this);

        boolean hayError = false;
        etUsuario.setError(null);
        etPassword.setError(null);

        if (etUsuario.getText().toString().trim().isEmpty()) {
            tilUsuario.setError(CAMPO_OBLIGATORIO);
            hayError = true;
        }
        if (etPassword.getText().toString().trim().isEmpty()) {
            tilPassword.setError(CAMPO_OBLIGATORIO);
            hayError = true;
        }
        if (hayError) {
            showMensaje("Ingresar usuario y/o contraseña", true);
        } else {
            if (!loginLocal()) {
                showMensaje("Descargado datos, espere...", false);
                new UsuarioSincronizar(this, false);
            }
        }
    }

    /**
     * Verifica las credenciales de manera local, y si es correcto lleva a la pantalla principal
     *
     * @return Si el loguin se realiza con éxito
     */
    public boolean loginLocal() {
        String usuario = etUsuario.getText().toString().trim();
        String password = "";
        try {
            password = Utileria.md5(etPassword.getText().toString().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Usuario user = usuarioActiveRecord.getUsuarioLogin(usuario, password);
        if (user != null) {
            MisPreferencias prefs = new MisPreferencias(this);
            prefs.setIdUsuarioLogueado(user.getUsuarioId());
            prefs.setIdTurnoUsuarioLogueado(user.getTurno_id());
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else {
            showMensaje("Usuario y/o contraseña incorrecto", true);
            tilUsuario.setError(VERIFICAR_CAMPO);
            tilPassword.setError(VERIFICAR_CAMPO);
            return false;
        }
    }

    public void showMensaje(String msj, boolean activarBoton) {
        Snackbar.make(findViewById(android.R.id.content), msj, Snackbar.LENGTH_SHORT).show();
        btnLogin.setEnabled(activarBoton);
    }
}
