package com.revature.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// Find products by category ID
    List<Product> findByCategoryId(Long categoryId);
}
