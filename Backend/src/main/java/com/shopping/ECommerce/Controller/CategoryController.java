package com.shopping.ECommerce.Controller;

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

//    @GetMapping("/")
//    public ResponseEntity<List<Category>> getCategories(){
//        List<Category> body = CategoryService.listCategories();
//        return new ResponseEntity<>(body, HttpStatus.OK);
//    }
    @PostMapping("/create")
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "success";

    }
}
