package com.tirumareddy.finance.service;

import com.tirumareddy.finance.dto.CreateExpenseRequest;
import com.tirumareddy.finance.dto.ExpenseResponse;
import com.tirumareddy.finance.entity.Category;
import com.tirumareddy.finance.entity.Expense;
import com.tirumareddy.finance.repository.CategoryRepository;
import com.tirumareddy.finance.repository.ExpenseRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void createExpense_whenCategoryExists_shouldCreateExpense() {

        // ARRANGE
        Long categoryId = 1L;

        CreateExpenseRequest request =
                new CreateExpenseRequest(
                        "Groceries",
                        new BigDecimal("850.00"),
                        categoryId
                );

        Category category =
                new Category(categoryId, "Groceries");

        Expense savedExpense =
                new Expense(
                        100L,
                        "Groceries",
                        new BigDecimal("850.00"),
                        categoryId
                );

 /*       when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));

        when(expenseRepository.save(any(Expense.class)))
                .thenReturn(savedExpense);*/

        given(categoryRepository.findById(categoryId))
                .willReturn(Optional.of(category));

        given(expenseRepository.save(any(Expense.class)))
                .willReturn(savedExpense);

        // ACT
        ExpenseResponse response =
                expenseService.createExpense(request);

        // ASSERT
        assertEquals(100L, response.id());
        assertEquals("Groceries", response.description());
        assertEquals(new BigDecimal("850.00"), response.amount());
        assertEquals(categoryId, response.categoryId());

        // VERIFY
        verify(categoryRepository).findById(categoryId);
        verify(expenseRepository).save(any(Expense.class));
    }
}