package com.example.lazadu.service;

import com.example.lazadu.dto.request.UpdateCategoryRequest;
import com.example.lazadu.dto.response.GetCategoryResponse;
import lombok.NonNull;

public interface CategoryService {
    String createCategory(@NonNull final String name);

    void updateCategory(@NonNull final UpdateCategoryRequest request);

    GetCategoryResponse getCategories(final int page, final int size);

    void deleteCategory(@NonNull final String id);
}
