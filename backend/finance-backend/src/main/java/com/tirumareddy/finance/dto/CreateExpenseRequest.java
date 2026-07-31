package com.tirumareddy.finance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateExpenseRequest(

        @NotBlank
        String description,

        @Positive
        BigDecimal amount
) {
        public CreateExpenseRequest {
                System.out.println("Create Expense Request DTO");
        }
}
