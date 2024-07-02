package com.example.lazadu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Id
    @UuidGenerator
    private String id;
    private String username;
    private String password;
    private String email;
    private String phoneNo;
    private boolean isDisabled;
}
