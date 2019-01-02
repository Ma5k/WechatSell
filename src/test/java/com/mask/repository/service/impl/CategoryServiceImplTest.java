package com.mask.repository.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.ProductCategory;
import com.mask.service.impl.CategoryServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Test
	public void findOneTest() throws Exception {
		ProductCategory productCategory = categoryService.findOne(1);
		Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
	}
	
	@Test
	public void findAllTest() throws Exception {
		List<ProductCategory> productCategoryList = categoryService.findAll();
		Assert.assertNotEquals(0, productCategoryList.size());
	}
	
	@Test
	public void findByCategoryTypeInTest() throws Exception {
		List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
		Assert.assertNotEquals(0, productCategoryList.size());
	}
	
	@Test
	public void saveTest() throws Exception {
		ProductCategory productCategory = new ProductCategory("烧烤专区", 3);
		ProductCategory result = categoryService.save(productCategory);
		Assert.assertNotNull(result);
	}
}
