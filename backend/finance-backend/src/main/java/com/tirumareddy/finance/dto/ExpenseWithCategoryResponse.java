package com.tirumareddy.finance.dto;

import java.math.BigDecimal;

public record ExpenseWithCategoryResponse(
        Long id,
        String description,
        BigDecimal amount,
        Long categoryId,
        String categoryName
) {
    public ExpenseWithCategoryResponse {
        System.out.println("Expense with Category Response DTO");
    }
}
