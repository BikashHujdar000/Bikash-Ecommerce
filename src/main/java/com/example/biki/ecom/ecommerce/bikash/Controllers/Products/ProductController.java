package com.example.biki.ecom.ecommerce.bikash.Controllers.Products;

import com.example.biki.ecom.ecommerce.bikash.Dtos.ProductDto;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.ProductService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/create/{userId}/{categoryId}")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            @PathVariable("userId") Long userId,
            @PathVariable("categoryId") Long categoryId) {
        ProductDto response = this.productService.createProduct(productDto,userId, categoryId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{productId}")
    public   ResponseEntity<ProductDto> getProductById(@PathVariable ("productId") Long productId)
    {

       ProductDto response =  this.productService.getProductById(productId);


       return new ResponseEntity<>(response,HttpStatus.OK);

    }


    // get all product
    @GetMapping("/list")
    public  ResponseEntity<List<ProductDto>> getAllProducts()
    {
       List<ProductDto> products =  this.productService.getAllProduct();

       return  new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN', 'SELLER')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/categoryProduct/{categoryId}")
    public  ResponseEntity<List<ProductDto>> getProductByCategoryId(@PathVariable("categoryId") Long categoryId)
    {

       List<ProductDto> categorizedList =  this.productService.getAllProductByCategory(categoryId);

       return  new ResponseEntity<>(categorizedList, HttpStatus.OK);

    }


}
