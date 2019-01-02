package com.mask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mask.dataobject.ProductInfo;

public interface ProductInfoResitory extends JpaRepository<ProductInfo, String> {
	
	List<ProductInfo> findByProductStatus(Integer productStatus);
}
