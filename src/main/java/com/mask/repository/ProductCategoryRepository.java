package com.mask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mask.dataobject.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
	
}
