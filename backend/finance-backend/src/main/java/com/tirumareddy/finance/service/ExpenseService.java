package com.tirumareddy.finance.service;

import com.tirumareddy.finance.entity.Expense;
import com.tirumareddy.finance.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id);

        if (expense == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Expense not found with id: " + id
            );
        }
        return expense;
    }
}
