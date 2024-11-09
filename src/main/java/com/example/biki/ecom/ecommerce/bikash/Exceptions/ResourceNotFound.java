package com.example.biki.ecom.ecommerce.bikash.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFound extends  RuntimeException  {


    String resourceName;
    String fieldName;
    Long fieldValue;


    public ResourceNotFound(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s with the %s : %d not found ",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }





}



