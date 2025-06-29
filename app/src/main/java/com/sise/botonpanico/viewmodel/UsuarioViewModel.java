package com.sise.botonpanico.viewmodel;

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
    private UsuarioRepository usuarioRepository;

    public UsuarioViewModel(){
        insertarUsuarioLiveData = new MutableLiveData<>();
        loginUsuarioLiveData = new MutableLiveData<>();
        usuarioRepository = new UsuarioRepository();
    }

    public LiveData<LiveDataResponse<Boolean>> getInsertarUsuarioLiveData(){
        return insertarUsuarioLiveData;
    }

    public LiveData<LiveDataResponse<Usuario>> getLoginUsuarioLiveData(){
        return loginUsuarioLiveData;
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
