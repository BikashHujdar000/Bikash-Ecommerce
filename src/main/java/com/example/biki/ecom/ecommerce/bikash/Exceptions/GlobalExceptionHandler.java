package com.example.biki.ecom.ecommerce.bikash.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFound.class)
  public ResponseEntity<ApiResponse>resourceNotFoundException(ResourceNotFound ex)
  {
      String message = ex.getMessage();
      ApiResponse apiResponse = new ApiResponse(message,false);
      return  new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(UnauthorizedException.class)
  public  ResponseEntity<ApiResponse>unauthorizedException(UnauthorizedException ex)
  {

      String message = ex.getMessage();
      ApiResponse apiResponse = new ApiResponse(message,false);
      return  new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
  }


  @ExceptionHandler(CartEmptyException.class)
public  ResponseEntity<ApiResponse> illegalStateException(CartEmptyException ex)
{
    String message = ex.getMessage();
    ApiResponse apiResponse = new ApiResponse(message,false);
    return  new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
}



}

