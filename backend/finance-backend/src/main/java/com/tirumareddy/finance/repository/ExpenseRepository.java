package com.tirumareddy.finance.repository;

import com.tirumareddy.finance.entity.Expense;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ExpenseRepository {

    public List<Expense> findAll() {
        return List.of(
                new Expense(1L, "Groceries", 1500.00),
                new Expense(2L, "Fuel", 2000.50)
        );
    }
}
