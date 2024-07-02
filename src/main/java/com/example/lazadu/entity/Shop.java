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
@Table(name = "shops")
@Entity
@EqualsAndHashCode(callSuper = true)
public class Shop extends BaseEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;
    private String userId;
    private String name;
    private boolean disabled;
    @OneToMany
    private List<Product> products;
}
