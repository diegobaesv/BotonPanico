package com.sise.botonpanico.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sise.botonpanico.entities.Incidencia;
import com.sise.botonpanico.repositories.IncidenciaRepository;
import com.sise.botonpanico.shared.Callback;
import com.sise.botonpanico.shared.LiveDataResponse;

public class IncidenciaViewModel extends ViewModel {

    private MutableLiveData<LiveDataResponse<Boolean>> insertarIncidenciaLiveData;
    private IncidenciaRepository incidenciaRepository;

    public IncidenciaViewModel(){
        insertarIncidenciaLiveData = new MutableLiveData<>();
        incidenciaRepository = new IncidenciaRepository();
    }

    public LiveData<LiveDataResponse<Boolean>> getInsertarIncidenciaLiveData(){
        return insertarIncidenciaLiveData;
    }

    public void insertarIncidencia(Incidencia incidencia){
        incidenciaRepository.insertarIncidencia(incidencia, new Callback<Incidencia>() {
            @Override
            public void onSuccess(Incidencia result) {
                insertarIncidenciaLiveData.postValue(LiveDataResponse.success(true));
            }

            @Override
            public void onFailure() {
                insertarIncidenciaLiveData.postValue(LiveDataResponse.error());
            }
        });
    }

}
