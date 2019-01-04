package com.mask.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mask.VO.ResultVO;
import com.mask.converter.OrderForm2OrderDTOConverter;
import com.mask.dto.OrderDTO;
import com.mask.enums.ResultEnum;
import com.mask.exception.SellException;
import com.mask.form.OrderForm;
import com.mask.service.OrderService;
import com.mask.service.impl.OrderServiceImpl;
import com.mask.utils.ResultVOUtil;


@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
	
	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderService orderService;
	
	//创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }
	//订单列表
	
	//订单详情
	
	//取消订单
}
