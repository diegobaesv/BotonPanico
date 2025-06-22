package com.sise.botonpanico.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sise.botonpanico.entities.TipoIncidencia;
import com.sise.botonpanico.repositories.TipoIncidenciaRepository;
import com.sise.botonpanico.shared.Callback;
import com.sise.botonpanico.shared.LiveDataResponse;

import java.util.List;

public class TipoIncidenciaViewModel extends ViewModel {
    private MutableLiveData<LiveDataResponse<List<TipoIncidencia>>> listarTipoIncidenciasLiveData;
    private TipoIncidenciaRepository tipoIncidenciaRepository;

    public TipoIncidenciaViewModel(){
        this.listarTipoIncidenciasLiveData = new MutableLiveData<>();
        this.tipoIncidenciaRepository = new TipoIncidenciaRepository();
    }

    public LiveData<LiveDataResponse<List<TipoIncidencia>>> getListarTipoIncidenciasLiveData(){
        return listarTipoIncidenciasLiveData;
    }

    public void listarTipoIncidencias(){
        this.tipoIncidenciaRepository.listarTipoIncidencias(new Callback<List<TipoIncidencia>>() {
            @Override
            public void onSuccess(List<TipoIncidencia> result) {
                listarTipoIncidenciasLiveData.postValue(LiveDataResponse.success(result));
            }

            @Override
            public void onFailure() {
                listarTipoIncidenciasLiveData.postValue(LiveDataResponse.error());
            }
        });
    }
}
