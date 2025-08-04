package com.vanessa.librarymanagementapi.exceptions.handler;

import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import com.vanessa.librarymanagementapi.exceptions.InvalidFieldException;
import com.vanessa.librarymanagementapi.exceptions.OperationNotAllowedException;
import com.vanessa.librarymanagementapi.exceptions.dto.ApiFieldError;
import com.vanessa.librarymanagementapi.exceptions.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateEntryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleDuplicateEntryException(DuplicateEntryException e){
        return ResponseError.conflict(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(OperationNotAllowedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleOperationNotAllowedException(OperationNotAllowedException e){
        return ResponseError.defaultResponse(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidFieldException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleInvalidFieldException(InvalidFieldException e){
        return new ResponseError(
                HttpStatus.UNPROCESSABLE_ENTITY.value()
                , "Validation error."
                ,List.of(new ApiFieldError(e.getField(), e.getMessage())));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError handleAccessDenied(AccessDeniedException e){
        return new ResponseError(HttpStatus.FORBIDDEN.value(), "Access denied.", List.of());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleUnexpectedErrors(RuntimeException e){
        return new ResponseError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected server error.",
                List.of());
    }
}
