package com.example.biki.ecom.ecommerce.bikash.Services.AllServices;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    // create category

    CategoryDto createCategory(CategoryDto categoryDto);

    //getAllCategory

    List<CategoryDto> getAllCategory();


    //get categoryBY Id

    CategoryDto getCategoryById(Long categoryId);


    // delete catergory

    void deleteCategory(Long categoryId);


    // update catergory

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);




}
