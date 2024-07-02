package com.example.lazadu.dto.response;

import com.example.lazadu.dto.CategoryDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetCategoryResponse {

    private List<CategoryDTO> categories;
    private long totalPage;
}
