package com.example.lazadu.repository;

import com.example.lazadu.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, String>, BillRepositoryCustom {
}
