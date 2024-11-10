package com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CategoryDto;
import com.example.biki.ecom.ecommerce.bikash.Dtos.ProductDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.Category;
import com.example.biki.ecom.ecommerce.bikash.Entities.Image;
import com.example.biki.ecom.ecommerce.bikash.Entities.Product;
import com.example.biki.ecom.ecommerce.bikash.Entities.User;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ApiResponse;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ResourceNotFound;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.UnauthorizedException;
import com.example.biki.ecom.ecommerce.bikash.Repositories.CategoryRepository;
import com.example.biki.ecom.ecommerce.bikash.Repositories.ProductRepository;
import com.example.biki.ecom.ecommerce.bikash.Repositories.UserRepository;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto, Long userId, Long categoryId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "userId", userId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "categoryId", categoryId));
        Product product = this.modelMapper.map(productDto, Product.class);
        product.setCategory(category);

        product.setUser(user);

        // hardcoded for right now we will use after cloundinary after use
        Image image1 = new Image(null, "https://example.com/images/product1-image1.jpg", product);
        Image image2 = new Image(null, "https://example.com/images/product1-image2.jpg", product);
        Image image3 = new Image(null, "https://example.com/images/product1-image3.jpg", product);
        product.setImages(Arrays.asList(image1, image2, image3));

        // okay every product details is set now i will send it to my database
        Product savedProduct = this.productRepository.save(product);
        return this.modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound("Product", "productId", productId));
        return this.modelMapper.map(product, ProductDto.class);
    }


    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = this.productRepository.findAll();

        List<ProductDto> responseList = products.stream().map(item -> this.modelMapper.map(item, ProductDto.class)).collect(Collectors.toList());
        return responseList;
    }


    @Override
    public List<ProductDto> getAllProductByCategory(Long categoryId) {


        List<Product> products = this.productRepository.findAll();

        List<Product> categorizedProducts = new ArrayList<>();

       products.forEach(item->{
           if (item.getCategory().getId() == categoryId)
           {
               categorizedProducts.add(item);
           }
       });

       log.info("categorized  products are : {}",categorizedProducts);
       List<ProductDto> response =  categorizedProducts.stream().map( cat -> this.modelMapper.map(cat,ProductDto.class)).collect(Collectors.toList());


        return response;

    }

    @Override
    public ProductDto updateProduct(ProductDto productDto,Long userId, Long productId) {

        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound("Product", "productId", productId));

if (product.getUser().getId() == userId)
{
    product.setName(productDto.getName());
    product.setDescription(product.getDescription());
    product.setPrice(productDto.getPrice());


    Product updatedProduct = this.productRepository.save(product);

    return  this.modelMapper.map(updatedProduct,ProductDto.class);
}else {

    throw  new UnauthorizedException("You are not allowed to update this product");
}


    }

    @Override
    public void deleteProduct(Long userId,Long productId) {


        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound("Product", "productId", productId));


        if (product.getUser().getId() == userId)
        {
            this.productRepository.delete(product);
        }
        else throw  new UnauthorizedException("You are not allowed to delete the produce");

    }
}
