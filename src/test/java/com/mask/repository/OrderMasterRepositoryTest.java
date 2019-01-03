package com.mask.repository;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mask.dataobject.OrderMaster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

	@Autowired
	private OrderMasterRepository repository;
	
	private final String OPENID = "1433452";
	
	@Test
	public void saveTest() {
		 OrderMaster orderMaster = new OrderMaster();
		 orderMaster.setOrderId("125436");
		 orderMaster.setBuyerName("mask");
		 orderMaster.setBuyerPhone("124545245");
		 orderMaster.setBuyerAddress("武汉");
		 orderMaster.setBuyerOpenid("1433452");
		 orderMaster.setOrderAmount(new BigDecimal(2.9));
		 
		 OrderMaster result = repository.save(orderMaster);
		 Assert.assertNotNull(result);
	}
	
	@Test
	public void findByBuyerOpenid() throws Exception {
		PageRequest request = new PageRequest(0, 1);
		
		Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
		Assert.assertNotEquals(0, result.getTotalElements());
	}

}
