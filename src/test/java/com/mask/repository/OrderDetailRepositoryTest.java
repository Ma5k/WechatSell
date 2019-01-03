package com.mask.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.OrderDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

	@Autowired
	private OrderDetailRepository repository;
	
	@Test
	public void saveTest() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailId("123456349");
		orderDetail.setOrderId("11135521");
		orderDetail.setProductIcon("xxx.jpg");
		orderDetail.setProductId("1342");
		orderDetail.setProductName("鱼香肉丝");
		orderDetail.setProductPrice(new BigDecimal(23.5));
		orderDetail.setProductQuantity(3);
		
		OrderDetail result = repository.save(orderDetail);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findByOrderIdTest() throws Exception {
		List<OrderDetail> orderDetailList = repository.findByOrderId("11111111");
		Assert.assertNotEquals(0, orderDetailList.size());
	}

}
