package com.example.biki.ecom.ecommerce.bikash.Services.ServiceImpl;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CategoryDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.Category;
import com.example.biki.ecom.ecommerce.bikash.Exceptions.ResourceNotFound;
import com.example.biki.ecom.ecommerce.bikash.Repositories.CategoryRepository;
import com.example.biki.ecom.ecommerce.bikash.Services.AllServices.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory( CategoryDto categoryDto) {

        Category category = new Category();
         category.setName(categoryDto.getName());
         category.setDescription(categoryDto.getDescription());


         Category savedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }


    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = this.categoryRepository.findAll();

        List<CategoryDto> responseList = categoryList.stream().map(item->this.modelMapper.map(item,CategoryDto.class)).collect(Collectors.toList());

         return  responseList;
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound("Category", "id", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound("Category", "id", categoryId));

      this.  categoryRepository.delete(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound("Category", "id", categoryId));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }
}
