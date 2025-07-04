package com.sise.botonpanico.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sise.botonpanico.entities.Incidencia;
import com.sise.botonpanico.repositories.IncidenciaRepository;
import com.sise.botonpanico.shared.Callback;
import com.sise.botonpanico.shared.LiveDataResponse;

import java.util.List;

public class IncidenciaViewModel extends ViewModel {

    private MutableLiveData<LiveDataResponse<List<Incidencia>>> listarIncidenciaLiveData;
    private MutableLiveData<LiveDataResponse<Boolean>> insertarIncidenciaLiveData;
    private IncidenciaRepository incidenciaRepository;

    public IncidenciaViewModel(){
        insertarIncidenciaLiveData = new MutableLiveData<>();
        listarIncidenciaLiveData = new MutableLiveData<>();
        incidenciaRepository = new IncidenciaRepository();
    }

    public LiveData<LiveDataResponse<Boolean>> getInsertarIncidenciaLiveData(){
        return insertarIncidenciaLiveData;
    }

    public LiveData<LiveDataResponse<List<Incidencia>>> getListarIncidenciaLiveData(){
        return listarIncidenciaLiveData;
    }

    public void listarIncidencias(){
        incidenciaRepository.listarIncidencias(new Callback<List<Incidencia>>() {
            @Override
            public void onSuccess(List<Incidencia> result) {
                listarIncidenciaLiveData.postValue(LiveDataResponse.success(result));
            }

            @Override
            public void onFailure() {
                listarIncidenciaLiveData.postValue(LiveDataResponse.error());
            }
        });
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
