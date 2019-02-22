package com.mask.dataobject.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.ProductCategory;
import com.mask.service.impl.OrderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)		//加上后面括号中内容的原因为:websocket是需要依赖tomcat等容器的启动。所以在测试过程中我们要真正的启动一个tomcat作为容器。
public class ProductCategoryMapperTest {

	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private ProductCategoryMapper mapper;
	
	@Test
	public void insertByMap() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("category_name", "hahaha");
		map.put("category_type", 10101);
		int result = mapper.insertByMap(map);
		Assert.assertEquals(1, result);
	}

	@Test
	public void insertByObject() throws Exception {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("xixixi");
		productCategory.setCategoryType(134);
		int result = mapper.insertByObject(productCategory);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void findByCategoryType() {
		ProductCategory result = mapper.findByCategoryType(134);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findByCategoryName() {
		List<ProductCategory> result = mapper.findByCategoryName("hahaha");
		Assert.assertNotEquals(0, result.size());
	}
	
	@Test
	public void updateByCategoryType() {
		int result = mapper.updateByCategoryType("哈哈哈", 11);
		Assert.assertEquals(1, result);
	}
	
	@Test 
	public void updateByObject() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName("嘻嘻嘻");
		productCategory.setCategoryType(134);
		int result = mapper.updateByObject(productCategory);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void deleteByCategoryType() {
		int result = mapper.deleteByCategoryType(10101);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void selectByCategoryType() {
		ProductCategory productCategory = mapper.selectByCategoryType(134);
		Assert.assertNotNull(productCategory);
	}
}
