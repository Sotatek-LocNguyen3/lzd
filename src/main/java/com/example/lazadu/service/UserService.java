package com.example.lazadu.service;

import com.example.lazadu.dto.request.RegisterRequest;
import com.example.lazadu.dto.request.ShopRegistrationRequest;
import com.example.lazadu.dto.request.UpdateUserRequest;
import com.example.lazadu.entity.User;
import lombok.NonNull;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    String register(@NonNull final RegisterRequest request);

    Set<String> getAuthoritiesByUserId(String userId);

    Optional<User> findByUsername(String username);

    void updateInfo(UpdateUserRequest request);

    String registerShop(ShopRegistrationRequest request);
}
