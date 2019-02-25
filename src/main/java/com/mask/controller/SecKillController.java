package com.mask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mask.service.SeckillSevice;
import com.mask.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/skill")
public class SecKillController {
	
	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private SeckillSevice secKillService;
	
	/**
	 * 查询秒杀活动特价商品的信息
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/query/{productId}")
	public String query(@PathVariable String productId) throws Exception {
		return secKillService.querySecKillProductInfo(productId);
	}
	
	/**
	 * 秒杀，没有抢到获得提示，抢到返回剩余库存量
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/order/{productId}")
	public String skill(@PathVariable String productId) throws Exception {
		log.info("@skill request, productId:" + productId);
		secKillService.orderProductMockDiffUser(productId);
		return secKillService.querySecKillProductInfo(productId);
	}
}
