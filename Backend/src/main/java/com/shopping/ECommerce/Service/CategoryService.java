package com.shopping.ECommerce.Service;

import com.shopping.ECommerce.Entity.Category;
import com.shopping.ECommerce.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public void createCategory(Category category){
        categoryRepository.save(category);
    }
    public List<Category> listCategory(){

        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
    public void updateCategory(int categoryId, Category category) throws NullPointerException{
        Category categoryToUpdate = findByCategoryId(categoryId);

        try {
            categoryToUpdate.setCategoryName(category.getCategoryName());
            categoryToUpdate.setDescription(category.getDescription());
            categoryToUpdate.setImageUrl(category.getImageUrl());
            categoryRepository.save(categoryToUpdate);
        }catch (Exception e){
            throw new NullPointerException("No category found with the given Id.");
        }
    }
    public Category findByCategoryId(int categoryId) {
        return categoryRepository.findByCategoryId(categoryId);
    }
}
