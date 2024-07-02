package com.example.lazadu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ShopRegistrationRequest {

    @NotBlank
    private String userId;
    /**
     * Id căn cước công dân
     */
    @NotBlank
    private String identificationId;
    /**
     * Trú quán
     */
    @NotBlank
    private String originAddress;
    /**
     * Cấp ngày - yyyy/MM/dd
     */
    @NotBlank
    private String issuedDate;
    /**
     * Cấp tại
     */
    @NotBlank
    private String issuedAt;
    /**
     * Ngành hàng
     */
    @NotNull
    private List<String> categoryIds;
    /**
     * Ảnh mặt trước CCCD
     */
    @NotNull
    private MultipartFile frontIdCard;
    /**
     * Ảnh mặt sau CCCD
     */
    @NotNull
    private MultipartFile backIdCard;
}
