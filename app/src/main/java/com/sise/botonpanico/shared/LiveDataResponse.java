package com.sise.botonpanico.shared;

public class LiveDataResponse {
    private boolean success;
    private Object data;

    public LiveDataResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public static LiveDataResponse success(Object data){
        return new LiveDataResponse(true, data);
    }

    public static LiveDataResponse error(){
        return new LiveDataResponse(false, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
