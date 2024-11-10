package com.example.biki.ecom.ecommerce.bikash.Exceptions;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnauthorizedException extends  RuntimeException{

    String message;

    public UnauthorizedException(String message) {
        super(message);

        this.message = message;
    }


}
