package com.shopping.ECommerce.Repository;

import com.shopping.ECommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "Select * from products where id = ?", nativeQuery = true)
    public Product  findByProductId(int productId);
}
