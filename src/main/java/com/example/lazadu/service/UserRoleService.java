package com.example.lazadu.service;

import com.example.lazadu.entity.Role;
import com.example.lazadu.entity.UserRole;

import java.util.List;
import java.util.Set;

public interface UserRoleService {
    Set<Role> getRoles(String userId);

    void save(UserRole userRole);

    List<UserRole> findByUserId(String id);
}
