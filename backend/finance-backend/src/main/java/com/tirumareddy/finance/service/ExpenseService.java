package com.tirumareddy.finance.service;

import com.tirumareddy.finance.entity.Expense;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    public List<Expense> getExpenses() {
        return List.of(
                new Expense(1L, "Groceries", new BigDecimal("1500.00")),
                new Expense(2L, "Fuel", new BigDecimal("2000.00"))
        );
    }
}
