package com.sise.botonpanico.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.sise.botonpanico.dto.LoginRequestDto;
import com.sise.botonpanico.entities.Usuario;
import com.sise.botonpanico.repositories.UsuarioRepository;
import com.sise.botonpanico.shared.Callback;
import com.sise.botonpanico.shared.LiveDataResponse;

public class UsuarioViewModel extends ViewModel {

    private MutableLiveData<LiveDataResponse<Boolean>> insertarUsuarioLiveData;
    private MutableLiveData<LiveDataResponse<Usuario>> loginUsuarioLiveData;
    private MutableLiveData<LiveDataResponse<Usuario>> validarUsuarioLiveData;
    private UsuarioRepository usuarioRepository;

    public UsuarioViewModel(Context context){
        insertarUsuarioLiveData = new MutableLiveData<>();
        loginUsuarioLiveData = new MutableLiveData<>();
        validarUsuarioLiveData = new MutableLiveData<>();
        usuarioRepository = new UsuarioRepository(context);
    }

    public LiveData<LiveDataResponse<Boolean>> getInsertarUsuarioLiveData(){
        return insertarUsuarioLiveData;
    }

    public LiveData<LiveDataResponse<Usuario>> getLoginUsuarioLiveData(){
        return loginUsuarioLiveData;
    }

    public LiveData<LiveDataResponse<Usuario>> getValidarUsuarioLiveData(){
        return validarUsuarioLiveData;
    }

    public void validarUsuarioLogueado(){
        usuarioRepository.validarUsuarioLogueado(new Callback<Usuario>() {
            @Override
            public void onSuccess(Usuario result) {
                validarUsuarioLiveData.setValue(LiveDataResponse.success(result));
            }

            @Override
            public void onFailure() {
                validarUsuarioLiveData.setValue(LiveDataResponse.error());
            }
        });
    }

    public void loginUsuario(LoginRequestDto loginRequestDto) {
        usuarioRepository.loginUsuario(loginRequestDto, new Callback<Usuario>() {
            @Override
            public void onSuccess(Usuario result) {
                loginUsuarioLiveData.postValue(LiveDataResponse.success(result));
            }

            @Override
            public void onFailure() {
                loginUsuarioLiveData.postValue(LiveDataResponse.error());
            }
        });
    }

    public void insertarUsuario(Usuario usuario){
        usuarioRepository.insertarUsuario(usuario, new Callback<Usuario>() {
            @Override
            public void onSuccess(Usuario result) {
                insertarUsuarioLiveData.postValue(LiveDataResponse.success(true));
            }

            @Override
            public void onFailure() {
                insertarUsuarioLiveData.postValue(LiveDataResponse.error());
            }
        });
    }
}
