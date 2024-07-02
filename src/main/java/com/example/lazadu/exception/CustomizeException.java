package com.example.lazadu.exception;

public abstract class CustomizeException extends RuntimeException {
    public abstract String getCode();

    public abstract String getMsg();
}
