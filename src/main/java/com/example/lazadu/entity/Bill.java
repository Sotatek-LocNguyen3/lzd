package com.example.lazadu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bills")
@Entity
@EqualsAndHashCode(callSuper = true)
public class Bill extends BaseEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;
    @OneToMany
    private List<Order> orders;
}
