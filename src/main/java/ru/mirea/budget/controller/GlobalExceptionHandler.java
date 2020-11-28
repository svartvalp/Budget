package ru.mirea.budget.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mirea.budget.exception.BusinessLogicException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> handleBusinessLogicException(BusinessLogicException exception) {
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        body.put("message", exception.getMessage());
        body.put("status", status.toString());
        return ResponseEntity.status(status).body(body);
    }
}
