package com.example.lazadu.exception;

public class CategoryIdNotCorrectException extends CustomizeException {

    @Override
    public String getCode() {
        return "CODE_ABC_XYZ";
    }

    @Override
    public String getMsg() {
        return "CategoryID is not correct!";
    }


}
