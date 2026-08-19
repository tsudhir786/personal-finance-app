package com.tirumareddy.finance.repository;

import com.tirumareddy.finance.entity.Expense;
import com.tirumareddy.finance.exception.ExpenseNotFoundException;
import com.tirumareddy.finance.repository.projection.ExpenseWithCategory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class ExpenseRepository {
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Expense> findAll() {
        String sql = """
                SELECT id, description, amount, category_id
                FROM expenses
                ORDER BY id
                """;
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNumber) -> new Expense(
                        resultSet.getLong("id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getObject("category_id", Long.class)
                )
        );
    }

    public Optional<Expense> findById(Long id) {
        String sql = """
                SELECT id, description, amount, category_id
                FROM expenses
                WHERE id = ?
                """;
        try {
            Expense expense = jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNum) -> new Expense(
                            resultSet.getLong("id"),
                            resultSet.getString("description"),
                            resultSet.getBigDecimal("amount"),
                            resultSet.getObject("category_id", Long.class)
                    ),
                    id
            );
            return Optional.of(expense);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    public Optional<ExpenseWithCategory> findByIdWithCategory(Long id) {
        String sql = """
                SELECT
                    e.id,
                    e.description,
                    e.amount,
                    e.category_id,
                    c.name AS category_name
                FROM expenses e
                LEFT JOIN categories c
                    ON e.category_id = c.id
                WHERE e.id = ?
                """;

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new ExpenseWithCategory(
                        resultSet.getLong("id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getObject("category_id", Long.class),
                        resultSet.getString("category_name")
                ),
                id
        ).stream().findFirst();
    }

    public Expense save(Expense expense) {
        String sql = """
                INSERT INTO expenses (description, amount, category_id)
                VALUES (?, ?, ?)
                RETURNING id, description, amount, category_id
                """;

        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Expense(
                        resultSet.getLong("id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getObject("category_id", Long.class)
                ),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCategoryId()
        );
    }

    public Expense update(Long id, Expense expense) {
        String sql = """
                UPDATE expenses
                SET description = ?,
                    amount = ?,
                    category_id = ?
                WHERE id = ?
                RETURNING id, description, amount, category_id
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNum) -> new Expense(
                            resultSet.getLong("id"),
                            resultSet.getString("description"),
                            resultSet.getBigDecimal("amount"),
                            resultSet.getLong("category_id")
                    ),
                    expense.getDescription(),
                    expense.getAmount(),
                    expense.getCategoryId(),
                    id
            );
        } catch (EmptyResultDataAccessException exception) {
            throw new ExpenseNotFoundException(id);
        }

    }

    public void delete(Long id) {
        String sql = """
                DELETE FROM expenses
                WHERE id = ?
                """;
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new ExpenseNotFoundException(id);
        }
    }

    public List<ExpenseWithCategory> findAllWithCategory() {
        String sql = """
                SELECT
                            e.id,
                            e.description,
                            e.amount,
                            e.category_id,
                            c.name AS category_name
                        FROM expenses e
                        LEFT JOIN categories c
                            ON e.category_id = c.id
                        ORDER BY e.id
                """;

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new ExpenseWithCategory(
                        resultSet.getLong("id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getObject("category_id", Long.class),
                        resultSet.getString("category_name")
                ));
    }
}
