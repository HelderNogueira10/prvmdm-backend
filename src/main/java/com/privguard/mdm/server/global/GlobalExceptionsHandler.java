package com.privguard.mdm.server.global;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.privguard.mdm.server.api_errors.ApiErrorEntity;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorEntity> handleValidation(MethodArgumentNotValidException _ex) {

        List<String> messages = _ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .toList();

        ApiErrorEntity error = new ApiErrorEntity();
        error.setTimestamp(LocalDateTime.now());
        error.setResponseStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Request Validation Failed!");
        error.setMessages(messages.stream().toString());

        return ResponseEntity.badRequest().body(error);
    }
}
