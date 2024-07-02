package com.example.lazadu.controller.core;

import com.example.lazadu.dto.request.LoginRequest;
import com.example.lazadu.dto.request.RegisterRequest;
import com.example.lazadu.dto.request.ShopRegistrationRequest;
import com.example.lazadu.dto.request.UpdateUserRequest;
import com.example.lazadu.service.LoginService;
import com.example.lazadu.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    /**
     * Register
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody final RegisterRequest request) {
        final String id = userService.register(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Shop register
     */
    @PostMapping("/register-shop")
    @PreAuthorize(value = "hasAuthority('USER')")
    public ResponseEntity<String> registerShop(@Valid final ShopRegistrationRequest request) {
        final String id = userService.registerShop(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Login
     */
    @PostMapping("/authenticate")
    public ResponseEntity<Void> authenticate(@RequestBody LoginRequest request) {
        final String token = loginService.authenticate(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

    /**
     * Update personal information
     */
    @PutMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> updateInfo(@Valid @RequestBody UpdateUserRequest request) {
        userService.updateInfo(request);
        return ResponseEntity.noContent().build();
    }
}
