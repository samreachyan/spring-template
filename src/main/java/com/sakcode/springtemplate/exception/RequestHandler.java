package com.sakcode.springtemplate.exception;

import com.sakcode.springtemplate.payload.response.BaseResponse;
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
    public BaseResponse handleInvalidException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMsg = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return BaseResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Invalid request params")
                .data(errorMsg)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public BaseResponse handleUserNotFound(UserNotFoundException exception) {
        return BaseResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }
}

