package com.smumo.inventorysystem.exception;

public class CustomerAlreadyExistsException extends  RuntimeException{
    private String message;
    public  CustomerAlreadyExistsException(){}

    public CustomerAlreadyExistsException(String msg){
        super(msg);
        message = msg;
    }

}
