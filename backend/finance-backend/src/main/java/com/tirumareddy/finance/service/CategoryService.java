package com.tirumareddy.finance.service;

import com.tirumareddy.finance.dto.CategoryResponse;
import com.tirumareddy.finance.dto.CreateCategoryRequest;
import com.tirumareddy.finance.dto.UpdateCategoryRequest;
import com.tirumareddy.finance.entity.Category;
import com.tirumareddy.finance.exception.CategoryAlreadyExistsException;
import com.tirumareddy.finance.exception.CategoryNotFoundException;
import com.tirumareddy.finance.repository.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.zip.DataFormatException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category ->
                        new CategoryResponse(
                                category.getId(),
                                category.getName()
                        )
                ).toList();
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(id));

        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = new Category(
                null,
                request.name()
        );
        try {
            Category savedCategory = categoryRepository.save(category);
            return new CategoryResponse(
                    savedCategory.getId(),
                    savedCategory.getName()
            );

        } catch (DataIntegrityViolationException exception) {
            throw new CategoryAlreadyExistsException(request.name());
        }
    }

    public CategoryResponse updateCategory(Long id, UpdateCategoryRequest request) {
        Category category = new Category(
                id,
                request.name()
        );
        try {
            Category updatedCategory = categoryRepository.update(id, category);

            return new CategoryResponse(
                    updatedCategory.getId(),
                    updatedCategory.getName());
        } catch (EmptyResultDataAccessException exception) {
            throw new CategoryNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new CategoryAlreadyExistsException(request.name());
        }
    }

    public void deleteCategory(Long id) {
        int rowsAffected = categoryRepository.deleteById(id);

        if (rowsAffected == 0) {
            throw new CategoryNotFoundException(id);
        }
    }
}
