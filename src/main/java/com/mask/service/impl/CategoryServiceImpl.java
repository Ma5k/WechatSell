package com.mask.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mask.dataobject.ProductCategory;
import com.mask.dataobject.dao.ProductCategoryDao;
import com.mask.repository.ProductCategoryRepository;
import com.mask.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ProductCategoryRepository repository;
	
	//使用mybatis时注入dao
	@Autowired
	private ProductCategoryDao dao;
	
	@Override
	public ProductCategory findOne(Integer categoryId) {
		return repository.findOne(categoryId);
	}

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return repository.save(productCategory);
	}

}
