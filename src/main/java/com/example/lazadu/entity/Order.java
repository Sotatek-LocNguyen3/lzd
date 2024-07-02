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
@Table(name = "orders")
@Entity
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;
    private String customerId;
    private String productId;
    private int quantity;
}
