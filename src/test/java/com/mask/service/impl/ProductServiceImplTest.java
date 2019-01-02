package com.mask.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.ProductInfo;
import com.mask.enums.ProductStatusEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	
	@Autowired 
	private ProductServiceImpl productServiceImpl;
	
	@Test
	public void findOneTest() throws Exception {
		ProductInfo productInfo = productServiceImpl.findOne("123456");
		Assert.assertEquals("123456", productInfo.getProductId());
	}
	
	@Test
	public void findUpAllTest() throws Exception {
		List<ProductInfo> productInfoList = productServiceImpl.findUpAll();
		Assert.assertNotEquals(0, productInfoList.size());
	}
	
	@Test
	public void findAllTest() throws Exception {
		PageRequest request = new PageRequest(0, 2);
		Page<ProductInfo> productInfoPage = productServiceImpl.findAll(request);
		System.out.println(productInfoPage.getTotalElements());
	}
	
	@Test
	public void saveTest() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("1234567");
		productInfo.setProductName("宫保鸡丁");
		productInfo.setProductPrice(new BigDecimal(33));
		productInfo.setProductStock(100);
		productInfo.setProductIcon("http://xxxx.jpg");
		productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
		productInfo.setCategoryType(2);
		
		ProductInfo result = productServiceImpl.save(productInfo);
		Assert.assertNotNull(result);
	}
	
}
