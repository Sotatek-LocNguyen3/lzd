package com.example.lazadu.service;

import com.example.lazadu.dto.request.RegisterRequest;
import com.example.lazadu.dto.request.ShopRegistrationRequest;
import com.example.lazadu.dto.request.UpdateUserRequest;
import com.example.lazadu.entity.Role;
import com.example.lazadu.entity.User;
import com.example.lazadu.entity.UserRole;
import com.example.lazadu.exception.DuplicateUserInfoException;
import com.example.lazadu.exception.InvalidAccessException;
import com.example.lazadu.exception.InvalidUsernameException;
import com.example.lazadu.repository.UserRepository;
import com.example.lazadu.service.mail.HtmlMailInfo;
import com.example.lazadu.service.mail.HtmlMailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String EMAIL = "email";
    private static final String USER_NAME = "username";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String REGISTRATION_TEMPLATE = "registration";

    @Value("${spring.mail.username}")
    private String from;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final HtmlMailService htmlMailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(@NonNull RegisterRequest request) {
        final Optional<User> userOptional = userRepository.findFirstByUsernameOrEmailOrPhoneNo(
                request.getUsername(),
                request.getEmail(),
                request.getPhoneNo()
        );

        if (userOptional.isPresent()) {
            final List<String> dupFields = new ArrayList<>();
            if (userOptional.get().getEmail().equals(request.getEmail())) {
                dupFields.add(EMAIL);
            }

            if (userOptional.get().getUsername().equals(request.getUsername())) {
                dupFields.add(USER_NAME);
            }

            if (userOptional.get().getPhoneNo().equals(request.getPhoneNo())) {
                dupFields.add(PHONE_NUMBER);
            }
            throw new DuplicateUserInfoException(dupFields);
        }

        // Save user
        final User user = userRepository.save(User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNo(request.getPhoneNo())
                .build());

        // Init USER role for user then save
        final UserRole userRole = UserRole.builder()
                .userId(user.getId())
                .role(Role.USER)
                .build();
        userRoleService.save(userRole);

        // Send mail welcome to user
        sendGreetingEmail(user);
        return user.getId();
    }

    private void sendGreetingEmail(@NonNull final User user) {
        final Thread thread = new Thread(() -> {
            htmlMailService.send(HtmlMailInfo.builder()
                    .to(new String[]{user.getEmail()})
                    .from(from)
                    .templateName(REGISTRATION_TEMPLATE)
                    .data(Map.of(USER_NAME, user.getUsername()))
                    .subject("Hello from lazadu")
                    .build()
            );
        });
        thread.start();
    }

    @Override
    public Set<String> getAuthoritiesByUserId(String userId) {
        return userRepository.findById(userId)
                .map(user ->
                        userRoleService
                                .findByUserId(user.getId())
                                .stream()
                                .map(UserRole::getRole)
                                .map(Enum::name)
                                .collect(Collectors.toSet()))
                .orElse(new HashSet<>());
    }

    @Override
    public Optional<User> findByUsername(@NonNull final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateInfo(@NonNull final UpdateUserRequest request) {
        final String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userRepository.findById(userId).orElseThrow(InvalidUsernameException::new);
        if (!user.getId().equals(request.getId())) {
            throw new InvalidAccessException();
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhoneNo(request.getPhoneNo());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    @Override
    public String registerShop(@NonNull final ShopRegistrationRequest request) {

        return null;
    }
}
