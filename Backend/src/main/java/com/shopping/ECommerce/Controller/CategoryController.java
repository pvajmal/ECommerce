package com.shopping.ECommerce.Controller;

import com.shopping.ECommerce.Common.ApiResponse;
import com.shopping.ECommerce.Entity.Category;
import com.shopping.ECommerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> listCategory(){
        List<Category> categories = categoryService.listCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }
    @PostMapping(value = "/update/{categoryId}",  produces = "application/json")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
        if (categoryService.findByCategoryId(categoryId) == null){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category not found"), HttpStatus.NOT_FOUND);
        }
        categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.CREATED);
    }
}
