package com.mask.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.OrderDetail;
import com.mask.dto.OrderDTO;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	@Autowired
	private OrderServiceImpl orderService;
	
	private final String BUYER_OPENID = "110110";
	
	@Test
	public void create() throws Exception {
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName("mask");
		orderDTO.setBuyerAddress("武汉");
		orderDTO.setBuyerPhone("133234545");
		orderDTO.setBuyerOpenid(BUYER_OPENID);
		
		//购物车
		List<OrderDetail> orderDetailList = new ArrayList<>();
		
		OrderDetail o1 = new OrderDetail();
		o1.setProductId("111");
		o1.setProductQuantity(1);
		
		OrderDetail o2 = new OrderDetail();
		o2.setProductId("333");
		o2.setProductQuantity(2);
		orderDetailList.add(o1);
		orderDetailList.add(o2);
		
		
		orderDTO.setOrderDetailList(orderDetailList);
		
		OrderDTO result = orderService.create(orderDTO);
		Assert.assertNotNull(result);
	}
}
