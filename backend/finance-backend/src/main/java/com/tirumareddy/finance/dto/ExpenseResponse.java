package com.tirumareddy.finance.dto;

import java.math.BigDecimal;

public record ExpenseResponse(
        Long id,
        String description,
        BigDecimal amount
) {
    public ExpenseResponse {
        System.out.println("Expense Response DTO");
    }
}
