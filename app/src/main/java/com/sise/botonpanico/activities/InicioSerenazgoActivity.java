package com.sise.botonpanico.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.botonpanico.R;
import com.sise.botonpanico.adapters.CardSerenazgoAlertaAdapter;
import com.sise.botonpanico.shared.MenuUtil;
import com.sise.botonpanico.viewmodel.IncidenciaViewModel;

public class InicioSerenazgoActivity extends AppCompatActivity {

    private IncidenciaViewModel incidenciaViewModel;
    private CardSerenazgoAlertaAdapter cardSerenazgoAlertaAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_serenazgo);

        Toolbar toolbar = findViewById(R.id.actinisera_toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.actinisera_reciclerview);

        incidenciaViewModel = new ViewModelProvider(this).get(IncidenciaViewModel.class);

        incidenciaViewModel.getListarIncidenciaLiveData().observe(this,listLiveDataResponse -> {
            if(listLiveDataResponse.isSuccess()) {
                cardSerenazgoAlertaAdapter = new CardSerenazgoAlertaAdapter(this,listLiveDataResponse.getData());
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(cardSerenazgoAlertaAdapter);
            }


        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        incidenciaViewModel.listarIncidencias();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_serenazgo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return MenuUtil.onClickMenuSerenazgoItem(InicioSerenazgoActivity.this, item) || super.onOptionsItemSelected(item);
    }

}