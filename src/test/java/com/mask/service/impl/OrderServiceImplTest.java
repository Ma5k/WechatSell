package com.mask.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.OrderDetail;
import com.mask.dto.OrderDTO;
import com.mask.enums.OrderStatusEnum;
import com.mask.enums.PayStatusEnum;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	@Autowired
	private OrderServiceImpl orderService;
	
	private final String BUYER_OPENID = "110110";
	
	private final String ORDER_ID = "1546526080313281354";
	
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
	
	@Test
	public void findOne() throws Exception {
		
		OrderDTO result = orderService.findOne(ORDER_ID);
		System.out.println(result);
		Assert.assertEquals(ORDER_ID, result.getOrderId());
	}
	
	@Test
	public void findList() throws Exception {
		PageRequest request = new PageRequest(0, 2);
		Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
		Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
	}
	
	@Test
	public void cancel() throws Exception {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.cancel(orderDTO);
		Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
	}
	
	@Test
	public void finish() throws Exception {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.finish(orderDTO);
		Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
	}
	
	@Test
	public void paid() throws Exception {
		OrderDTO orderDTO = orderService.findOne(ORDER_ID);
		OrderDTO result = orderService.paid(orderDTO);
		Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
	}
}
