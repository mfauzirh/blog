package com.oldcatlabs.blog.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @SuppressWarnings("unused")
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handlerApiException(ApiException e) {
        List<String> errorMessages = new ArrayList<>(Collections.singletonList(e.getMessage()));
        ApiExceptionResponse response = ApiExceptionResponse.builder()
                .errorMessages(errorMessages)
                .build();
        return ResponseEntity.status(e.getHttpStatus()).body(response);
    }

}
