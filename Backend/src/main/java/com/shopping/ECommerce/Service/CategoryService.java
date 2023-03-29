package com.shopping.ECommerce.Service;

import com.shopping.ECommerce.Entity.Category;
import com.shopping.ECommerce.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public void createCategory(Category category){
        categoryRepository.save(category);
    }
}
