package com.tirumareddy.finance.repository;

import com.tirumareddy.finance.entity.Expense;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    public Optional<Expense> findById(Long id) {
        String sql = """
                SELECT id, description, amount
                FROM expenses
                WHERE id = ?
                """;
        try {
            Expense expense = jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNum) -> new Expense(
                            resultSet.getLong("id"),
                            resultSet.getString("description"),
                            resultSet.getBigDecimal("amount")
                    ),
                    id
            );
            return Optional.of(expense);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }
}
