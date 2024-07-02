package com.example.lazadu.service;

import com.example.lazadu.dto.CategoryDTO;
import com.example.lazadu.dto.request.UpdateCategoryRequest;
import com.example.lazadu.dto.response.GetCategoryResponse;
import com.example.lazadu.entity.Category;
import com.example.lazadu.exception.CategoryIdNotCorrectException;
import com.example.lazadu.repository.CategoryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public String createCategory(@NonNull final String name) {
        final Category save = categoryRepository.save(Category.builder()
                .name(name)
                .build()
        );
        return save.getId();
    }

    @Override
    public void updateCategory(@NonNull final UpdateCategoryRequest request) {
        final Category category =
                categoryRepository.findById(request.getId()).orElseThrow(CategoryIdNotCorrectException::new);
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    /**
     * Get categories by page and size
     */
    @Override
    public GetCategoryResponse getCategories(final int page, final int size) {
        final Pageable pageable = PageRequest.of(page, size);
        final Page<Category> pg = categoryRepository.findByDisabledFalse(pageable);
        return GetCategoryResponse.builder()
                .totalPage(pg.getTotalPages())
                .categories(getCategoriesFromPage(pg))
                .build();
    }

    /**
     * This method demonstrates how to use JAVA 8 Stream API
     */
    private List<CategoryDTO> getCategoriesFromPage(Page<Category> pg) {
        return pg
                .stream()
                .map(category -> CategoryDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * This method demonstrates how to use for loop
     */
    private List<CategoryDTO> getCategoriesFromPageV2(Page<Category> pg) {
        final List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : pg) {
            categoryDTOS.add(CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build());
        }
        return categoryDTOS;
    }


    /**
     * This method demonstrates how to use for each
     */
    private List<CategoryDTO> getCategoriesFromPageV3(Page<Category> pg) {
        final List<CategoryDTO> categoryDTOS = new ArrayList<>();
        pg.forEach(category ->
                categoryDTOS.add(
                        CategoryDTO.builder()
                                .id(category.getId())
                                .name(category.getName())
                                .build()));
        return categoryDTOS;
    }

    /**
     * This method demonstrates how to use normal for loop
     */
    private List<CategoryDTO> getCategoriesFromPageV4(Page<Category> pg) {
        final List<CategoryDTO> categoryDTOS = new ArrayList<>();
        final List<Category> categories = pg.getContent();
        for (int i = 0; i < pg.getSize(); i++) {
            categoryDTOS.add(
                    CategoryDTO.builder()
                            .id(categories.get(i).getId())
                            .name(categories.get(i).getName())
                            .build());
        }
        return categoryDTOS;
    }

    /**
     * This method demonstrates how to use {@link Iterator#next()} and {@link Iterator#hasNext()}
     */
    private List<CategoryDTO> getCategoriesFromPageV5(Page<Category> pg) {
        final List<CategoryDTO> categoryDTOS = new ArrayList<>();
        final Iterator<Category> iterator = pg.iterator();
        while (iterator.hasNext()) {
            categoryDTOS.add(
                    CategoryDTO.builder()
                            .id(iterator.next().getId())
                            .name(iterator.next().getName())
                            .build());
        }
        return categoryDTOS;
    }

    /**
     * This method demonstrates how to use {@link Iterator#forEachRemaining(Consumer)} ()}}
     */
    private List<CategoryDTO> getCategoriesFromPageV6(Page<Category> pg) {
        final List<CategoryDTO> categoryDTOS = new ArrayList<>();
        final Iterator<Category> iterator = pg.iterator();
        iterator.forEachRemaining(
                // TODO : Study about Consumer
                category -> categoryDTOS.add(
                        CategoryDTO.builder()
                                .id(iterator.next().getId())
                                .name(iterator.next().getName())
                                .build())
        );
        return categoryDTOS;
    }

    /**
     * Mark category is deleted
     */
    @Override
    public void deleteCategory(@NonNull final String id) {
        deleteCategoryV3(id);
    }

    private void deleteCategoryV1(@NonNull final String id) {
        final Category category =
                categoryRepository.findById(id).orElseThrow(CategoryIdNotCorrectException::new);
        category.setDisabled(true);
        categoryRepository.save(category);
    }

    /**
     * Mark category is deleted using {@link org.springframework.data.jpa.repository.Query}
     */
    private void deleteCategoryV2(@NonNull final String id) {
        categoryRepository.deleteCategory(id);
    }

    /**
     * Mark category is deleted using JDBC Template
     */
    private void deleteCategoryV3(@NonNull final String id) {
        categoryRepository.deleteCategoryV3(id);
    }


}
