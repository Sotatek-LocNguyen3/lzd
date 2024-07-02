package com.example.lazadu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SaveProductRequest {

    private String id;
    private String shopId;
    @NotBlank
    private String name;
    private int price;
    private String categoryId;
}
