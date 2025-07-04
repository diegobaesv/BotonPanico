package com.sise.botonpanico.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sise.botonpanico.entities.Incidencia;
import com.sise.botonpanico.shared.BaseResponse;
import com.sise.botonpanico.shared.Callback;
import com.sise.botonpanico.shared.Constants;
import com.sise.botonpanico.shared.HttpUtil;

import java.lang.reflect.Type;
import java.util.List;

public class IncidenciaRepository {

    public void insertarIncidencia(Incidencia incidencia, Callback<Incidencia> callback) {
        new Thread(() -> {
            try {
                // Hace peticion enviando el objeto incidencia como String, y devuelve la respuesta del API en string
                String response = HttpUtil.POST(Constants.BASE_URL_API, "/incidencias", new Gson().toJson(incidencia));
                if (response == null) {
                    callback.onFailure();
                    return;
                }
                // Convertir el response String en el objeto BaseResponse<Incidencia>
                BaseResponse<Incidencia> baseResponse = new Gson().fromJson(response, TypeToken.getParameterized(BaseResponse.class, Incidencia.class).getType());
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

    public void listarIncidencias(Callback<List<Incidencia>> callback){
        new Thread(() -> {
            try {
                String response = HttpUtil.GET(Constants.BASE_URL_API, "/incidencias");
                if (response == null) {
                    callback.onFailure();
                    return;
                }

                Type type = TypeToken.getParameterized(BaseResponse.class,
                        TypeToken.getParameterized(List.class, Incidencia.class).getType()).getType();

                BaseResponse<List<Incidencia>> baseResponse = new Gson().fromJson(response, type);

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

}
