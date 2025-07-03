package com.sise.botonpanico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sise.botonpanico.MainActivity;
import com.sise.botonpanico.R;
import com.sise.botonpanico.entities.Usuario;
import com.sise.botonpanico.shared.Constants;
import com.sise.botonpanico.shared.Data;
import com.sise.botonpanico.shared.Message;
import com.sise.botonpanico.viewmodel.UsuarioViewModel;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    private UsuarioViewModel usuarioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarioViewModel = new UsuarioViewModel(this);
        suscribeUsuarioLogueado();
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> {
            usuarioViewModel.validarUsuarioLogueado();
        }, 2000);
    }

    private void suscribeUsuarioLogueado(){
        usuarioViewModel.getValidarUsuarioLiveData().observe(SplashActivity.this, usuarioLiveDataResponse -> {
            if(!usuarioLiveDataResponse.isSuccess() || usuarioLiveDataResponse.getData() == null ) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            Usuario usuario = usuarioLiveDataResponse.getData();
            Toast.makeText(getApplicationContext(),"¡Bienvenido "+usuario.getNombres()+"!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SplashActivity.this, Objects.equals(usuario.getRol().getIdRol(), Data.ID_ROL_VECINO) ? InicioActivity.class : InicioSerenazgoActivity.class);
            //Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}