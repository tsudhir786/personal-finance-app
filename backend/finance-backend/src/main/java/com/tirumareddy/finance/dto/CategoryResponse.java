package com.tirumareddy.finance.dto;

public record CategoryResponse(
        Long id,
        String name
) {
    public CategoryResponse {
        System.out.println("Category Response DTO");
    }
}
