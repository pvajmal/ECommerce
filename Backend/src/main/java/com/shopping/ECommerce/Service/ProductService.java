package com.shopping.ECommerce.Service;

import com.shopping.ECommerce.Entity.Category;
import com.shopping.ECommerce.Entity.Product;
import com.shopping.ECommerce.Repository.ProductRepository;
import com.shopping.ECommerce.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product =  new Product();
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);
    }
    public ProductDto getProductDto(Product product){
        ProductDto productDto =  new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setName(product.getName());
        productDto.setImageURL(product.getImageURL());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }
    public List<ProductDto> listProducts() {
        List<Product> allProducts =  productRepository.findAll();
        List<ProductDto> productDtos= new ArrayList<>();
        for(Product product: allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public Product findByProductId(int productId) {
        return productRepository.findByProductId(productId);
    }

    public void updateProduct(int productId, ProductDto productDto, Category category) {
        Product productToUpdate = findByProductId(productId);
        try{
            productToUpdate.setName(productDto.getName());
            productToUpdate.setPrice(productDto.getPrice());
            productToUpdate.setImageURL(productDto.getImageURL());
            productToUpdate.setDescription(productDto.getDescription());
            productToUpdate.setCategory(category);
            productRepository.save(productToUpdate);
        }catch (Exception e){
            throw new NullPointerException("No product found with the given Id.");
        }
    }
}
