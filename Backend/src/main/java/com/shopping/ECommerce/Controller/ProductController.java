package com.shopping.ECommerce.Controller;

import com.shopping.ECommerce.Common.ApiResponse;
import com.shopping.ECommerce.Entity.Category;
import com.shopping.ECommerce.Repository.CategoryRepository;
import com.shopping.ECommerce.Service.ProductService;
import com.shopping.ECommerce.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> listProduct(){
        List<ProductDto> products = productService.listProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);

    }
    @PostMapping(value = "/update/{productId}",  produces = "application/json")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("productId") int productId, @RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(optionalCategory.isEmpty()){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exists"), HttpStatus.BAD_REQUEST);
        }
        if (productService.findByProductId(productId) == null){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "product not found"), HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(productId, productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.CREATED);
    }
}
