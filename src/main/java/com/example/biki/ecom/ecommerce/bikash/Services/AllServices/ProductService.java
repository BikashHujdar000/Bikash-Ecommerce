package com.example.biki.ecom.ecommerce.bikash.Services.AllServices;


import com.example.biki.ecom.ecommerce.bikash.Dtos.ProductDto;

import java.util.List;

public interface ProductService {

    // create product

    ProductDto createProduct(ProductDto productDto,Long userId,Long categoryId);

    // get  product by Id

    ProductDto getProductById(Long productId);

    // get all Products

    List<ProductDto> getAllProduct();

    //  product by categoryId Id
    List<ProductDto> getAllProductByCategory(Long categoryId);

    //update Product
    ProductDto updateProduct(ProductDto productDto,Long userId, Long productId);

    // delete product
    void  deleteProduct(Long userId, Long productId);



}
