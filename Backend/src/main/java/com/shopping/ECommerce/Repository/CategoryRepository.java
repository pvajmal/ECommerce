package com.shopping.ECommerce.Repository;

import com.shopping.ECommerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "Select * from categories where id = ?", nativeQuery = true)
    public Category findByCategoryId(int categoryId);
//    @Query(value = "update categories set category_name = ?2, description = ?3, image_url = ?4 where id = ?1 ", nativeQuery = true)
//    public void updateByCategoryId(int id, String name, String description, String imageUrl);
}
