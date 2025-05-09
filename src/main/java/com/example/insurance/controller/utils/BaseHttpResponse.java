package com.example.insurance.controller.utils;

public class BaseHttpResponse<T> {
    private T data;
    private long timestamp;
    private String message;

    public BaseHttpResponse(T data, long timestamp, String message) {
        this.data = data;
        this.timestamp = timestamp;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}