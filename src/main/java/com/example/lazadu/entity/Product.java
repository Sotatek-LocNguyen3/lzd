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
@Table(name = "products")
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;
    private String shopId;
    private String name;
    private int price;
    private String categoryId;
    private boolean disabled;
}
