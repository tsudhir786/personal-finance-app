package com.tirumareddy.finance.controller;

import com.tirumareddy.finance.dto.CreateExpenseRequest;
import com.tirumareddy.finance.dto.ExpenseResponse;
import com.tirumareddy.finance.dto.ExpenseWithCategoryResponse;
import com.tirumareddy.finance.dto.UpdateExpenseRequest;
import com.tirumareddy.finance.entity.Expense;
import com.tirumareddy.finance.repository.projection.ExpenseWithCategory;
import com.tirumareddy.finance.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<ExpenseWithCategoryResponse> getExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ExpenseWithCategoryResponse getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseWithCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse createExpense(@Valid @RequestBody CreateExpenseRequest request) {
        System.out.println("Controller reached");
        return expenseService.createExpense(request);
    }

    @PutMapping("/{id}")
    public ExpenseResponse updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody UpdateExpenseRequest request) {
        return expenseService.updateExpense(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(
            @PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}