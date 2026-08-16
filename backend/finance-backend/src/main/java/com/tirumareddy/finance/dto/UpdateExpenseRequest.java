package com.tirumareddy.finance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateExpenseRequest(

        @NotBlank
        String description,

        @Positive
        BigDecimal amount,

        @NotNull
        Long categoryId

) {
    public UpdateExpenseRequest {
        System.out.println("Update Expense Request DTO");
    }
}