package com.tirumareddy.finance.dto;

import java.util.Map;
import java.util.SplittableRandom;

public record ApiErrorResponse(
        int status,
        String error,
        String message,
        Map<String, String> fieldErrors
) {
    public ApiErrorResponse {
        System.out.println("Api Response DTO");
    }
}
