package com.mask.dataobject.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mask.dataobject.mapper.ProductCategoryMapper;

@Repository
public class ProductCategoryDao {
	
	@Autowired
	ProductCategoryMapper mapper;
	
	int insertByMap(Map<String, Object> map) {
		 return mapper.insertByMap(map);
	}
}
