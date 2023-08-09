package com.example.spring_boot.exptions;

public class ValidateUSerOwner extends Exception {
    public ValidateUSerOwner(String message){
        super(message);
    }
}