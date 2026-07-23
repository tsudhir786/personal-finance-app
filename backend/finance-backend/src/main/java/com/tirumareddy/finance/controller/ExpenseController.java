package com.tirumareddy.finance.controller;

import com.tirumareddy.finance.entity.Expense;
import com.tirumareddy.finance.service.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/api/expenses")
    public List<Expense> getExpenses() {
        return expenseService.getExpenses();
    }
}