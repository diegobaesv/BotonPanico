package com.sise.botonpanico.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sise.botonpanico.R;
import com.sise.botonpanico.adapters.TipoIncidenciaSpinnerAdapter;
import com.sise.botonpanico.entities.EstadoIncidencia;
import com.sise.botonpanico.entities.Incidencia;
import com.sise.botonpanico.entities.TipoIncidencia;
import com.sise.botonpanico.shared.Message;
import com.sise.botonpanico.viewmodel.IncidenciaViewModel;
import com.sise.botonpanico.viewmodel.TipoIncidenciaViewModel;

import java.util.ArrayList;
import java.util.List;

public class OtroIncidenteActivity extends AppCompatActivity {

    private final String TAG = OtroIncidenteActivity.class.getSimpleName();
    private TipoIncidenciaViewModel tipoIncidenciaViewModel;
    private IncidenciaViewModel incidenciaViewModel;
    private Spinner tipoIncidenciaSpinner;
    private EditText etDescripcion;
    private LinearLayout linearLayoutBotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otro_incidente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tipoIncidenciaSpinner = findViewById(R.id.activityotroincidente_sp_tipoincidente);
        etDescripcion = findViewById(R.id.activityotroincidente_et_descripcion);
        linearLayoutBotones = findViewById(R.id.activityotroincidente_ly_linear);

        incidenciaViewModel = new ViewModelProvider(this).get(IncidenciaViewModel.class);
        tipoIncidenciaViewModel = new ViewModelProvider(this).get(TipoIncidenciaViewModel.class);

        observeTipoIncidenciaViewModel();
        observeIncidenciaViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        tipoIncidenciaViewModel.listarTipoIncidencias();
    }

    private void observeTipoIncidenciaViewModel(){
        tipoIncidenciaViewModel.getListarTipoIncidenciasLiveData().observe(this, tipoIncidencias -> {

            if(tipoIncidencias == null) {
               Toast.makeText(this, Message.INTENTAR_MAS_TARDE,Toast.LENGTH_LONG).show();
               return;
            }

            List<TipoIncidencia> tipoIncidenciaNoBoton = new ArrayList<>();
            for ( TipoIncidencia _tipoIncidencia : tipoIncidencias) {
                if(_tipoIncidencia.getFlagBoton().equals("0")){
                    tipoIncidenciaNoBoton.add(_tipoIncidencia);
                }
            }
            TipoIncidenciaSpinnerAdapter adapter = new TipoIncidenciaSpinnerAdapter(this,tipoIncidenciaNoBoton);
            tipoIncidenciaSpinner.setAdapter(adapter);

            for (TipoIncidencia tipoIncidencia: tipoIncidencias) {
                if(tipoIncidencia.getFlagBoton().equals("1")){
                    Button btnTipoIncidencia = new Button(OtroIncidenteActivity.this);
                    btnTipoIncidencia.setText(tipoIncidencia.getDescripcion());
                    btnTipoIncidencia.setBackgroundResource(R.drawable.btn_otro_incidente_background);
                    //btnTipoIncidencia.setBackgroundTintMode(null);
                    linearLayoutBotones.addView(btnTipoIncidencia);
                }

            }
        });
    }

    private void observeIncidenciaViewModel(){
        incidenciaViewModel.getInsertarIncidenciaStatus().observe(this, aBoolean -> {
            if(aBoolean == null || !aBoolean) {
                Toast.makeText(this, Message.INTENTAR_MAS_TARDE,Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(getApplicationContext(),"¡Se ha enviado correctamente!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(OtroIncidenteActivity.this, InicioActivity.class);
            startActivity(intent);
        });
    }

    public void onClickEnviar(View view) {

        String descripcion = etDescripcion.getText().toString().trim();
        if(descripcion.isEmpty()){
            etDescripcion.setError("Debe ingresar una descripción");
            return;
        }

        Incidencia incidencia = new Incidencia();
        incidencia.setDescripcion(etDescripcion.getText().toString());

        EstadoIncidencia estadoIncidencia = new EstadoIncidencia();
        estadoIncidencia.setIdEstadoIncidencia(1);
        incidencia.setEstadoIncidencia(estadoIncidencia);

        TipoIncidencia tipoIncidencia = (TipoIncidencia)tipoIncidenciaSpinner.getSelectedItem();
        incidencia.setTipoIncidencia(tipoIncidencia);
        incidenciaViewModel.insertarIncidencia(incidencia);
    }



}