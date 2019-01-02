package com.mask.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mask.dataobject.ProductInfo;
import com.mask.enums.ProductStatusEnum;
import com.mask.repository.ProductInfoResitory;
import com.mask.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductInfoResitory repository;
	
	@Override
	public ProductInfo findOne(String productId) {
		return repository.findOne(productId);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return repository.save(productInfo);
	}

}
