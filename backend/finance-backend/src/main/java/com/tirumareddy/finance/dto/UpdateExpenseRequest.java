package com.tirumareddy.finance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UpdateExpenseRequest(

        @NotBlank
        String description,

        @Positive
        BigDecimal amount

) {
    public UpdateExpenseRequest {
        System.out.println("Update Expense Request DTO");
    }
}