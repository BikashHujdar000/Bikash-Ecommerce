package com.example.biki.ecom.ecommerce.bikash.Dtos;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private double price;
    private Long categoryId;
    private  Long userId;
    private List<ImageDto> images;



}
