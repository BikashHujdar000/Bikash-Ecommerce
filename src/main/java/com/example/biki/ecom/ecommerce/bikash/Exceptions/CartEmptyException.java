package com.example.biki.ecom.ecommerce.bikash.Exceptions;

public class CartEmptyException extends RuntimeException {

    String message ;

    public CartEmptyException(String message) {
        super(message);
        this.message = message;
    }
}
