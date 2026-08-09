package com.tirumareddy.finance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(
        @NotBlank(message = "Category name is required")
        @Size(max = 100, message = "Category name must not exceed 100 characters")
        String name
) {
    public UpdateCategoryRequest {
        System.out.println("Category Update Request DTO");
    }
}
