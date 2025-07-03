package com.sise.botonpanico.repositories;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sise.botonpanico.dto.LoginRequestDto;
import com.sise.botonpanico.entities.Usuario;
import com.sise.botonpanico.shared.BaseResponse;
import com.sise.botonpanico.shared.Callback;
import com.sise.botonpanico.shared.Constants;
import com.sise.botonpanico.shared.HttpUtil;
import com.sise.botonpanico.shared.SharedPreferencesUtil;

public class UsuarioRepository {

    private Context context;

    public UsuarioRepository(Context context) {
        this.context = context;
    }

    public void loginUsuario(LoginRequestDto loginRequestDto, Callback<Usuario> callback) {
        new Thread(() -> {
            try {
                // Hace peticion enviando el objeto incidencia como String, y devuelve la respuesta del API en string
                String response = HttpUtil.POST(Constants.BASE_URL_API, "/usuarios/login", new Gson().toJson(loginRequestDto));
                if (response == null) {
                    callback.onFailure();
                    return;
                }
                // Convertir el response String en el objeto BaseResponse<Incidencia>
                BaseResponse<Usuario> baseResponse = new Gson().fromJson(response, TypeToken.getParameterized(BaseResponse.class, Usuario.class).getType());
                if(baseResponse == null){
                    callback.onFailure();
                    return;
                }

                if(!baseResponse.isSuccess()){
                    callback.onFailure();
                    return;
                }
                Usuario usuario = baseResponse.getData();
                SharedPreferencesUtil.guardar(context, Constants.SHARED_PREFERENCES_USUARIO_LOGUEADO, new Gson().toJson(usuario));
                callback.onSuccess(usuario);
            } catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
                callback.onFailure();
            }
        }).start();
    }

    public void insertarUsuario(Usuario usuario, Callback<Usuario> callback) {
        new Thread(() -> {
            try {
                // Hace peticion enviando el objeto incidencia como String, y devuelve la respuesta del API en string
                String response = HttpUtil.POST(Constants.BASE_URL_API, "/usuarios", new Gson().toJson(usuario));
                if (response == null) {
                    callback.onFailure();
                    return;
                }
                // Convertir el response String en el objeto BaseResponse<Incidencia>
                BaseResponse<Usuario> baseResponse = new Gson().fromJson(response, TypeToken.getParameterized(BaseResponse.class, Usuario.class).getType());
                if(baseResponse == null){
                    callback.onFailure();
                    return;
                }

                if(!baseResponse.isSuccess()){
                    callback.onFailure();
                    return;
                }
                callback.onSuccess(baseResponse.getData());
            } catch (Exception e){
                System.out.println(e);
                e.printStackTrace();
                callback.onFailure();
            }
        }).start();
    }

    public void validarUsuarioLogueado(Callback<Usuario> callback) {
        try {
            String json = SharedPreferencesUtil.obtener(context, Constants.SHARED_PREFERENCES_USUARIO_LOGUEADO);
            if(json == null) {
                callback.onFailure();
                return;
            }
            Usuario usuario = new Gson().fromJson(json,Usuario.class);
            callback.onSuccess(usuario);
        } catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            callback.onFailure();
        }
    }
}
