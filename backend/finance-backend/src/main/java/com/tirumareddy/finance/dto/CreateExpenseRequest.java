package com.tirumareddy.finance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateExpenseRequest(

        @NotBlank
        String description,

        @Positive
        BigDecimal amount,

        @NotNull
        Long categoryId
) {
    public CreateExpenseRequest {
        System.out.println("Create Expense Request DTO");
    }
}
