package com.sise.botonpanico.shared;

public class LiveDataResponse<T> {
    private boolean success;
    private T data;

    public LiveDataResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static <T> LiveDataResponse<T> success(T data) {
        return new LiveDataResponse<>(true, data);
    }

    public static <T> LiveDataResponse<T> error() {
        return new LiveDataResponse<>(false, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
