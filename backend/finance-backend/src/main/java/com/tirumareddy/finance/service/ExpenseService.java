package com.tirumareddy.finance.service;

import com.tirumareddy.finance.dto.CreateExpenseRequest;
import com.tirumareddy.finance.dto.ExpenseResponse;
import com.tirumareddy.finance.dto.UpdateExpenseRequest;
import com.tirumareddy.finance.entity.Expense;
import com.tirumareddy.finance.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Expense not found with id: " + id
                ));
    }

    public ExpenseResponse createExpense(CreateExpenseRequest request) {
        Expense expense = new Expense(
                null,
                request.description(),
                request.amount()
        );

        Expense savedExpense = expenseRepository.save(expense);

        return new ExpenseResponse(
                savedExpense.getId(),
                savedExpense.getDescription(),
                savedExpense.getAmount()
        );
    }

    public ExpenseResponse updateExpense(Long id, UpdateExpenseRequest request) {
        Expense expense = new Expense(
                id,
                request.description(),
                request.amount()
        );

        Expense updatedExpense = expenseRepository.update(id, expense);

        return new ExpenseResponse(
                updatedExpense.getId(),
                updatedExpense.getDescription(),
                updatedExpense.getAmount()
        );
    }

    public void deleteExpense(Long id) {
        expenseRepository.delete(id);
    }
}
