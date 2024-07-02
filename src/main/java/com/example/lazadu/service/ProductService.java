package com.example.lazadu.service;

import com.example.lazadu.dto.request.SaveProductRequest;
import lombok.NonNull;

public interface ProductService {
    String addNewProduct(@NonNull final SaveProductRequest request);
}
