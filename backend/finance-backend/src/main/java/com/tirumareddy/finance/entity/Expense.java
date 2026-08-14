package com.tirumareddy.finance.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class Expense {

    private Long id;
    private String description;
    private BigDecimal amount;
    private Long categoryId;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Expense() {
    }

    public Expense(
            Long id,
            String description,
            BigDecimal amount
    ) {
        this(id, description, amount, null);
    }

    public Expense(
            Long id,
            String description,
            BigDecimal amount,
            Long categoryId
    ) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}