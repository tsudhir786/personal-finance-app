package com.tirumareddy.finance.exception;

public class CategoryInUseException extends RuntimeException {

    public CategoryInUseException(Long id) {
        super("Category with id " + id +
                " is currently used by expenses and cannot be deleted");
    }
}
