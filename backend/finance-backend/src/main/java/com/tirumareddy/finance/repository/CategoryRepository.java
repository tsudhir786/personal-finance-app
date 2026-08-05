package com.tirumareddy.finance.repository;

import com.tirumareddy.finance.entity.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Category> findAll() {
        String sql = """
                SELECT id, name
                FROM categories
                ORDER BY id
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Category(
                        rs.getLong("id"),
                        rs.getString("name")
                )
        );
    }

    public Optional<Category> findById(Long id) {
        String sql = """
                SELECT id, name
                FROM categories
                WHERE id = ?
                """;

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Category(
                        resultSet.getLong("id"),
                        resultSet.getString("description")
                ),
                id
        ).stream().findFirst();
    }
}

















