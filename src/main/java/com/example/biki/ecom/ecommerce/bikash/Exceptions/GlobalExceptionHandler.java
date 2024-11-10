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



}


//
//@RestControllerAdvice
//public class GlobalExceptionalHandler {
//
//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<ApiResponse>responseNotFoundException (ResourceNotFound ex)
//    {
//        String message =  ex.getMessage();
//        ApiResponse apiResponse = new ApiResponse(message,false);
//        return   new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
//
//
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String,String>> validationException (MethodArgumentNotValidException ex)
//    {
//
//        Map <String,String> resp = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error)->{
//
//            String fieldName = ((FieldError)error).getField();
//            String message = error.getDefaultMessage();
//            resp.put(fieldName,message);
//
//        });
//
//        return new ResponseEntity<Map<String , String>>(resp,HttpStatus.BAD_REQUEST);
//    }
//
//}
