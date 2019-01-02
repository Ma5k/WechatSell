package com.mask.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.ProductCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
	@Autowired
	private ProductCategoryRepository repository;
	
	@Test
	public void findOneTest() {
		ProductCategory productCategory = repository.findOne(1);
		System.out.println(productCategory.toString());
	}
	
	@Test
	public void saveTest() {
		ProductCategory productCategory = repository.findOne(2);
		productCategory.setCategoryType(5);
		repository.save(productCategory);
	}
}