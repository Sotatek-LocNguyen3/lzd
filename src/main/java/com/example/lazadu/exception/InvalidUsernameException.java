package com.example.lazadu.exception;

public class InvalidUsernameException extends CustomizeException {
    @Override
    public String getCode() {
        return "INVALID_USERNAME";
    }

    @Override
    public String getMsg() {
        return "Username is not registered yet !";
    }
}
