package com.example.lazadu.service;

import com.example.lazadu.entity.Role;
import com.example.lazadu.entity.UserRole;
import com.example.lazadu.repository.UserRoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    public Set<Role> getRoles(@NonNull final String userId) {
        return userRoleRepository.findByUserId(userId).stream().map(UserRole::getRole).collect(Collectors.toSet());
    }

    @Override
    public void save(@NonNull final UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> findByUserId(@NonNull final String id) {
        return userRoleRepository.findByUserId(id);
    }
}
