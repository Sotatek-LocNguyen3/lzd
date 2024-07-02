package com.example.lazadu.exception;

public class InvalidAccessException extends CustomizeException {

    @Override
    public String getCode() {
        return "INVALID_ACCESS";
    }

    @Override
    public String getMsg() {
        return "Trying to access other's resources is forbidden!";
    }
}
