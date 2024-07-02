package com.example.lazadu.exception;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class DuplicateUserInfoException extends RuntimeException {

    private final List<String> dupFields;

    public DuplicateUserInfoException(@NonNull final List<String> dupFields) {
        this.dupFields = dupFields;
    }
}
