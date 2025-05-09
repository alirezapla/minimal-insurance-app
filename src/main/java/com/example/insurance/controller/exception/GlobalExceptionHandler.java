package com.example.insurance.controller.exception;

import com.example.insurance.common.exceptions.ProviderNotFoundException;
import com.example.insurance.common.exceptions.QuoteNotFoundException;
import com.example.insurance.controller.utils.BaseHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({QuoteNotFoundException.class, ProviderNotFoundException.class})
    public ResponseEntity<BaseHttpResponse<String>> handleQuoteNotFound(RuntimeException ex) {
        BaseHttpResponse<String> response = new BaseHttpResponse<>(
                null,
                System.currentTimeMillis(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
