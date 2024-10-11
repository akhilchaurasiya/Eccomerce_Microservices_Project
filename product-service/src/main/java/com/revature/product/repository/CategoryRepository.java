package com.revature.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.product.model.Category;

	
	@Repository
	public interface CategoryRepository extends JpaRepository<Category, Long>{
		


	}

