package com.sise.botonpanico.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sise.botonpanico.entities.Incidencia;
import com.sise.botonpanico.entities.TipoIncidencia;
import com.sise.botonpanico.shared.BaseResponse;
import com.sise.botonpanico.shared.Callback;
import com.sise.botonpanico.shared.Constants;
import com.sise.botonpanico.shared.HttpUtil;

import java.lang.reflect.Type;
import java.util.List;

public class TipoIncidenciaRepository {
    public void listarTipoIncidencias(Callback<List<TipoIncidencia>> callback){
        new Thread(() -> {
            try {
                String response = HttpUtil.GET(Constants.BASE_URL_API, "/tipo-incidencias");
                if (response == null) {
                    callback.onFailure();
                    return;
                }

                Type type = TypeToken.getParameterized(BaseResponse.class,
                        TypeToken.getParameterized(List.class, TipoIncidencia.class).getType()).getType();

                BaseResponse<List<TipoIncidencia>> baseResponse = new Gson().fromJson(response, type);

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
