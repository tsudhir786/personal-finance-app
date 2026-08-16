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
                        resultSet.getString("name")
                ),
                id
        ).stream().findFirst();
    }

    public Category save(Category category) {
        String sql = """
                        INSERT INTO categories (name)
                        VALUES (?)
                        RETURNING id, name
                """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new Category(
                        rs.getLong("id"),
                        rs.getString("name")
                ),
                category.getName());
    }

    public Category update(Long id, Category category) {
        String sql = """
                UPDATE categories
                SET name = ?
                WHERE id = ?
                RETURNING id, name
                """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new Category(
                        rs.getLong("id"),
                        rs.getString("name")
                ),
                category.getName(),
                category.getId()
        );
    }

    public int deleteById(Long id) {
        String sql = """
                DELETE FROM Categories
                WHERE id = ?
                """;

        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected;
    }
}

















