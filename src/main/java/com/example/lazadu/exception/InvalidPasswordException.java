package com.example.lazadu.exception;

public class InvalidPasswordException extends CustomizeException {
    @Override
    public String getCode() {
        return "INVALID_EXCEPTION";
    }

    @Override
    public String getMsg() {
        return "The password you entered is not correct!";
    }
}
