package com.smumo.inventorysystem.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(value = NoSuchCustomerExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleException(NoSuchCustomerExistsException ex)
    {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(CustomerAlreadyExistsException ex){
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    //@Override

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ){
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        body.put("errors",errors);

       return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
//        List<String> errors = new ArrayList<>();
//
//        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
//
//        Map<String, List<String>> result = new HashMap<>();
//        result.put("errors", errors);
//
//        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//    }

    //https://medium.com/@tericcabrel/validate-request-body-and-parameter-in-spring-boot-53ca77f97fe9

}
