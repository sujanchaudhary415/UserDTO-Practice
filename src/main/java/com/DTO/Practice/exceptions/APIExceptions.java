package com.DTO.Practice.exceptions;

public class APIExceptions extends RuntimeException{
    public APIExceptions()
    {

    }
    public APIExceptions(String message)
    {
        super(message);
    }
}
