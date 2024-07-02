package com.example.lazadu.repository;

import com.example.lazadu.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>, OrderRepositoryCustom {
}
