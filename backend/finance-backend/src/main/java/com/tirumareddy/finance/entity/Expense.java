package com.tirumareddy.finance.entity;

import java.math.BigDecimal;

public class Expense {

    private Long id;
    private String description;
    private double amount;

    public Expense(Long id, String description, double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}