package com.example.lazadu.exception.handler;

import com.example.lazadu.exception.CustomizeException;
import com.example.lazadu.exception.DuplicateUserInfoException;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = CustomizeException.class)
    private ResponseEntity<Error> handler(CustomizeException ex) {
        return new ResponseEntity<>(
                Error.builder()
                        .code(ex.getCode())
                        .message(ex.getMsg())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    private ResponseEntity<ValidationError> handler(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
                ValidationError.builder()
                        .fieldErrors(
                                ex.getFieldErrors()
                                        .stream()
                                        .map(fe -> ValidationError.FieldError.builder()
                                                .fieldName(fe.getField())
                                                .message(fe.getDefaultMessage())
                                                .build())
                                        .collect(Collectors.toList()))
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = DuplicateUserInfoException.class)
    private ResponseEntity<RegistrationError> handler(DuplicateUserInfoException ex) {
        return new ResponseEntity<>(
                RegistrationError.builder()
                        .dupFields(ex.getDupFields())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @Data
    @Builder
    private static class Error {
        private String code;
        private String message;
    }

    @Data
    @Builder
    private static class ValidationError {

        private List<FieldError> fieldErrors;

        @Data
        @Builder
        private static class FieldError {
            private String fieldName;
            private String message;
        }

    }

    @Data
    @Builder
    private static class RegistrationError {
        private List<String> dupFields;
    }

}
