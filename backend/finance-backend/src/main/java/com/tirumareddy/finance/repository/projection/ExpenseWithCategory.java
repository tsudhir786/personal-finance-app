package com.tirumareddy.finance.repository.projection;

import java.math.BigDecimal;

public record ExpenseWithCategory(
        Long id,
        String description,
        BigDecimal amount,
        Long categoryId,
        String categoryName
) {
    public ExpenseWithCategory {
        System.out.println("ExpenseWithCategory Project Read Record Created");
    }
}
