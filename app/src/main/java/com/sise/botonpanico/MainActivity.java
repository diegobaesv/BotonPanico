package com.sise.botonpanico;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sise.botonpanico.activities.InicioActivity;
import com.sise.botonpanico.activities.InicioSerenazgoActivity;
import com.sise.botonpanico.activities.PerfilCiudadanoActivity;
import com.sise.botonpanico.dto.LoginRequestDto;
import com.sise.botonpanico.shared.Message;
import com.sise.botonpanico.viewmodel.UsuarioViewModel;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private UsuarioViewModel usuarioViewModel;
    private EditText etNumeroDocumento;
    private EditText etClave;
    private Switch swModoLogin;
    private TextView txtRegistrarse;
    private boolean swModoLoginIsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Ejecutado metodo onCreate()");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarioViewModel = new UsuarioViewModel(MainActivity.this);
        etNumeroDocumento = findViewById(R.id.activitymain_et_username);
        etClave = findViewById(R.id.activitymain_et_password);
        swModoLogin = findViewById(R.id.actmain_sw_modologin);
        txtRegistrarse = findViewById(R.id.activitymain_txt_registrar);

        swModoLogin.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            swModoLoginIsChecked = isChecked;
            txtRegistrarse.setVisibility( isChecked ? View.INVISIBLE : View.VISIBLE);
            etNumeroDocumento.setHint( isChecked ? "DNI Serenazgo" : "DNI Vecino");
        });

        observeUsuarioViewModel();
    }

    public void onClickPerfilCiudadano(View view){
        Intent intent = new Intent(this, PerfilCiudadanoActivity.class);
        startActivity(intent);
    }

    public void observeUsuarioViewModel(){
        usuarioViewModel.getLoginUsuarioLiveData().observe(this,liveDataResponse -> {
            if(!liveDataResponse.isSuccess() || liveDataResponse.getData() == null ) {
                Toast.makeText(this, "Usuario y/o clave incorrecta" ,Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(getApplicationContext(),"¡Se ha ingresado correctamente!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, swModoLoginIsChecked ? InicioSerenazgoActivity.class : InicioActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void onClickLogin(View view){
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setNumeroDocumento(etNumeroDocumento.getText().toString());
        loginRequestDto.setClave(etClave.getText().toString());
        loginRequestDto.setRol(swModoLoginIsChecked ? "SERENAZGO" : "VECINO");
        usuarioViewModel.loginUsuario(loginRequestDto);
    }

}