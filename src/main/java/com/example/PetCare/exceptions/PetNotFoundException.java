package com.example.PetCare.exceptions;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(String message) {
        super(message);
    }
}