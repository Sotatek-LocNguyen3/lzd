package com.example.lazadu.controller.admin;

import com.example.lazadu.dto.request.UpdateCategoryRequest;
import com.example.lazadu.dto.response.GetCategoryResponse;
import com.example.lazadu.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Add new category
     */
    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<String> createCategory(
            @RequestParam final String name) {

        final String categoryId = categoryService.createCategory(name);
        return new ResponseEntity<>(categoryId, HttpStatus.CREATED);
    }

    /**
     * Save category
     */
    @PutMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> updateCategory(
            @RequestBody final @Validated UpdateCategoryRequest request) {

        categoryService.updateCategory(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * List all category
     */
    @GetMapping
    public ResponseEntity<GetCategoryResponse> listCategories(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {

        final GetCategoryResponse response = categoryService.getCategories(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Remove category
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable String id) {

        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
