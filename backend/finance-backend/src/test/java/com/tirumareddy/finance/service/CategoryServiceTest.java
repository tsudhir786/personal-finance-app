package com.tirumareddy.finance.service;

import com.tirumareddy.finance.exception.CategoryInUseException;
import com.tirumareddy.finance.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;


    @Test
    void deleteCategory_whenCategoryIsInUse_shouldThrowCategoryInUseException() {

//        GIVEN / ARRANGE
        Long categoryId = 1L;

        given(categoryRepository.deleteById(categoryId))
                .willThrow(new DataIntegrityViolationException(
                        "Category is referenced by expenses"
                ));

//        WHEN + THEN
        assertThrows(
                CategoryInUseException.class,
                () -> categoryService.deleteCategory(categoryId)
        );

//        VERIFY
        then(categoryRepository)
                .should()
                .deleteById(categoryId);
    }
}
