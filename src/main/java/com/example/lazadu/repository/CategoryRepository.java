package com.example.lazadu.repository;

import com.example.lazadu.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>, CategoryRepositoryCustom {

    @Query(value = "UPDATE Category c SET c.disabled = TRUE WHERE c.id = :id")
    void deleteCategory(@Param(value = "id") String id);

    Page<Category> findByDisabledFalse(Pageable pageable);
}
