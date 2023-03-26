package com.sakcode.advice;

import com.sakcode.dto.response.ResponseBase;
import com.sakcode.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RequestHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBase handleInvalidException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMsg = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseBase.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Invalid request params")
                .data(errorMsg)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseBase handleUserNotFound(UserNotFoundException exception) {
        return ResponseBase.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }
}
