package com.example.lazadu.service;

import com.example.lazadu.dto.request.LoginRequest;
import com.example.lazadu.entity.Role;
import com.example.lazadu.exception.InvalidPasswordException;
import com.example.lazadu.exception.InvalidUsernameException;
import com.example.lazadu.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtTokenUtil tokenUtil;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(LoginRequest request) {
        return userService
                .findByUsername(request.getUsername())
                .map(user -> {
                            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                                final Set<Role> roles = userRoleService.getRoles(user.getId());
                                return tokenUtil.generateToken(user, roles);
                            }
                            throw new InvalidPasswordException();
                        }
                )
                .orElseThrow(InvalidUsernameException::new);
    }
}
