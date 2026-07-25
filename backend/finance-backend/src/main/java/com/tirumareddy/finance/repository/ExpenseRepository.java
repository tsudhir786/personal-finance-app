package com.tirumareddy.finance.repository;

import com.tirumareddy.finance.entity.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ExpenseRepository {
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Expense> findAll() {
   /*     return List.of(
                new Expense(1L, "Groceries", new BigDecimal(1001)),
                new Expense(2L, "Fuel", new BigDecimal(2001)),
                new Expense(3L, "Shopping", new BigDecimal(3001))
        );*/
        String sql = """
                SELECT id, description, amount
                FROM expenses
                ORDER BY id
                """;
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> new Expense(
                        resultSet.getLong("id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("amount")
                )
        );
    }

    public Expense findById(Long id) {
        for (Expense expense : findAll()) {
            if (expense.getId().equals(id)) {
                return expense;
            }
        }
        return null;
    }
}
