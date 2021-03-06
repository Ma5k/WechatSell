package com.mask.service;

import com.mask.dto.OrderDTO;

/**
 * 	推送消息
 * @author Mask
 *
 */
public interface PushMessageService {
	
	/**
	 * 	订单状态变更消息
	 * @param orderDTO
	 */
	void orderStatus(OrderDTO orderDTO);
}
