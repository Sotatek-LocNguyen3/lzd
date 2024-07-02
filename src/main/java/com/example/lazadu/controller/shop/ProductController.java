package com.example.lazadu.controller.shop;

import com.example.lazadu.dto.request.SaveProductRequest;
import com.example.lazadu.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/shop")
@PreAuthorize("hasRole('SHOP')")
public class ProductController {

    private final ProductService productService;

    /**
     * Listing new products
     */
    @PostMapping("/prods")
    public ResponseEntity<String> addNewProduct(
            @RequestBody @Valid final SaveProductRequest request) {

        final String productId = productService.addNewProduct(request);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    /**
     * Update existing product
     */

    /**
     * View product detail
     */

    /**
     * Permanently delete product by id
     */

    /**
     * Permanently delete products by ids
     */

    /**
     * Permanently delete products by category
     */

    /**
     * Disable product by id
     */

    /**
     * Disable by ids
     */

    /**
     * Disable products by category
     */

}
