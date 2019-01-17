package com.mask.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mask.dto.OrderDTO;
import com.mask.enums.ResultEnum;
import com.mask.exception.SellException;
import com.mask.service.BuyerService;
import com.mask.service.OrderService;
import com.mask.service.impl.OrderServiceImpl;

@Service
public class BuyerServiceImpl implements BuyerService {

	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public OrderDTO findOrderOne(String openid, String orderId) {
		return checkOrderOwner(openid, orderId);
	}

	@Override
	public OrderDTO cancelOrder(String openid, String orderId) {
		OrderDTO orderDTO = checkOrderOwner(openid, orderId);
		if (orderDTO == null) {
			log.error("【取消订单】查不到该订单，orderId={}", orderId);
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		return orderService.cancel(orderDTO);
	}
	
	private OrderDTO checkOrderOwner(String openid, String orderId) {
		OrderDTO orderDTO = orderService.findOne(orderId);
		if (orderDTO == null) {
			return null;
		}
		//判断是否是自己的订单
		if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
			log.error("【查询订单】订单的openid不一致，openid={}, orderDTO={}", openid, orderDTO);
			throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
		}
		return orderDTO;
	}
}
