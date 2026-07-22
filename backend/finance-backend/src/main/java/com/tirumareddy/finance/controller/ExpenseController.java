package com.tirumareddy.finance.controller;

import com.tirumareddy.finance.entity.Expense;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ExpenseController {

    @GetMapping("/api/expenses")
    public List<Expense> getExpenses() {
        return List.of(
                new Expense(1L, "Petrol", new BigDecimal("2500.00")),
                new Expense(2L, "Food", new BigDecimal("600.00")),
                new Expense(3L, "Electricity", new BigDecimal("1800.00"))
        );
    }

}