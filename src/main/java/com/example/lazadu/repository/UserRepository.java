package com.example.lazadu.repository;

import com.example.lazadu.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findFirstByUsernameOrEmailOrPhoneNo(@NonNull final String username,
                                                       @NonNull final String email,
                                                       @NonNull final String phoneNo);

    Optional<User> findByUsername(String username);
}
