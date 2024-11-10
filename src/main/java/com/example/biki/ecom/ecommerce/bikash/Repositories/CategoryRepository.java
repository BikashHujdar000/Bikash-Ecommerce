package com.example.biki.ecom.ecommerce.bikash.Repositories;

import com.example.biki.ecom.ecommerce.bikash.Dtos.CategoryDto;
import com.example.biki.ecom.ecommerce.bikash.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Long> {

}
