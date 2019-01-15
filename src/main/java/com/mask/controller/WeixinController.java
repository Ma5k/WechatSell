package com.mask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mask.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/weixin")
public class WeixinController {

	private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);

    }
}
