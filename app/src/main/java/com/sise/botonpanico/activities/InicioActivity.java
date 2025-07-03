package com.sise.botonpanico.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sise.botonpanico.R;
import com.sise.botonpanico.entities.EstadoIncidencia;
import com.sise.botonpanico.entities.Incidencia;
import com.sise.botonpanico.entities.TipoIncidencia;
import com.sise.botonpanico.shared.MenuUtil;
import com.sise.botonpanico.shared.Message;
import com.sise.botonpanico.viewmodel.IncidenciaViewModel;

public class InicioActivity extends AppCompatActivity {

    private IncidenciaViewModel incidenciaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);

        Toolbar toolbar = findViewById(R.id.actini_toolbar);
        setSupportActionBar(toolbar);

        incidenciaViewModel = new ViewModelProvider(this).get(IncidenciaViewModel.class);

        observeIncidenciaViewModel();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vecino,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return MenuUtil.onClickMenuVecinoItem(InicioActivity.this, item) || super.onOptionsItemSelected(item);
    }

    private void observeIncidenciaViewModel(){
        incidenciaViewModel.getInsertarIncidenciaLiveData().observe(InicioActivity.this, liveDataResponse -> {
            if(!liveDataResponse.isSuccess() || liveDataResponse.getData() == null ) {
                Toast.makeText(InicioActivity.this, Message.INTENTAR_MAS_TARDE,Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(getApplicationContext(),"¡Se ha enviado correctamente!", Toast.LENGTH_LONG).show();
        });
    }

    public void onClickSOSPrincipal(View view){
        Incidencia incidencia = new Incidencia();
        incidencia.setDescripcion("SOS Principal");

        EstadoIncidencia estadoIncidencia = new EstadoIncidencia();
        estadoIncidencia.setIdEstadoIncidencia(1);
        incidencia.setEstadoIncidencia(estadoIncidencia);

        TipoIncidencia tipoIncidencia = new TipoIncidencia();
        tipoIncidencia.setIdTipoIncidencia(1);
        incidencia.setTipoIncidencia(tipoIncidencia);
        incidenciaViewModel.insertarIncidencia(incidencia);
    }

    public void onClickOtraIncidencia(View view){
        Intent intent = new Intent(this, OtroIncidenteActivity.class);
        startActivity(intent);
    }
}