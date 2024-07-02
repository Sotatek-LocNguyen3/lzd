package com.example.lazadu.repository;

import com.example.lazadu.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String>, ShopRepositoryCustom {
}
