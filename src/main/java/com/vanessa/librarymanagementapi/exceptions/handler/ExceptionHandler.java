package com.vanessa.librarymanagementapi.exceptions.handler;

import com.vanessa.librarymanagementapi.exceptions.dto.ApiFieldError;
import com.vanessa.librarymanagementapi.exceptions.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldError = e.getFieldErrors();
        List<ApiFieldError> list = fieldError.stream().map(fe -> new ApiFieldError(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());
        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error.", list);
    }
}
