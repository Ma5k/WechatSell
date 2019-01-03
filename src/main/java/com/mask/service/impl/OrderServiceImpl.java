package com.mask.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mask.dataobject.OrderDetail;
import com.mask.dataobject.OrderMaster;
import com.mask.dataobject.ProductInfo;
import com.mask.dto.CartDTO;
import com.mask.dto.OrderDTO;
import com.mask.enums.OrderStatusEnum;
import com.mask.enums.PayStatusEnum;
import com.mask.enums.ResultEnum;
import com.mask.exception.SellException;
import com.mask.repository.OrderDetailRepository;
import com.mask.repository.OrderMasterRepository;
import com.mask.service.OrderService;
import com.mask.service.ProductService;
import com.mask.utils.KeyUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired 
	private OrderMasterRepository orderMasterRepository;
	
	@Override
	public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

//        List<CartDTO> cartDTOList = new ArrayList<>();

        //1. 查询商品（数量, 价格）
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            ProductInfo productInfo =  productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }


        //3. 写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4. 扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

	@Override
	public OrderDTO findOne(String oderId) {
		return null;
	}

	@Override
	public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
		return null;
	}

	@Override
	public OrderDTO cancel(OrderDTO orderDTO) {
		return null;
	}

	@Override
	public OrderDTO finish(OrderDTO orderDTO) {
		return null;
	}

	@Override
	public OrderDTO paid(OrderDTO orderDTO) {
		return null;
	}
	
}
