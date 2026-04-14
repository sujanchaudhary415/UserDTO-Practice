package com.DTO.Practice.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request)
    {
        Map<String,Object>validationErrors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String FieldName=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            validationErrors.put(FieldName,message);
        });

        Map<String,Object>response=new HashMap<>();
        response.put("timestamp",LocalDateTime.now());
        response.put("status",HttpStatus.BAD_REQUEST.value());
        response.put("errors",validationErrors);
        response.put("message",ex.getMessage());
        response.put("path",request.getRequestURI());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

     //APIEXceptions
    @ExceptionHandler(APIExceptions.class)
    public ResponseEntity<ErrorResponse> handleAPIExceptions(APIExceptions ex, HttpServletRequest request)
    {
        ErrorResponse error=new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    //ResourceNorFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException (ResourceNotFoundException ex, HttpServletRequest request)
    {
        ErrorResponse error=new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    //UseralreadyExistException
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException (UserAlreadyExistException ex, HttpServletRequest request)
    {
        ErrorResponse error=new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "User already Exist",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
