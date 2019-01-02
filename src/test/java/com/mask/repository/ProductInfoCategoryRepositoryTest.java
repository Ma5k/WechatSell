package com.mask.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.ProductInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoCategoryRepositoryTest {
	
	@Autowired
	private ProductInfoResitory resitory;
	
	@Test
	public void saveTest() {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId("123456");
		productInfo.setProductName("鱼香肉丝");
		productInfo.setProductPrice(new BigDecimal(23));
		productInfo.setProductStock(100);
		productInfo.setProductIcon("http://xxxx.jpg");
		productInfo.setProductStatus(0);
		productInfo.setCategoryType(1);
		
		ProductInfo result = resitory.save(productInfo);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findByProductStatus() throws Exception {
		List<ProductInfo> productInfoList = resitory.findByProductStatus(0);
		Assert.assertNotEquals(0, productInfoList.size());
	}
}
