package com.example.lazadu.service;

import com.example.lazadu.dto.request.SaveProductRequest;
import com.example.lazadu.entity.Category;
import com.example.lazadu.entity.Product;
import com.example.lazadu.repository.CategoryRepository;
import com.example.lazadu.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public String addNewProduct(@NonNull SaveProductRequest request) {
        final Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        final Product.ProductBuilder builder = Product.builder();
        builder.name(request.getName());
        builder.price(request.getPrice());
        builder.shopId(request.getShopId());

        if (Objects.nonNull(category)) {
            builder.categoryId(category.getId());
        }

        final Product prod = productRepository.save(builder.build());
        return prod.getId();
    }
}
