package com.example.lazadu.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void deleteCategoryV3(String id) {
        jdbcTemplate.update("UPDATE categories SET deleted = TRUE WHERE id =?", id);
    }
}
