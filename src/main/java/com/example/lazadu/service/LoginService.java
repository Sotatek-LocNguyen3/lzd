package com.example.lazadu.service;

import com.example.lazadu.dto.request.LoginRequest;

public interface LoginService {
    String authenticate(LoginRequest request);
}
