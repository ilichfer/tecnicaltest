package com.capitole.api.infrastructure.adapters.in.rest;

import com.capitole.api.domain.exception.PriceException;
import com.capitole.api.infrastructure.adapters.util.ErrorCatalog;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PriceRestAdvice {
/*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PriceException.class)
    public ErrorResponse handleEmptyInput(PriceException emptyInputException){
        return ErrorResponse.builder().code(HttpStatus.BAD_REQUEST.toString())
                .message(emptyInputException.getErrorMessage())
                .timeStamp(LocalDateTime.now())
                .prueba("prueba de error ilich")
                .build();
    }

 */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PriceException.class)
    public ResponseEntity<String> handleEmptyInput(PriceException emptyInputException){
        return new ResponseEntity<>(ErrorCatalog.NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
